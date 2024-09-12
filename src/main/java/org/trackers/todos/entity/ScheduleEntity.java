package org.trackers.todos.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Schedule")
public class ScheduleEntity {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private long userId;
	private String completed;
	
	public ScheduleEntity() {}
	public ScheduleEntity(long id, String name, LocalDateTime startDate, LocalDateTime endDate, long userId, String completed) {
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.userId = userId;
		this.completed = completed;
	}
	
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public LocalDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public void setCompleted(String completed) {
		this.completed = completed;
	}
	public String getCompleted() {
		return this.completed;
	}
}
