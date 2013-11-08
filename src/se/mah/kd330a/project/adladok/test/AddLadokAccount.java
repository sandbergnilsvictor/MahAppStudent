package se.mah.kd330a.project.adladok.test;

import java.util.Observable;
import java.util.Observer;
import se.mah.kd330a.project.R;
import se.mah.kd330a.project.adladok.model.Course;
import se.mah.kd330a.project.adladok.model.Me;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddLadokAccount extends Activity implements Observer {
	private final String userFile = "userFile";
	private SharedPreferences sharedPref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ad_ladok_test);
		EditText userID_et = (EditText) findViewById(R.id.userIDet);
		sharedPref = getSharedPreferences(userFile, Context.MODE_PRIVATE);
		userID_et.setText(sharedPref.getString("user_id", ""));

		Log.i("UserInfo", "Antal observers :" + Me.observable.countObservers());
		if (Me.observable.countObservers() > 0) {
			Me.observable.deleteObservers();
		}
		Me.observable.addObserver(this);
	}

	public void forgetButtonClicked(View v) {
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putString("user_id", "");
		editor.putString("user_password", "");
		editor.commit();
		finish();
	}

	public void update(View v) {
		// clear all
		Me.setDispayName("");
		Me.setEmail("");
		Me.setFirstName("");
		Me.setIsStudent(false);
		Me.setIsStaff(false);
		Me.setLastName("");
		Me.setPassword("");
		Me.setUserID("");
		Me.clearCourses();

		// First we have to set username and password those should are probably
		// be saved in Sharedprefs
		EditText userID_et = (EditText) findViewById(R.id.userIDet);
		EditText password_et = (EditText) findViewById(R.id.passWordET);
		Me.setUserID(userID_et.getText().toString());
		Me.setPassword(password_et.getText().toString());
		Me.updateMe();
		
		int i=0;
		
		for (Course c : Me.getCourses()) {
			
			switch (i) {
			case 0:
				c.setColor(this.getResources().getColor(R.color.blue));
				break;
			case 1:
				c.setColor(this.getResources().getColor(R.color.orange));								
				break;
			case 2:
				c.setColor(this.getResources().getColor(R.color.grey));
				break;
			case 3:
				c.setColor(this.getResources().getColor(R.color.green));
				break;
			case 4:
				c.setColor(this.getResources().getColor(R.color.yellow));
				break;
			default:
				break;			
			}
			i++;
		}

		

		
		
		
		

		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putString("user_id", userID_et.getText().toString());
		editor.putString("user_password", password_et.getText().toString());
		editor.commit();
	}

	@Override
	public void update(Observable observable, Object data) {
		String courses = "";
		Log.i("UserInfo", "Length: " + Me.getCourses().size());
		
		int i=0;
		for (Course c : Me.getCourses()) {
			courses = courses + "course: \n" + "NameSV: "
					+ c.getDisplaynameSv() + "\n" + "NameEN: "
					+ c.getDisplaynameEn() + "\n" + "CourseID: "
					+ c.getCourseID() + "\n" + "Program: " + c.getProgram()
					+ "\n" + "Term: " + c.getTerm() + "\n" + "regCode: "
					+ c.getRegCode() + "\n" + "KronoxCodeString: "
					+ c.getKronoxCalendarCode() + "\n";
		
			switch (i) {
			case 0:
				c.setColor(this.getResources().getColor(R.color.blue));
				break;
			case 1:
				c.setColor(this.getResources().getColor(R.color.orange));								
				break;
			case 2:
				c.setColor(this.getResources().getColor(R.color.grey));
				break;
			case 3:
				c.setColor(this.getResources().getColor(R.color.green));
				break;
			case 4:
				c.setColor(this.getResources().getColor(R.color.yellow));
				break;
			default:
				break;			
			}
			
			i++;
			
		}

		Log.i("Ladok", "c.getKronoxCalendarCode()");
		if (Me.getFirstName().isEmpty()) {
			Toast.makeText(this, "Please enter a valid Username or Password",
					Toast.LENGTH_LONG).show();
		} else {
			Me.observable.deleteObserver(this);
			finish();
		}
	}

}
