package com.xpeter.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xpeter.model.Record;
import com.xpeter.model.Staff;
import com.xpeter.service.RecordService;
import com.xpeter.service.StaffService;

@Controller
@RequestMapping(value = "recordManage")
public class RecordController {
	private boolean recordType;

	@Autowired
	RecordService recordService;
	@Autowired
	StaffService staffService;

	public void setRecordService(RecordService recordService) {
		this.recordService = recordService;
	}

	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}

	@RequestMapping()
	public String display(ModelMap model, @RequestParam String type) {
		recordType = false;
		if (type.equalsIgnoreCase("true")) {
			recordType = true;
		}
		List<Record> list = recordService.getListRecordByType(recordType);
		model.addAttribute("ListRecord", list);
		model.addAttribute("RecordType", recordType);
		model.addAttribute("Record", new Record());
		return "recordManage";
	}

	@RequestMapping(params = "insert")
	public String insertRecord(ModelMap model, HttpServletRequest request) {
		String mess = "Insert error occur!";
		Record record = getModel(request);
		if (recordService.insertRecord(record)) {
			mess = "Insert success!";
		}
		model.addAttribute("mess", mess);
		return "redirect:recordManage.htm?type=" + recordType;
	}

	@RequestMapping(params = "update")
	public String updateRecord(ModelMap model, HttpServletRequest request) {
		String mess = "Update error occur!";
		Record record = getModel(request);
		if (recordService.updateRecord(record)) {
			mess = "Update success!";
		}
		model.addAttribute("mess", mess);
		return "redirect:recordManage.htm?type=" + recordType;
	}

	@RequestMapping(params = "delete")
	public String deleteRecord(ModelMap model, HttpServletRequest request) {
		int recordId = Integer.parseInt(request.getParameter("recordId"));
		String mess = "Delete error occur!";
		if (recordService.deleteRecord(recordId)) {
			mess = "Delete success!";
		}
		model.addAttribute("mess", mess);
		return "redirect:recordManage.htm?type=" + recordType;
	}

	@RequestMapping(value = "ajaxRecord", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> displayAjax(HttpServletRequest request) {
		int recordId = Integer.parseInt(request.getParameter("recordId"));
		ObjectMapper objectMapper = new ObjectMapper();
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		objectMapper.setDateFormat(df);
		Record result = recordService.getInfoRecord(recordId);
		String ajax = "";
		try {
			System.out.println("debug: " + result);
			System.out.println("debug2: " + result.getStaff());
			Staff staff = result.getStaff();
			staff.setDepartId(null);
			staff.setRecordCollection(null);
			result.setStaff(staff);
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

	public Record getModel(HttpServletRequest request) {
		String recordIdTemp = request.getParameter("recordId");
		String staffId = request.getParameter("staffId");
		Staff staff = staffService.getInfoStaff(staffId);
		String date = request.getParameter("date");
		Date date1 = new Date();
		String reason = request.getParameter("reason");
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		Record record;
		if (!recordIdTemp.equalsIgnoreCase("Auto identity")) {
			int recordId = Integer.parseInt(recordIdTemp);
			record = new Record(recordId, recordType, reason, date1, staff);
		} else {
			record = new Record(recordType, reason, date1, staff);
		}
		return record;
	}
}
