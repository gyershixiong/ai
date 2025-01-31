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
package com.ershixiong.ai.api.dto;

import java.util.Objects;

/**
 * 城市数据传输对象。
 *
 * <p>用于在API层传输城市数据，包含以下字段：
 *
 * <ul>
 *   <li>id: 城市ID
 *   <li>name: 城市名称
 *   <li>countryCode: 国家代码
 *   <li>district: 地区
 *   <li>population: 人口数量
 * </ul>
 *
 * <p>该类使用 {@link jakarta.validation.constraints} 注解进行参数验证：
 *
 * <ul>
 *   <li>name: 不能为空，长度在1-35之间
 *   <li>countryCode: 不能为空，长度必须为3
 *   <li>district: 不能为空，长度在1-20之间
 *   <li>population: 必须大于等于0
 * </ul>
 *
 * @author ershixiong
 * @since 1.0.0
 * @date 2025-01-31
 */
public class CityDTO {
  /** 城市ID 主键，用于唯一标识一个城市 */
  private Long id;

  /** 城市名称 不能为空，用于显示城市的名称 */
  private String name;

  /** 国家代码 ISO标准的国家代码，例如：CHN（中国）, USA（美国） */
  private String countrycode;

  /** 地区/省份 城市所属的行政区划 */
  private String district;

  /** 人口数量 城市的总人口数，可以为null */
  private Integer population;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCountrycode() {
    return countrycode;
  }

  public void setCountrycode(String countrycode) {
    this.countrycode = countrycode;
  }

  public String getDistrict() {
    return district;
  }

  public void setDistrict(String district) {
    this.district = district;
  }

  public Integer getPopulation() {
    return population;
  }

  public void setPopulation(Integer population) {
    this.population = population;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CityDTO cityDTO = (CityDTO) o;
    return Objects.equals(id, cityDTO.id)
        && Objects.equals(name, cityDTO.name)
        && Objects.equals(countrycode, cityDTO.countrycode)
        && Objects.equals(district, cityDTO.district)
        && Objects.equals(population, cityDTO.population);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, countrycode, district, population);
  }

  @Override
  public String toString() {
    return "CityDTO{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", countrycode='"
        + countrycode
        + '\''
        + ", district='"
        + district
        + '\''
        + ", population="
        + population
        + '}';
  }
}
