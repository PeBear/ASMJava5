package com.xpeter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xpeter.dao.RecordDAO;
import com.xpeter.model.Record;

@Service
@Transactional
public class RecordServiceImpl implements RecordService {
	@Autowired
	RecordDAO recordDAO;

	public void setRecordDAO(RecordDAO recordDAO) {
		this.recordDAO = recordDAO;
	}

	@Override
	@Transactional
	public List<Record> getListRecord() {
		return recordDAO.getListRecord();
	}

	@Override
	@Transactional
	public List<Record> getListRecordByType(boolean type) {
		return recordDAO.getListRecordByType(type);
	}

	@Override
	@Transactional
	public Record getInfoRecord(int recordId) {
		return recordDAO.getInfoRecord(recordId);
	}

	@Override
	public boolean insertRecord(Record record) {
		return recordDAO.insertRecord(record);
	}

	@Override
	public boolean updateRecord(Record record) {
		return recordDAO.updateRecord(record);
	}

	@Override
	public boolean deleteRecord(int recordId) {
		return recordDAO.deleteRecord(recordId);
	}

}
