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
package com.ershixiong.ai.infrastructure.repository.mybatis.converter;

import com.ershixiong.ai.domain.model.City;
import com.ershixiong.ai.infrastructure.repository.mybatis.dataobject.CityDO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 城市数据对象转换器。
 *
 * <p>负责在领域层和基础设施层之间转换城市对象：
 * <ul>
 *   <li>将领域模型 City 转换为数据对象 CityDO
 *   <li>将数据对象 CityDO 转换为领域模型 City
 * </ul>
 *
 * @author ershixiong
 * @since 1.0.0
 * @date 2025-01-31
 */
@Component
public class CityDataConverter {

  /**
   * 将城市领域模型转换为数据对象。
   *
   * @param city 城市领域模型
   * @return 城市数据对象，如果输入为null则返回null
   */
  public CityDO toDO(City city) {
    if (city == null) {
      return null;
    }
    return city.toDO();
  }

  /**
   * 将城市数据对象转换为领域模型。
   *
   * @param cityDO 城市数据对象
   * @return 城市领域模型，如果输入为null则返回null
   */
  public City toEntity(CityDO cityDO) {
    if (cityDO == null) {
      return null;
    }
    return City.from(cityDO);
  }

  /**
   * 将城市领域模型列表转换为数据对象列表。
   *
   * @param cities 城市领域模型列表
   * @return 城市数据对象列表，如果输入为null则返回null
   */
  public List<CityDO> toDOList(List<City> cities) {
    if (cities == null) {
      return null;
    }
    List<CityDO> cityDOs = new ArrayList<>();
    for (City city : cities) {
      cityDOs.add(toDO(city));
    }
    return cityDOs;
  }

  /**
   * 将城市数据对象列表转换为领域模型列表。
   *
   * @param cityDOs 城市数据对象列表
   * @return 城市领域模型列表，如果输入为null则返回null
   */
  public List<City> toEntityList(List<CityDO> cityDOs) {
    if (cityDOs == null) {
      return null;
    }
    List<City> cities = new ArrayList<>();
    for (CityDO cityDO : cityDOs) {
      cities.add(toEntity(cityDO));
    }
    return cities;
  }
}
