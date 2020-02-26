package com.xpeter.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xpeter.model.Depart;
import com.xpeter.model.ThanhTich;

@Repository
public class DepartDAOImpl implements DepartDAO {

	@Autowired
	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Depart> getListDepart(String name) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Depart";
		if (name != null) {
			hql += " WHERE Name like " + name;
		}
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Depart> list = query.list();
		return list;
	}

	@Override
	public Depart getInfoDepart(String departId) {
		Session session = sessionFactory.getCurrentSession();
		Depart depart = (Depart) session.get(Depart.class, departId);
		return depart;
	}

	@Override
	public boolean insertDepart(Depart depart) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(depart);
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
	public boolean updateDepart(Depart depart) {
		if (getInfoDepart(depart.getDepartId()) == null) {
			return false;
		}
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.update(depart);
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
	public boolean deleteDepart(String departId) {
		Depart depart = getInfoDepart(departId);
		if (depart == null) {
			return false;
		}
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.delete(depart);
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
	public List<ThanhTich> getListStatistic() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "CALL spThongKeThanhTichPhongBan()";
		Query query = session.createSQLQuery(hql);
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
}
