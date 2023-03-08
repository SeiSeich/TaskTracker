package com.example.tasktracker.Controllers;


import com.example.tasktracker.DTO.TaskDTO;
import com.example.tasktracker.Model.Task;
import com.example.tasktracker.Model.User;
import com.example.tasktracker.Service.AuthorizationService;
import com.example.tasktracker.Service.TaskService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainController {

	private final TaskService taskService;

	private final AuthorizationService authorizationService;

	public MainController(TaskService taskService, AuthorizationService authorizationService) {
		this.taskService = taskService;
		this.authorizationService = authorizationService;
	}

	@GetMapping
	public String findTask(@AuthenticationPrincipal User user, Model model) {
		return taskService.outputOfAll(user, model);
	}

	@PostMapping("/create")
	public String addTask( @ModelAttribute Task taskForm, @AuthenticationPrincipal User user,Model model) {
		return taskService.save(user, taskForm, model);
	}

/*	@PostMapping("/filter")
	public String find(@AuthenticationPrincipal User user, @ModelAttribute("task") TaskDTO taskForm, Model model) {
		return taskService.filter(user, taskForm, model);
	}*/

	@GetMapping("/changePassword")
	public String changePasswod(@AuthenticationPrincipal User user, Model model){
		model.addAttribute("user", user);
		return "changePassword";
	}

	@PostMapping("/changePassword")
	public String changePasswordAction(@ModelAttribute("old_password") String oldPassword,
			@ModelAttribute("new_password") String newPasswod, @AuthenticationPrincipal User user,
			Model model){
		return authorizationService.changePass(oldPassword, newPasswod, user, model);
	}

	@GetMapping("editTask/{id}")
	public String editTaskForm(@PathVariable("id") Integer id, @AuthenticationPrincipal User user, Model model){
		return taskService.viewTaskEditPage(id, user, model);
	}

	@PostMapping("editTask/{id}")
	public String editTask(@ModelAttribute TaskDTO editTask, @AuthenticationPrincipal User user, Model model) {
		return taskService.editTask(editTask, user, model);
	}

		@GetMapping("deleteTask/{id}")
	public String deleteTask(@PathVariable("id") Integer id, @AuthenticationPrincipal User user, Model model){
		return taskService.deleteTask(id, user, model);
		}
}
