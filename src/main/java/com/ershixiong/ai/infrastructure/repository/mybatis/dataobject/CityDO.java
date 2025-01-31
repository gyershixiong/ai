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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 城市数据对象。
 *
 * <p>该类是数据库表的映射对象，包含以下属性：
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
@TableName("city")
public class CityDO {
  /** 城市ID */
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  /** 城市名称 */
  private String name;

  /** 国家代码 */
  private String countrycode;

  /** 城市所属行政区 */
  private String district;

  /** 城市人口 */
  private Integer population;
}
