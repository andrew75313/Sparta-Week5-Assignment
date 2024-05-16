package com.sparta.scheduler.controller;

import com.sparta.scheduler.dto.SchedulerRequestDto;
import com.sparta.scheduler.dto.SchedulerResponseDto;
import com.sparta.scheduler.entity.Scheduler;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SchedulerController {

    private final Map<Long, Scheduler> schedulerList = new HashMap<>();

    /*일정 저장*/
    @PostMapping("/schedulers")
    public SchedulerResponseDto createSchedule(@RequestBody SchedulerRequestDto requestDto) {
        // RequestDto -> Entity
        Scheduler scheduler = new Scheduler(requestDto);

        // scheduler Max ID Check
        Long maxId = schedulerList.size() > 0 ? Collections.max(schedulerList.keySet()) + 1 : 1;
        scheduler.setId(maxId);

        // 일정 DB 저장
        schedulerList.put(scheduler.getId(), scheduler);

        // Entity -> ResponseDto
        SchedulerResponseDto schedulerResponseDto = new SchedulerResponseDto(scheduler);

        return schedulerResponseDto;
    }

    /*일정 조회*/
    @GetMapping("/schedulers")
    public List<SchedulerResponseDto> getSchedulers() {
        // Map To responseList
        List<SchedulerResponseDto> responseList = schedulerList.values().stream()
                .map(SchedulerResponseDto::new).toList();

        return responseList;
    }
}

