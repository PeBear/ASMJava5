package com.xpeter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xpeter.dao.DepartDAO;
import com.xpeter.model.Depart;
import com.xpeter.model.ThanhTich;

@Service
public class DepartServiceImpl implements DepartService {

	@Autowired
	DepartDAO departDAO;

	public void setDepartDAO(DepartDAO departDAO) {
		this.departDAO = departDAO;
	}

	@Override
	@Transactional
	public List<Depart> getListDepart(String name) {
		return departDAO.getListDepart(name);
	}

	@Override
	@Transactional
	public Depart getInfoDepart(String departId) {
		return departDAO.getInfoDepart(departId);
	}

	@Override
	@Transactional
	public boolean insertDepart(Depart depart) {
		return departDAO.insertDepart(depart);
	}

	@Override
	@Transactional
	public boolean updateDepart(Depart depart) {
		return departDAO.updateDepart(depart);
	}

	@Override
	@Transactional
	public boolean deleteDepart(String departId) {
		return departDAO.deleteDepart(departId);
	}

	@Override
	@Transactional
	public List<ThanhTich> getListStatistic() {
		return departDAO.getListStatistic();
	}

}
