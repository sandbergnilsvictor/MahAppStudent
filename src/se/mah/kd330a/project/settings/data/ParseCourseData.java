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
		CourseItem item3 = new CourseItem();
		
		item1.setDisplayName("Mobile Application Developement");
		item1.setKronoxId("1");	
		item1.setItslId("0");
		
		item2.setDisplayName("Introduktion till Systemutveckling");
		item2.setKronoxId("2");
		item2.setItslId("0");
		
		item3.setDisplayName("Kaffekoking for Dummies");
		item3.setKronoxId("3");
		item3.setItslId("0");
		
		myCourses.add(item1);
		myCourses.add(item2);
		myCourses.add(item3);	
	}
	
	public ArrayList<CourseItem> getCourses() {
		return myCourses;
	}

}
