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
package com.ershixiong.ai.infrastructure.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis Plus配置类。
 *
 * <p>提供MyBatis Plus的核心配置，包括：
 *
 * <ul>
 *   <li>分页插件配置
 *   <li>性能分析插件配置
 *   <li>SQL注入器配置
 * </ul>
 *
 * <p>配置说明：
 *
 * <ul>
 *   <li>启用分页功能，支持多数据库类型
 *   <li>配置最大限制条数，防止内存溢出
 *   <li>支持count优化，当count为0时直接返回
 * </ul>
 *
 * @author ershixiong
 * @since 1.0.0
 * @date 2025-01-31
 */
@Configuration
public class MybatisPlusConfig {

  /**
   * 配置MyBatis Plus分页插件。
   *
   * <p>主要配置：
   *
   * <ul>
   *   <li>设置请求的最大页面大小，防止内存溢出
   *   <li>设置数据库类型，确保SQL方言正确
   *   <li>优化count查询，当count为0时直接返回
   * </ul>
   *
   * @return 分页插件
   */
  @Bean
  public MybatisPlusInterceptor mybatisPlusInterceptor() {
    MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

    // 创建分页插件配置
    PaginationInnerInterceptor paginationInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
    // 设置最大单页限制数量，默认 500 条，-1 不受限制
    paginationInterceptor.setMaxLimit(500L);
    // 设置请求页面大于最大页后操作，true调回到首页，false继续请求
    paginationInterceptor.setOverflow(false);
    // 当count为0时，不执行分页查询
    paginationInterceptor.setOptimizeJoin(true);

    interceptor.addInnerInterceptor(paginationInterceptor);
    return interceptor;
  }
}
