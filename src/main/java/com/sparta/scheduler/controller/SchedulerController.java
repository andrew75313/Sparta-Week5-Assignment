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

    /*일정 수정*/
    @PutMapping("/schedulers/{id}")
    public Long updateScheduler(@PathVariable Long id, @RequestBody SchedulerRequestDto requestDto) {
        // 해당 일정이 DB에 존재하는지 확인
        if(schedulerList.containsKey(id)) {
            // 해당 일정 가져오기
            Scheduler scheduler = schedulerList.get(id);
            // 해당 일정 수정(update)
            scheduler.update(requestDto);
            return scheduler.getId();
        } else {
            throw new IllegalArgumentException("선택한 일정은 존재하지 않습니다.");
        }
    }


    /*일정 삭제*/
    @DeleteMapping("/schedulers/{id}")
    public Long deleteScheduler(@PathVariable Long id) {
        // 해당 메모가 DB에 존재하는지 확인
        if(schedulerList.containsKey(id)) {
            // 해당 메모 삭제하기
            schedulerList.remove(id);
            return id;
        } else {
            throw new IllegalArgumentException("선택한 일정은 존재하지 않습니다.");
        }
    }
}

