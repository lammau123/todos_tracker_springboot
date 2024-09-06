package com.todos.todos_tracker.model;

import java.time.LocalDateTime;

public record ScheduleDto (long id, String name, LocalDateTime startDate, LocalDateTime endDate, long userId) {
}
