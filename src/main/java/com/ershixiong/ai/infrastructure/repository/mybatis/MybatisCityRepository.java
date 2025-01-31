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

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ershixiong.ai.common.exception.BusinessException;
import com.ershixiong.ai.common.exception.ErrorCode;
import com.ershixiong.ai.domain.model.City;
import com.ershixiong.ai.domain.repository.CityRepository;
import com.ershixiong.ai.infrastructure.repository.mybatis.converter.CityDataConverter;
import com.ershixiong.ai.infrastructure.repository.mybatis.dataobject.CityDO;
import com.ershixiong.ai.infrastructure.repository.mybatis.mapper.CityMapper;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * MyBatis实现的城市仓储类。
 *
 * <p>该类实现了{@link CityRepository}接口，提供以下功能：
 *
 * <ul>
 *   <li>基本的CRUD操作
 *   <li>分页查询
 *   <li>模糊搜索
 * </ul>
 *
 * <p>实现说明：
 *
 * <ul>
 *   <li>使用MyBatis Plus进行数据访问
 *   <li>通过{@link CityDataConverter}进行DO和实体对象的转换
 *   <li>包含完整的参数验证和异常处理
 * </ul>
 *
 * @author ershixiong
 * @since 1.0.0
 * @date 2025-01-31
 */
@Slf4j
@Repository
public class MybatisCityRepository implements CityRepository {

  private final CityMapper cityMapper;
  private final CityDataConverter cityDataConverter;

  public MybatisCityRepository(CityMapper cityMapper, CityDataConverter cityDataConverter) {
    this.cityMapper = cityMapper;
    this.cityDataConverter = cityDataConverter;
  }

  @Override
  public List<City> findAll() {
    List<CityDO> cityDOs = cityMapper.selectList(null);
    return cityDataConverter.toEntityList(cityDOs);
  }

  @Override
  public Optional<City> findById(Long id) {
    CityDO cityDO = cityMapper.selectById(id);
    return Optional.ofNullable(cityDO).map(cityDataConverter::toEntity);
  }

  /**
   * 保存城市信息。
   *
   * <p>该方法会根据城市ID是否存在来决定执行插入或更新操作：
   *
   * <ul>
   *   <li>如果ID为null，执行插入操作
   *   <li>如果ID不为null，执行更新操作
   * </ul>
   *
   * @param city 要保存的城市对象
   * @return 保存后的城市对象
   * @throws BusinessException 当city参数为null时抛出
   */
  @Override
  public City save(City city) {
    if (city == null) {
      throw new BusinessException(ErrorCode.PARAM_INVALID, "City cannot be null");
    }

    CityDO cityDO = cityDataConverter.toDO(city);
    if (city.getId() == null) {
      cityMapper.insert(cityDO);
    } else {
      cityMapper.updateById(cityDO);
    }
    return cityDataConverter.toEntity(cityDO);
  }

  @Override
  public void deleteById(Long id) {
    cityMapper.deleteById(id);
  }

  /**
   * 根据名称模糊查询城市，支持分页。
   *
   * <p>查询说明：
   *
   * <ul>
   *   <li>使用LIKE语句进行模糊匹配
   *   <li>结果会根据传入的分页参数进行分页
   *   <li>返回的Page对象包含总记录数和当前页数据
   * </ul>
   *
   * @param namePattern 城市名称模式
   * @param page 分页参数
   * @return 分页的城市列表
   */
  @Override
  public Page<City> findByNameLike(String namePattern, Page<City> page) {
    Page<CityDO> cityDOPage = new Page<>(page.getCurrent(), page.getSize());
    LambdaQueryWrapper<CityDO> queryWrapper =
        new LambdaQueryWrapper<CityDO>().like(CityDO::getName, namePattern);

    Page<CityDO> resultPage = cityMapper.selectPage(cityDOPage, queryWrapper);

    // 转换结果
    Page<City> resultCityPage =
        new Page<>(
            resultPage.getCurrent(),
            resultPage.getSize(),
            resultPage.getTotal(),
            resultPage.searchCount());

    resultCityPage.setRecords(cityDataConverter.toEntityList(resultPage.getRecords()));
    return resultCityPage;
  }
}
