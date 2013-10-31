package se.mah.kd330a.project.settings.view;

import java.util.ArrayList;

import se.mah.kd330a.project.R;
import se.mah.kd330a.project.adladok.test.AdLadokTest;
import se.mah.kd330a.project.adladok.model.Me;
import se.mah.kd330a.project.adladok.model.Course;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.FrameLayout.LayoutParams;

public class SettingsActivity extends Activity {
	private static final String PREFS_NAME = "myCourses";
	SharedPreferences sharedPref;
	private String profilName;
	private String profilId;
	private ArrayList<Course> myCourses;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		addProfilInformation();

	}

	@SuppressLint("ResourceAsColor")
	private void addProfilInformation() {
		if (Me.getUserID() != null) {
			profilName = Me.getDispayName();
			profilId = Me.getUserID();
			myCourses = (ArrayList<Course>) Me.getCourses();
			TextView profilNameTextView = (TextView) findViewById(R.id.profil_name);
			TextView profilIdTextView = (TextView) findViewById(R.id.profil_id);
			profilNameTextView.setText(profilName);
			profilIdTextView.setText(profilId);
			LinearLayout coursesContent = (LinearLayout) findViewById(R.id.my_courses_content);
			LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			
			for (Course course : myCourses) {
				TextView currentCourse = new TextView(this);
				currentCourse.setText(course.getDisplaynameSv());
				currentCourse.setLayoutParams(params);
				currentCourse.setPadding(12, 5, 12, 5);
				currentCourse.setTextColor(R.color.grey_dark);
				currentCourse.setTextSize(14);
				coursesContent.addView(currentCourse);
			}
		} else {
			TextView profilNameTextView = (TextView) findViewById(R.id.profil_name);
			profilNameTextView.setText("No profile");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.help, menu);
		return true;
	}

	public void toLadokInlogg(View view) {
		Intent intent = new Intent(this, AdLadokTest.class);
		startActivity(intent);
		finish();

	}

}
