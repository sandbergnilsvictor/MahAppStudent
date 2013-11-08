package se.mah.kd330a.project.schedule.data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.component.VEvent;

import android.util.Log;

import se.mah.kd330a.project.adladok.model.Me;
import se.mah.kd330a.project.schedule.model.*;

public class ParseData {
	//
	ArrayList<ScheduleWeek> scheduleWeeks;

	public ParseData() {
		scheduleWeeks = new ArrayList<ScheduleWeek>();

	}

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
		int displayedWeek=0; 
		
		Calendar calendarForWeek=Calendar.getInstance();
				
		displayedWeek=calendarForWeek.get(Calendar.WEEK_OF_YEAR)+weekFromThisWeek-1;
		scheduleWeek.setWeekNumber(displayedWeek % 52 +1);

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
