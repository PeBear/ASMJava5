package com.xpeter.service;

import java.util.List;

import com.xpeter.model.Depart;

public interface DepartService {
	public List<Depart> getListDepart(String name);

	public Depart getInfoDepart(String departId);

	public boolean insertDepart(Depart depart);

	public boolean updateDepart(Depart depart);

	public boolean deleteDepart(String departId);
}
