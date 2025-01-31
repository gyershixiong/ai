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
package com.ershixiong.ai.api.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * 创建城市请求对象。
 *
 * <p>用于接收创建城市的API请求参数，包含：
 *
 * <ul>
 *   <li>name: 城市名称
 *   <li>countryCode: 国家代码
 *   <li>district: 地区
 *   <li>population: 人口数量
 * </ul>
 *
 * <p>参数验证规则：
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
public class CreateCityRequest {
  @NotBlank(message = "城市名称不能为空")
  @Size(max = 50, message = "城市名称长度不能超过50个字符")
  private String name;

  @NotBlank(message = "国家代码不能为空")
  @Size(min = 3, max = 3, message = "国家代码必须是3个字符")
  private String countrycode;

  @NotBlank(message = "行政区不能为空")
  @Size(max = 50, message = "行政区长度不能超过50个字符")
  private String district;

  @NotNull(message = "人口数量不能为空")
  @Min(value = 0, message = "人口数量不能为负数")
  private Integer population;

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
    CreateCityRequest that = (CreateCityRequest) o;
    return Objects.equals(name, that.name)
        && Objects.equals(countrycode, that.countrycode)
        && Objects.equals(district, that.district)
        && Objects.equals(population, that.population);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, countrycode, district, population);
  }

  @Override
  public String toString() {
    return "CreateCityRequest{"
        + "name='"
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
