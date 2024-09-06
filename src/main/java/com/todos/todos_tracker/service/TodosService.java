package com.todos.todos_tracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.todos.todos_tracker.model.ScheduleDto;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import com.todos.todos_tracker.repository.ScheduleRepository;

@Service
public class TodosService {
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	public List<ScheduleDto> getSchedules(final Long userId){
		return scheduleRepository.findByUserId(userId)
				.stream()
				.map(e -> new ScheduleDto(e.getId(), e.getName(), 
						e.getStartDate(), e.getEndDate(), e.getUserId()))
				.toList();
	}
}
