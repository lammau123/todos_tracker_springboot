package org.trackers.todos.api;

import java.util.List;

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
	
	@GetMapping("/{userId}")
	public ResponseEntity<List<Schedule>> getTodos(@PathVariable("userId") Long userId){
		var listOfTodos = scheduleRepository.findByUserId(userId);		
		return ResponseEntity.ok(listOfTodos.stream()
				.map(e -> new Schedule(e.getId(), e.getName(), e.getStartDate(), e.getEndDate(), e.getUserId(), e.getCompleted()))
				.toList());
	}
}
