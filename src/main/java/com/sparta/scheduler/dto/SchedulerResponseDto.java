package com.sparta.scheduler.dto;

import com.sparta.scheduler.entity.Schedule;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SchedulerResponseDto {
    private Long id;
    private String username;
    private String password;
    private String title;
    private String contents;
    private LocalDate createdAt;
    private String message;

    public SchedulerResponseDto() {
    }
    public SchedulerResponseDto(Schedule scheduler) {
        this.id = scheduler.getId();
        this.username = scheduler.getUsername();
        this.password = scheduler.getPassword();
        this.title = scheduler.getTitle();
        this.contents = scheduler.getContents();
        this.createdAt = scheduler.getCreatedAt();
    }
}