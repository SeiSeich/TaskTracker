package com.example.tasktracker.Controllers;


import com.example.tasktracker.Model.User;
import com.example.tasktracker.DTO.UserDTO;
import com.example.tasktracker.Service.AdminService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String userEditForm(@PathVariable("id") Integer id, @AuthenticationPrincipal User user, Model model){
		return adminService.editUserForm(id, user, model);
	}

	@PostMapping("/editUser/{id}")
	public String userEditActtion(@ModelAttribute("usr") UserDTO userChanged,
			@AuthenticationPrincipal User user,
			Model model){
		return adminService.editUserAction(userChanged, user, model);
	}

	@GetMapping("/delete/{id}")
	public String deleteForm(@PathVariable("id") Integer id, @AuthenticationPrincipal User user, Model model){
		return adminService.delete(id, user, model);
	}

}
