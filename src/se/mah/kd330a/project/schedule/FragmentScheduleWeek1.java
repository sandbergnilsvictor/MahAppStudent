package se.mah.kd330a.project.schedule;

import android.widget.ExpandableListView;
import se.mah.kd330a.project.R;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class FragmentScheduleWeek1 extends Fragment {
	private static final String ARG_POSITION = "position";

	private int position;

	public static FragmentScheduleWeek1 newInstance(int position) {
		FragmentScheduleWeek1 f = new FragmentScheduleWeek1();
		Bundle b = new Bundle();
		b.putInt(ARG_POSITION, position);
		f.setArguments(b);
		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		position = getArguments().getInt(ARG_POSITION);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_schedule_expendable_list_view, container, false);
		
		TextView v = (TextView) rootView.findViewById(R.id.schudule_week_number);
		v.setText("WEEK " + (position + 1));		
		ExpandableListView elv = (ExpandableListView) rootView.findViewById(R.id.expandable_list);
		elv.setAdapter(new ExpandableListViewAdapter());
		return rootView;
	}
	
	public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
		 
	    private String[] groups = { "People Names", "Dog Names", "Cat Names", "Fish Names" };
	 
	        private String[][] children = {
	            { "Arnold", "Barry", "Chuck", "David" },
	        { "Ace", "Bandit", "Cha-Cha", "Deuce" },
	        { "Fluffy", "Snuggles" },
	        { "Goldy", "Bubbles" }
	        };
	 
	        @Override
	        public int getGroupCount() {
	            return groups.length;
	        }
	 
	        @Override
	        public int getChildrenCount(int i) {
	            return children[i].length;
	        }
	 
	        @Override
	        public Object getGroup(int i) {
	            return groups[i];
	        }
	 
	        @Override
	        public Object getChild(int i, int i1) {
	            return children[i][i1];
	        }
	 
	        @Override
	        public long getGroupId(int i) {
	            return i;
	        }
	 
	        @Override
	        public long getChildId(int i, int i1) {
	            return i1;
	        }
	 
	        @Override
	        public boolean hasStableIds() {
	            return true;
	        }
	 
	        @Override
	        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
	        	String headerTitle = (String) getGroup(groupPosition);
	            if (convertView == null) {
	            	
	                LayoutInflater infalInflater = (LayoutInflater) getActivity()
	                        .getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
	                convertView = infalInflater.inflate(R.layout.schedule_list_group, null);
	            }
	     
	            TextView lblListHeader = (TextView) convertView
	                    .findViewById(R.id.lblListHeader);
	            lblListHeader.setTypeface(null, Typeface.BOLD);
	            lblListHeader.setText(headerTitle);
	     
	            return convertView;
	        	
	        }
	 
	        @Override
	        public View getChildView(int groupPosition, final int childPosition,
	                boolean isLastChild, View convertView, ViewGroup parent) {
	        	 final String childText = (String) getChild(groupPosition, childPosition);
	        	 
	             if (convertView == null) {
	                 LayoutInflater infalInflater = (LayoutInflater) getActivity().
	                         getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
	                 convertView = infalInflater.inflate(R.layout.schedule_list_item, null);
	             }
	      
	             TextView txtListChild = (TextView) convertView
	                     .findViewById(R.id.lblListItem);
	      
	             txtListChild.setText(childText);
	             return convertView;
	        }
	 
	        @Override
	        public boolean isChildSelectable(int i, int i1) {
	            return true;
	        }
	 
	    }
	
}
