package se.mah.kd330a.project.itsl;

import java.util.ArrayList;

import se.mah.kd330a.project.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

public class TabFragment extends Fragment implements OnChildClickListener
{
	private static final String TAG = "TabFragment";
	private ExpandableListAdapter listAdapter;
	private ExpandableListView expListView;
	private ArrayList<Article> articleList;

	public TabFragment(ArrayList<Article> articles)
	{
		articleList = articles;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.itsl_fragment_main, container, false);
		listAdapter = new ExpandableListAdapter(getActivity(), articleList);
		expListView = (ExpandableListView) rootView.findViewById(R.id.lvExp);
		expListView.setAdapter(listAdapter);
		expListView.setOnChildClickListener(this);
		return rootView;
	}

	public void refresh()
	{
		/*
		 * Close all expanded childviews, otherwise they will incorrectly 
		 * linger in the UI even after we invalidate the dataset.
		 */
		int count = listAdapter.getGroupCount();
		for (int i = 0; i < count; i++)
			expListView.collapseGroup(i);

		listAdapter.notifyDataSetChanged();
	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)
	{
		return parent.collapseGroup(groupPosition);
	}
}
