package org.trackers.todos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trackers.todos.entity.ScheduleEntity;

public interface ScheduleRepository extends CrudRepository<ScheduleEntity, Long>, PagingAndSortingRepository<ScheduleEntity, Long> {
	Page<ScheduleEntity> findByUserId(Long userId, Pageable pageabe);
}
