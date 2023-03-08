package com.example.tasktracker.DTO;


import com.example.tasktracker.Enums.Priority;
import com.example.tasktracker.Model.Task;
import com.example.tasktracker.Model.User;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class TaskDTO{
private Integer id;

		private String taskName;

		private String notes;

		private String targetDate;

		private String targetTime;

		private String priority;

		private User author;

	public TaskDTO(Integer id, String taskName, String notes, LocalDateTime creationDate, String targetDate,
			String targetTime, String priority, User author) {
		this.id = id;
		this.taskName = taskName;
		this.notes = notes;
		this.targetDate = targetDate;
		this.targetTime = targetTime;
		this.priority = priority;
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

	public Set<Priority> getPriority() {
		Set<Priority> prioritySet = new HashSet<>();
		Priority p = Priority.valueOf(priority);
		prioritySet.add(p);
		return prioritySet;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public boolean equalsTask(Task task){
		return taskName.equals(task.getTaskName()) && notes.equals(task.getNotes()) &&
				targetDate.equals(task.getTargetDate())
				&& targetTime.equals(getTargetTime()) && priority.equals(task.getPriority());
	}

}
