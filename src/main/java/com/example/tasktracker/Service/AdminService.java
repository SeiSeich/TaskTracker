package com.example.tasktracker.Service;

import com.example.tasktracker.Model.User;
import com.example.tasktracker.DTO.UserDTO;
import com.example.tasktracker.Repositories.UserRepository;
import com.example.tasktracker.Enums.Role;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

@Service
public class AdminService {


	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public AdminService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public String getAdminPage(User user, Model model){
		Iterable<User> users = userRepository.findAll();
		model.addAttribute("user", user);
		model.addAttribute("users", users);
		return "admin";
	}

	public String editUserForm(Integer userId, User user, Model model){
		User userToEdit = userRepository.findById(userId);
		model.addAttribute("roles", Role.values());
		model.addAttribute("usr", userToEdit);
		model.addAttribute("user", user);
		return "userEditForm";
	}

	public String  editUserAction(UserDTO userChanged, User user, Model model){
		User userToEdit = userRepository.findById(userChanged.getId());
		if(!userChanged.equals(userToEdit)) {
			if (userChanged.getUsername() != null) {
				userToEdit.setUsername(userChanged.getUsername());
			}
			if(bCryptPasswordEncoder.encode(userChanged.getPassword()) != null) {
				userToEdit.setPassword(bCryptPasswordEncoder.encode(userChanged.getPassword()));
			}
			if(userChanged.getEmail() != null) {
				userToEdit.setEmail(userChanged.getEmail());
			}
			if(userChanged.getPhoneNumber() != null) {
				userToEdit.setPhoneNumber(userChanged.getPhoneNumber());
			}
			if(userChanged.getRole() != null) {
				userToEdit.setRole(userChanged.getRole());
			}
			userRepository.save(userToEdit);
			model.addAttribute("user", user);
			return "redirect:/admin";
		}
		model.addAttribute("message", "Вы не изменили данные");
		model.addAttribute("roles", Role.values());
		model.addAttribute("usr", userToEdit);
		model.addAttribute("user", user);
		return "userEditForm";
	}

	@Transactional
	public String delete(Integer id, User user, Model model){
		userRepository.deleteById(id);
		model.addAttribute("user", user);
		return "redirect:/admin";
	}


}
