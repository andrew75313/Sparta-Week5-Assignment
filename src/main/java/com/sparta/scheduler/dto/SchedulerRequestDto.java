package com.sparta.scheduler.dto;

import lombok.Getter;

@Getter
public class SchedulerRequestDto {
    private String username;
    private String password;
    private String title;
    private String contents;
}
