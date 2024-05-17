package com.sparta.scheduler.exception;

import com.sparta.scheduler.dto.SchedulerResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class SchedulerExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ResponseEntity<SchedulerResponseDto> handleIllegalArgumentException(IllegalArgumentException e) {
        SchedulerResponseDto responseDto = new SchedulerResponseDto();
        responseDto.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }
}
