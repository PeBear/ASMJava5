package com.xpeter.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.xpeter.model.Depart;
import com.xpeter.model.Staff;
import com.xpeter.service.StaffService;

@Controller
public class StaffController {
	@Autowired
	StaffService staffService;

	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}

	@RequestMapping(value = "staffManage", method = RequestMethod.GET)
	public String display(ModelMap model) {
		List<Staff> list = staffService.getListStaff(null);
		model.addAttribute("ListStaff", list);
		model.addAttribute("Staff", new Staff());
		return "staffManage";
	}

	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public String insertStudent(@ModelAttribute("Staff") @DateTimeFormat(pattern = "yyyy-MM-dd") Staff staff,
			ModelMap model) {
		String mess = "Insert error occur!";
		staff.setDepartId(new Depart("PB01", "TPHCM"));
		if (staffService.insertStaff(staff)) {
			mess = "Insert success!";
		}
		model.addAttribute("mess", mess);
		return "redirect:staffManage.htm";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String updateStudent(@ModelAttribute("Staff") Staff staff, ModelMap model) {
		String mess = "Update error occur!";
		staff.setDepartId(new Depart("PB01", "TPHCM"));
		if (staffService.updateStaff(staff)) {
			mess = "Update success!";
		}
		model.addAttribute("mess", mess);
		System.out.println(staff.toString());
		return "redirect:staffManage.htm";
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String deleteStudent(HttpServletRequest req, ModelMap model) {
		String staffId = req.getParameter("staffId");
		String mess = "Delete error occur!";
		if (staffService.deleteStaff(staffId)) {
			mess = "Delete success!";
		}
		model.addAttribute("mess", mess);
		return "redirect:staffManage.htm";
	}

	@RequestMapping(value = "testAJAX", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> displayAjax(HttpServletRequest request) {

		String staffId = request.getParameter("name");
		ObjectMapper objectMapper = new ObjectMapper();
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		objectMapper.setDateFormat(df);

		Staff result = staffService.getInfoStaff(staffId);
		//set null for escape StackOverFlow Exception while convert Object to JSON
		result.setRecordCollection(null);
		result.setDepartId(null);
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
