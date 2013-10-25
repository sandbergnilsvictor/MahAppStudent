package se.mah.kd330a.project.home;
import java.text.SimpleDateFormat;
import java.util.Locale;
import net.fortuna.ical4j.model.component.VEvent;
/*
 *  TODO: 
 *  Kronox gives us a very oddly formatted SUMMARY field. We do not want to show this as is.
 *  So we need to figure out what information we can extract here and categorize it.
 *  You get the summary by the command v.getSummary().getValue() in the constructor below
 *  A typical string can look like:
 *
 *  Coursegrp: KD330A-20132-62311- Sign: K3LARA Description: Project room Activity type: Okänd
 *  Programme: VGSJU13h VGSJU13h1 VGSJU13h2 Coursegrp: OM113A-20132-OM113-D16 Sign: HSANMOS Description: Övning Injektionsgivning VP. 16:1 Activity type: Okänd
 *  
 *  Can we make it presentable?
 */
public class ScheduleItem {
	SimpleDateFormat time_format = new SimpleDateFormat("HH:mm", Locale.US);
	SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd HH:mm",
	                                                    Locale.US);
	private String startTime;
	private String endTime;
	private String location;
	private String courseName;
	// text += "Summary:" + v.getSummary().getValue() + "\n";
	// text += "Last modified:" +
	// date_format.format(v.getLastModified().getDate()) + "\n";
	public ScheduleItem(VEvent v) {
		startTime = time_format.format(v.getStartDate().getDate());
		endTime = time_format.format(v.getEndDate().getDate());
		location = v.getLocation().getValue();
		courseName = "Mobile Applications Development";
	}
	public String getStartTime() {
		return startTime;
	}
	public String getRoomCode() {
		return location;
	}
	public String getEndTime() {
		return endTime;
	}
	public String getCourseName() {
		return courseName;
	}
}
