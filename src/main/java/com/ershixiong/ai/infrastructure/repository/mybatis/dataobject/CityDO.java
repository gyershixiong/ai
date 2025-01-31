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
package com.ershixiong.ai.infrastructure.repository.mybatis.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Objects;

/** 城市数据对象(Data Object) 用于与数据库表进行映射的对象，属于基础设施层 */
@TableName("city")
public class CityDO {

  /** 城市ID */
  @TableId(type = IdType.AUTO)
  private Long id;

  /** 城市名称 */
  private String name;

  /** 国家代码 */
  private String countrycode;

  /** 地区 */
  private String district;

  /** 人口数量 */
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
    CityDO cityDO = (CityDO) o;
    return Objects.equals(id, cityDO.id)
        && Objects.equals(name, cityDO.name)
        && Objects.equals(countrycode, cityDO.countrycode)
        && Objects.equals(district, cityDO.district)
        && Objects.equals(population, cityDO.population);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, countrycode, district, population);
  }

  @Override
  public String toString() {
    return "CityDO{"
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
