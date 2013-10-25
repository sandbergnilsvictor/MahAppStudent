package se.mah.kd330a.project.itsl;

import android.graphics.Color;

public class Course {
	
	String courseCode;
	int color;
	
	public Course(String courseCode, int color)
	{
		this.courseCode = courseCode;
		this.color = color;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

}
