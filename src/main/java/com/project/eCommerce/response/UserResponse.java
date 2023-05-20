package com.project.eCommerce.response;

import com.project.eCommerce.entities.User;

public class UserResponse {
	
	Long id;
	String userName;

	public UserResponse(User entity) {
		this.id = entity.getUserId();
		this.userName = entity.getUsername();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	} 
	
	
}