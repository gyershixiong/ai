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

import com.ershixiong.ai.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器。
 *
 * <p>统一处理系统中的各类异常，包括：
 *
 * <ul>
 *   <li>业务异常 {@link BusinessException}
 *   <li>参数验证异常 {@link BindException}
 *   <li>系统运行时异常 {@link Exception}
 * </ul>
 *
 * <p>异常处理策略：
 *
 * <ul>
 *   <li>业务异常：返回具体的错误码和消息
 *   <li>参数验证异常：返回详细的参数错误信息
 *   <li>系统异常：返回统一的系统错误提示
 * </ul>
 *
 * @author ershixiong
 * @since 1.0.0
 * @date 2025-01-31
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
  private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  /**
   * 处理业务异常
   *
   * @param ex 业务异常对象
   * @return 包含错误信息的HTTP响应
   */
  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<Result<Void>> handleBusinessException(BusinessException ex) {
    LOGGER.warn("业务异常: {}", ex.getMessage());
    Result<Void> result = Result.fail(ex.getErrorCode().getCode(), ex.getMessage());
    return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
  }

  /**
   * 处理参数校验异常
   *
   * @param ex 参数校验异常对象
   * @return 包含错误信息的HTTP响应
   */
  @ExceptionHandler(BindException.class)
  public ResponseEntity<Result<Void>> handleValidationException(BindException ex) {
    String message =
        ex.getBindingResult().getFieldErrors().stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .findFirst()
            .orElse("参数校验失败");
    LOGGER.warn("参数校验异常: {}", message);
    Result<Void> result = Result.fail(ErrorCode.PARAM_INVALID.getCode(), message);
    return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
  }

  /**
   * 处理其他未预期的异常
   *
   * @param ex 异常对象
   * @return 包含错误信息的HTTP响应
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Result<Void>> handleException(Exception ex) {
    LOGGER.error("系统异常: ", ex);
    Result<Void> result = Result.fail(ErrorCode.SYS_INTERNAL_ERROR.getCode(), "系统内部错误");
    return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
