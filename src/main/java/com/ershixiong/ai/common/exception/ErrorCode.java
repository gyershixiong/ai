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
 * 错误码枚举类。
 *
 * <p>定义系统中所有的错误码，分为以下类别：
 *
 * <ul>
 *   <li>系统错误: SYSTEM_ERROR
 *   <li>参数错误: PARAM_INVALID
 *   <li>业务错误: CITY_NOT_FOUND
 * </ul>
 *
 * <p>错误码格式：
 *
 * <ul>
 *   <li>前三位: 错误类型
 *   <li>后三位: 具体错误
 * </ul>
 *
 * <p>使用示例：
 *
 * <pre>{@code
 * throw new BusinessException(ErrorCode.PARAM_INVALID, "参数无效");
 * }</pre>
 *
 * @author ershixiong
 * @since 1.0.0
 * @date 2025-01-31
 */
public enum ErrorCode {
  /** 系统内部错误 当系统发生未预期的错误时使用 例如：数据库操作失败、文件操作失败等 */
  SYS_INTERNAL_ERROR("SYS_INTERNAL_ERROR", "系统内部错误"),

  /** 系统配置错误 当系统配置出现问题时使用 例如：缺少必要的配置项、配置值无效等 */
  SYS_CONFIG_ERROR("SYS_CONFIG_ERROR", "系统配置错误"),

  /** 必填参数缺失 当请求中缺少必需的参数时使用 */
  PARAM_MISSING("PARAM_MISSING", "必填参数缺失"),

  /** 参数格式无效 当参数不符合预期的格式或规则时使用 例如：日期格式错误、手机号格式错误等 */
  PARAM_INVALID_FORMAT("PARAM_INVALID_FORMAT", "参数格式无效"),

  /** 参数值超出范围 当参数值超出允许的范围时使用 例如：分页大小超出限制、数值超出范围等 */
  PARAM_OUT_OF_RANGE("PARAM_OUT_OF_RANGE", "参数值超出范围"),

  /** 参数无效 当参数值不符合业务规则时使用 */
  PARAM_INVALID("PARAM_INVALID", "参数无效"),

  /** 数据不存在 当请求的数据在系统中不存在时使用 例如：根据ID查询记录不存在等 */
  BIZ_DATA_NOT_FOUND("BIZ_DATA_NOT_FOUND", "数据不存在"),

  /** 数据已存在 当要创建的数据在系统中已经存在时使用 例如：用户名已被注册、编码重复等 */
  BIZ_DATA_EXISTS("BIZ_DATA_EXISTS", "数据已存在"),

  /** 数据状态错误 当数据的当前状态不允许执行请求的操作时使用 例如：已删除的记录不能修改、已完成的订单不能取消等 */
  BIZ_INVALID_STATUS("BIZ_INVALID_STATUS", "数据状态错误"),

  /** 操作不允许 当用户尝试执行不被允许的操作时使用 例如：权限不足、操作限制等 */
  BIZ_OPERATION_NOT_ALLOWED("BIZ_OPERATION_NOT_ALLOWED", "操作不允许"),

  /** 远程服务不可用 当调用的外部服务无法访问时使用 例如：服务宕机、网络故障等 */
  RPC_SERVICE_UNAVAILABLE("RPC_SERVICE_UNAVAILABLE", "远程服务不可用"),

  /** 远程服务调用超时 当调用外部服务超过预设时间时使用 */
  RPC_CALL_TIMEOUT("RPC_CALL_TIMEOUT", "远程服务调用超时"),

  /** 远程服务调用失败 当外部服务调用返回错误时使用 例如：接口返回错误码、响应格式错误等 */
  RPC_CALL_FAILED("RPC_CALL_FAILED", "远程服务调用失败"),

  /** 服务熔断 当服务熔断时使用 */
  RPC_CIRCUIT_BREAK("RPC_CIRCUIT_BREAK", "服务熔断");

  /** 错误码 用于唯一标识一个错误 */
  private final String code;

  /** 错误信息 用于描述错误的具体内容 */
  private final String message;

  /**
   * 构造函数
   *
   * @param code 错误码
   * @param message 错误信息
   */
  ErrorCode(String code, String message) {
    this.code = code;
    this.message = message;
  }

  /**
   * 获取错误码
   *
   * @return 错误码字符串
   */
  public String getCode() {
    return code;
  }

  /**
   * 获取错误信息
   *
   * @return 错误信息字符串
   */
  public String getMessage() {
    return message;
  }
}
