package com.xpeter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xpeter.dao.StaffDAO;
import com.xpeter.model.Staff;

@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
	StaffDAO staffDAO;

	public void setStaffDAO(StaffDAO staffDAO) {
		this.staffDAO = staffDAO;
	}

	@Override
	public List<Staff> getListStaff(String name) {
		return staffDAO.getListStaff(name);
	}

	@Override
	public Staff getInfoStaff(String staffId) {
		return staffDAO.getInfoStaff(staffId);
	}

	@Override
	public boolean insertStaff(Staff staff) {
		return staffDAO.insertStaff(staff);
	}

	@Override
	public boolean updateStaff(Staff staff) {
		return staffDAO.updateStaff(staff);
	}

	@Override
	public boolean deleteStaff(String staffId) {
		return staffDAO.deleteStaff(staffId);
	}

}
