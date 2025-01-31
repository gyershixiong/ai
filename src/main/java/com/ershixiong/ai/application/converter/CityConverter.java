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
package com.ershixiong.ai.application.converter;

import com.ershixiong.ai.api.dto.CityDTO;
import com.ershixiong.ai.domain.model.City;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 城市对象转换器。
 *
 * <p>负责在应用层和领域层之间转换城市对象：
 * <ul>
 *   <li>将领域模型 City 转换为数据传输对象 CityDTO
 *   <li>将数据传输对象 CityDTO 转换为领域模型 City
 * </ul>
 *
 * @author ershixiong
 * @since 1.0.0
 * @date 2025-01-31
 */
@Component
public class CityConverter {

  /**
   * 将城市领域模型转换为DTO。
   *
   * @param city 城市领域模型
   * @return 城市DTO，如果输入为null则返回null
   */
  public CityDTO toDTO(City city) {
    if (city == null) {
      return null;
    }
    return city.toDTO();
  }

  /**
   * 将城市DTO转换为领域模型。
   *
   * @param dto 城市DTO
   * @return 城市领域模型，如果输入为null则返回null
   */
  public City toEntity(CityDTO dto) {
    if (dto == null) {
      return null;
    }
    return City.from(dto);
  }

  /**
   * 将城市领域模型列表转换为DTO列表。
   *
   * @param cities 城市领域模型列表
   * @return 城市DTO列表，如果输入为null则返回null
   */
  public List<CityDTO> toDTOList(List<City> cities) {
    if (cities == null) {
      return null;
    }
    List<CityDTO> dtos = new ArrayList<>();
    for (City city : cities) {
      dtos.add(toDTO(city));
    }
    return dtos;
  }

  /**
   * 将城市DTO列表转换为领域模型列表。
   *
   * @param dtos 城市DTO列表
   * @return 城市领域模型列表，如果输入为null则返回null
   */
  public List<City> toEntityList(List<CityDTO> dtos) {
    if (dtos == null) {
      return null;
    }
    List<City> cities = new ArrayList<>();
    for (CityDTO dto : dtos) {
      cities.add(toEntity(dto));
    }
    return cities;
  }
}
