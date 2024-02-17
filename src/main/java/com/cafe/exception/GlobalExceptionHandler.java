package com.cafe.exception;

import com.cafe.common.MyCafeResponse;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public MyCafeResponse<Void> handleException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return MyCafeResponse.fail(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public MyCafeResponse<Void> handleInvalidInputException(MethodArgumentNotValidException e) {
        log.debug(e.getMessage(), e);
        return MyCafeResponse.fail(ErrorCode.INVALID_INPUT);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public MyCafeResponse<Void> handleInvalidInputException(HttpMessageNotReadableException e) {
        log.debug(e.getMessage(), e);
        return MyCafeResponse.fail(ErrorCode.INVALID_INPUT);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MyCafeException.class)
    public MyCafeResponse<Void> handleSzsException(MyCafeException e) {
        log.debug(e.getMessage(), e);
        return MyCafeResponse.fail(e.getErrorCode());
    }

}
