package org.trackers.todos.api;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.trackers.todos.model.Schedule;
import org.trackers.todos.repository.ScheduleRepository;

@RestController
@RequestMapping("/todos")
public class TodosController {
	final private ScheduleRepository scheduleRepository;
	
	private TodosController(ScheduleRepository scheduleRepository) {
		this.scheduleRepository = scheduleRepository;
	}
	
	@GetMapping("/users/{userId}")
	private ResponseEntity<List<Schedule>> getTodosByUserId(@PathVariable("userId") Long userId, Pageable pageable){
		var page = scheduleRepository.findByUserId(userId, 
				PageRequest.of(pageable.getPageNumber(), 
								pageable.getPageSize(),
								pageable.getSortOr(Sort.by(Direction.ASC, "name"))));		
		return ResponseEntity.ok(page.stream()
				.map(e -> new Schedule(e.getId(), e.getName(), e.getStartDate(), e.getEndDate(), e.getUserId(), e.getCompleted()))
				.toList());
	}
	
	@GetMapping
	private ResponseEntity<List<Schedule>> getTodos(Pageable pageable){
		var page = scheduleRepository.findAll(
				PageRequest.of(pageable.getPageNumber(), 
								pageable.getPageSize(),
								pageable.getSortOr(Sort.by(Direction.ASC, "name"))));	
		return ResponseEntity.ok(page.stream()
				.map(e -> new Schedule(e.getId(), e.getName(), e.getStartDate(), e.getEndDate(), e.getUserId(), e.getCompleted()))
				.toList());
	}
}
