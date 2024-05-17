package com.sparta.scheduler.exception;

import com.sparta.scheduler.dto.ExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class SchedulerExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ResponseEntity<ExceptionResponseDto> handleIllegalArgumentException(IllegalArgumentException e) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto();
        exceptionResponseDto.setMessage(e.getMessage());
        exceptionResponseDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponseDto);
    }
}
