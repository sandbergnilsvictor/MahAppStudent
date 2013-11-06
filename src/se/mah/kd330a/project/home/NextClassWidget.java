package se.mah.kd330a.project.home;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.ContactsContract.Contacts.Data;
import android.util.Log;

import se.mah.kd330a.project.adladok.model.Me;
import se.mah.kd330a.project.schedule.data.KronoxCalendar;
import se.mah.kd330a.project.schedule.model.ScheduleItem;

import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.component.VEvent;

public class NextClassWidget {
	ArrayList<ScheduleItem> items;

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

	public boolean getTodaysClasses() {
		if (!Me.getFirstName().isEmpty()) {
			listEvents();
			if (setData()) {
				return true;
			}
			return false;
		} else {
			return false;
		}

	}

	private void listEvents() {
		items = new ArrayList<ScheduleItem>();
		Collection<?> kronox_events = KronoxCalendar.todaysEvents();

		if (kronox_events != null) {

			items.clear();
			for (Iterator<?> i = kronox_events.iterator(); i.hasNext();) {
				Component c = (Component) i.next();
				if (c instanceof VEvent) {
					items.add(new ScheduleItem((VEvent) c));
				}
			}
		}

	}

	private boolean setData() {
		if (!items.isEmpty()) {

			setCourseName(items.get(0).getCourseName());
			setLocation(items.get(0).getRoomCode());
			setStartTime(items.get(0).getStartTime());
			setEndTime(items.get(0).getEndTime());
			setDate(items.get(0).getDateAndTime2());
			return true;
		} else {
			Log.e(getClass().toString(), "item list is empty");
			return false;
		}
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
