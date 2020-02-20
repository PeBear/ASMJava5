package com.xpeter.dao;

import java.util.List;

import com.xpeter.model.Staff;

public interface StaffDAO {
	public List<Staff> getListStaff(String name);

	public Staff getInfoStaff(String staffId);

	public boolean insertStaff(Staff staff);

	public boolean updateStaff(Staff staff);

	public boolean deleteStaff(String staffId);
}
