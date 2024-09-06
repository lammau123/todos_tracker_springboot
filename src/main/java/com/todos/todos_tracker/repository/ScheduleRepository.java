package com.todos.todos_tracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.todos.todos_tracker.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>{
	List<Schedule> findByUserId(Long userId);
}
