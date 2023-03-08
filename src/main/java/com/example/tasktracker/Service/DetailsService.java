package com.example.tasktracker.Service;

import com.example.tasktracker.Model.User;
import com.example.tasktracker.Repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class DetailsService implements UserDetailsService {


	private final UserRepository userRepository;

	public DetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public String viewHomePage(User user,Model model){
		model.addAttribute("user", user);
		return "home";
	}

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		return userRepository.findByUsername(name);
	}

}
