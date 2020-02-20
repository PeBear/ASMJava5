package com.xpeter.service;

import java.util.List;

import com.xpeter.model.User;

public interface UserService {
	public List<User> getListUser(String fullname);

	public User getInfoUser(String username);

	public boolean insertUser(User user);

	public boolean updateUser(User user);

	public boolean deleteUser(String username);
}
