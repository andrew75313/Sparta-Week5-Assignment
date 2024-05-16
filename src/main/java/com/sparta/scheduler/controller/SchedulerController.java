package com.sparta.scheduler.controller;

import com.sparta.scheduler.dto.SchedulerRequestDto;
import com.sparta.scheduler.dto.SchedulerResponseDto;
import com.sparta.scheduler.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        // RequestDto -> Entity
        Schedule schedule = new Schedule(requestDto);

        // DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체

        String sql = "INSERT INTO schedule (username, password, title, contents) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, schedule.getUsername());
                    preparedStatement.setString(2, schedule.getPassword());
                    preparedStatement.setString(3, schedule.getTitle());
                    preparedStatement.setString(4, schedule.getContents());
                    return preparedStatement;
                },
                keyHolder);

        // DB Insert 후 받아온 기본키 확인
        Long id = keyHolder.getKey().longValue();
        schedule.setId(id);

        // Entity -> ResponseDto
        SchedulerResponseDto schedulerResponseDto = new SchedulerResponseDto(schedule);

        return schedulerResponseDto;
    }

    /*일정 조회*/
    @GetMapping("/schedules")
    public List<SchedulerResponseDto> getSchedules() {
        // DB 조회
        String sql = "SELECT * FROM schedule";

        return jdbcTemplate.query(sql, new RowMapper<SchedulerResponseDto>() {
            @Override
            public SchedulerResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                // SQL 의 결과로 받아온 Scheduler 데이터들을 SchedulerResponseDto 타입으로 변환해줄 메서드
                Long id = rs.getLong("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String title = rs.getString("title");
                String contents = rs.getString("contents");
                return new SchedulerResponseDto(id, username, password, title, contents);
            }
        });
    }

    /*일정 수정*/
    @PutMapping("/schedules/{id}")
    public Long updateSchedule(@PathVariable Long id, @RequestBody SchedulerRequestDto requestDto) {
        // 해당 일정가 DB에 존재하는지 확인
        Schedule schedule = findById(id);
        if (schedule != null) {
            // scheduler 내용 수정
            String sql = "UPDATE schedule SET username = ?, password = ?, title = ?, contents = ? WHERE id = ?";
            jdbcTemplate.update(sql, requestDto.getUsername(), requestDto.getPassword(), requestDto.getTitle(), requestDto.getContents(), id);

            return id;
        } else {
            throw new IllegalArgumentException("선택한 일정은 존재하지 않습니다.");
        }
    }

    /*일정 삭제*/
    @DeleteMapping("/schedules/{id}")
    public Long deleteSchedule(@PathVariable Long id) {
        // 해당 일정가 DB에 존재하는지 확인
        Schedule schedule = findById(id);
        if (schedule != null) {
            // scheduler 삭제
            String sql = "DELETE FROM schedule WHERE id = ?";
            jdbcTemplate.update(sql, id);

            return id;
        } else {
            throw new IllegalArgumentException("선택한 일정는 존재하지 않습니다.");
        }
    }

    /*일정을 찾는 메서드*/
    private Schedule findById(Long id) {
        // DB 조회
        String sql = "SELECT * FROM schedule WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setUsername(resultSet.getString("username"));
                schedule.setUsername(resultSet.getString("password"));
                schedule.setUsername(resultSet.getString("title"));
                schedule.setContents(resultSet.getString("contents"));
                return schedule;
            } else {
                return null;
            }
        }, id);
    }
}