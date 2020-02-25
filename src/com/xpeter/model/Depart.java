package com.xpeter.model;

import java.io.Serializable;
import java.util.Collection;

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
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "departId")
	private Collection<Staff> staffCollection;

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
	
	public Collection<Staff> getStaffCollection() {
		return staffCollection;
	}

	public void setStaffCollection(Collection<Staff> staffCollection) {
		this.staffCollection = staffCollection;
	}

	@Override
	public String toString() {
		return "Depart [departId=" + departId + ", name=" + name + "]";
	}

}
