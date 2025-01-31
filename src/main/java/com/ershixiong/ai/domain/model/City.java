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
package com.ershixiong.ai.domain.model;

import com.ershixiong.ai.api.dto.CityDTO;
import com.ershixiong.ai.infrastructure.repository.mybatis.dataobject.CityDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * 城市领域模型。
 *
 * <p>该类是核心业务领域模型，代表一个城市实体，包含以下属性：
 *
 * <ul>
 *   <li>id: 城市唯一标识
 *   <li>name: 城市名称
 *   <li>countrycode: 国家代码
 *   <li>district: 地区
 *   <li>population: 人口数量
 * </ul>
 *
 * @author ershixiong
 * @since 1.0.0
 * @date 2025-01-31
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class City {
  /** 城市ID 主键，自增长 */
  private Long id;

  /** 城市名称 必填字段，不能为空 */
  private String name;

  /** 国家代码 使用ISO 3166-1 alpha-3标准的三字母国家代码 例如：CHN（中国）, USA（美国）, GBR（英国） */
  private String countrycode;

  /** 城市所属行政区 城市所属的行政区划或地理区域 */
  private String district;

  /** 城市人口 记录城市的常住人口数量 可以为null，表示未知人口数量 */
  private Integer population;

  /**
   * 从 DTO 创建领域对象
   *
   * @param dto CityDTO对象
   * @return City领域对象
   */
  public static City from(CityDTO dto) {
    if (dto == null) {
      return null;
    }
    return City.builder()
        .id(dto.getId())
        .name(dto.getName())
        .countrycode(dto.getCountrycode())
        .district(dto.getDistrict())
        .population(dto.getPopulation())
        .build();
  }

  /**
   * 从 DO 创建领域对象
   *
   * @param cityDO CityDO对象
   * @return City领域对象
   */
  public static City from(CityDO cityDO) {
    if (cityDO == null) {
      return null;
    }
    return City.builder()
        .id(cityDO.getId())
        .name(cityDO.getName())
        .countrycode(cityDO.getCountrycode())
        .district(cityDO.getDistrict())
        .population(cityDO.getPopulation())
        .build();
  }

  /**
   * 转换为 DTO
   *
   * @return CityDTO对象
   */
  public CityDTO toDTO() {
    return CityDTO.builder()
        .id(this.id)
        .name(this.name)
        .countrycode(this.countrycode)
        .district(this.district)
        .population(this.population)
        .build();
  }

  /**
   * 转换为 DO
   *
   * @return CityDO对象
   */
  public CityDO toDO() {
    return CityDO.builder()
        .id(this.id)
        .name(this.name)
        .countrycode(this.countrycode)
        .district(this.district)
        .population(this.population)
        .build();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    City city = (City) o;
    return Objects.equals(id, city.id)
        && Objects.equals(name, city.name)
        && Objects.equals(countrycode, city.countrycode)
        && Objects.equals(district, city.district)
        && Objects.equals(population, city.population);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, countrycode, district, population);
  }

  @Override
  public String toString() {
    return "City{"
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
