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
package com.ershixiong.ai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 应用程序主入口类。
 *
 * <p>该类负责启动Spring Boot应用程序，并提供以下功能：
 *
 * <ul>
 *   <li>自动配置Spring Boot组件
 *   <li>启用MyBatis Plus支持
 *   <li>开启Web服务
 * </ul>
 *
 * <p>技术栈：
 *
 * <ul>
 *   <li>Spring Boot 3.x
 *   <li>MyBatis Plus
 *   <li>MySQL
 * </ul>
 *
 * @author ershixiong
 * @since 1.0.0
 * @date 2025-01-31
 */
@SpringBootApplication
@MapperScan("com.ershixiong.ai.infrastructure.repository.mybatis.mapper")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
