package se.mah.kd330a.project.framework;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import se.mah.kd330a.project.R;
import se.mah.kd330a.project.adladok.model.Course;
import se.mah.kd330a.project.adladok.model.Me;
import se.mah.kd330a.project.adladok.test.AddLadokAccount;
import se.mah.kd330a.project.home.data.DOMParser;
import se.mah.kd330a.project.home.data.RSSFeed;
import se.mah.kd330a.project.schedule.data.KronoxCalendar;
import se.mah.kd330a.project.schedule.data.KronoxCourse;
import se.mah.kd330a.project.schedule.data.KronoxJSON;
import se.mah.kd330a.project.schedule.data.KronoxReader;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

public class SplashActivity extends Activity
{
	private final String TAG = "SplashActivity";
	private String RSSNEWSFEEDURL = "http://www.mah.se/Nyheter/RSS/News/";
	private RSSFeed feed;
<<<<<<< HEAD
=======
	private FileOutputStream fout = null;
	private ObjectOutputStream out = null;
	private ArrayList<KronoxCourse> courses;
	private boolean goToMainActivity = false;
	private KronoxCourse[] courses_array;
>>>>>>> c5b89319c20e040eaf8f1ac56b486e22fc4976f3

	/*
	 * the solution here is (not optimal) but we know that N number of tasks
	 * must be completed, so for each task that completes we increment the
	 * counter. when the counter equals N, all tasks have completed. 
	 */
	private final int totalTaskCounter = 3;
	private int downloadTaskCounter;
	
	@Override
<<<<<<< HEAD
	public void onCreate(Bundle savedInstanceState)
	{
		setContentView(R.layout.activity_splash);
		super.onCreate(savedInstanceState);

		/*
		 *  Check if there is a user stored
		 */
		SharedPreferences sharedPref = getSharedPreferences("userFile", Context.MODE_PRIVATE);
=======
	public void onCreate(Bundle savedInstanceState)  {
		setContentView(R.layout.activity_splash);
		super.onCreate(savedInstanceState);

		// Check if there is a user stored
		SharedPreferences sharedPref = getSharedPreferences("userFile",
				Context.MODE_PRIVATE);
>>>>>>> c5b89319c20e040eaf8f1ac56b486e22fc4976f3
		String userId = sharedPref.getString("user_id", "No user");
		String userPassword = sharedPref.getString("user_password", "No password");
		
		/*
		 *  Redirects the user to enter ladokId and password (we continue in onResume)
		 *  The login screen, could've been a view in this activitiy that
		 *  displays/hides as shown in comments below:
		 */
		
		// this.findViewById(R.id.loginLayout).setVisibility(View.GONE);
		if (userId.equals("No user") || userId == null)
		{
			// this.findViewById(R.id.loginLayout).setVisibility(View.VISIBLE);

			Intent intent = new Intent(SplashActivity.this, AddLadokAccount.class);
			startActivity(intent);
			Log.i(TAG, "starting login intent");
		}
		else
		{
			Me.setUserID(userId);
			Me.setPassword(userPassword);
			Me.updateMe();
			onResume();
		}
	}

	/**
	 * Call this method from all async tasks last in onPostExecute, when all
	 * tasks have completed we can go on to the next activity
	 */
	public void taskComplete(Object task)
	{
		downloadTaskCounter++;

		Log.i(TAG, String.format("taskComplete(): %d of %d tasks completed", downloadTaskCounter, totalTaskCounter));
		
		if (downloadTaskCounter >= totalTaskCounter)
		{
			Log.i(TAG, "Task counter > " + totalTaskCounter + ", starting intent");
			Intent intent = new Intent(SplashActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
		}
	}

	@Override
	protected void onResume()
	{
		super.onResume();
<<<<<<< HEAD

		downloadTaskCounter = 0;
		
		Log.i(TAG, String.format("onResume(): %d of %d tasks completed", downloadTaskCounter, totalTaskCounter));

		new GetNewsFeed(this).execute(); // 1
		
		if (!Me.getCourses().isEmpty())
		{
			ArrayList<KronoxCourse> courses = new ArrayList<KronoxCourse>();
			
			for (Course c : Me.getCourses())
			{
				String courseId = c.getKronoxCalendarCode().substring(2);
				courses.add(new KronoxCourse(courseId));
			}

			KronoxCourse[] courses_array = new KronoxCourse[courses.size()];
			courses.toArray(courses_array);

			new FetchCourseName(this).execute(courses_array); // 2
		
			if (courses_array.length > 0) // 3
			{
				try
				{
					Log.i(TAG, "Creating Calender");
					KronoxCalendar.createCalendar(KronoxReader.getFile(getApplicationContext()));
					downloadTaskCounter++;
				}
				catch (Exception e)
				{
					Log.i(TAG, "Downloading schedule");
					new DownloadSchedule(this).execute(courses_array); // 3
				}
			}
			else
			{
				Log.i(TAG, "Get schedule: No classes");
				downloadTaskCounter++;
			}
		}
		else
		{
			downloadTaskCounter += 2;
=======
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
>>>>>>> c5b89319c20e040eaf8f1ac56b486e22fc4976f3
		}
	
			//new GetNewsFeed().execute();

	}

<<<<<<< HEAD
	private class DownloadSchedule extends AsyncTask<KronoxCourse, Void, Void>
	{
		private SplashActivity appContext;

		public DownloadSchedule(SplashActivity context)
		{
			appContext = context;
		}

		@Override
		protected Void doInBackground(KronoxCourse... courses)
		{
			try
			{
=======
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

	private class DownloadSchedule extends AsyncTask<KronoxCourse, Void, Void> {
		@Override
		protected Void doInBackground(KronoxCourse... courses) {
			try {
				Log.i("SplashActivity", "Kronoxdownload started");
>>>>>>> c5b89319c20e040eaf8f1ac56b486e22fc4976f3
				KronoxReader.update(getApplicationContext(), courses);
			}
			catch (Exception e)
			{
				Log.e(TAG, e.toString());
			}
			return null;
		}

		@Override
<<<<<<< HEAD
		protected void onPostExecute(Void v)
		{
			Log.i("DownloadSchedule", "Schedule downloaded");
			
			/*
			 *  i guess the calendar object has to be created, now that the ical
			 *  has been downloaded? /mm
			 */
			try
			{
				KronoxCalendar.createCalendar(KronoxReader.getFile(getApplicationContext()));
			}
			catch (Exception e)
			{
				Log.e(TAG, e.toString());
			}
			
			appContext.taskComplete(this);
=======
		protected void onPostExecute(Void _void) {
			Log.i("SplashActivity", "KronoxSchedule downloaded");
			Intent intent = new Intent(SplashActivity.this, MainActivity.class);
			startActivity(intent);
			finish();

>>>>>>> c5b89319c20e040eaf8f1ac56b486e22fc4976f3
		}
	}

	private class FetchCourseName extends AsyncTask<KronoxCourse, Void, KronoxCourse>
	{
		private SplashActivity appContext;

		public FetchCourseName(SplashActivity context)
		{
			appContext = context;
		}

		@Override
<<<<<<< HEAD
		protected KronoxCourse doInBackground(KronoxCourse... courses)
		{
			try
			{
=======
		protected KronoxCourse doInBackground(KronoxCourse... courses) {
			try {
				Log.i("SplashActivity", "FetchCourseName started");
>>>>>>> c5b89319c20e040eaf8f1ac56b486e22fc4976f3
				return KronoxJSON.getCourse(courses[0].getFullCode());
			}
			catch (Exception e)
			{
				Log.e(TAG, e.toString());
			}
			return null;
		}

		@Override
<<<<<<< HEAD
		protected void onPostExecute(KronoxCourse course)
		{
			if (course != null)
			{
				SharedPreferences sharedPref = getSharedPreferences("courseName", Context.MODE_PRIVATE);
				SharedPreferences.Editor editor = sharedPref.edit();
				editor.putString(course.getFullCode(), course.getName());
				editor.commit();
				Log.i(TAG, String.format("Course: %s, %s", course.getFullCode(), course.getName()));
			}

			appContext.taskComplete(this);
=======
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
>>>>>>> c5b89319c20e040eaf8f1ac56b486e22fc4976f3
		}
	}

	private class GetNewsFeed extends AsyncTask<Void, Void, Void>
	{
		private SplashActivity appContext;

		public GetNewsFeed(SplashActivity context)
		{
			appContext = context;
		}

		@Override
<<<<<<< HEAD
		protected Void doInBackground(Void... params)
		{
			try
			{
=======
		protected String[] doInBackground(Void... params) {
			// Simulates a background job.
			Log.i("SplashActivity", "GetNewsFeed started");
			try {
>>>>>>> c5b89319c20e040eaf8f1ac56b486e22fc4976f3
				DOMParser myParser = new DOMParser();
				feed = myParser.parseXml(RSSNEWSFEEDURL);
			}
			catch (Exception e)
			{
				Log.e(TAG, e.toString());
			}
			return null;
		}

		protected void onPostExecute(Void n)
		{
			try
			{
				FileOutputStream fout = openFileOutput("filename", Context.MODE_PRIVATE);
				ObjectOutputStream out = new ObjectOutputStream(fout);
				out.writeObject(feed);
				out.close();
				fout.close();
<<<<<<< HEAD
=======

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
>>>>>>> c5b89319c20e040eaf8f1ac56b486e22fc4976f3
			}
			catch (Exception e)
			{
				Log.e(TAG, e.toString());
			}

			appContext.taskComplete(this);
		}
	}
<<<<<<< HEAD
=======
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i("SplashActivity", "finish called");
	
	}
>>>>>>> c5b89319c20e040eaf8f1ac56b486e22fc4976f3
}
