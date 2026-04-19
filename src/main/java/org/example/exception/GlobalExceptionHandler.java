package org.example.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<String> handleNotFound(DataNotFoundException e) {
        log.warn("資源未找到: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("錯誤：" + e.getMessage());
    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<String> handleExternalApiError(RestClientException e) {
        log.warn("呼叫外部 API 失敗: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("錯誤：呼叫外部 API 失敗，請稍後再試");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralError(Exception e) {
        log.warn("伺服器發生未知錯誤: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("伺服器發生未知錯誤：" + e.getMessage());
    }

}
