package com.julyday.test_mybatis.entity;

import java.util.List;

public class Grade {
	private int id;
	private String gname;
	private String gdesc;
	private List<Student> students;

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getGdesc() {
		return gdesc;
	}

	public void setGdesc(String gdesc) {
		this.gdesc = gdesc;
	}

	public Grade() {
		
	}

	public Grade(String gname, String gdesc) {
		this.gname = gname;
		this.gdesc = gdesc;
	}

	@Override
	public String toString() {
		return "Grade [id=" + id + ", gname=" + gname + ", gdesc=" + gdesc
				+ "]";
	}
}
