/*
 * Copyright (c) 2025 The Ershixiong Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ershixiong.ai.infrastructure.repository.mybatis;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ershixiong.ai.common.exception.BusinessException;
import com.ershixiong.ai.domain.model.City;
import com.ershixiong.ai.domain.repository.CityRepository;
import com.ershixiong.ai.infrastructure.repository.mybatis.converter.CityDataConverter;
import com.ershixiong.ai.infrastructure.repository.mybatis.dataobject.CityDO;
import com.ershixiong.ai.infrastructure.repository.mybatis.mapper.CityMapper;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class MybatisCityRepositoryTest {

  @Mock private CityMapper cityMapper;

  @Autowired private CityDataConverter cityDataConverter;

  private CityRepository cityRepository;

  private City testCity;

  private CityDO testCityDO;

  @BeforeEach
  void setUp() {
    cityRepository = new MybatisCityRepository(cityMapper, cityDataConverter);

    // 初始化测试数据
    testCity = City.builder()
        .id(1L)
        .name("Test City")
        .countrycode("CHN")
        .district("Test District")
        .population(1000000)
        .build();

    testCityDO = testCity.toDO();
  }

  @Test
  void findAll_ShouldReturnAllCities() {
    // Arrange
    List<CityDO> cityDOs = Arrays.asList(testCityDO);
    when(cityMapper.selectList(null)).thenReturn(cityDOs);

    // Act
    List<City> actualCities = cityRepository.findAll();

    // Assert
    assertEquals(1, actualCities.size());
    assertEquals(testCity.getId(), actualCities.get(0).getId());
    assertEquals(testCity.getName(), actualCities.get(0).getName());
    assertEquals(testCity.getCountrycode(), actualCities.get(0).getCountrycode());
    assertEquals(testCity.getDistrict(), actualCities.get(0).getDistrict());
    assertEquals(testCity.getPopulation(), actualCities.get(0).getPopulation());
    verify(cityMapper).selectList(null);
  }

  @Test
  void findById_WhenCityExists_ShouldReturnCity() {
    // Arrange
    when(cityMapper.selectById(1L)).thenReturn(testCityDO);

    // Act
    Optional<City> result = cityRepository.findById(1L);

    // Assert
    assertTrue(result.isPresent());
    City city = result.get();
    assertEquals(testCity.getId(), city.getId());
    assertEquals(testCity.getName(), city.getName());
    assertEquals(testCity.getCountrycode(), city.getCountrycode());
    assertEquals(testCity.getDistrict(), city.getDistrict());
    assertEquals(testCity.getPopulation(), city.getPopulation());
    verify(cityMapper).selectById(1L);
  }

  @Test
  void findById_WhenCityDoesNotExist_ShouldReturnEmpty() {
    // Arrange
    when(cityMapper.selectById(999L)).thenReturn(null);

    // Act
    Optional<City> result = cityRepository.findById(999L);

    // Assert
    assertFalse(result.isPresent());
    verify(cityMapper).selectById(999L);
  }

  @Test
  void save_WhenCityIsNull_ShouldThrowException() {
    // Act & Assert
    assertThrows(BusinessException.class, () -> cityRepository.save(null));
    verify(cityMapper, never()).insert(any());
    verify(cityMapper, never()).updateById(any());
  }

  @Test
  void save_WhenCityHasId_ShouldUpdate() {
    // Arrange
    when(cityMapper.updateById(any(CityDO.class))).thenReturn(1);

    // Act
    City savedCity = cityRepository.save(testCity);

    // Assert
    assertEquals(testCity.getId(), savedCity.getId());
    assertEquals(testCity.getName(), savedCity.getName());
    assertEquals(testCity.getCountrycode(), savedCity.getCountrycode());
    assertEquals(testCity.getDistrict(), savedCity.getDistrict());
    assertEquals(testCity.getPopulation(), savedCity.getPopulation());
    verify(cityMapper).updateById(any(CityDO.class));
    verify(cityMapper, never()).insert(any());
  }

  @Test
  void save_WhenCityHasNoId_ShouldInsert() {
    // Arrange
    testCity.setId(null);
    testCityDO.setId(null);
    when(cityMapper.insert(any(CityDO.class))).thenReturn(1);

    // Act
    City savedCity = cityRepository.save(testCity);

    // Assert
    assertEquals(testCity.getName(), savedCity.getName());
    assertEquals(testCity.getCountrycode(), savedCity.getCountrycode());
    assertEquals(testCity.getDistrict(), savedCity.getDistrict());
    assertEquals(testCity.getPopulation(), savedCity.getPopulation());
    verify(cityMapper).insert(any(CityDO.class));
    verify(cityMapper, never()).updateById(any());
  }

  @Test
  void deleteById_ShouldDeleteCity() {
    // Arrange
    when(cityMapper.deleteById(1L)).thenReturn(1);

    // Act
    cityRepository.deleteById(1L);

    // Assert
    verify(cityMapper).deleteById(1L);
  }

  @SuppressWarnings("unchecked")
  @Test
  void findByNameLike_ShouldReturnPagedResults() {
    // Arrange
    String namePattern = "%Test%";
    Page<City> inputPage = new Page<>(1, 10);
    Page<CityDO> doPage = new Page<>(1, 10);
    List<CityDO> cityDOs = Arrays.asList(testCityDO);
    Page<CityDO> resultDoPage = doPage.setRecords(cityDOs).setTotal(1);

    when(cityMapper.selectPage(any(), any(LambdaQueryWrapper.class))).thenReturn(resultDoPage);

    // Act
    Page<City> result = cityRepository.findByNameLike(namePattern, inputPage);

    // Assert
    assertEquals(1, result.getTotal());
    assertEquals(1, result.getRecords().size());
    City city = result.getRecords().get(0);
    assertEquals(testCity.getId(), city.getId());
    assertEquals(testCity.getName(), city.getName());
    assertEquals(testCity.getCountrycode(), city.getCountrycode());
    assertEquals(testCity.getDistrict(), city.getDistrict());
    assertEquals(testCity.getPopulation(), city.getPopulation());
    verify(cityMapper).selectPage(any(), any(LambdaQueryWrapper.class));
  }
}
