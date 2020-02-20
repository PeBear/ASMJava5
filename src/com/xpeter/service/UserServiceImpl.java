package com.xpeter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xpeter.dao.UserDAO;
import com.xpeter.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public List<User> getListUser(String fullname) {
		return userDAO.getListUser(fullname);
	}

	@Override
	public User getInfoUser(String username) {
		return userDAO.getInfoUser(username);
	}

	@Override
	public boolean insertUser(User user) {
		return userDAO.insertUser(user);
	}

	@Override
	public boolean updateUser(User user) {
		return userDAO.updateUser(user);
	}

	@Override
	public boolean deleteUser(String username) {
		return userDAO.deleteUser(username);
	}

}
