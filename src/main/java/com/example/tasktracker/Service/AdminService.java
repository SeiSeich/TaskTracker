package com.example.tasktracker.Service;

import com.example.tasktracker.Model.User;
import com.example.tasktracker.Repositories.UserReposiroty;
import com.example.tasktracker.Role.Role;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class AdminService {


	private final UserReposiroty userReposiroty;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public AdminService(UserReposiroty userReposiroty, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userReposiroty = userReposiroty;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public String getAdminPage(User user, Model model){
		Iterable<User> users = userReposiroty.findAll();
		model.addAttribute("user", user);
		model.addAttribute("users", users);
		return "admin";
	}

	public String editUserForm(String username, User user, Model model){
		User userToEdit = userReposiroty.findByUsername(username);
		model.addAttribute("roles", Role.values());
		model.addAttribute("usr", userToEdit);
		model.addAttribute("user", user);
		return "userEditForm";
	}

	public String  editUserAction(User userChanged, User userToEdit, User user, Model model){
		userToEdit.setUsername(userChanged.getUsername());
		userToEdit.setPassword(bCryptPasswordEncoder.encode(userChanged.getPassword()));
		userToEdit.setEmail(userChanged.getEmail());
		userToEdit.setPhoneNumber(userChanged.getPhoneNumber());
		userToEdit.setRole(userChanged.getRole());
		userReposiroty.save(userToEdit);
		return "redirect:/admin";
	}

}
