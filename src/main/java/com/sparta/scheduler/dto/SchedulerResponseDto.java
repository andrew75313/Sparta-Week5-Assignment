package com.sparta.scheduler.dto;

import com.sparta.scheduler.entity.Scheduler;
import lombok.Getter;

@Getter
public class SchedulerResponseDto {
    private Long id;
    private String username;
    private String password;
    private String title;
    private String contents;

    public SchedulerResponseDto(Scheduler scheduler) {
        this.id = scheduler.getId();
        this.username = scheduler.getUsername();
        this.password = scheduler.getPassword();
        this.title = scheduler.getTitle();
        this.contents = scheduler.getContents();
    }
}