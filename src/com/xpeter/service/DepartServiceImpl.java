package com.xpeter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xpeter.dao.DepartDAO;
import com.xpeter.model.Depart;

@Service
public class DepartServiceImpl implements DepartService {

	@Autowired
	DepartDAO departDAO;

	public void setDepartDAO(DepartDAO departDAO) {
		this.departDAO = departDAO;
	}

	@Override
	public List<Depart> getListDepart(String name) {
		return departDAO.getListDepart(name);
	}

	@Override
	public Depart getInfoDepart(String departId) {
		return departDAO.getInfoDepart(departId);
	}

	@Override
	public boolean insertDepart(Depart depart) {
		return departDAO.insertDepart(depart);
	}

	@Override
	public boolean updateDepart(Depart depart) {
		return departDAO.updateDepart(depart);
	}

	@Override
	public boolean deleteDepart(String departId) {
		return departDAO.deleteDepart(departId);
	}

}
