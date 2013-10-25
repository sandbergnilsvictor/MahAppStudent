package se.mah.kd330a.project.settings.data;

import java.util.ArrayList;

import se.mah.kd330a.project.settings.model.CourseItem;
import se.mah.kd330a.project.settings.model.MyCourses;


public class ParseCourseData {
	
	private ArrayList<CourseItem> myCourses; 
	
	
	public ParseCourseData() {
		myCourses = new ArrayList<CourseItem>();
		
	}
	
	public void generateCourseSampleData() {
		CourseItem item1 = new CourseItem();
		CourseItem item2 = new CourseItem();
		
		
		item1.setDisplayName("Mobile Application Developement");
		item1.setKronoxId("KD330A-20132-62311");	
		item1.setItslId("0");
		
		item2.setDisplayName("Beslutstšdsystem");
		item2.setKronoxId("DA246A-20132-69045");
		item2.setItslId("0");
		
		myCourses.add(item1);
		myCourses.add(item2);
	
	}
	
	public ArrayList<CourseItem> getCourses() {
		return myCourses;
	}

}
