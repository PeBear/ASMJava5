package com.xpeter.model;

public class ThanhTich {
	private String name;
	private int achievement;
	private int discipline;
	private int result;

	public ThanhTich() {
		super();
	}

	public ThanhTich(String name, int achievement, int discipline, int result) {
		super();
		this.name = name;
		this.achievement = achievement;
		this.discipline = discipline;
		this.result = result;
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

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "ThanhTich [name=" + name + ", achievement=" + achievement + ", discipline=" + discipline + ", result="
				+ result + "]";
	}

}
