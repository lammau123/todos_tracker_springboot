package org.trackers.todos.model;

import java.time.LocalDateTime;

public record Schedule (Long id, String name, LocalDateTime startDate, LocalDateTime endDate, long userId, String completed) {
}
