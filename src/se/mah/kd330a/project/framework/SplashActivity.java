package se.mah.kd330a.project.framework;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import org.json.JSONException;
import se.mah.kd330a.project.schedule.data.KronoxJSON;
import se.mah.kd330a.project.schedule.data.KronoxCalendar;
import se.mah.kd330a.project.schedule.data.KronoxCourse;
import se.mah.kd330a.project.schedule.data.KronoxReader;
import se.mah.kd330a.project.R;
import se.mah.kd330a.project.adladok.model.Course;
import se.mah.kd330a.project.adladok.model.Me;
import se.mah.kd330a.project.adladok.test.AddLadokAccount;
import se.mah.kd330a.project.home.data.*;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import android.content.Context;
import android.content.SharedPreferences;

import android.content.Intent;

public class SplashActivity extends Activity{

	private String RSSNEWSFEEDURL = "http://www.mah.se/Nyheter/RSS/News/";
	private RSSFeed feed;
	private FileOutputStream fout = null;
	private ObjectOutputStream out = null;
	private ArrayList<KronoxCourse> courses;
	private boolean goToMainActivity = false;
	private KronoxCourse[] courses_array;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)  {
		setContentView(R.layout.activity_splash);
		super.onCreate(savedInstanceState);
//		Log.i("UserInfo","Antal observers :"+ Me.observable.countObservers());
//		if (Me.observable.countObservers()>0){
//			Me.observable.deleteObservers();
//		}
//		Me.observable.addObserver(this);

		// Check if there is a user stored
		SharedPreferences sharedPref = getSharedPreferences("userFile",
				Context.MODE_PRIVATE);
		String userId = sharedPref.getString("user_id", "No user");
		String userPassword = sharedPref.getString("user_password",
				"No password");

		// Redirects the user to enter ladokId and password and load data for
		// Newsfeed
		if (userId.equals("No user") || userId == null) {
			new GetNewsFeed().execute();
			Intent intent = new Intent(SplashActivity.this,
					AddLadokAccount.class);
			startActivity(intent);
			//finish();
		} else {
			Me.setUserID(userId);
			Me.setPassword(userPassword);
			Me.updateMe();
		}
	}
	

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i("SplashActivity","Ok back in Splash");
		if (Me.getCourses().isEmpty()){
			courses = new ArrayList<KronoxCourse>();
			List<Course> ladokCourses = Me.getCourses();
			for (Course c : ladokCourses) {
				String courseId = c.getKronoxCalendarCode();
				courseId = courseId.substring(2);
				courses.add(new KronoxCourse(courseId));
			}
	
			courses_array = new KronoxCourse[courses.size()];
			courses.toArray(courses_array);
			
			new FetchCourseName().execute(courses_array);
		}
	
			//new GetNewsFeed().execute();
	
//			if (courses_array.length != 0) {
//				try {
//					KronoxCalendar.createCalendar(KronoxReader
//							.getFile(getApplicationContext()));
//					Log.i("SplashActivity", "Creating Calender");
//					goToMainActivity = true;  //if there are no courses we stop here
//	
//				} catch (Exception e) {
//					new DownloadSchedule().execute(courses_array);
//					Log.i("SplashActivity", "Downloading schedule");
//				}
//	
//			} else {
//				Log.i("Get schedule", "No classes");
//			}
//		}

	}

	private void endThis(){
		if (courses_array.length != 0) {
			try {
				KronoxCalendar.createCalendar(KronoxReader
						.getFile(getApplicationContext()));
				Log.i("SplashActivity", "Creating Calender");
				goToMainActivity = true;  //if there are no courses we stop here

			} catch (Exception e) {
				new DownloadSchedule().execute(courses_array);
				Log.i("SplashActivity", "Downloading schedule");
			}

		} else {
			Log.i("Get schedule", "No classes");
			Intent intent = new Intent(SplashActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
		}
		
	}
//	@Override
//	public void update(Observable observable, Object data) {
//		
//		courses = new ArrayList<KronoxCourse>();
//		List<Course> ladokCourses = Me.getCourses();
//		for (Course c : ladokCourses) {
//			String courseId = c.getKronoxCalendarCode();
//			courseId = courseId.substring(2);
//			courses.add(new KronoxCourse(courseId));
//		}
//
//		KronoxCourse[] courses_array = new KronoxCourse[courses.size()];
//		courses.toArray(courses_array);
//		
//		new FetchCourseName().execute(courses_array);
//
//		new GetNewsFeed().execute();
//
//		if (courses_array.length != 0) {
//			try {
//				KronoxCalendar.createCalendar(KronoxReader
//						.getFile(getApplicationContext()));
//				Log.i("SplashActivity", "Creating Calender");
//				goToMainActivity = true;
//
//			} catch (Exception e) {
//				new DownloadSchedule().execute(courses_array);
//				Log.i("SplashActivity", "Downloading schedule");
//			}
//
//		} else {
//			Log.i("Get schedule", "No classes");
//		}
//
//	}

	private class DownloadSchedule extends AsyncTask<KronoxCourse, Void, Void> {
		@Override
		protected Void doInBackground(KronoxCourse... courses) {
			try {
				Log.i("SplashActivity", "Kronoxdownload started");
				KronoxReader.update(getApplicationContext(), courses);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void _void) {
			Log.i("SplashActivity", "KronoxSchedule downloaded");
			Intent intent = new Intent(SplashActivity.this, MainActivity.class);
			startActivity(intent);
			finish();

		}

	}

	private class FetchCourseName extends
			AsyncTask<KronoxCourse, Void, KronoxCourse> {
		@Override
		protected KronoxCourse doInBackground(KronoxCourse... courses) {
			try {
				Log.i("SplashActivity", "FetchCourseName started");
				return KronoxJSON.getCourse(courses[0].getFullCode());
			}
			 catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (JSONException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(KronoxCourse course) {
			Log.i("SplashActivity", "FetchCourseName stopped");	
			if (course != null) {
				SharedPreferences sharedPref = getSharedPreferences("courseName",
						Context.MODE_PRIVATE);
				SharedPreferences.Editor editor = sharedPref.edit();
				editor.putString(course.getFullCode(), course.getName());
				editor.commit();
				Log.i("SplashActivity", course.getFullCode());
				Log.i("SplashActivity", String.format("course:%s,%s",
						course.getFullCode(), course.getName()));
				
			}
			new GetNewsFeed().execute(); //LH
		}
	}

	private class GetNewsFeed extends AsyncTask<Void, Void, String[]> {

		@Override
		protected String[] doInBackground(Void... params) {
			// Simulates a background job.
			Log.i("SplashActivity", "GetNewsFeed started");
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

			} finally {
				Log.i("SplashActivity", "GetNewsFeed in postexecute and goToMainActivity: "+goToMainActivity);
				endThis();
//				if (goToMainActivity == true) {
//					goToMainActivity = false;
//					Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//					startActivity(intent);
//					finish();
//				}
			}
		}
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i("SplashActivity", "finish called");
	
	}
}
