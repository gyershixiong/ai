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
package com.ershixiong.ai.infrastructure.repository.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ershixiong.ai.infrastructure.repository.mybatis.dataobject.CityDO;
import org.apache.ibatis.annotations.Mapper;

/** 城市Mapper接口 继承BaseMapper获取基础的CRUD功能 包括分页查询功能 */
@Mapper
public interface CityMapper extends BaseMapper<CityDO> {
  // BaseMapper提供了足够的基础功能，包括分页查询
  // 如果需要复杂的自定义查询，可以在这里添加
}
