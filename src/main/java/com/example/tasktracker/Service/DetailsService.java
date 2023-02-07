package com.example.tasktracker.Service;

import com.example.tasktracker.Repositories.UserReposiroty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DetailsService implements UserDetailsService {

	@Autowired
	private UserReposiroty userReposiroty;

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

		return userReposiroty.findByUsername(name);
	}

}
