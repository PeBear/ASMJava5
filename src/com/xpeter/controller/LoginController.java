package com.xpeter.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xpeter.model.User;
import com.xpeter.service.UserService;

@Controller
public class LoginController {

	@Autowired
	UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String displayLogin(ModelMap model) {
		return "login";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String checkLogin(ModelMap model, HttpServletRequest request) {
		String username = request.getParameter("username");
		User user = userService.getInfoUser(username);
		if (user == null) {
			return "login";
		} else {
			String password = request.getParameter("password");
			if (user.getPassword().equals(password)) {
				HttpSession session = request.getSession(true);
				session.setAttribute("user", user);
				session.setMaxInactiveInterval(300);
				return "redirect:dashboard.htm";
			}
		}
		return "login";
	}
}
