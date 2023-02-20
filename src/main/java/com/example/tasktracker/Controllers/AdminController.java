package com.example.tasktracker.Controllers;


import com.example.tasktracker.Model.User;
import com.example.tasktracker.Repositories.UserReposiroty;
import com.example.tasktracker.Service.AdminService;
import java.util.Optional;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private final AdminService adminService;

	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

	@GetMapping
	public String gerAdminManePage(@AuthenticationPrincipal User user, Model model){
		return adminService.getAdminPage(user, model);
	}

	@GetMapping("/editUser/{id}")
	public String userEditForm(@PathVariable("id") String username, @AuthenticationPrincipal User user, Model model){
		return adminService.editUserForm(username, user, model);
	}

	@PostMapping("/editUser")
	public String userEditActrion(@ModelAttribute User userChanged, @RequestParam("id") User userToEdit, @AuthenticationPrincipal User user, Model model){
		return adminService.editUserAction(userChanged, userToEdit, user, model);
	}

}
