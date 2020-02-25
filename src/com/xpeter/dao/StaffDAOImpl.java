package com.xpeter.dao;

import java.util.List;

import javax.persistence.StoredProcedureQuery;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xpeter.model.Staff;
import com.xpeter.model.ThanhTich;

@Repository
public class StaffDAOImpl implements StaffDAO {

	@Autowired
	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Staff> getListStaff(String name) {
		Session session = sessionFactory.openSession();
		String hql = "FROM Staff";
		if (name != null) {
			hql += " WHERE Name like " + name;
		}
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Staff> list = query.list();
		session.close();
		return list;
	}

	@Override
	public Staff getInfoStaff(String staffId) {
		Session session = sessionFactory.getCurrentSession();
		Staff staff = (Staff) session.get(Staff.class, staffId);
		return staff;
	}

	@Override
	public boolean insertStaff(Staff staff) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(staff);
			session.getTransaction().commit();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public boolean updateStaff(Staff staff) {
		if (getInfoStaff(staff.getStaffId()) == null) {
			return false;
		}
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.update(staff);
			session.getTransaction().commit();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public boolean deleteStaff(String staffId) {
		Staff staff = getInfoStaff(staffId);
		if (staff == null) {
			return false;
		}
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.delete(staff);
			session.getTransaction().commit();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return false;
	}

	public void getThanhTich(String staffId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "CALL spThongKeThanhTich(:staffId)";
		Query query = session.createSQLQuery(hql);
		query.setParameter("staffId", staffId);
		List<Object[]> list = query.list();
		ThanhTich thanhTich = new ThanhTich();
		//Dua vao cot cua sp
		if (list.size() > 0) {
			String name = String.valueOf((list.get(0)[0]));
			int achievement = Integer.parseInt((list.get(0)[1]).toString());
			int discipline = Integer.parseInt((list.get(0)[2]).toString());
			thanhTich.setName(name);
			thanhTich.setAchievement(achievement);
			thanhTich.setDiscipline(discipline);
		}
		System.out.println(thanhTich);
	}
}
