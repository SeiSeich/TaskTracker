package com.example.tasktracker.Repositories;

import com.example.tasktracker.Model.Task;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface TaskReporitory extends CrudRepository<Task, Long> {

	List<Task> findByTaskName(String taskName);

}
