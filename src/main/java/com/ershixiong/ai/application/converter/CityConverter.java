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
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

/**
 * 城市对象转换器。
 *
 * <p>负责不同层次间城市对象的转换，包括：
 *
 * <ul>
 *   <li>DTO与领域对象的互转
 *   <li>请求对象到领域对象的转换
 *   <li>分页对象的转换
 * </ul>
 *
 * <p>转换规则：
 *
 * <ul>
 *   <li>保持属性名称一致时自动映射
 *   <li>不同名称的属性通过@Mapping注解指定
 *   <li>复杂对象通过自定义方法转换
 * </ul>
 *
 * <p>使用MapStruct自动生成实现类。
 *
 * @author ershixiong
 * @since 1.0.0
 * @date 2025-01-31
 */
@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CityConverter {

  /**
   * 将实体转换为DTO
   *
   * @param city 城市实体
   * @return 城市DTO
   */
  @Mapping(target = "id", source = "id")
  @Mapping(target = "name", source = "name")
  @Mapping(target = "countrycode", source = "countrycode")
  @Mapping(target = "district", source = "district")
  @Mapping(target = "population", source = "population")
  CityDTO toDTO(City city);

  /**
   * 将DTO转换为实体
   *
   * @param cityDTO 城市DTO
   * @return 城市实体
   */
  @Mapping(target = "id", source = "id")
  @Mapping(target = "name", source = "name")
  @Mapping(target = "countrycode", source = "countrycode")
  @Mapping(target = "district", source = "district")
  @Mapping(target = "population", source = "population")
  City toEntity(CityDTO cityDTO);

  /**
   * 将实体列表转换为DTO列表
   *
   * @param cities 城市实体列表
   * @return 城市DTO列表
   */
  List<CityDTO> toDTOList(List<City> cities);

  /**
   * 将DTO列表转换为实体列表
   *
   * @param cityDTOs 城市DTO列表
   * @return 城市实体列表
   */
  List<City> toEntityList(List<CityDTO> cityDTOs);
}
