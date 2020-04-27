package com.xpeter.service;

import java.util.List;

import com.xpeter.model.Staff;
import com.xpeter.model.ThanhTich;

public interface StaffService {
	public List<Staff> getListStaff(String name);

	public Staff getInfoStaff(String staffId);

	public boolean insertStaff(Staff staff);

	public boolean updateStaff(Staff staff);

	public boolean deleteStaff(String staffId);

	public List<ThanhTich> getListStatistic(String staffId);
	
	public List<String> getTop10Statistic();
}
