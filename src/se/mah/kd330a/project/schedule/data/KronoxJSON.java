package se.mah.kd330a.project.schedule.data;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.text.Html;
public class KronoxJSON {
	private static final String TYPE_COURSE = "typ=kurs";
	@SuppressWarnings("unused")
	private static final String TYPE_PROGRAM = "typ=program";
	@SuppressWarnings("unused")
  private static final String TYPE_TEACHER_ID = "typ=signatur";
	/**
	 * Generate the URL to KronoX' AJAX with the specified search options.
	 * 
	 * @param type
	 *        One of the constants KronoxJSON.TYPE_*
	 * @param search
	 *        Search string (directly passed to KronoX)
	 * @return The URL
	 */
	private static String generateURL(String type, String search) {
		String url = "http://kronox.mah.se/ajax/ajax_autocompleteResurser.jsp?";
		url += type + "&term=" + search;
		return url;
	}
	/**
	 * Accesses KronoX' AJAX auto-complete service and returns the exact data.
	 * 
	 * @param type
	 *        One of the constants KronoxJSON.TYPE_*
	 * @param search
	 *        Search string (directly passed to KronoX)
	 * @return The AJAX response in its entirety
	 * @throws IOException
	 *         Thrown if there is any error while retrieving the file.
	 */
	private static String getData(String type, String search) throws IOException {
		URL url = new URL(generateURL(type, search));
		InputStream in = url.openStream();
		BufferedReader buf = new BufferedReader(new InputStreamReader(in));
		String s, r = "";
		while((s = buf.readLine()) != null) {
			r += s;
		}
		return r;
	}
	/**
	 * Splitting the JSONArray into something that is Iterable. This probably is
	 * not efficient, but it makes the later code more readable.
	 * 
	 * @param type
	 *        One of the constants KronoxJSON.TYPE_*
	 * @param search
	 *        Search string (directly passed to KronoX)
	 * @return A list of JSONObjects
	 * @throws IOException
	 *         see KronoxJSON.getData()
	 * @throws JSONException
	 *         If anything goes wrong with parsing the JSON, this will be thrown.
	 *         For example, it could be that we get a corrupt response from
	 *         KronoX or that KronoX has changed their API.
	 */
	private static ArrayList<JSONObject> parseData(String type, String search) throws IOException, JSONException {
		ArrayList<JSONObject> ls = new ArrayList<JSONObject>();
		String data = getData(type, search);
		JSONArray a;
		a = new JSONArray(data);
		for(int i = 0; i < a.length(); i++) {
			ls.add(a.getJSONObject(i));
		}
		return ls;
	}
	/**
	 * For some reason, KronoX sends us a trailing dash in the course code:
	 * DA117A-20132-TS241-
	 * 
	 * I find this to be too ugly, and we shall handle it internally as
	 * DA117A-20132-TS241
	 * 
	 * @param c
	 *        Course code with evil trailing dash
	 * @return Purified course code
	 */
	private static String cleanCourseCode(String c) {
		while(c.endsWith("-")) {
			c = c.substring(0, c.length() - 1); // remove trailing dash
		}
		return c;
	}
	/**
	 * The data you get from KronoX' AJAX service basically looks like this:
	 * "KD330A-20132-62311-, Mobile Application Development, 15 hp Fristående kurs, 100% dagtid"
	 * + a lot of HTML tags
	 * 
	 * This function will strip all HTML tags and get the 2nd part (course name)
	 * only.
	 * 
	 * @param s
	 *        The data from KronoX
	 * @return Course name
	 */
	private static String cleanCourseName(String s) {
		s = Html.fromHtml(s).toString(); // removes HTML tags
		String[] parts = s.split(",");
		if(parts.length > 1) {
			return parts[1].trim();
		} else {
			return s; // split failed, but return something
		}
	}
	/**
	 * Finds some matching courses in KronoX. Use this when you need to search
	 * for courses using auto-completion.
	 * 
	 * @param search
	 *        Search string (directly passed to KronoX)
	 * @return A possibly empty list of matching KronoxCourse objects
	 * @throws IOException
	 *         see KronoxJSON.getData()
	 * @throws JSONException
	 *         see KronoxJSON.parseData()
	 */
	public static ArrayList<KronoxCourse> getCourses(String search) throws IOException, JSONException {
		ArrayList<KronoxCourse> ls = new ArrayList<KronoxCourse>();
		for(JSONObject o : parseData(TYPE_COURSE, search)) {
			String code = cleanCourseCode(o.optString("value"));
			String name = cleanCourseName(o.optString("label"));
			ls.add(new KronoxCourse(code, name));
		}
		return ls;
	}
	/**
	 * Finds the first matching course in KronoX. Use this when you have the
	 * exact course code and just want the course name.
	 * 
	 * @param search
	 *        Search string (directly passed to KronoX)
	 * @return The matching course from KronoX or null if none.
	 * @throws IOException
	 *         see KronoxJSON.getData()
	 * @throws JSONException
	 *         see KronoxJSON.parseData()
	 */
	public static KronoxCourse getCourse(String search) throws IOException, JSONException {
		for(JSONObject o : parseData(TYPE_COURSE, search)) {
			String code = cleanCourseCode(o.optString("value"));
			String name = cleanCourseName(o.optString("label"));
			return new KronoxCourse(code, name);
		}
		return null;
	}
}