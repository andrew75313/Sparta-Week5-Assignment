package com.sparta.scheduler.dto;

import com.sparta.scheduler.entity.Schedule;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class SchedulerResponseDto {
    private Long id;
    private String username;
    private String password;
    private String title;
    private String contents;
    private LocalDateTime createdAt;

    public SchedulerResponseDto(Schedule scheduler) {
        this.id = scheduler.getId();
        this.username = scheduler.getUsername();
        this.title = scheduler.getTitle();
        this.contents = scheduler.getContents();
        this.createdAt = scheduler.getCreatedAt();
    }
}