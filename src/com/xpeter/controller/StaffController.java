package com.xpeter.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xpeter.model.Depart;
import com.xpeter.model.Staff;
import com.xpeter.service.DepartService;
import com.xpeter.service.StaffService;

@Controller
@RequestMapping(value = "staffManage")
public class StaffController {
	@Autowired
	StaffService staffService;
	@Autowired
	DepartService departService;
	@Autowired
	ServletContext context;

	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}

	public void setDepartService(DepartService departService) {
		this.departService = departService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String display(ModelMap model) {
		List<Staff> list = staffService.getListStaff(null);
		model.addAttribute("ListStaff", list);
		model.addAttribute("ListDepart", departService.getListDepart(null));
		return "staffManage";
	}

	@RequestMapping(params = "insert", method = RequestMethod.POST)
	public String insertStudent(HttpServletRequest request, ModelMap model,
			@RequestParam("filePhoto") MultipartFile photo) {
		String mess = "Insert error occur!";
		Staff staff = getModel(request);
		if (photo.isEmpty()) {
			staff.setPhoto("unknow.jpeg");
		} else {
			String path = "/home/xpeter/eclipse-workspace/Java5_Database/WebContent/resources/images/staff/"
					+ photo.getOriginalFilename();
			String path1 = context.getRealPath("resources/images/staff/") + photo.getOriginalFilename();
			try {
				//photo.transferTo(new File(path));
				photo.transferTo(new File(path1));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			staff.setPhoto(photo.getOriginalFilename());
		}

		if (staffService.insertStaff(staff)) {
			mess = "Insert success!";
		}
		model.addAttribute("mess", mess);
		return "redirect:staffManage.htm";
	}

	@RequestMapping(params = "update", method = RequestMethod.POST)
	public String updateStudent(HttpServletRequest request, ModelMap model,
			@RequestParam("filePhoto") MultipartFile photo) {
		String mess = "Update error occur!";
		Staff staff = getModel(request);
		if (photo.isEmpty()) {
			staff.setPhoto(staffService.getInfoStaff(staff.getStaffId()).getPhoto());
		} else {
			String path = "/home/xpeter/eclipse-workspace/Java5_Database/WebContent/resources/images/staff/"
					+ photo.getOriginalFilename();
			String path1 = context.getRealPath("resources/images/staff/") + photo.getOriginalFilename();
			try {
				//photo.transferTo(new File(path));
				photo.transferTo(new File(path1));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			staff.setPhoto(photo.getOriginalFilename());
		}
		if (staffService.updateStaff(staff)) {
			mess = "Update success!";
		}
		model.addAttribute("mess", mess);
		return "redirect:staffManage.htm";
	}

	@RequestMapping(params = "delete", method = RequestMethod.POST)
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
		// set null for escape StackOverFlow Exception while convert Object to JSON
		result.setRecordCollection(null);
		Depart depart = result.getDepartId();
		result.setDepartId(new Depart(depart.getDepartId(), depart.getName()));
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

	private Staff getModel(HttpServletRequest request) {
		String staffId = request.getParameter("staffId");
		String name = request.getParameter("name");
		boolean gender = false;
		if (request.getParameter("gender").equals("Male")) {
			gender = true;
		}
		String photo = "";
		String date = request.getParameter("birthday");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date birthday = new Date();
		try {
			birthday = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		;
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		float salary = Float.parseFloat(request.getParameter("salary"));
		String notes = request.getParameter("notes");
		Depart depart = departService.getInfoDepart(request.getParameter("departId"));
		Staff staff = new Staff(staffId, name, gender, birthday, photo, email, phone, salary, notes);
		staff.setDepartId(depart);
		return staff;
	}
}
