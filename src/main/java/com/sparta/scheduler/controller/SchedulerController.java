package com.sparta.scheduler.controller;

import com.sparta.scheduler.dto.SchedulerRequestDto;
import com.sparta.scheduler.dto.SchedulerResponseDto;
import com.sparta.scheduler.repository.SchedulerRepository;
import com.sparta.scheduler.service.SchedulerService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SchedulerController {

    // 중복 코드 : JdbcTemplate 객체를 계속 생성해주는 코드
    private final SchedulerService schedulerService;

    public SchedulerController(JdbcTemplate jdbcTemplate) {
        this.schedulerService = new SchedulerService(jdbcTemplate);
    }

    /*일정 등록*/
    @PostMapping("/schedules")
    public SchedulerResponseDto createScheduler(@RequestBody SchedulerRequestDto requestDto) {
        return schedulerService.createSchedule(requestDto);
    }

    /*일정 조회*/
    @GetMapping("/schedules")
    public List<SchedulerResponseDto> getSchedules() {
        return schedulerService.getSchedules();
    }

    /*일정 수정*/
    @PutMapping("/schedules/{id}")
    public Long updateSchedule(@PathVariable Long id, @RequestBody SchedulerRequestDto requestDto) {
        return schedulerService.updateSchedule(id, requestDto);
    }

    /*일정 삭제*/
    @DeleteMapping("/schedules/{id}")
    public Long deleteSchedule(@PathVariable Long id) {
        return schedulerService.deleteSchedule(id);
    }
}