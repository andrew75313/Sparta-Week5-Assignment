package com.sparta.scheduler.dto;

import com.sparta.scheduler.entity.Schedule;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class SchedulerResponseDto {
    private Long id;
    private String username;
    private String password;
    private String title;
    private String contents;
    private LocalDate createdAt;

    public SchedulerResponseDto(Schedule scheduler) {
        this.id = scheduler.getId();
        this.username = scheduler.getUsername();
        this.password = scheduler.getPassword();
        this.title = scheduler.getTitle();
        this.contents = scheduler.getContents();
        this.createdAt = scheduler.getCreatedAt();
    }
}