package com.example.tasktracker.Service;



import com.example.tasktracker.DTO.TaskDTO;
import com.example.tasktracker.Enums.Priority;
import com.example.tasktracker.Model.Task;
import com.example.tasktracker.Model.User;
import com.example.tasktracker.Repositories.TaskRepository;
import java.util.Collections;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

@Service
public class TaskService {

	private final TaskRepository taskRepository;

	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	public String outputOfAll(User user, Model model) {
		Iterable<Task> tasks = taskRepository.findByAuthor(user);
		model.addAttribute("priorities", Priority.values());
		model.addAttribute("tasks", tasks);
		model.addAttribute("user", user);
		return "main";
	}

	public String save(User user,Task task, Model model) {
		task.setAuthor(user);
		taskRepository.save(task);
		Set priorities = Collections.singleton(Priority.values());
		model.addAttribute("priorities", priorities);
		model.addAttribute("user", user);
		Iterable<Task> tasks = taskRepository.findByAuthor(user);
		model.addAttribute("tasks", tasks);
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
		model.addAttribute("priorities", Priority.values());
		return "main";
	}

	public String viewTaskEditPage(Integer id, User user, Model model){
		model.addAttribute("task", taskRepository.findById(id));
		model.addAttribute("user", user);
		model.addAttribute("priorities",Priority.values());
		return "taskEditForm";
	}

	public String editTask(TaskDTO editTask, User user, Model model){
		Task taskToEdit = taskRepository.findById(editTask.getId());
		if(!editTask.equalsTask(taskToEdit)){
			if(editTask.getTaskName() != null){
				taskToEdit.setTaskName(editTask.getTaskName());
			}
			taskToEdit.setNotes(editTask.getNotes());
			if(editTask.getTargetDate() != null){
				taskToEdit.setTargetDate(editTask.getTargetDate());;
			}
			if(editTask.getTargetTime() != null){
				taskToEdit.setTargetTime(editTask.getTargetTime());
			}
			if(editTask.getPriority() != null){
				taskToEdit.setPriority(editTask.getPriority());
			}
			taskRepository.save(taskToEdit);
			model.addAttribute("tasks", taskRepository.findByAuthor(user));
			model.addAttribute("user", user);
			model.addAttribute("priorities", Priority.values());
			return "redirect:/main";
		}
		model.addAttribute("user", user);
		model.addAttribute("priorities", Priority.values());
		model.addAttribute("message", "Вы не изменили данные");
		return "taskEditForm";
	}

	@Transactional
	public String deleteTask(Integer id, User user, Model model){
		taskRepository.deleteById(id);
		model.addAttribute("user", user);
		model.addAttribute("priorities", Priority.values());
		return "redirect:/main";
	}
}
