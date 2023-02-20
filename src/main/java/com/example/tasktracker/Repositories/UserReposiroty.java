package com.example.tasktracker.Repositories;

import com.example.tasktracker.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReposiroty extends JpaRepository<User, Long> {

	/*User findById(Long id);*/

	User findByUsername(String username);

	User findByEmail(String email);

	User findByPassword(String password);

}
