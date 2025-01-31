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
package com.ershixiong.ai.domain.repository;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ershixiong.ai.domain.model.City;

import java.util.List;
import java.util.Optional;

/**
 * 城市仓储接口。
 *
 * <p>定义城市领域模型的持久化操作：
 *
 * <ul>
 *   <li>保存城市信息
 *   <li>查询城市信息
 *   <li>删除城市信息
 *   <li>分页查询城市列表
 * </ul>
 *
 * <p>设计原则：
 *
 * <ul>
 *   <li>遵循领域驱动设计
 *   <li>与具体持久化技术解耦
 *   <li>支持事务操作
 * </ul>
 *
 * <p>实现说明：
 *
 * <ul>
 *   <li>由基础设施层提供具体实现
 *   <li>可以有多种实现方式（MyBatis、JPA等）
 *   <li>通过依赖注入使用具体实现
 * </ul>
 *
 * @author ershixiong
 * @since 1.0.0
 * @date 2025-01-31
 */
public interface CityRepository {
  /** 根据ID查询城市 */
  Optional<City> findById(Long id);

  /** 查询所有城市 */
  List<City> findAll();

  /**
   * 根据名称模糊查询城市，支持分页
   *
   * @param namePattern 名称模式（包含%通配符）
   * @param page 分页对象
   * @return 分页的城市列表
   */
  Page<City> findByNameLike(String namePattern, Page<City> page);

  /**
   * 保存城市
   *
   * @return 保存后的城市对象
   */
  City save(City city);

  /** 删除城市 */
  void deleteById(Long id);
}
