package com.xpeter.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class Depart implements Serializable {
	@Id
	@Column(name = "Id")
	private String departId;
	private String name;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "depart")
	private Set<Staff> staffs = new HashSet<>();

	public Depart() {
		super();
	}

	public Depart(String departId, String name) {
		super();
		this.departId = departId;
		this.name = name;
	}

	public String getDepartId() {
		return departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public Set<Staff> getStaffs() {
//		return staffs;
//	}
//
//	public void setStaffs(Set<Staff> staffs) {
//		this.staffs = staffs;
//	}
	
	@Override
	public String toString() {
		return "Depart [departId=" + departId + ", name=" + name + "]";
	}

}
