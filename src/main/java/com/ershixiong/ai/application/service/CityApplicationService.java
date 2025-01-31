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
package com.ershixiong.ai.application.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ershixiong.ai.common.exception.BusinessException;
import com.ershixiong.ai.common.exception.ErrorCode;
import com.ershixiong.ai.domain.model.City;
import com.ershixiong.ai.domain.repository.CityRepository;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 城市应用服务。
 *
 * <p>该服务作为应用层的门面，协调领域层和基础设施层，提供以下功能：
 *
 * <ul>
 *   <li>城市信息的查询和管理
 *   <li>城市数据的转换和验证
 *   <li>事务管理和异常处理
 * </ul>
 *
 * <p>设计说明：
 *
 * <ul>
 *   <li>采用领域驱动设计思想
 *   <li>通过CityConverter进行DTO和领域对象的转换
 *   <li>通过CityRepository进行数据持久化
 * </ul>
 *
 * @author ershixiong
 * @since 1.0.0
 * @date 2025-01-31
 */
@Service
public class CityApplicationService {

  private static final int MAX_PAGE_SIZE = 100;
  private final CityRepository cityRepository;

  public CityApplicationService(CityRepository cityRepository) {
    this.cityRepository = cityRepository;
  }

  /** 根据ID查询城市 */
  public City getById(Long id) {
    if (id == null) {
      throw new BusinessException(ErrorCode.PARAM_MISSING, "城市ID不能为空");
    }
    return cityRepository
        .findById(id)
        .orElseThrow(
            () ->
                new BusinessException(
                    ErrorCode.BIZ_DATA_NOT_FOUND, String.format("城市不存在，ID：%d", id)));
  }

  /** 查询所有城市 */
  public List<City> getAll() {
    return cityRepository.findAll();
  }

  /**
   * 根据名称搜索城市 处理搜索参数中的特殊字符，并添加模糊匹配 支持分页查询
   *
   * @param name 城市名称
   * @param page 页码（从1开始）
   * @param size 每页记录数
   * @return 分页的城市列表
   */
  public Page<City> searchByName(String name, long page, long size) {
    // 校验分页参数
    if (page <= 0) {
      throw new BusinessException(ErrorCode.PARAM_OUT_OF_RANGE, "页码必须大于0");
    }
    if (size <= 0) {
      throw new BusinessException(ErrorCode.PARAM_OUT_OF_RANGE, "每页记录数必须大于0");
    }
    if (size > MAX_PAGE_SIZE) {
      throw new BusinessException(ErrorCode.PARAM_OUT_OF_RANGE, "每页记录数不能超过" + MAX_PAGE_SIZE);
    }

    if (name == null || name.trim().isEmpty()) {
      return new Page<>(page, size);
    }

    // 转义特殊字符
    String escapedName = name.replace("%", "").replace("_", "");

    // 添加模糊匹配
    String likePattern = "%" + escapedName + "%";
    return cityRepository.findByNameLike(likePattern, new Page<>(page, size));
  }

  /**
   * 验证城市数据
   *
   * @param city 城市对象
   */
  private void validateCity(City city) {
    if (city == null) {
      throw new BusinessException(ErrorCode.PARAM_MISSING, "城市对象不能为空");
    }

    if (StringUtils.isBlank(city.getName())) {
      throw new BusinessException(ErrorCode.PARAM_MISSING, "城市名称不能为空");
    }

    if (city.getName().length() > 50) {
      throw new BusinessException(ErrorCode.PARAM_INVALID, "城市名称长度不能超过50个字符");
    }

    if (StringUtils.isBlank(city.getCountrycode())) {
      throw new BusinessException(ErrorCode.PARAM_MISSING, "国家代码不能为空");
    }

    if (city.getCountrycode().length() != 3) {
      throw new BusinessException(ErrorCode.PARAM_INVALID, "国家代码必须是3个字符");
    }

    if (StringUtils.isBlank(city.getDistrict())) {
      throw new BusinessException(ErrorCode.PARAM_MISSING, "行政区不能为空");
    }

    if (city.getDistrict().length() > 50) {
      throw new BusinessException(ErrorCode.PARAM_INVALID, "行政区长度不能超过50个字符");
    }

    if (city.getPopulation() == null) {
      throw new BusinessException(ErrorCode.PARAM_MISSING, "人口数量不能为空");
    }

    if (city.getPopulation() < 0) {
      throw new BusinessException(ErrorCode.PARAM_INVALID, "人口数量不能为负数");
    }
  }

  /**
   * 创建城市
   *
   * @param city 城市对象
   * @return 创建后的城市对象
   */
  public City create(City city) {
    validateCity(city);
    return cityRepository.save(city);
  }

  /** 更新城市 */
  public City updateCity(City city) {
    validateCity(city);
    Optional<City> existingCity = cityRepository.findById(city.getId());
    if (existingCity.isEmpty()) {
      throw new BusinessException(ErrorCode.BIZ_DATA_NOT_FOUND, "城市不存在");
    }
    return cityRepository.save(city);
  }

  /** 删除城市 */
  public void deleteCity(Long id) {
    if (id == null) {
      throw new BusinessException(ErrorCode.PARAM_MISSING, "城市ID不能为空");
    }
    // 确保城市存在
    getById(id);
    cityRepository.deleteById(id);
  }
}
