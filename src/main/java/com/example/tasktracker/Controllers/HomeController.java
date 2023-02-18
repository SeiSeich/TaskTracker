package com.example.tasktracker.Controllers;

import com.example.tasktracker.Model.User;
import com.example.tasktracker.Service.DetailsService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	private final DetailsService detailsService;

	public HomeController(DetailsService detailsService) {
		this.detailsService = detailsService;
	}

	@GetMapping("/")
	public String homePage(@AuthenticationPrincipal User user, Model model) {
		return detailsService.viewHomePage(user, model);
	}


}
