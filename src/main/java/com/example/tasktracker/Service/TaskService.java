package com.example.tasktracker.Service;



import com.example.tasktracker.Model.Task;
import com.example.tasktracker.Model.User;
import com.example.tasktracker.Repositories.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class TaskService {

	private final TaskRepository taskRepository;

	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	public String outputOfAll(User user, Model model) {
		Iterable<Task> tasks = taskRepository.findByAuthor(user);
		model.addAttribute("tasks", tasks);
		model.addAttribute("user", user);
		return "main";
	}

	public String save(User user,Task task, Model model) {
		task.setAuthor(user);
		taskRepository.save(task);
		model.addAttribute("user", user);
		return "main";
	}


	public String filter(User user, Task taskForm, Model model) {
		Iterable<Task> tasksFiltered;
		tasksFiltered = taskRepository.findAll((root, cq, cb) -> cb.and(
				taskForm.getTaskName() != null ? cb.equal(root.get("taskName"), taskForm.getTaskName()) : cb.and(),
				taskForm.getTargetDate() != null ? cb.equal(root.get("targetDate"), taskForm.getTargetDate()) : cb.and(),
				taskForm.getTargetTime() != null ? cb.equal(root.get("targetTime"), taskForm.getTargetTime()) : cb.and(),
				taskForm.getPriority() != null ? cb.equal(root.get("priority"), taskForm.getPriority()) : cb.and(),
				cb.equal(root.get("author").get("id"), user.getId())
		));


		model.addAttribute("tasks", tasksFiltered);
		model.addAttribute("user", user);
		return "main";


	}

}
