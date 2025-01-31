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
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

/** 城市数据对象转换器 负责 DO 和领域实体之间的转换 */
@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CityDataConverter {

  /**
   * 将 DO 转换为领域实体
   *
   * @param cityDO 城市DO
   * @return 城市实体
   */
  City toEntity(CityDO cityDO);

  /**
   * 将领域实体转换为 DO
   *
   * @param city 城市实体
   * @return 城市DO
   */
  CityDO toDO(City city);

  /**
   * 将 DO 列表转换为实体列表
   *
   * @param cityDOs DO列表
   * @return 实体列表
   */
  List<City> toEntityList(List<CityDO> cityDOs);

  /**
   * 将实体列表转换为 DO 列表
   *
   * @param cities 实体列表
   * @return DO列表
   */
  List<CityDO> toDOList(List<City> cities);
}
