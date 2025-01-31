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
package com.ershixiong.ai.common.exception;

/**
 * 业务异常类。
 *
 * <p>用于处理业务逻辑异常，具有以下特点：
 *
 * <ul>
 *   <li>包含错误码和错误消息
 *   <li>支持参数化的错误消息
 *   <li>与{@link ErrorCode}枚举类配合使用
 * </ul>
 *
 * <p>使用示例：
 *
 * <pre>{@code
 * throw new BusinessException(ErrorCode.PARAM_INVALID, "城市名称不能为空");
 * }</pre>
 *
 * @author ershixiong
 * @since 1.0.0
 * @date 2025-01-31
 */
public class BusinessException extends RuntimeException {

  /** 错误码 */
  private final ErrorCode errorCode;

  /**
   * 构造函数 使用指定的错误码枚举
   *
   * @param errorCode 错误码枚举
   */
  public BusinessException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }

  /**
   * 构造函数 使用指定的错误码枚举和自定义错误信息
   *
   * @param errorCode 错误码枚举
   * @param message 自定义错误信息
   */
  public BusinessException(ErrorCode errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
  }

  /**
   * 构造函数 使用指定的错误码枚举和原始异常
   *
   * @param errorCode 错误码枚举
   * @param cause 原始异常
   */
  public BusinessException(ErrorCode errorCode, Throwable cause) {
    super(errorCode.getMessage(), cause);
    this.errorCode = errorCode;
  }

  /**
   * 构造函数 使用指定的错误码枚举、自定义错误信息和原始异常
   *
   * @param errorCode 错误码枚举
   * @param message 自定义错误信息
   * @param cause 原始异常
   */
  public BusinessException(ErrorCode errorCode, String message, Throwable cause) {
    super(message, cause);
    this.errorCode = errorCode;
  }

  /**
   * 获取错误码
   *
   * @return 错误码枚举
   */
  public ErrorCode getErrorCode() {
    return errorCode;
  }
}
