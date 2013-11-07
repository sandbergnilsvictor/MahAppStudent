package se.mah.kd330a.project.schedule.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.component.VEvent;

import android.util.Log;

import se.mah.kd330a.project.adladok.model.Me;
import se.mah.kd330a.project.schedule.model.*;

public class ParseData {

	ArrayList<ScheduleWeek> scheduleWeeks;

	public ParseData() {
		scheduleWeeks = new ArrayList<ScheduleWeek>();

	}
	/*
	public ArrayList<ScheduleWeek> getParsedDataFromKronoxByWeek() {
		for (int i = 43; i < 46; i++) {
			ScheduleWeek scheduleWeek = new ScheduleWeek();
			scheduleWeek.setWeekNumber(i);
			Log.i("getParsedDataFromKronoxByWeek()", Integer.toString(i));
			ArrayList<ScheduleItemTest> sampleWeekList = new ArrayList<ScheduleItemTest>();
			for (int j = 0; j < 3; j++) {
				ScheduleItemTest sampleClass = new ScheduleItemTest();
				sampleClass.setStartTime("1" + j + ":00");
				sampleClass.setEndTime("1" + (j + 1) + ":00");
				sampleClass
						.setCourseName("Course with a long title and too much information "
								+ i + " " + j);
				sampleClass
						.setAddtionalInformation("The class will be hold as a "
								+ "seminar so please come well prepared to class");
				sampleClass.setLocation("K2C12" + j);
				sampleClass.setLector("Lector " + i + " " + j);
				int date = (i - 22) + ((i - 43) * 7);
				if (j == 0 || j == 1) {
					sampleClass.setDate("Monday " + date + "/10/2013");
				} else {
					sampleClass.setDate("Wednesday " + (date + 2) + "/10/2013");
				}
				sampleWeekList.add(sampleClass);
				Log.i("getParsedDataFromKronoxByWeek()",
						sampleClass.getCourseName());
			}
			// scheduleWeek.setScheduleItems(sampleWeekList);
			scheduleWeeks.add(scheduleWeek);
		}
		return scheduleWeeks;
	}*/


	public ArrayList<ScheduleWeek> getParsedDataFromKronoxByWeekNew(
			int numberOfWeeks) {
		if (Me.getUserID() != null) {
			for (int i = 0; i < numberOfWeeks; i++) {
				scheduleWeeks.add(getScheduleWeek(i));
			}
			return scheduleWeeks;
		}
		return null;

	}

	private ScheduleWeek getScheduleWeek(int weekFromThisWeek) {
		ScheduleWeek scheduleWeek = new ScheduleWeek();
		scheduleWeek.setWeekNumber(54+weekFromThisWeek);
		ArrayList<ScheduleItem> thisWeekList = new ArrayList<ScheduleItem>();
		Collection<?> kronox_events = KronoxCalendar
				.getWeeksEventsFromThisWeek(weekFromThisWeek);
		Log.i("Schedule", "How many did we find?: " + kronox_events.size());
		// Here we only take seven days from today this should be calculated
		// from the monday this should be done in
		// KronoxCalendar.sevenDaysEvents()
		for (Iterator<?> i = kronox_events.iterator(); i.hasNext();) {
			Component c = (Component) i.next();
			if (c instanceof VEvent) {
				ScheduleItem s = new ScheduleItem((VEvent) c);
				// New day????
				/*
				 * if (!s.getWeekDay().equals(lastWeekDay)){ //Put in extra
				 * element in list that should be an divider with day and so on
				 * ScheduleItemTest s2 = new ScheduleItemTest((VEvent)c);
				 * s2.setDividerElement(); items.add(s2); lastWeekDay =
				 * s2.getWeekDay(); //Set the new day.... }
				 */

				thisWeekList.add(s);
			}
		}
		scheduleWeek.setScheduleItems(thisWeekList);
		return scheduleWeek;
	}

}
