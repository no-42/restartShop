package org.restart.shop.exception;

import lombok.extern.slf4j.Slf4j;
import org.restart.shop.resp.ResultData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 默认全局异常处理。
     * @param e the e
     * @return ResultData
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData<String> exception(Exception e) {
        if (e instanceof RsException) {
            return ResultData.fail(((RsException) e).getErrorCode(), ((RsException) e).getErrorMessage());
        }
        return ResultData.fail("-1", e.getMessage());
    }
}
