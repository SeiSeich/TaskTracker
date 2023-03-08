package com.example.tasktracker.DTO;


import com.example.tasktracker.Enums.Role;
import java.util.HashSet;
import java.util.Set;


public class UserDTO {

	private Integer id;

	private String username;

	private String password;

	private String email;

	private String phoneNumber;

	private String role;

	public UserDTO(Integer id, String username, String password, String email, String phoneNumber, String role) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Set<Role> getRole() {
		Set<Role> roleSet = new HashSet<>();
		Role r = Role.valueOf(role);
		roleSet.add(r);
		return roleSet;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
