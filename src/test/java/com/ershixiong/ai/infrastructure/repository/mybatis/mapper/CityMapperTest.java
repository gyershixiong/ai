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
package com.ershixiong.ai.infrastructure.repository.mybatis.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ershixiong.ai.infrastructure.repository.mybatis.dataobject.CityDO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
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
class CityMapperTest {

  private static final String DEFAULT_COUNTRY_CODE = "CHN";
  private static final String DEFAULT_DISTRICT = "Test District";
  private static final int DEFAULT_POPULATION = 1000000;
  private static final String INVALID_COUNTRY_CODE = "INVALID";
  private static final int INVALID_POPULATION = -1;

  @Autowired private CityMapper cityMapper;

  /**
   * 创建测试城市数据。
   *
   * <p>根据指定名称创建一个新的城市数据。
   *
   * @param name 城市名称
   * @return 测试城市数据
   */
  private CityDO createTestCity(String name) {
    return CityDO.builder()
        .name(name)
        .countrycode(DEFAULT_COUNTRY_CODE)
        .district(DEFAULT_DISTRICT)
        .population(DEFAULT_POPULATION)
        .build();
  }
  /**
   * 插入测试组。
   *
   * <p>测试场景包括：
   *
   * <ul>
   *   <li>有效数据的插入
   *   <li>无效数据的处理
   *   <li>边界条件的验证
   * </ul>
   */
  @Nested
  @DisplayName("插入测试")
  class InsertTests {
    /**
     * 测试有效数据的插入。
     *
     * <p>验证插入成功后：
     *
     * <ul>
     *   <li>城市ID不为空
     *   <li>城市数据正确保存
     * </ul>
     */
    @Test
    @Order(1)
    @DisplayName("当数据有效时应插入成功")
    void testInsert_ShouldSucceed_WithValidData() {
      CityDO city = createTestCity("New Test City");
      int result = cityMapper.insert(city);

      assertThat(result).isEqualTo(1);
      assertThat(city.getId()).isNotNull();

      CityDO saved = cityMapper.selectById(city.getId());
      assertThat(saved)
          .isNotNull()
          .satisfies(
              c -> {
                assertThat(c.getName()).isEqualTo("New Test City");
                assertThat(c.getCountrycode()).isEqualTo(DEFAULT_COUNTRY_CODE);
                assertThat(c.getPopulation()).isEqualTo(DEFAULT_POPULATION);
              });
    }

    /**
     * 测试名称为null的插入。
     *
     * <p>验证插入失败：
     *
     * <ul>
     *   <li>抛出DataIntegrityViolationException异常
     * </ul>
     */
    @Test
    @Order(2)
    @DisplayName("当名称为null时应插入失败")
    void testInsert_ShouldFail_WithNullName() {
      CityDO city = CityDO.builder()
          .countrycode(DEFAULT_COUNTRY_CODE)
          .district(DEFAULT_DISTRICT)
          .population(DEFAULT_POPULATION)
          .build();

      assertThatThrownBy(() -> cityMapper.insert(city))
          .isInstanceOf(DataIntegrityViolationException.class);
    }

    /**
     * 测试国家代码无效的插入。
     *
     * <p>验证插入失败：
     *
     * <ul>
     *   <li>抛出DataIntegrityViolationException异常
     * </ul>
     */
    @Test
    @Order(3)
    @DisplayName("当国家代码无效时应插入失败")
    void testInsert_ShouldFail_WithInvalidCountryCode() {
      CityDO city = CityDO.builder()
          .name("Invalid Country Code City")
          .countrycode(INVALID_COUNTRY_CODE)
          .district(DEFAULT_DISTRICT)
          .population(DEFAULT_POPULATION)
          .build();

      assertThatThrownBy(() -> cityMapper.insert(city))
          .isInstanceOf(DataIntegrityViolationException.class);
    }

    /**
     * 测试人口数为负数的插入。
     *
     * <p>验证插入失败：
     *
     * <ul>
     *   <li>抛出DataIntegrityViolationException异常
     * </ul>
     */
    @Test
    @Order(4)
    @DisplayName("当人口数为负数时应插入失败")
    void testInsert_ShouldFail_WithNegativePopulation() {
      CityDO city = CityDO.builder()
          .name("Negative Population City")
          .countrycode(DEFAULT_COUNTRY_CODE)
          .district(DEFAULT_DISTRICT)
          .population(INVALID_POPULATION)
          .build();

      assertThatThrownBy(() -> cityMapper.insert(city))
          .isInstanceOf(DataIntegrityViolationException.class);
    }
  }

  /**
   * 更新测试组。
   *
   * <p>测试场景包括：
   *
   * <ul>
   *   <li>正常更新操作
   *   <li>更新不存在的记录
   *   <li>更新无效数据
   * </ul>
   */
  @Nested
  @DisplayName("更新测试")
  class UpdateTests {
    /**
     * 测试有效数据的更新。
     *
     * <p>验证更新成功后：
     *
     * <ul>
     *   <li>城市数据正确更新
     * </ul>
     */
    @Test
    @Order(1)
    @DisplayName("当数据有效时应更新成功")
    void testUpdate_ShouldSucceed_WithValidData() {
      CityDO city = createTestCity("Original Name");
      cityMapper.insert(city);

      city.setName("Updated Name");
      city.setPopulation(2000000);
      int result = cityMapper.updateById(city);

      assertThat(result).isEqualTo(1);

      CityDO updated = cityMapper.selectById(city.getId());
      assertThat(updated)
          .isNotNull()
          .satisfies(
              c -> {
                assertThat(c.getName()).isEqualTo("Updated Name");
                assertThat(c.getPopulation()).isEqualTo(2000000);
              });
    }

    /**
     * 测试更新不存在的记录。
     *
     * <p>验证更新结果：
     *
     * <ul>
     *   <li>返回0
     * </ul>
     */
    @Test
    @Order(2)
    @DisplayName("当城市不存在时应返回0")
    void testUpdate_ShouldReturnZero_WhenCityNotExists() {
      CityDO city = CityDO.builder()
          .id(999999L)
          .name("Non-existent City")
          .countrycode(DEFAULT_COUNTRY_CODE)
          .district(DEFAULT_DISTRICT)
          .population(DEFAULT_POPULATION)
          .build();

      int result = cityMapper.updateById(city);
      assertThat(result).isEqualTo(0);
    }

    /**
     * 测试名称为null的更新。
     *
     * <p>验证更新失败：
     *
     * <ul>
     *   <li>抛出DataIntegrityViolationException异常
     * </ul>
     */
    @Test
    @Order(3)
    @DisplayName("当名称为null时更新应失败")
    void testUpdate_ShouldFail_WithNullName() {
      CityDO city = createTestCity("Test City");
      cityMapper.insert(city);

      assertThatThrownBy(
              () -> {
                UpdateWrapper<CityDO> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("id", city.getId()).set("name", null);
                cityMapper.update(null, updateWrapper);
              })
          .isInstanceOf(DataIntegrityViolationException.class);
    }
  }

  /**
   * 删除测试组。
   *
   * <p>测试场景包括：
   *
   * <ul>
   *   <li>正常删除操作
   *   <li>删除不存在的记录
   * </ul>
   */
  @Nested
  @DisplayName("删除测试")
  class DeleteTests {
    /**
     * 测试正常删除操作。
     *
     * <p>验证删除成功后：
     *
     * <ul>
     *   <li>城市数据不存在
     * </ul>
     */
    @Test
    @Order(1)
    @DisplayName("当城市存在时应删除成功")
    void testDelete_ShouldSucceed_WhenExists() {
      CityDO city = createTestCity("City To Delete");
      cityMapper.insert(city);

      int result = cityMapper.deleteById(city.getId());
      assertThat(result).isEqualTo(1);

      CityDO deleted = cityMapper.selectById(city.getId());
      assertThat(deleted).isNull();
    }

    /**
     * 测试删除不存在的记录。
     *
     * <p>验证删除结果：
     *
     * <ul>
     *   <li>返回0
     * </ul>
     */
    @Test
    @Order(2)
    @DisplayName("当城市不存在时应返回0")
    void testDelete_ShouldReturnZero_WhenNotExists() {
      int result = cityMapper.deleteById(999999L);
      assertThat(result).isEqualTo(0);
    }
  }

  /**
   * 查询测试组。
   *
   * <p>测试内容：
   *
   * <ul>
   *   <li>分页查询功能
   *   <li>查询条件处理
   *   <li>空结果处理
   *   <li>分页参数验证
   * </ul>
   */
  @Nested
  @DisplayName("查询测试")
  class QueryTests {
    /**
     * 测试分页查询功能。
     *
     * <p>验证分页结果：
     *
     * <ul>
     *   <li>总记录数正确
     *   <li>当前页记录数正确
     *   <li>页码正确
     * </ul>
     */
    @Test
    @Order(1)
    @DisplayName("分页查询应返回匹配的城市")
    void testSelectPage_ShouldReturnMatchingCities() {
      // 准备测试数据
      for (int i = 1; i <= 15; i++) {
        cityMapper.insert(createTestCity("City " + i));
      }

      // 执行分页查询
      Page<CityDO> page = new Page<>(2, 5);
      LambdaQueryWrapper<CityDO> wrapper = new LambdaQueryWrapper<>();
      wrapper.like(CityDO::getName, "City");

      Page<CityDO> result = cityMapper.selectPage(page, wrapper);

      // 验证结果
      assertThat(result.getTotal()).isGreaterThanOrEqualTo(15);
      assertThat(result.getRecords()).hasSize(5);
      assertThat(result.getCurrent()).isEqualTo(2);
    }

    /**
     * 测试空结果处理。
     *
     * <p>验证空结果：
     *
     * <ul>
     *   <li>总记录数为0
     *   <li>记录列表为空
     * </ul>
     */
    @Test
    @Order(2)
    @DisplayName("当没有匹配记录时应返回空页")
    void testSelectPage_ShouldReturnEmptyPage_WhenNoMatch() {
      Page<CityDO> page = new Page<>(1, 10);
      LambdaQueryWrapper<CityDO> wrapper = new LambdaQueryWrapper<>();
      wrapper.like(CityDO::getName, "NonExistentCity");

      Page<CityDO> result = cityMapper.selectPage(page, wrapper);

      assertThat(result.getTotal()).isEqualTo(0);
      assertThat(result.getRecords()).isEmpty();
    }

    /**
     * 测试分页参数处理。
     *
     * <p>验证分页参数：
     *
     * <ul>
     *   <li>页大小正确
     *   <li>页码正确
     *   <li>记录数正确
     * </ul>
     */
    @Test
    @Order(3)
    @DisplayName("分页参数处理应正确")
    void testSelectPage_ShouldHandlePageParameters() {
      // 准备测试数据
      for (int i = 1; i <= 25; i++) {
        cityMapper.insert(createTestCity("PageCity " + i));
      }

      // 测试不同页码和页大小
      Page<CityDO> page = new Page<>(3, 8);
      LambdaQueryWrapper<CityDO> wrapper = new LambdaQueryWrapper<>();
      wrapper.like(CityDO::getName, "PageCity");

      Page<CityDO> result = cityMapper.selectPage(page, wrapper);

      assertThat(result.getSize()).isEqualTo(8);
      assertThat(result.getCurrent()).isEqualTo(3);
      assertThat(result.getRecords()).hasSizeLessThanOrEqualTo(8);
    }
  }
}
