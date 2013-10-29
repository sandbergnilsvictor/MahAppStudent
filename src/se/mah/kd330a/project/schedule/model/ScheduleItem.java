package se.mah.kd330a.project.schedule.model;

import java.io.Serializable;

public class ScheduleItem implements Serializable{

	private static final long serialVersionUID = 1L;
	private String date;
	private String startTime;
	private String endTime;
	private String location;
	private String courseName;
	private String lector;
	private String addtionalInformation;
	
	public ScheduleItem(String startTime, String endTime, String location, String courseName, String addtionalInformation, String date) {
		this.setStartTime(startTime);
		this.setEndTime(endTime);
		this.setLocation(location);
		this.setCourseName(courseName);
		this.setAddtionalInformation(addtionalInformation);
		this.setDate(date);
		
		
	}
	
	public ScheduleItem() {
		
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getLector() {
		return lector;
	}

	public void setLector(String lector) {
		this.lector = lector;
	}

	public String getAddtionalInformation() {
		return addtionalInformation;
	}

	public void setAddtionalInformation(String addtionalInformation) {
		this.addtionalInformation = addtionalInformation;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}



	
	

}
