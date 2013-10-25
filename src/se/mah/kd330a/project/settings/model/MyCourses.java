package se.mah.kd330a.project.settings.model;

import java.io.Serializable;
import java.util.ArrayList;

import se.mah.kd330a.project.schedule.data.ParseData;
import se.mah.kd330a.project.settings.data.ParseCourseData;

public class MyCourses implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private ArrayList<CourseItem> myCourses = new ArrayList<CourseItem>();
	
	public MyCourses() {
		
	}
	
	/*
	 * If returns false courseItem is null
	 */
	
	public boolean addClassItemToMyCourses(CourseItem courseItem) {
		if(courseItem == null) {
			return false;
		}
		myCourses.add(courseItem);
		return true;
		
	}
	
	
	
	public ArrayList<CourseItem> getMyCourses() {
		ParseCourseData data = new ParseCourseData();
		data.generateCourseSampleData();
		myCourses = data.getCourses();
		return myCourses;
	}
	
	
	
	
	
	

}
