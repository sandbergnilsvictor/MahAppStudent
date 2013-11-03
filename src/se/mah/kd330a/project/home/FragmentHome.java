
package se.mah.kd330a.project.home;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
//import com.handmark.pulltorefresh.library.PullToRefreshBase;
//import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
//import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import se.mah.kd330a.project.home.data.RSSFeed;
import se.mah.kd330a.project.R;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentHome extends Fragment {
	
	private NextClassWidget nextClass;
	private ViewGroup rootView;
	private RSSFeed newsFeed;
	private ObjectInputStream in = null;
	private FileInputStream fis = null;
	private boolean profileRegistered = false;
	
	public FragmentHome () {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {	
		nextClass = new NextClassWidget();
		profileRegistered = nextClass.getTodaysClasses();
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { 
		
		rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_screen_home, container, false);
		setNextClassWidget(rootView);
		setNewsFeedMah(rootView);
		setCalenderFeedMah(rootView);
		return rootView;
		
	}

	private void setNewsFeedMah(ViewGroup rootView) {
	   try {
	        fis = getActivity().openFileInput("filename");
	        in = new ObjectInputStream(fis);
	        newsFeed = (RSSFeed) in.readObject();
	        in.close();
	        fis.close();
	        Log.i("setNewsFeedMah", Integer.toString(newsFeed.getItemCount()));
	    } catch (Exception ex) {
	        System.out.println("Error in get method");
	    }
	   
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		LinearLayout newsFeedMahWidget = (LinearLayout) rootView.findViewById(R.id.news_feed_mah);
		for (int i = 0; i < 5; i++) {
			TextView pubDate = new TextView(getActivity());
			pubDate.setLayoutParams(params);
			pubDate.setText(newsFeed.getItem(i).getDate());
			TextView title = new TextView(getActivity());
			title.setLayoutParams(params);
			title.setText(newsFeed.getItem(i).getTitle());
			title.setTextSize(18);
			TextView description = new TextView(getActivity());
			description.setLayoutParams(params);
			description.setText(newsFeed.getItem(i).getDescription());
			TextView link = new TextView(getActivity());
			link.setLayoutParams(params);
			link.setText(newsFeed.getItem(i).getLink());
			TextView creator = new TextView(getActivity());
			creator.setLayoutParams(params);
			creator.setText(newsFeed.getItem(i).getCreator());
			
			//add extra space between news items - will be changed later
			TextView space = new TextView(getActivity());
			space.setLayoutParams(params);
			space.setText(" ");
			

			newsFeedMahWidget.addView(pubDate);
			newsFeedMahWidget.addView(title);
			newsFeedMahWidget.addView(description);	
			newsFeedMahWidget.addView(link);	
			newsFeedMahWidget.addView(creator);	
			newsFeedMahWidget.addView(space);	
			
		}	
	}

	private void setNextClassWidget(ViewGroup rootView) {
		LinearLayout nextClassWidget = (LinearLayout) rootView.findViewById(R.id.next_class_widget);
		if (profileRegistered) {
		TextView textNextClassName = (TextView) nextClassWidget.findViewById(R.id.text_next_class_name);
		textNextClassName.setText(nextClass.getCourseName());
		TextView textNextClassDate = (TextView) nextClassWidget.findViewById(R.id.text_next_class_date);
		textNextClassDate.setText(nextClass.getDate());
		TextView textNextClassStartTime = (TextView) nextClassWidget.findViewById(R.id.text_next_class_start_time);
		textNextClassStartTime.setText(nextClass.getStartTime());
		TextView textNextClassEndTime = (TextView) nextClassWidget.findViewById(R.id.text_next_class_end_time);
		textNextClassEndTime.setText(nextClass.getEndTime());
		TextView textNextClassLocation = (TextView) nextClassWidget.findViewById(R.id.text_next_class_location);
		textNextClassLocation.setText(nextClass.getLocation());
		} else {
			TextView textNextClassDate = (TextView) nextClassWidget.findViewById(R.id.text_next_class_date);
			textNextClassDate.setText("No classes");
		}
		
	}
	
	private void setCalenderFeedMah(ViewGroup rootView) {
		// TODO Auto-generated method stub	
	}

	private class UpdateDataTask extends AsyncTask<Void, Void, String[]> {

		@Override
		protected String[] doInBackground(Void... params) {
			// Simulates a background job.
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				
			}
			return null;
		}

		@Override
		protected void onPostExecute(String[] result) {
			// Do some stuff here

			// Call onRefreshComplete when the list has been refreshed.
			//mPullRefreshScrollView.onRefreshComplete();
			super.onPostExecute(result);
		}
	}

}