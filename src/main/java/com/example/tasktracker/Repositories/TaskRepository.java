package com.example.tasktracker.Repositories;

import com.example.tasktracker.Model.Task;
import com.example.tasktracker.Model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long>, JpaSpecificationExecutor<Task> {

	List<Task> findByTaskName(String taskName);

	List<Task> findByAuthor(User user);

	List<Task> findByTaskNameOrTargetDateOrTargetTimeOrPriority(String taskName, String targetDate, String targetTime, String priority);

	List<Task> findByPriority(String priority);


}
