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
package com.ershixiong.ai.common;

import com.ershixiong.ai.common.exception.ErrorCode;

/**
 * 统一响应结果类。
 *
 * <p>封装所有API响应的标准格式，包含：
 *
 * <ul>
 *   <li>success: 是否成功
 *   <li>code: 响应码
 *   <li>message: 响应消息
 *   <li>data: 响应数据
 * </ul>
 *
 * <p>使用示例：
 *
 * <pre>{@code
 * // 成功响应
 * Result.success(cityDTO);
 *
 * // 失败响应
 * Result.error(ErrorCode.PARAM_INVALID, "参数无效");
 * }</pre>
 *
 * @author ershixiong
 * @since 1.0.0
 * @date 2025-01-31
 */
public final class Result<T> {
  /** 错误码，用于标识具体的错误类型 为空表示成功，非空表示失败 */
  private String errorCode;

  /** 响应信息 成功时为"OK"或成功提示信息 失败时为具体的错误信息 */
  private String message;

  /** 响应数据 可以在成功或失败时包含数据 */
  private T data;

  /**
   * 私有构造函数，禁止外部直接创建实例 必须通过提供的静态方法创建对象
   *
   * @param errorCode 错误码
   * @param message 响应信息
   * @param data 响应数据
   */
  private Result(String errorCode, String message, T data) {
    this.errorCode = errorCode;
    this.message = message;
    this.data = data;
  }

  /**
   * 创建成功响应，不带数据
   *
   * @param <T> 响应数据类型
   * @return Result对象
   */
  public static <T> Result<T> success() {
    return new Result<>(null, "OK", null);
  }

  /**
   * 创建成功响应，带数据
   *
   * @param data 响应数据
   * @param <T> 响应数据类型
   * @return Result对象
   */
  public static <T> Result<T> success(T data) {
    return new Result<>(null, "OK", data);
  }

  /**
   * 创建成功响应，带自定义消息和数据
   *
   * @param message 成功提示信息
   * @param data 响应数据
   * @param <T> 响应数据类型
   * @return Result对象
   */
  public static <T> Result<T> success(String message, T data) {
    return new Result<>(null, message, data);
  }

  /**
   * 创建失败响应，指定错误码和错误信息
   *
   * @param errorCode 错误码
   * @param message 错误信息
   * @param <T> 响应数据类型
   * @return Result对象
   */
  public static <T> Result<T> fail(String errorCode, String message) {
    return new Result<>(errorCode, message, null);
  }

  /**
   * 创建失败响应，指定错误码、错误信息和数据
   *
   * @param errorCode 错误码
   * @param message 错误信息
   * @param data 响应数据
   * @param <T> 响应数据类型
   * @return Result对象
   */
  public static <T> Result<T> fail(String errorCode, String message, T data) {
    return new Result<>(errorCode, message, data);
  }

  /**
   * 创建失败响应，使用错误码枚举
   *
   * @param errorCode 错误码枚举
   * @param <T> 响应数据类型
   * @return Result对象
   */
  public static <T> Result<T> fail(ErrorCode errorCode) {
    return new Result<>(errorCode.getCode(), errorCode.getMessage(), null);
  }

  /**
   * 创建失败响应，使用错误码枚举和自定义错误信息
   *
   * @param errorCode 错误码枚举
   * @param message 错误信息
   * @param <T> 响应数据类型
   * @return Result对象
   */
  public static <T> Result<T> fail(ErrorCode errorCode, String message) {
    return new Result<>(errorCode.getCode(), message, null);
  }

  /**
   * 创建失败响应，使用错误码枚举和数据
   *
   * @param errorCode 错误码枚举
   * @param data 响应数据
   * @param <T> 响应数据类型
   * @return Result对象
   */
  public static <T> Result<T> fail(ErrorCode errorCode, T data) {
    return new Result<>(errorCode.getCode(), errorCode.getMessage(), data);
  }

  /**
   * 创建失败响应，使用错误码枚举、自定义错误信息和数据
   *
   * @param errorCode 错误码枚举
   * @param message 错误信息
   * @param data 响应数据
   * @param <T> 响应数据类型
   * @return Result对象
   */
  public static <T> Result<T> fail(ErrorCode errorCode, String message, T data) {
    return new Result<>(errorCode.getCode(), message, data);
  }

  /**
   * 创建失败响应，使用默认系统错误码和自定义错误信息
   *
   * @param message 错误信息
   * @param <T> 响应数据类型
   * @return Result对象
   */
  public static <T> Result<T> fail(String message) {
    return new Result<>(ErrorCode.SYS_INTERNAL_ERROR.getCode(), message, null);
  }

  /**
   * 获取错误码
   *
   * @return 错误码
   */
  public String getErrorCode() {
    return errorCode;
  }

  /**
   * 设置错误码
   *
   * @param errorCode 错误码
   */
  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  /**
   * 获取响应信息
   *
   * @return 响应信息
   */
  public String getMessage() {
    return message;
  }

  /**
   * 设置响应信息
   *
   * @param message 响应信息
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * 获取响应数据
   *
   * @return 响应数据
   */
  public T getData() {
    return data;
  }

  /**
   * 设置响应数据
   *
   * @param data 响应数据
   */
  public void setData(T data) {
    this.data = data;
  }

  /**
   * 重写toString方法，用于日志打印
   *
   * @return 对象的字符串表示
   */
  @Override
  public String toString() {
    return "Result{"
        + "errorCode='"
        + errorCode
        + '\''
        + ", message='"
        + message
        + '\''
        + ", data="
        + data
        + '}';
  }
}
