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
package com.ershixiong.ai.common.page;

/**
 * 分页请求对象。
 *
 * <p>封装分页查询的通用参数：
 *
 * <ul>
 *   <li>pageNum: 当前页码，从1开始
 *   <li>pageSize: 每页记录数
 * </ul>
 *
 * <p>使用说明：
 *
 * <ul>
 *   <li>继承此类以添加特定的查询条件
 *   <li>通过validate()方法进行参数校验
 *   <li>支持转换为MyBatis Plus的Page对象
 * </ul>
 *
 * <p>默认值：
 *
 * <ul>
 *   <li>pageNum: 1
 *   <li>pageSize: 10
 *   <li>最大页面大小: 100
 * </ul>
 *
 * @author ershixiong
 * @since 1.0.0
 * @date 2025-01-31
 */
public class PageRequest {
  private int pageNo = 1;
  private int pageSize = 10;

  public int getPageNo() {
    return pageNo;
  }

  public void setPageNo(int pageNo) {
    this.pageNo = pageNo;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }
}
