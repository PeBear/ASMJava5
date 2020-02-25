package com.xpeter.model;

public class ThanhTich {
	private String name;
	private int achievement;
	private int discipline;

	public ThanhTich() {
		super();
	}

	public ThanhTich(String name, int achievement, int discipline) {
		super();
		this.name = name;
		this.achievement = achievement;
		this.discipline = discipline;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAchievement() {
		return achievement;
	}

	public void setAchievement(int achievement) {
		this.achievement = achievement;
	}

	public int getDiscipline() {
		return discipline;
	}

	public void setDiscipline(int discipline) {
		this.discipline = discipline;
	}

	@Override
	public String toString() {
		return "ThanhTich [name=" + name + ", achievement=" + achievement + ", discipline=" + discipline + "]";
	}

}
