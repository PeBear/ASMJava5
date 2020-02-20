package com.xpeter.service;

import java.util.List;

import com.xpeter.model.Record;

public interface RecordService {
	public List<Record> getListRecord();

	public List<Record> getListRecordByType(boolean type);

	public Record getInfoRecord(int recordId);

	public boolean insertRecord(Record record);

	public boolean updateRecord(Record record);

	public boolean deleteRecord(int recordId);
}
