package se.mah.kd330a.project.schedule.model;

import java.io.Serializable;
import java.util.ArrayList;


public class ScheduleWeek implements Serializable{

	private static final long serialVersionUID = 1L;
	private ArrayList<ScheduleItem> scheduleItems;
	private int weekNumber;
	
	public ScheduleWeek(int weekNumber, ArrayList<ScheduleItem> scheduleItems) {
		this.setWeekNumber(weekNumber);
		this.setScheduleItems(scheduleItems);
	}
	
	public ScheduleWeek() {
		
	}


	public int getWeekNumber() {
		return weekNumber;
	}


	public void setWeekNumber(int weekNumber) {
		this.weekNumber = weekNumber;
	}


	public ArrayList<ScheduleItem> getScheduleItems() {
		return scheduleItems;
	}


	public void setScheduleItems(ArrayList<ScheduleItem> scheduleItems) {
		this.scheduleItems = scheduleItems;
	}
	
	
	

}
