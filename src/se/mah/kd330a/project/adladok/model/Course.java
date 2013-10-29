package se.mah.kd330a.project.adladok.model;

public class Course {
 private String displayname;
 private String courseID; //the long one
 public Course(String displayName, String courseID) {
	this.displayname = displayName;
	this.courseID = courseID;
}
	 public String getDisplayname() {
		return displayname;
	}
	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	public String getCourseCode() {
		return courseID.substring(0, 4);
	}

}
