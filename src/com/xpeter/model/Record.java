package com.xpeter.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@SuppressWarnings("serial")
@Entity
public class Record implements Serializable {
	@Id
	@GeneratedValue
	@Column(name = "Id")
	private int recordId;
	private boolean type;
	private String reason;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date date;
	@ManyToOne
	@JoinColumn(name = "staffId")
	private Staff staff;

	public Record() {
		super();
	}

	public Record(int recordId, boolean type, String reason, Date date) {
		super();
		this.recordId = recordId;
		this.type = type;
		this.reason = reason;
		this.date = date;
	}

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

//	public Staff getStaff() {
//		return staff;
//	}
//
//	public void setStaff(Staff staff) {
//		this.staff = staff;
//	}
	
	@Override
	public String toString() {
		return "Record [recordId=" + recordId + ", type=" + type + ", reason=" + reason + ", date=" + date + "]";
	}

}
