package com.example.tasktracker.Service;


import com.example.tasktracker.Model.User;
import com.example.tasktracker.Repositories.UserReposiroty;
import com.example.tasktracker.Role.Role;
import java.util.Collections;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class AuthorizationService {


	private final UserReposiroty userReposiroty;

	private final PasswordEncoder bCryptPasswordEncoder;

	public AuthorizationService(UserReposiroty userReposiroty,
			PasswordEncoder bCryptPasswordEncoder) {
		this.userReposiroty = userReposiroty;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public String checkAuthority(String error, User user, Model model, HttpServletRequest request) {
		model.addAttribute("user", user);
		if (user != null)
			return "redirect:/main";
		if(error != null){
			model.addAttribute("message", "Неправильный логин или пароль");
		}
		else{
			Cookie[] cookies = request.getCookies();
			if(cookies!=null)
				for (Cookie c: cookies){
					if(c.getName().equals("password"))
						model.addAttribute("password", c.getValue());
					if(c.getName().equals("login"))
						model.addAttribute("username", c.getValue());
				}
		}
		return "login";
	}

	public String viewRegistrationPage(User user, Model model){
		model.addAttribute("user", user);
		return "registration";
	}

	public String save(User user, Model model){
		model.addAttribute("user", user);
		user.setUsername(user.getUsername());
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setEmail(user.getEmail());
		user.setPhoneNumber(user.getPhoneNumber());
		user.setRole(Collections.singleton(Role.USER));
		userReposiroty.save(user);
		return "redirect:/login";
	}

}
