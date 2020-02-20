package com.xpeter.controller;

import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xpeter.model.Staff;
import com.xpeter.service.StaffService;

@Controller
public class HomeController {
	@Autowired
	StaffService staffService;

	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String display(ModelMap model) {
		List<Staff> list = staffService.getListStaff(null);
		model.addAttribute("ListStaff", list);
		model.addAttribute("Staff", new Staff());
		return "index";
	}

	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public String insertStudent(@ModelAttribute("Staff") @DateTimeFormat(pattern = "dd-MM-yyyy") Staff staff,
			ModelMap model) {
//		String mess = "Insert error occur!";
//		if (staffService.insertStaff(staff)) {
//			mess = "Insert success!";
//		}
//		model.addAttribute("mess", mess);
		System.out.println(staff.toString());
		return "redirect:index.htm";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String updateStudent(@ModelAttribute("Staff") Staff staff, ModelMap model) {
//		String mess = "Update error occur!";
//		if (staffService.updateStaff(staff)) {
//			mess = "Update success!";
//		}
//		model.addAttribute("mess", mess);
		System.out.println(staff.toString());
		return "redirect:index.htm";
	}

	/*
	 * @RequestMapping(value = "form", method = RequestMethod.POST) public String
	 * displayForm(HttpServletRequest req, ModelMap model) { String username =
	 * req.getParameter("username"); Student student = new Student(); boolean
	 * isUpdate = false; if (username.equals("")) { model.addAttribute("Student",
	 * student); } else { student = staffService.getInfoStudent(username);
	 * model.addAttribute("Student", student); isUpdate = true; }
	 * model.addAttribute("isUpdate", isUpdate); return "form"; }
	 */

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String deleteStudent(HttpServletRequest req, ModelMap model) {
		String staffId = req.getParameter("staffId");
		String mess = "Delete error occur!";
		if (staffService.deleteStaff(staffId)) {
			mess = "Delete success!";
		}
		model.addAttribute("mess", mess);
		return "redirect:index.htm";
	}

	@RequestMapping(value = "testAJAX", method = RequestMethod.GET)
	public @ResponseBody String displayAjax(HttpServletRequest request) {

		String staffId = request.getParameter("name");
		ObjectMapper objectMapper = new ObjectMapper();
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		objectMapper.setDateFormat(df);

		Staff result = staffService.getInfoStaff(staffId);
		String ajax = "";
		try {
			ajax = objectMapper.writeValueAsString(result);

		} catch (JsonProcessingException e) {
			// e.printStackTrace();
			System.out.println("loi exception: " + e);
		}

		return ajax;
	}
}
