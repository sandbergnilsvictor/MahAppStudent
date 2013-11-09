package se.mah.kd330a.project.adladok.test;

import se.mah.kd330a.project.R;
import se.mah.kd330a.project.R.layout;
import se.mah.kd330a.project.R.menu;
import se.mah.kd330a.project.adladok.model.Course;
import se.mah.kd330a.project.adladok.model.Me;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ImageButton;

public class TestColor extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_color);
		Me.addCourse(new Course("Testkurs","kkkk"));
		Me.addCourse(new Course("Testkurs1","222"));
		Me.addCourse(new Course("Testkurs3","333"));
		ImageButton i1 = (ImageButton) findViewById(R.id.imageButton1);
		i1.setBackgroundColor(Me.getCourses().get(0).getColor());
		ImageButton i2 = (ImageButton) findViewById(R.id.imageButton2);
		i2.setBackgroundColor(Me.getCourses().get(0).getColor());
		ImageButton i3 = (ImageButton) findViewById(R.id.imageButton3);
		i3.setBackgroundColor(Me.getCourses().get(0).getColor());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test_color, menu);
		return true;
	}

}
