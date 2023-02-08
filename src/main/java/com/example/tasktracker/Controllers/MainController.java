package com.example.tasktracker.Controllers;

import com.example.tasktracker.Repositories.TaskReporitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

	@Autowired
	private TaskReporitory taskReporitory;

	@GetMapping("/")
	public String homePage(@RequestParam(name = "name", required = false, defaultValue = "World")
			String name, Model model) {
		model.addAttribute("some", "Lets code.");
		model.addAttribute("name", name);
		return "home";
	}


}
