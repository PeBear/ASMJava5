package com.xpeter.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
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
		Session session = sessionFactory.openSession();
		Staff staff = (Staff) session.get(Staff.class, staffId);
		if (staff == null) {
			return false;
		}

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

	@SuppressWarnings("unchecked")
	@Override
	public List<ThanhTich> getListStatistic(String staffId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "CALL spThongKeThanhTich(:staffId)";
		Query query = session.createSQLQuery(hql);
		query.setParameter("staffId", staffId);
		List<Object[]> list = query.list();
		List<ThanhTich> listStatistic = new ArrayList<ThanhTich>();
		// Dua vao cot cua sp
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				String name = String.valueOf((list.get(i)[0]));
				int achievement = Integer.parseInt((list.get(i)[1]).toString());
				int discipline = Integer.parseInt((list.get(i)[2]).toString());
				int result = Integer.parseInt((list.get(i)[3]).toString());
				ThanhTich thanhTich = new ThanhTich(name, achievement, discipline, result);
				listStatistic.add(thanhTich);
			}
		}
		return listStatistic;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getTop10Statistic() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "CALL spGetTop10Staff()";
		Query query = session.createSQLQuery(hql);
		List<Object[]> list = query.list();
		List<String> listStaffId = new ArrayList<>();
		// Dua vao cot cua sp
		if (list.size() > 0) {
			// get Top 10 cause spThongKeThanhTich has order desc
			if (list.size() >= 10) {
				for (int i = 0; i < 10; i++) {
					String staffId = String.valueOf((list.get(i)[0]));
					listStaffId.add(staffId);
				}
			}
			for (int i = 0; i < list.size(); i++) {
				String staffId = String.valueOf((list.get(i)[0]));
				listStaffId.add(staffId);
			}
		}
		return listStaffId;
	}
}
