package org.trackers.todos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.trackers.todos.entity.ScheduleEntity;

public interface ScheduleRepository extends CrudRepository<ScheduleEntity, Long> {
	List<ScheduleEntity> findByUserId(Long userId);
}
