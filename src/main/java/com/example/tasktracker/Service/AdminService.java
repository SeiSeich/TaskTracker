package com.example.tasktracker.Service;

import com.example.tasktracker.Repositories.UserReposiroty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

	@Autowired
	private UserReposiroty userReposiroty;

}
