package se.mah.kd330a.project.framework;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.json.JSONException;

import net.fortuna.ical4j.data.ParserException;

import se.mah.kd330a.project.schedule.data.KronoxJSON;
import se.mah.kd330a.project.schedule.data.KronoxCalendar;
import se.mah.kd330a.project.schedule.data.KronoxCourse;
import se.mah.kd330a.project.schedule.data.KronoxReader;
import se.mah.kd330a.project.R;
import se.mah.kd330a.project.adladok.model.Course;
import se.mah.kd330a.project.adladok.model.Me;
import se.mah.kd330a.project.adladok.test.AdLadokTest;
import se.mah.kd330a.project.home.data.*;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import android.content.Context;
import android.content.SharedPreferences;

import android.content.Intent;

public class SplashActivity extends Activity implements Observer {

	// how long until we go to the next activity
	protected int _splashTime = 4000;
	private String RSSNEWSFEEDURL = "http://www.mah.se/Nyheter/RSS/Nyheter-fran-Malmo-hogskola/";
	private RSSFeed feed;
	private FileOutputStream fout = null;
	private ObjectOutputStream out = null;
	private ArrayList<KronoxCourse> courses;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Me.observable.addObserver(this);
		setContentView(R.layout.activity_splash);
		courses = new ArrayList<KronoxCourse>();

		SharedPreferences sharedPref = getSharedPreferences("userFile",
				Context.MODE_PRIVATE);
		// String userId = sharedPref.getString("user_id", "No user");
		// String userPassword = sharedPref.getString("user_password",
		// "No password");
		String userId = "m11p1128";
		String userPassword = "Armani4055";
		Toast.makeText(this, "Hej " + userId, Toast.LENGTH_LONG).show();
		if (!userId.equals("No user")) {
			Me.setUserID(userId);
			Me.setPassword(userPassword);
			Me.updateMe();
		} else {
			Log.i("No user", "GetDataTask is called");
			new GetDataTask().execute();
		}
	}

	@Override
	public void update(Observable observable, Object data) {
		Log.i("LadokCourses", Integer.toString((Me.getCourses().size())));
		List<Course> ladokCourses = Me.getCourses();
		for (Course c : ladokCourses) {
			String courseId = c.getKronoxCalendarCode();
			courseId = courseId.substring(2);
			Log.i("LadokCourseIdNew", courseId);
			courses.add(new KronoxCourse(courseId));
		}
		KronoxCourse[] courses_array = new KronoxCourse[courses.size()];
		courses.toArray(courses_array);
		if (courses_array.length != 0) {
			try {
				KronoxCalendar.createCalendar(KronoxReader
						.getFile(getApplicationContext()));
				Log.i("try", "createCalender");
			} catch (Exception e) {
				new DownloadSchedule().execute(courses_array);
				Log.i("try", "catch1");
			}
			new FetchCourseName().execute(courses_array);
		} else {
			Log.i("Get schedule", "No classes");
		}
		new GetDataTask().execute();

	}

	private class DownloadSchedule extends AsyncTask<KronoxCourse, Void, Void> {
		@Override
		protected Void doInBackground(KronoxCourse... courses) {
			try {
				KronoxReader.update(getApplicationContext(), courses);
			} catch (IOException e) {
				e.printStackTrace();
				// TODO: toast on error?
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void _void) {
			// TODO: update current view
		}
	}

	private class FetchCourseName extends
			AsyncTask<KronoxCourse, Void, KronoxCourse> {
		@Override
		protected KronoxCourse doInBackground(KronoxCourse... courses) {
			try {
				return KronoxJSON.getCourse(courses[0].getFullCode());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(KronoxCourse course) {

			if (course != null) {
				Log.i("Schedule", String.format("course:%s,%s",
						course.getFullCode(), course.getName()));
			}
		}
	}

	private class GetDataTask extends AsyncTask<Void, Void, String[]> {

		@Override
		protected String[] doInBackground(Void... params) {
			// Simulates a background job.
			try {
				DOMParser myParser = new DOMParser();
				feed = myParser.parseXml(RSSNEWSFEEDURL);
			} catch (Exception e) {

			}
			return null;

		}

		@Override
		protected void onPostExecute(String[] result) {
			try {
				fout = openFileOutput("filename", Context.MODE_PRIVATE);
				out = new ObjectOutputStream(fout);
				out.writeObject(feed);
				out.close();
				fout.close();

			} catch (IOException ioe) {
				System.out.println("Error in save method");

			} finally {
				Intent intent = new Intent(SplashActivity.this,
						MainActivity.class);
				startActivity(intent);

			}
		}
	}
}
