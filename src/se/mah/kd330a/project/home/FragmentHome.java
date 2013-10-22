
package se.mah.kd330a.project.home;
import java.util.ArrayList;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;

import se.mah.kd330a.project.home.data.NewsFeedMahParser;
import se.mah.kd330a.project.home.data.NewsMah;
import se.mah.kd330a.project.home.data.RssReader;
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


public class FragmentHome extends Fragment {
	
	private NextClassWidget nextClass;
	private NewsFeedMahParser newsFeed;
	private ArrayList<NewsMah> listOfNewsMah;
	private ViewGroup rootView;
	private RssReader rssReader;
	PullToRefreshScrollView mPullRefreshScrollView;
	ScrollView mScrollView;
	
	public FragmentHome () {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		rssReader = new RssReader("http://www.mah.se/Nyheter/RSS/Nyheter-fran-Malmo-hogskola/");
		nextClass = new NextClassWidget();
		nextClass.generateSampleCourse();
		newsFeed = new NewsFeedMahParser();
		listOfNewsMah = new ArrayList<NewsMah>();
		listOfNewsMah = newsFeed.getAllNews();
		Log.i("onCreate", Integer.toString(listOfNewsMah.size()));
		new DoInBackground().execute();
		
		
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { 
		
		rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_screen_home, container, false);
		mPullRefreshScrollView = (PullToRefreshScrollView) rootView.findViewById(R.id.pull_refresh_scrollview);
		mPullRefreshScrollView.setOnRefreshListener(new OnRefreshListener<ScrollView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
				new GetDataTask().execute();
			}
		});

		mScrollView = mPullRefreshScrollView.getRefreshableView();
		setNextClassWidget(rootView);
		setCalenderFeedMah(rootView);
		return rootView;
		
	}
	
	private class GetDataTask extends AsyncTask<Void, Void, String[]> {

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
			mPullRefreshScrollView.onRefreshComplete();

			super.onPostExecute(result);
		}
	}

	private void setCalenderFeedMah(ViewGroup rootView) {
		// TODO Auto-generated method stub
		
	}

	private void setNewsFeedMah(ViewGroup rootView) {
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		LinearLayout newsFeedMahWidget = (LinearLayout) rootView.findViewById(R.id.news_feed_mah);
		for (NewsMah n : listOfNewsMah) {
			TextView pubDate = new TextView(getActivity());
			pubDate.setLayoutParams(params);
			pubDate.setText(n.getPubDate());
			TextView title = new TextView(getActivity());
			title.setLayoutParams(params);
			title.setText(n.getTitle());
			title.setTextSize(18);
			TextView description = new TextView(getActivity());
			description.setLayoutParams(params);
			description.setText(n.getDescription());
			TextView link = new TextView(getActivity());
			link.setLayoutParams(params);
			link.setText(n.getLink());
			TextView creator = new TextView(getActivity());
			creator.setLayoutParams(params);
			creator.setText(n.getCreator());
			
			
			newsFeedMahWidget.addView(pubDate);
			newsFeedMahWidget.addView(title);
			newsFeedMahWidget.addView(description);	
			newsFeedMahWidget.addView(link);	
			newsFeedMahWidget.addView(creator);	
		}
		
	}

	private void setNextClassWidget(ViewGroup rootView) {
		LinearLayout nextClassWidget = (LinearLayout) rootView.findViewById(R.id.next_class_widget);
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
		
	}
	
	 private class DoInBackground extends AsyncTask<String,Integer,Long>{
			@Override
			protected Long doInBackground(String... params) {
				Log.i("DoInbackground", "doinBackgroud called");
				listOfNewsMah.clear();  //Clear last search
				try {
					listOfNewsMah.addAll(rssReader.getNews()); //Add all stations found
					Log.i("doInBackground", Integer.toString(listOfNewsMah.size()));
					
				} catch(Exception e) {
					Log.i("tag", "msg", e);
				}
				return null;
			}
			@Override
			protected void onProgressUpdate(Integer... values) {
				
				super.onProgressUpdate(values);
			}
			
			@Override
			protected void onPostExecute(Long result) {
				setNewsFeedMah(rootView);
				super.onPostExecute(result);
			}
	    }

}