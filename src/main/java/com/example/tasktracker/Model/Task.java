package com.example.tasktracker.Model;


import com.example.tasktracker.DTO.TaskDTO;
import com.example.tasktracker.Enums.Priority;
import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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

	@ElementCollection(targetClass = Priority.class, fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	private Set<Priority> priority;

	@Column(name = "active")
	private boolean active = true;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User author;

	public Task() {
	}

	public Task(String taskName, String notes, String targetDate, String targetTime, Set<Priority> priority, User user) {
		this.author = user;
		this.taskName = taskName;
		this.notes = notes;
		this.targetDate = targetDate;
		this.targetTime = targetTime;
		this.priority = priority;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
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
		String levelOfPriority = priority.toString();
		return levelOfPriority;
	}

	public void setPriority(Set<Priority> priority) {
		this.priority = priority;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
