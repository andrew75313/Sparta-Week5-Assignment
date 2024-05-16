package com.sparta.scheduler.controller;

import com.sparta.scheduler.dto.SchedulerRequestDto;
import com.sparta.scheduler.dto.SchedulerResponseDto;
import com.sparta.scheduler.service.SchedulerService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SchedulerController {

    //jdbc template 주입
    private final JdbcTemplate jdbcTemplate;

    public SchedulerController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*일정 등록*/
    @PostMapping("/schedules")
    public SchedulerResponseDto createScheduler(@RequestBody SchedulerRequestDto requestDto) {
        SchedulerService schedulerService = new SchedulerService(jdbcTemplate);
        return schedulerService.createSchedule(requestDto);
    }

    /*일정 조회*/
    @GetMapping("/schedules")
    public List<SchedulerResponseDto> getSchedules() {
        SchedulerService schedulerService = new SchedulerService(jdbcTemplate);
        return schedulerService.getSchedules();
    }

    /*일정 수정*/
    @PutMapping("/schedules/{id}")
    public Long updateSchedule(@PathVariable Long id, @RequestBody SchedulerRequestDto requestDto) {
        SchedulerService schedulerService = new SchedulerService(jdbcTemplate);
        return schedulerService.updateSchedule(id, requestDto);
    }

    /*일정 삭제*/
    @DeleteMapping("/schedules/{id}")
    public Long deleteSchedule(@PathVariable Long id) {
        SchedulerService schedulerService = new SchedulerService(jdbcTemplate);
        return schedulerService.deleteSchedule(id);
    }
}