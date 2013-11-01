package se.mah.kd330a.project.schedule.data;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.filter.Filter;
import net.fortuna.ical4j.filter.PeriodRule;
import net.fortuna.ical4j.filter.Rule;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Dur;
import net.fortuna.ical4j.model.Period;
public class KronoxCalendar {
	private static Calendar calendar;
	/**
	 * Create the calendar object. Must be called before anything else.
	 * 
	 * @param fin
	 *        KronoxReader.getFile()
	 * @throws IOException
	 *         Local file could be missing or corrupt
	 * @throws ParserException
	 *         Internal errors in the iCalendar file
	 */
	public static void createCalendar(FileInputStream fin) throws IOException, ParserException {
		CalendarBuilder builder = new CalendarBuilder();
		KronoxCalendar.calendar = builder.build(fin);
	}
	public static Collection<?> todaysEvents() {
		java.util.Calendar today = java.util.Calendar.getInstance();
		today.set(java.util.Calendar.HOUR_OF_DAY, 0);
		today.clear(java.util.Calendar.MINUTE);
		today.clear(java.util.Calendar.SECOND);
		Dur one_day = new Dur(1, 0, 0, 0);
		Rule[] rules = new Rule[1];
		Period period = new Period(new DateTime(today.getTime()), one_day);
		rules[0] = new PeriodRule(period);
		Filter filter = new Filter(rules, Filter.MATCH_ANY);
		return filter.filter(calendar.getComponents(Component.VEVENT));
	}
}
// MATCH_ALL - all rules must be matched
// MATCH_ANY - any rule may be matched
