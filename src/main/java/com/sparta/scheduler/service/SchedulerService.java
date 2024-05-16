package com.sparta.scheduler.service;

import com.sparta.scheduler.dto.SchedulerRequestDto;
import com.sparta.scheduler.dto.SchedulerResponseDto;
import com.sparta.scheduler.entity.Schedule;
import com.sparta.scheduler.repository.SchedulerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SchedulerService {

    private final SchedulerRepository schedulerRepository;

    public SchedulerService(SchedulerRepository schedulerRepository) {
        this.schedulerRepository = schedulerRepository;
    }


    /*일정 등록*/
    @Transactional
    public SchedulerResponseDto createSchedule(SchedulerRequestDto requestDto) {

        // RequestDto -> Entity
        Schedule schedule = new Schedule(requestDto);

        // Repository에서 저장
        schedulerRepository.save(schedule);

        // Entity -> ResponseDto
        SchedulerResponseDto schedulerResponseDto = new SchedulerResponseDto(schedule);

        return schedulerResponseDto;
    }

    /*일정 조회*/
    public List<SchedulerResponseDto> getSchedules() {
        return schedulerRepository.findAllByOrderByCreatedAtDesc().stream().map(SchedulerResponseDto::new).toList();
    }

    /*선택 일정 조회*/
    public SchedulerResponseDto getSchedule(Long id) {
        return new SchedulerResponseDto(findSchedule(id));
    }

    @Transactional
    /*일정 수정*/
    public Long updateSchedule(Long id, SchedulerRequestDto requestDto) {
        // 해당 일정이 DB에 존재하는지 + 비밀번호가 DB password와 같은지 확인
        Schedule schedule = matchSchedule(id, requestDto);
        // 일정을 수정
        schedule.update(requestDto);

        return id;
    }

    /*일정 삭제*/
    @Transactional
    public Long deleteSchedule(Long id, SchedulerRequestDto requestDto) {
        // 해당 일정이 DB에 존재하는지 + 비밀번호가 DB password와 같은지 확인
        Schedule schedule = matchSchedule(id, requestDto);
        // Repository통해 DB에서 일정 삭제
        schedulerRepository.delete(schedule);

        return id;
    }

    /*일정 존재 여부 확인 메서드*/
    private Schedule findSchedule(Long id) {
        // 존재할 경우, schedule 반환, null 일 경우 예외
        return schedulerRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("선택한 일정은 존재하지 않습니다.")
        );
    }

    /*일정 id - password 일치 확인메서드*/
    private Schedule matchSchedule(Long id, SchedulerRequestDto requestDto) {
        Schedule schedule = findSchedule(id);
        // 일치할 경우, schedule 반환 아닐 경우 예외
        if (schedule.getPassword().equals(requestDto.getPassword())) {
            return schedule;
        } else {
            throw new IllegalArgumentException("비밀번호를 다시 한 번 확인해 주세요.");
        }
    }
}
