package com.example.tasktracker.Service;


import com.example.tasktracker.Model.User;
import com.example.tasktracker.Repositories.UserRepository;
import com.example.tasktracker.Enums.Role;
import java.util.Collections;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class AuthorizationService {


	private final UserRepository userRepository;

	private final PasswordEncoder bCryptPasswordEncoder;

	public AuthorizationService(UserRepository userRepository,
			PasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
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
		userRepository.save(user);
		return "redirect:/login";
	}

	private boolean matches(String oldPassword, String password){
		return bCryptPasswordEncoder.matches(oldPassword, password);
	}

	private void updatePassword(User user){
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	public String changePass(String oldPassword, String newPassword, User user, Model model){
		if(matches(oldPassword, user.getPassword())){
			if(!newPassword.equals(oldPassword)){
				user.setPassword(newPassword);
				updatePassword(user);
				model.addAttribute("user", user);
				return "redirect:/main";
			}else{
				model.addAttribute("message", "Новый пароль должен отличаться.");
				model.addAttribute("user", user);
				return"/changePassword";
			}
		}else{
			model.addAttribute("message", "Введите действующий пароль");
			model.addAttribute("user", user);
			return "/changePassword";
		}
	}
}
