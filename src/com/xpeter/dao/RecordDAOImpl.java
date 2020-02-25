package com.xpeter.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xpeter.model.Record;

@Repository
public class RecordDAOImpl implements RecordDAO {

	@Autowired
	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Record> getListRecord() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Record";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Record> list = query.list();
		return list;
	}

	@Override
	public List<Record> getListRecordByType(boolean type) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Record WHERE Type = :type");
		query.setParameter("type", type);
		@SuppressWarnings("unchecked")
		List<Record> list = query.list();
		return list;
	}

	@Override
	public Record getInfoRecord(int recordId) {
		Session session = sessionFactory.getCurrentSession();
		Record record = (Record) session.get(Record.class, recordId);
		return record;
	}

	@Override
	public boolean insertRecord(Record record) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(record);
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
	public boolean updateRecord(Record record) {
		if (getInfoRecord(record.getRecordId()) == null) {
			return false;
		}
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.update(record);
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
	public boolean deleteRecord(int recordId) {
		Record record = getInfoRecord(recordId);
		if (record == null) {
			return false;
		}
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.delete(record);
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
