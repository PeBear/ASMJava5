package com.xpeter.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sun.org.glassfish.external.statistics.Statistic;
import com.xpeter.model.Staff;
import com.xpeter.model.ThanhTich;
import com.xpeter.service.StaffService;

@Controller
public class DashboardController {
	@Autowired
	StaffService staffService;
	
	
	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}


	@RequestMapping(value = "dashboard", method = RequestMethod.GET)
	public String display(ModelMap model) {
		List<String> list = staffService.getTop10Statistic();
		List<Staff> listStaff = new ArrayList<>();
		for (String x : list) {
			Staff staff = staffService.getInfoStaff(x);
			listStaff.add(staff);
		}
		model.addAttribute("ListStaff", listStaff);
		return "dashboard";
	}
}
