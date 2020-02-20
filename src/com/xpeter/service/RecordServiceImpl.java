package com.xpeter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xpeter.dao.RecordDAO;
import com.xpeter.model.Record;

@Service
public class RecordServiceImpl implements RecordService {
	@Autowired
	RecordDAO recordDAO;

	public void setRecordDAO(RecordDAO recordDAO) {
		this.recordDAO = recordDAO;
	}

	@Override
	public List<Record> getListRecord() {
		return recordDAO.getListRecord();
	}

	@Override
	public List<Record> getListRecordByType(boolean type) {
		return recordDAO.getListRecordByType(type);
	}

	@Override
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
