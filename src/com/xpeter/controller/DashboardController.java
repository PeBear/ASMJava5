package com.xpeter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xpeter.service.StaffService;

@Controller
public class DashboardController {
	@Autowired
	StaffService staffService;
	
	
	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}


	@RequestMapping(value = "dashboard", method = RequestMethod.GET)
	public String display() {
		staffService.getThanhTich("PS09070");
		return "dashboard";
	}
}
