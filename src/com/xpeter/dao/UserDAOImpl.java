package com.xpeter.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xpeter.model.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<User> getListUser(String fullname) {
		Session session = sessionFactory.openSession();
		String hql = "FROM User";
		if (fullname != null) {
			hql += " WHERE Fullname like " + fullname;
		}
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<User> list = query.list();
		session.close();
		return list;
	}

	@Override
	public User getInfoUser(String username) {
		Session session = sessionFactory.openSession();
		User user = (User) session.get(User.class, username);
		session.close();
		return user;
	}

	@Override
	public boolean insertUser(User user) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(user);
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
	public boolean updateUser(User user) {
		if (getInfoUser(user.getUsername()) == null) {
			return false;
		}
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.update(user);
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
	public boolean deleteUser(String username) {
		User user = getInfoUser(username);
		if (user == null) {
			return false;
		}
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.delete(user);
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
