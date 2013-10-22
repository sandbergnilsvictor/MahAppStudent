package se.mah.kd330a.project.home;

public class NextClassWidget {
	
	private String courseName;
	private String courseId;
	private String location;
	private String date;
	private String startTime;
	private String endTime;
	
	public NextClassWidget() {
		
	}
	
	public void generateSampleCourse() {
		this.courseName = "Att utvecklas som hand- ledare inom odontologisk utbildning";
		this.courseId = "KD330a";
		this.location = "B310, K2B212, K203A4...";
		this.startTime = "10:15";
		this.endTime = "12:00";
		this.date = "Today";
		
				
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
