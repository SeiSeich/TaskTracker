package com.example.tasktracker.Repositories;

import com.example.tasktracker.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	User findByEmail(String email);

	User findByPassword(String password);

	User findById(Integer id);

	void deleteById(Integer id);
}
