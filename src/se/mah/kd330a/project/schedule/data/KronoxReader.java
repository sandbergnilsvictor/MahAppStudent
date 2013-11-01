package se.mah.kd330a.project.schedule.data;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import android.content.Context;
public class KronoxReader {
	private final static String LOCAL_FILENAME = "courses.ical";
	private final static String LENGTH_UNIT = "v"; // d=days, v=weeks, m=months
	private final static int LENGTH = 2;
	// We will need to be consistent here, since the _tags_ in the summary field
	// are language dependent!
	private final static String LANGUAGE = "EN";
	private KronoxReader() {
	}
	/**
	 * Generates the URL that gets the iCalendar files for our courses.
	 * 
	 * @param courses Any number of courses
	 * @return The URL
	 */
	private static String generateURL(KronoxCourse[] courses) {
		String kurser = "";
		for(KronoxCourse course : courses) {
			kurser += String.format("k.%s-%%2C", course.getFullCode());
		}
		String url = "http://schema.mah.se/setup/jsp/SchemaICAL.ics";
		url += String.format("?startDatum=idag&intervallTyp=%s&intervallAntal=%d",
		                     LENGTH_UNIT, LENGTH);
		url += "&sprak=" + LANGUAGE;
		url += "&sokMedAND=false";
		url += "&resurser=" + kurser;
		return url;
	}
	/**
	 * This will download the iCalendar file from KronoX and save it locally.
	 * 
	 * @param ctx
	 *        Context from getApplicationContext()
	 * @param courses
	 *        The courses that will be included in the calendar
	 * @throws IOException
	 *         This will be thrown on network errors or file writing errors
	 */
	public static void update(Context ctx, KronoxCourse[] courses) throws IOException {
		URL url = new URL(KronoxReader.generateURL(courses));
		InputStream is = url.openStream();
		DataInputStream dis = new DataInputStream(is);
		FileOutputStream fos = ctx.openFileOutput(KronoxReader.LOCAL_FILENAME,
		                                          Context.MODE_PRIVATE);
		byte[] buffer = new byte[4096];
		int length;
		while((length = dis.read(buffer)) > 0) {
			fos.write(buffer, 0, length);
		}
		fos.close();
	}
	/**
	 * This will give you a file stream to the calendar. The calendar will not be
	 * updated beforehand.
	 * 
	 * @return The file stream
	 * @throws FileNotFoundException
	 */
	public static FileInputStream getFile(Context ctx) throws FileNotFoundException {
		return ctx.openFileInput(KronoxReader.LOCAL_FILENAME);
	}
}