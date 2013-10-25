

package se.mah.kd330a.project.itsl;


import se.mah.kd330a.project.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
<<<<<<< HEAD

public class FragmentITSL extends Fragment {


	
=======
import java.util.ArrayList;
import android.app.ProgressDialog;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

/*
 * @author asampe, marcusmansson
 * 
 * 
 * HISTORY:
 * 
 * 0.8.0
 *  o transfered files and resources to the framwork project, adapted code for
 *    fragment
 *   
 * 0.7.4
 *  o moved all file handling to FeedManager
 *  o code and comments cleaned up
 *   
 * 0.7.3
 *  o merged two branches (unknown version) with 0.7.2 
 *  o course colors in the UI 
 *  o fixed text overflow in the UI
 *  
 * 0.7.2 
 * 	o added saving/loading of articles to/from cache
 *  o load saved articles and initialize list on app start 
 * 	o removed unused code
 * 
 * TODO:
 * 
 *  o fix a better looking progressbar, until then use old progressdialog
 *  o seem to be a glitch when expanding, sometimes the child 
 *    gets a black background
 *    
 */
public class FragmentITSL extends Fragment implements FeedManager.FeedManagerDoneListener, 
	OnScrollListener, OnChildClickListener, OnClickListener
{
	static final String TAG = "ITSL_fragment";

	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	FeedManager feedManager;
	ProgressDialog dialog;
	ProgressBar progBar;
	TextView txProgress;
	View headerView;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		feedManager = new FeedManager(this, getActivity());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_screen_itsl, container, false);

		/*
		 *  create settings view and hide it
		 */
		headerView = inflater.inflate(R.layout.itsl_list_header, null);
		headerView.findViewById(R.id.button1).setOnClickListener(this);
		headerView.findViewById(R.id.button2).setOnClickListener(this);
		hideSettingsView();

		/*
		 *  set up the listview
		 */
		listAdapter = new ExpandableListAdapter(getActivity(), feedManager.getArticles());
		expListView = (ExpandableListView) rootView.findViewById(R.id.lvExp);
		expListView.addHeaderView(headerView);
		expListView.setAdapter(listAdapter);
		expListView.setOnScrollListener(this);
		expListView.setOnChildClickListener(this);

		/*
		 *  set up progressbar
		 */
		progBar = (ProgressBar) rootView.findViewById(R.id.progress);
		txProgress = (TextView) rootView.findViewById(R.id.txProgess);
		progBar.setVisibility(ProgressBar.GONE);
		txProgress.setVisibility(TextView.GONE);

		/*
		 *  in case there was nothing in the cache, or it didn't exist
		 *  we have to refresh
		 */
		if (feedManager.getArticles().isEmpty())
			refresh();
		
		return rootView;
	}
	
	public void onFeedManagerProgress(int progress, int max)
	{
		/*
		 *  set up progress dialog if there isn't one.
		 */
		if (dialog == null)
		{
			dialog = new ProgressDialog(getActivity());
			dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			dialog.setMessage("Downloading...");
			dialog.show();
		}
		
		dialog.setProgress(progress);
		dialog.setMax(max);
		
		/*
		progBar.setVisibility(ProgressBar.VISIBLE);
		txProgress.setVisibility(TextView.VISIBLE);
		progBar.setProgress(progress);
		progBar.setMax(max);
		*/
	}

	@Override
	public void onFeedManagerDone(ArrayList<Article> articles)
	{
		/*
		 * display the data in our listview
		 */
		listAdapter.notifyDataSetInvalidated();

		/*
		progBar.setVisibility(ProgressBar.GONE);
		txProgress.setVisibility(TextView.GONE);
		*/
		dialog.dismiss();
		dialog = null;

		Toast.makeText(getActivity(), "" + articles.size() + " articles", Toast.LENGTH_LONG).show();
	}

	private void refresh()
	{
		/*
		 * close all expanded childviews, otherwise they will incorrectly 
		 * linger in the UI even after we invalidate the dataset
		 */
		int count = listAdapter.getGroupCount();
		for (int i = 0; i < count; i++)
			expListView.collapseGroup(i);

		/*
		 * as soon as we add feeds to feedManager, queueSize is no longer 0,  
		 * therefore this will only be done once
		 */
		if (feedManager.queueSize() == 0)
		{
			feedManager.addFeedURL("https://mah.itslearning.com/Bulletin/RssFeed.aspx?LocationType=1&LocationID=18178&PersonId=25776&CustomerId=719&Guid=d50eaf8a1781e4c8c7cdc9086d1248b1&Culture=sv-SE");
			feedManager.addFeedURL("https://mah.itslearning.com/Bulletin/RssFeed.aspx?LocationType=1&LocationID=16066&PersonId=71004&CustomerId=719&Guid=52845be1dfae034819b676d6d2b18733&Culture=sv-SE");
			feedManager.addFeedURL("https://mah.itslearning.com/Bulletin/RssFeed.aspx?LocationType=1&LocationID=18190&PersonId=94952&CustomerId=719&Guid=96721ee137e0c918227093aa54f16f80&Culture=en-GB");
			feedManager.addFeedURL("http://www.mah.se/Nyheter/RSS/Anslagstavla-fran-Malmo-hogskola/");
			feedManager.addFeedURL("https://mah.itslearning.com/Dashboard/NotificationRss.aspx?LocationType=1&LocationID=18178&PersonId=25776&CustomerId=719&Guid=d50eaf8a1781e4c8c7cdc9086d1248b1&Culture=sv-SE");
		}

		feedManager.reset();
		feedManager.processFeeds();
	}

	private void hideSettingsView()
	{
		headerView.findViewById(R.id.headerLayout).setVisibility(View.GONE);
	}

	private void showSettingsView()
	{
		headerView.findViewById(R.id.headerLayout).setVisibility(View.VISIBLE);
	}
>>>>>>> origin/marcus-0.0.1

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
<<<<<<< HEAD
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_screen_itsl, container, false);
		return rootView;
		
	}

=======
	public void onScrollStateChanged(AbsListView view, int scrollState)
	{
		if (expListView.getFirstVisiblePosition() == 0 && scrollState == OnScrollListener.SCROLL_STATE_IDLE)
			/*
			 * you could refresh content here directly instead of showing the header
			 */
			showSettingsView();
		else
			hideSettingsView();
	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)
	{
		return parent.collapseGroup(groupPosition);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.button1:
				refresh();
				hideSettingsView();
				break;
			case R.id.button2:
				feedManager.reset();
				feedManager.deleteCache();
				listAdapter.notifyDataSetInvalidated();
				break;
		}
	}
>>>>>>> origin/marcus-0.0.1
}