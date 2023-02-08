package com.example.tasktracker.Controllers;


import com.example.tasktracker.Model.User;
import com.example.tasktracker.Repositories.UserReposiroty;
import com.example.tasktracker.Role.Role;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthorizationController {


	@Autowired
	private UserReposiroty userReposiroty;

	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

/*
	@GetMapping("/login")
	public String login(User user, Model model){
		model.addAttribute("user", user);
		return "login";
	}
*/

	@GetMapping("/registration")
	public String registration(){
		return "registration";
	}

	@PostMapping("/registration")
	public String addUser(User user, Model model){
		user.setUsername(user.getUsername());
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setEmail(user.getEmail());
		user.setPhoneNumber(user.getPhoneNumber());
		user.setRole(Collections.singleton(Role.USER));
		userReposiroty.save(user);
		return "redirect:/login";
	}

}
