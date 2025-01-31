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
package com.ershixiong.ai.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ershixiong.ai.api.dto.CityDTO;
import com.ershixiong.ai.api.request.CreateCityRequest;
import com.ershixiong.ai.api.request.SearchCityRequest;
import com.ershixiong.ai.api.request.UpdateCityRequest;
import com.ershixiong.ai.application.converter.CityConverter;
import com.ershixiong.ai.application.service.CityApplicationService;
import com.ershixiong.ai.common.Result;
import com.ershixiong.ai.common.page.PageResponse;
import com.ershixiong.ai.domain.model.City;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.*;

/**
 * 城市资源控制器，处理所有与城市相关的HTTP请求。
 *
 * <p>该控制器提供以下功能：
 *
 * <ul>
 *   <li>查询城市信息
 *   <li>创建新城市
 *   <li>更新城市信息
 *   <li>删除城市
 * </ul>
 *
 * <p>所有响应数据都会被自动转换为JSON格式
 *
 * @author ershixiong
 * @since 1.0.0
 * @date 2025-01-31
 */
@RestController
@RequestMapping("/api/cities")
public class CityController {

  private final CityApplicationService cityApplicationService;
  private final CityConverter cityConverter;

  /**
   * 构造函数注入必要的服务
   *
   * @param cityApplicationService 城市应用服务，处理业务逻辑
   * @param cityConverter DTO转换器，处理数据转换
   */
  public CityController(
      CityApplicationService cityApplicationService, CityConverter cityConverter) {
    this.cityApplicationService = cityApplicationService;
    this.cityConverter = cityConverter;
  }

  /**
   * 获取所有城市信息 HTTP GET /api/cities
   *
   * @return 所有城市的DTO列表
   */
  @GetMapping
  public Result<List<CityDTO>> getAllCities() {
    return Result.success(cityConverter.toDTOList(cityApplicationService.getAll()));
  }

  /**
   * 根据ID获取指定城市信息 HTTP GET /api/cities/{id}
   *
   * @param id 城市ID
   * @return 城市DTO，如果找不到对应城市会抛出异常
   */
  @GetMapping("/{id}")
  public Result<CityDTO> getCityById(@PathVariable Long id) {
    return Result.success(cityConverter.toDTO(cityApplicationService.getById(id)));
  }

  /**
   * 根据名称搜索城市，支持分页 HTTP GET /api/cities/search?name={name}&pageNo={pageNo}&pageSize={pageSize}
   *
   * @param request 搜索请求对象
   * @return 分页的城市DTO列表
   */
  @GetMapping("/search")
  public Result<PageResponse<CityDTO>> searchCitiesByName(@Valid SearchCityRequest request) {
    Page<City> result =
        cityApplicationService.searchByName(
            request.getName(), request.getPageNo(), request.getPageSize());
    List<CityDTO> dtos = result.getRecords().stream().map(cityConverter::toDTO).toList();
    PageResponse<CityDTO> pageResponse =
        PageResponse.of(dtos, result.getTotal(), request.getPageNo(), request.getPageSize());
    return Result.success(pageResponse);
  }

  /**
   * 创建新城市 HTTP POST /api/cities
   *
   * @param request 创建城市请求对象
   * @return 创建成功的城市DTO，包含生成的ID
   */
  @PostMapping
  public Result<CityDTO> createCity(@Valid @RequestBody CreateCityRequest request) {
    City city = new City();
    city.setName(request.getName());
    city.setCountrycode(request.getCountrycode());
    city.setDistrict(request.getDistrict());
    city.setPopulation(request.getPopulation());

    City savedCity = cityApplicationService.create(city);
    return Result.success("城市创建成功", cityConverter.toDTO(savedCity));
  }

  /**
   * 更新指定城市信息 HTTP PUT /api/cities/{id}
   *
   * @param id 要更新的城市ID
   * @param request 更新城市请求对象
   * @return 更新后的城市DTO
   */
  @PutMapping("/{id}")
  public Result<CityDTO> updateCity(
      @PathVariable Long id, @Valid @RequestBody UpdateCityRequest request) {
    if (!id.equals(request.getId())) {
      return Result.fail("路径ID与请求体ID不匹配");
    }

    City city = new City();
    city.setId(request.getId());
    city.setName(request.getName());
    city.setCountrycode(request.getCountrycode());
    city.setDistrict(request.getDistrict());
    city.setPopulation(request.getPopulation());

    City updatedCity = cityApplicationService.updateCity(city);
    return Result.success("城市更新成功", cityConverter.toDTO(updatedCity));
  }

  /**
   * 删除指定城市 HTTP DELETE /api/cities/{id}
   *
   * @param id 要删除的城市ID
   */
  @DeleteMapping("/{id}")
  public Result<Void> deleteCity(@PathVariable Long id) {
    cityApplicationService.deleteCity(id);
    return Result.success();
  }
}
