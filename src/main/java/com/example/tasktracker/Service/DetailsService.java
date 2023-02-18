package com.example.tasktracker.Service;

import com.example.tasktracker.Model.User;
import com.example.tasktracker.Repositories.UserReposiroty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class DetailsService implements UserDetailsService {


	private final UserReposiroty userReposiroty;

	public DetailsService(UserReposiroty userReposiroty) {
		this.userReposiroty = userReposiroty;
	}

	public String viewHomePage(User user,Model model){
		model.addAttribute("user", user);
		return "home";
	}

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		return userReposiroty.findByUsername(name);
	}

}
