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

import java.util.List;

/**
 * 分页响应对象。
 *
 * <p>封装分页查询的结果数据：
 *
 * <ul>
 *   <li>total: 总记录数
 *   <li>pageNum: 当前页码
 *   <li>pageSize: 每页记录数
 *   <li>records: 当前页的数据列表
 * </ul>
 *
 * <p>特性：
 *
 * <ul>
 *   <li>支持泛型，可以存储任意类型的数据
 *   <li>提供便捷的构建方法
 *   <li>可以从MyBatis Plus的IPage对象转换
 * </ul>
 *
 * <p>使用示例：
 *
 * <pre>{@code
 * PageResponse<CityDTO> response = PageResponse.of(page, records);
 * }</pre>
 *
 * @author ershixiong
 * @since 1.0.0
 * @date 2025-01-31
 */
public class PageResponse<T> {
  private List<T> datas;
  private long totalCount;
  private int totalPage;
  private int currentPage;
  private int pageSize;

  public List<T> getDatas() {
    return datas;
  }

  public void setDatas(List<T> datas) {
    this.datas = datas;
  }

  public long getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(long totalCount) {
    this.totalCount = totalCount;
  }

  public int getTotalPage() {
    return totalPage;
  }

  public void setTotalPage(int totalPage) {
    this.totalPage = totalPage;
  }

  public int getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public static <T> PageResponse<T> of(
      List<T> datas, long totalCount, int currentPage, int pageSize) {
    PageResponse<T> response = new PageResponse<>();
    response.setDatas(datas);
    response.setTotalCount(totalCount);
    response.setCurrentPage(currentPage);
    response.setPageSize(pageSize);
    response.setTotalPage((int) Math.ceil((double) totalCount / pageSize));
    return response;
  }
}
