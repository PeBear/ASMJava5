package com.xpeter.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@SuppressWarnings("serial")
@Entity
public class Staff implements Serializable {
	@Id
	@Column(name = "Id")
	private String staffId;
	private String name;
	private boolean gender;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date birthday;
	private String photo;
	private String email;
	private String phone;
	private float salary;
	private String notes;
	@ManyToOne
	@JoinColumn(name = "departId")
	private Depart depart;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "staff")
	private Set<Record> records = new HashSet<>();

	public Staff() {
		super();
	}

	public Staff(String staffId, String name, boolean gender, Date birthday, String photo, String email, String phone,
			float salary, String notes) {
		super();
		this.staffId = staffId;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.photo = photo;
		this.email = email;
		this.phone = phone;
		this.salary = salary;
		this.notes = notes;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

//	public Depart getDepart() {
//		return depart;
//	}
//
//	public void setDepart(Depart depart) {
//		this.depart = depart;
//	}
//
//	public Set<Record> getRecords() {
//		return records;
//	}
//
//	public void setRecords(Set<Record> records) {
//		this.records = records;
//	}

	@Override
	public String toString() {
		return "Staff [staffId=" + staffId + ", name=" + name + ", gender=" + gender + ", birthday=" + birthday
				+ ", photo=" + photo + ", email=" + email + ", phone=" + phone + ", salary=" + salary + ", notes="
				+ notes + "]";
	}
}
