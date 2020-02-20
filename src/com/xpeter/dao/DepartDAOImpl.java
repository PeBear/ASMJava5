package com.xpeter.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xpeter.model.Depart;

@Repository
public class DepartDAOImpl implements DepartDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Depart> getListDepart(String name) {
		Session session = sessionFactory.openSession();
		String hql = "FROM Depart";
		if (name != null) {
			hql += " WHERE Name like " + name;
		}
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Depart> list = query.list();
		session.close();
		return list;
	}

	@Override
	public Depart getInfoDepart(String departId) {
		Session session = sessionFactory.openSession();
		Depart depart = (Depart) session.get(Depart.class, departId);
		session.close();
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

}
