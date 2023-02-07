package com.example.tasktracker.Model;


import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name = "tasks")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "task_name")
	private String taskName;

	@Column(name = "notes")
	private String notes;

	@Column(name = "creation_date")
	@CreationTimestamp
	private LocalDateTime creationDate;

	@Column(name = "target_date")
	private String targetDate;

	@Column(name = "target_time")
	private String targetTime;

	@Column(name = "priority")
	private String priority;

	@Column(name = "active")
	@ColumnDefault("true")
	private boolean active;

	public Task() {
	}

	public Task(String taskName, String notes, String targetDate, String targetTime, String priority) {
		this.taskName = taskName;
		this.notes = notes;
		this.targetDate = targetDate;
		this.targetTime = targetTime;
		this.priority = priority;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public String getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(String targetDate) {
		this.targetDate = targetDate;
	}

	public String getTargetTime() {
		return targetTime;
	}

	public void setTargetTime(String targetTime) {
		this.targetTime = targetTime;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
