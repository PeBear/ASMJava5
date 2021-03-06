package com.xpeter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xpeter.dao.StaffDAO;
import com.xpeter.model.Staff;
import com.xpeter.model.ThanhTich;

@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
	StaffDAO staffDAO;

	public void setStaffDAO(StaffDAO staffDAO) {
		this.staffDAO = staffDAO;
	}

	@Override
	@Transactional
	public List<Staff> getListStaff(String name) {
		return staffDAO.getListStaff(name);
	}

	@Override
	@Transactional
	public Staff getInfoStaff(String staffId) {
		return staffDAO.getInfoStaff(staffId);
	}

	@Override
	@Transactional
	public boolean insertStaff(Staff staff) {
		return staffDAO.insertStaff(staff);
	}

	@Override
	@Transactional
	public boolean updateStaff(Staff staff) {
		return staffDAO.updateStaff(staff);
	}

	@Override
	@Transactional
	public boolean deleteStaff(String staffId) {
		return staffDAO.deleteStaff(staffId);
	}

	@Override
	@Transactional
	public List<ThanhTich> getListStatistic(String staffId) {
		return staffDAO.getListStatistic(staffId);
	}

	@Override
	@Transactional
	public List<String> getTop10Statistic() {
		return staffDAO.getTop10Statistic();
	}
}
