package com.xpeter.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xpeter.model.Staff;

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
		Session session = sessionFactory.openSession();
		Staff staff = (Staff) session.get(Staff.class, staffId);
		session.close();
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

}
