package com.xpeter.controller;

import java.util.List;

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
import com.xpeter.model.Depart;
import com.xpeter.service.DepartService;

@Controller
@RequestMapping(value = "departManage")
public class DepartController {

	@Autowired
	DepartService departService;

	public void setDepartService(DepartService departService) {
		this.departService = departService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String display(ModelMap model) {
		List<Depart> list = departService.getListDepart(null);
		model.addAttribute("ListDepart", list);
		model.addAttribute("Depart", new Depart());
		return "departManage";
	}

	@RequestMapping(params = "insert")
	public String insertDepart(ModelMap model, @ModelAttribute("Depart") Depart depart) {
		String mess = "Insert error occur!";
		if (departService.insertDepart(depart)) {
			mess = "Insert success!";
		}
		model.addAttribute("mess", mess);
		return "redirect:departManage.htm";
	}

	@RequestMapping(params = "update")
	public String updateDepart(ModelMap model, @ModelAttribute("Depart") Depart depart) {
		String mess = "Update error occur!";
		if (departService.updateDepart(depart)) {
			mess = "Update success!";
		}
		model.addAttribute("mess", mess);
		return "redirect:departManage.htm";
	}

	@RequestMapping(params = "delete")
	public String deleteDepart(ModelMap model, HttpServletRequest request) {
		String departId = request.getParameter("departId");
		String mess = "Delete error occur!";
		if (departService.deleteDepart(departId)) {
			mess = "Delete success!";
		}
		model.addAttribute("mess", mess);
		return "redirect:departManage.htm";
	}

	@RequestMapping(value = "ajaxDepart", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> displayAjax(HttpServletRequest request) {
		String departId = request.getParameter("departId");
		ObjectMapper objectMapper = new ObjectMapper();
		Depart result = departService.getInfoDepart(departId);
		result.setStaffCollection(null);
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
