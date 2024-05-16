package com.sparta.scheduler.dto;

import com.sparta.scheduler.entity.Schedule;
import lombok.Getter;

@Getter
public class SchedulerResponseDto {
    private Long id;
    private String username;
    private String password;
    private String title;
    private String contents;

    public SchedulerResponseDto(Schedule scheduler) {
        this.id = scheduler.getId();
        this.username = scheduler.getUsername();
        this.password = scheduler.getPassword();
        this.title = scheduler.getTitle();
        this.contents = scheduler.getContents();
    }

    public SchedulerResponseDto(Long id, String username, String password, String title, String contents) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.title = title;
        this.contents = contents;

    }
}