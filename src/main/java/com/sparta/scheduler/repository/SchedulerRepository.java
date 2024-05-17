package com.sparta.scheduler.repository;

import com.sparta.scheduler.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchedulerRepository extends JpaRepository<Schedule, Long> {
    //등록일 순으로 내림차순 정렬
    List<Schedule> findAllByOrderByCreatedAtDesc();

    //id 해당하는 일정 조회
    Schedule findAllById(Long id);
}
