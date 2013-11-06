package se.mah.kd330a.project.home;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import se.mah.kd330a.project.framework.MainActivity;
//import com.handmark.pulltorefresh.library.PullToRefreshBase;
//import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
//import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import se.mah.kd330a.project.home.data.RSSFeed;
import se.mah.kd330a.project.schedule.view.FragmentScheduleWeekPager;
import se.mah.kd330a.project.R;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

<<<<<<< HEAD
public class FragmentHome extends Fragment
{
=======
public class FragmentHome extends Fragment {
>>>>>>> origin/Login_Schedule

	private NextClassWidget nextClass;
	private ViewGroup rootView;
	private RSSFeed newsFeed;
	private ObjectInputStream in = null;
	private FileInputStream fis = null;

	private boolean profileRegistered = false;

	public FragmentHome() {
	}

	@Override
<<<<<<< HEAD
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
=======
	public void onCreate(Bundle savedInstanceState) {
>>>>>>> origin/Login_Schedule
		nextClass = new NextClassWidget();
		profileRegistered = nextClass.getTodaysClasses();
		super.onCreate(savedInstanceState);
	}

	@Override
<<<<<<< HEAD

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { 
		rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_screen_home, container, false);
=======
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = (ViewGroup) inflater.inflate(R.layout.fragment_screen_home,
				container, false);
>>>>>>> origin/Login_Schedule
		setNextClassWidget(rootView);
		setNewsFeedMah(rootView);
		setLastItslPost(rootView);
		return rootView;
<<<<<<< HEAD

	}

=======
>>>>>>> origin/Login_Schedule

	}

<<<<<<< HEAD
	private void setNewsFeedMah(ViewGroup rootView)
	{
		try
		{
=======
	private void setNewsFeedMah(ViewGroup rootView) {
		try {
>>>>>>> origin/Login_Schedule
			fis = getActivity().openFileInput("filename");
			in = new ObjectInputStream(fis);
			newsFeed = (RSSFeed) in.readObject();
			in.close();
			fis.close();
			Log.i("setNewsFeedMah", Integer.toString(newsFeed.getItemCount()));
<<<<<<< HEAD
		}
		catch (Exception ex)
		{
			System.out.println("Error in get method");
		}

		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		LinearLayout newsFeedMahWidget = (LinearLayout) rootView.findViewById(R.id.news_feed_mah);
		try
		{
=======
		} catch (Exception ex) {
			System.out.println("Error in get method");
		}

		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		LinearLayout newsFeedMahWidget = (LinearLayout) rootView
				.findViewById(R.id.news_feed_widget);
>>>>>>> origin/Login_Schedule
		for (int i = 0; i < 1; i++) {
			TextView title = (TextView) newsFeedMahWidget
					.findViewById(R.id.text_latest_news_heading);

			title.setText(newsFeed.getItem(i).getTitle());
			TextView description = (TextView) newsFeedMahWidget
					.findViewById(R.id.text_latest_news_description);
			
<<<<<<< HEAD
			//add extra space between news items - will be changed later
			TextView space = new TextView(getActivity());
			space.setLayoutParams(params);
			space.setText(" ");
				//add extra space between news items - will be changed later
				TextView space1 = new TextView(getActivity());
				space1.setLayoutParams(params);
				space1.setText(" ");

				newsFeedMahWidget.addView(pubDate);
				newsFeedMahWidget.addView(title);
				newsFeedMahWidget.addView(description);
				newsFeedMahWidget.addView(link);
				newsFeedMahWidget.addView(creator);
				newsFeedMahWidget.addView(space1);

			}
		}
		catch (Exception e)
		{
			Log.e("FragmentHome", e.toString());
		}
	}

	private void setNextClassWidget(ViewGroup rootView)
	{
		LinearLayout nextClassWidget = (LinearLayout) rootView.findViewById(R.id.next_class_widget);
=======
			description.setText(newsFeed.getItem(i).getDescription());
			

		}
	}

	private void setNextClassWidget(ViewGroup rootView) {
		LinearLayout nextClassWidget = (LinearLayout) rootView
				.findViewById(R.id.next_class_widget);
		SharedPreferences sharedPref = this.getActivity().getSharedPreferences("courseName",
				Context.MODE_PRIVATE);
		String courseName = sharedPref.getString(nextClass.getCourseName(), nextClass.getCourseName());
>>>>>>> origin/Login_Schedule
		if (profileRegistered) {
			TextView textNextClassName = (TextView) nextClassWidget
					.findViewById(R.id.text_next_class_name);
			textNextClassName.setText(courseName);
			TextView textNextClassDate = (TextView) nextClassWidget
					.findViewById(R.id.text_next_class_date);
			textNextClassDate.setText(nextClass.getDate());
			TextView textNextClassStartTime = (TextView) nextClassWidget
					.findViewById(R.id.text_next_class_start_time);
			textNextClassStartTime.setText(nextClass.getStartTime());
			TextView textNextClassEndTime = (TextView) nextClassWidget
					.findViewById(R.id.text_next_class_end_time);
			textNextClassEndTime.setText(nextClass.getEndTime());
			TextView textNextClassLocation = (TextView) nextClassWidget
					.findViewById(R.id.text_next_class_location);
			textNextClassLocation.setText(nextClass.getLocation());
		} else {
			TextView textNextClassDate = (TextView) nextClassWidget
					.findViewById(R.id.text_next_class_date);
			textNextClassDate.setText("No classes");
		}
<<<<<<< HEAD
=======

>>>>>>> origin/Login_Schedule
	}

	private SharedPreferences getSharedPreferences(String string,
			int modePrivate) {
		// TODO Auto-generated method stub
		return null;
	}

	private void setLastItslPost(ViewGroup rootView) {
		// TODO Auto-generated method stub

	}

	private class UpdateDataTask extends AsyncTask<Void, Void, String[]>
	{

		@Override
		protected String[] doInBackground(Void... params)
		{
			// Simulates a background job.
			try
			{
				Thread.sleep(4000);
<<<<<<< HEAD
			}
			catch (InterruptedException e)
			{
=======
			} catch (InterruptedException e) {
>>>>>>> origin/Login_Schedule

			}
			return null;
		}

		@Override
		protected void onPostExecute(String[] result)
		{
			// Do some stuff here

			// Call onRefreshComplete when the list has been refreshed.
			// mPullRefreshScrollView.onRefreshComplete();
			super.onPostExecute(result);
		}
	}
<<<<<<< HEAD
}
=======

}
>>>>>>> origin/Login_Schedule
