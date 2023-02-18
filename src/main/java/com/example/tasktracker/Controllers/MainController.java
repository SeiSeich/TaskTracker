package com.example.tasktracker.Controllers;


import com.example.tasktracker.Model.Task;
import com.example.tasktracker.Model.User;
import com.example.tasktracker.Service.TaskService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainController {

	private final TaskService taskService;

	public MainController(TaskService taskService) {
		this.taskService = taskService;
	}

	@GetMapping
	public String findTask(@AuthenticationPrincipal User user, Model model) {
		return taskService.outputOfAll(user, model);
	}

	@PostMapping("/create")
	public String addTask(@AuthenticationPrincipal User user, @ModelAttribute Task taskForm, Model model) {
		return taskService.save(user, taskForm, model);
	}

	@PostMapping("/filter")
	public String find(@AuthenticationPrincipal User user, @ModelAttribute("task") Task taskForm, Model model) {
		return taskService.filter(user, taskForm, model);
	}


}
