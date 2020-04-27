package com.xpeter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	public List<User> getListUser(String fullname) {
		return userDAO.getListUser(fullname);
	}

	@Override
	@Transactional
	public User getInfoUser(String username) {
		return userDAO.getInfoUser(username);
	}

	@Override
	@Transactional
	public boolean insertUser(User user) {
		return userDAO.insertUser(user);
	}

	@Override
	@Transactional
	public boolean updateUser(User user) {
		return userDAO.updateUser(user);
	}

	@Override
	@Transactional
	public boolean deleteUser(String username) {
		return userDAO.deleteUser(username);
	}

}
