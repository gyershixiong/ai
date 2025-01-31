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
package com.ershixiong.ai.api.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * 搜索城市请求对象。
 *
 * <p>用于接收城市搜索的API请求参数，支持以下搜索条件：
 *
 * <ul>
 *   <li>name: 城市名称（模糊匹配）
 *   <li>countryCode: 国家代码（精确匹配）
 *   <li>district: 地区（模糊匹配）
 *   <li>minPopulation: 最小人口数
 *   <li>maxPopulation: 最大人口数
 * </ul>
 *
 * <p>分页参数：
 *
 * <ul>
 *   <li>pageSize: 每页记录数，默认10
 *   <li>pageNum: 页码，从1开始
 * </ul>
 *
 * @author ershixiong
 * @since 1.0.0
 * @date 2025-01-31
 */
public class SearchCityRequest {
  @NotBlank(message = "搜索关键词不能为空")
  private String name;

  @Min(value = 1, message = "页码必须大于0")
  private int pageNo = 1;

  @Min(value = 1, message = "每页记录数必须大于0")
  @Max(value = 100, message = "每页记录数不能超过100")
  private int pageSize = 10;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SearchCityRequest that = (SearchCityRequest) o;
    return pageNo == that.pageNo && pageSize == that.pageSize && Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, pageNo, pageSize);
  }

  @Override
  public String toString() {
    return "SearchCityRequest{"
        + "name='"
        + name
        + '\''
        + ", pageNo="
        + pageNo
        + ", pageSize="
        + pageSize
        + '}';
  }
}
