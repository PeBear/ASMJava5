package com.xpeter.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xpeter.model.User;
import com.xpeter.service.UserService;

@Controller
@RequestMapping(value = "userManage")
public class UserController {
	@Autowired
	UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String display(ModelMap model) {
		model.addAttribute("User", new User());
		model.addAttribute("ListUser", userService.getListUser(null));
		return "userManage";
	}

	@RequestMapping(params = "insert")
	public String insertUser(ModelMap model, @ModelAttribute("User") User user) {
		String mess = "Insert error occur!";
		if (userService.insertUser(user)) {
			mess = "Insert success!";
		}
		model.addAttribute("mess", mess);
		return "redirect:userManage.htm";
	}

	@RequestMapping(params = "update")
	public String updateUser(ModelMap model, @ModelAttribute("User") User user) {
		String mess = "Update error occur!";
		if (userService.updateUser(user)) {
			mess = "Update success!";
		}
		model.addAttribute("mess", mess);
		return "redirect:userManage.htm";
	}

	@RequestMapping(params = "delete")
	public String deleteUser(ModelMap model, HttpServletRequest request) {
		String username = request.getParameter("username");
		String mess = "Delete error occur!";
		if (userService.deleteUser(username)) {
			mess = "Delete success!";
		}
		model.addAttribute("mess", mess);
		return "redirect:userManage.htm";
	}

	@RequestMapping(value = "ajaxUser", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> displayAjax(HttpServletRequest request) {
		String username = request.getParameter("username");
		ObjectMapper objectMapper = new ObjectMapper();
		User result = userService.getInfoUser(username);
		String ajax = "";
		try {
			ajax = objectMapper.writeValueAsString(result);
		} catch (JsonProcessingException e) {
			// e.printStackTrace();
			System.out.println("loi exception: " + e);
		}
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json; charset=utf-8");
		return new ResponseEntity<String>(ajax, responseHeaders, HttpStatus.CREATED);
		// return ajax;
	}
}
