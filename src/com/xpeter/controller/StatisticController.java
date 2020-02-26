package com.xpeter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xpeter.service.DepartService;
import com.xpeter.service.StaffService;

@Controller
public class StatisticController {

	@Autowired
	StaffService staffService;
	@Autowired
	DepartService departService;

	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}

	public void setDepartService(DepartService departService) {
		this.departService = departService;
	}

	@RequestMapping(value = "statisticStaff", method = RequestMethod.GET)
	public String displayStatisticStaff(ModelMap model) {
		model.addAttribute("ListStatistic", staffService.getListStatistic(null));
		return "statisticStaff";
	}

	@RequestMapping(value = "statisticDepart", method = RequestMethod.GET)
	public String displayStatisticDepart(ModelMap model) {
		model.addAttribute("ListStatistic", departService.getListStatistic());
		return "statisticDepart";
	}
}
