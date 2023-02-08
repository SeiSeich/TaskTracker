package com.example.tasktracker.Controllers;


import com.example.tasktracker.Model.Task;
import com.example.tasktracker.Repositories.TaskReporitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/main")
public class TaskController {

	@Autowired
	private TaskReporitory taskReporitory;

	@GetMapping
	public String findTask(Model model) {
		Iterable<Task> tasks = taskReporitory.findAll();
		model.addAttribute("tasks", tasks);
		return "main";
	}

	@PostMapping("/create")
	public String addTask(@RequestParam String taskName, @RequestParam String notes,
			@RequestParam String targetDate, @RequestParam String targetTime, @RequestParam String priority,
			Model model) {
		Task task = new Task(taskName, notes, targetDate, targetTime, priority);
		taskReporitory.save(task);
		Iterable<Task> tasks = taskReporitory.findAll();
		model.addAttribute("tasks", tasks);
		return "main";
	}

	@PostMapping("/filter")
	public String findByName(@RequestParam String name, Model model) {
		Iterable<Task> tasks;
		if (name != null && !name.isEmpty()){
			tasks = taskReporitory.findByTaskName(name);;
		}else {
			tasks = taskReporitory.findAll();
		}
		model.addAttribute("tasks", tasks);
		return "main";
	}


}
