package com.example.tasktracker.Controllers;


import com.example.tasktracker.Model.User;
import com.example.tasktracker.Service.AuthorizationService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizationController {

	private final AuthorizationService authorizationService;

	public AuthorizationController(AuthorizationService authorizationService) {
		this.authorizationService = authorizationService;
	}

	@GetMapping("/login")
	public String authorizationPage(@RequestParam(required = false) String error, @AuthenticationPrincipal User user, Model model, HttpServletRequest request) {
		return authorizationService.checkAuthority(error, user, model, request);
	}

	@GetMapping("/registration")
	public String registration(@AuthenticationPrincipal User user, Model model){
		return authorizationService.viewRegistrationPage(user, model);
	}

	@PostMapping("/registration")
	public String addUser(Model model, User user){
		return authorizationService.save(user, model);
	}

}
