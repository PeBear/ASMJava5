package com.xpeter.dao;

import java.util.List;

import com.xpeter.model.Depart;
import com.xpeter.model.ThanhTich;

public interface DepartDAO {
	public List<Depart> getListDepart(String name);

	public Depart getInfoDepart(String departId);

	public boolean insertDepart(Depart depart);

	public boolean updateDepart(Depart depart);

	public boolean deleteDepart(String departId);

	public List<ThanhTich> getListStatistic();
}
