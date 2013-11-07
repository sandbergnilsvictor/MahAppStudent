package se.mah.kd330a.project.schedule.view;

import java.util.ArrayList;

import android.widget.ExpandableListView;
import se.mah.kd330a.project.R;
import se.mah.kd330a.project.find.data.RoomDbHandler;
import se.mah.kd330a.project.find.view.FragmentFloorMap;
import se.mah.kd330a.project.find.view.FragmentResult;
import se.mah.kd330a.project.schedule.model.ScheduleItem;
import se.mah.kd330a.project.schedule.model.ScheduleWeek;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentScheduleWeek extends Fragment {

	// private static ArrayList<ScheduleItem> _scheduleItemsThisWeek;

	public static FragmentScheduleWeek newInstance(ScheduleWeek scheduleWeek,
			int position) {
		FragmentScheduleWeek f = new FragmentScheduleWeek();
		Bundle b = new Bundle();
		b.putSerializable("ScheduleWeek", scheduleWeek);
		f.setArguments(b);
		return f;
	}

	ScheduleWeek _scheduleWeek;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("onCreate", "called");
		_scheduleWeek = (ScheduleWeek) getArguments().getSerializable(
				"ScheduleWeek");

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		ViewGroup rootView = (ViewGroup) inflater.inflate(
				R.layout.fragment_schedule_expendable_list_view, container,
				false);

		TextView v = (TextView) rootView
				.findViewById(R.id.schudule_week_number);
		v.setText("WEEK " + (_scheduleWeek.getWeekNumber()));
		Log.i("onCreateView", Integer.toString(_scheduleWeek.getWeekNumber()));
		ExpandableListView elv = (ExpandableListView) rootView
				.findViewById(R.id.expandable_list);
		elv.setAdapter(new ExpandableListViewAdapter());

		return rootView;
	}


	public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

		String lastDate = null;
		String location;
		
		@Override
		public int getGroupCount() {
			return _scheduleWeek.getScheduleItems().size();
		}

		@Override
		public int getChildrenCount(int i) {
			return 1;
		}

		@Override
		public Object getGroup(int i) {
			return _scheduleWeek.getScheduleItems().get(i);
		}

		@Override
		public Object getChild(int i, int i1) {
			ArrayList<String> childs = new ArrayList<String>();
			childs.add(_scheduleWeek.getScheduleItems().get(i).getTeacherID());
			return childs;
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
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			LayoutInflater infalInflater = (LayoutInflater) getActivity()
					.getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
			ScheduleItem currentSI = (ScheduleItem) getGroup(groupPosition);
			String currentDate = currentSI.getDateAndTime2();
			SharedPreferences sharedPref = getActivity().getSharedPreferences("courseName",
					Context.MODE_PRIVATE);
			
			String courseName = sharedPref.getString(currentSI.getCourseName(), currentSI.getCourseName());
			
			if (lastDate == null || !currentDate.equals(lastDate)
					|| convertView == null) {
				convertView = infalInflater.inflate(
						R.layout.schedule_list_seperator, null);
				TextView sepertorText = (TextView) convertView
						.findViewById(R.id.list_item_section_text);
				sepertorText.setText(currentDate);
				TextView courseNameText = (TextView) convertView
						.findViewById(R.id.list_course_name);
				courseNameText.setText(courseName);
				TextView startTime = (TextView) convertView
						.findViewById(R.id.list_course_start_time);
				startTime.setText(currentSI.getStartTime());
				TextView endTime = (TextView) convertView
						.findViewById(R.id.list_course_end_time);
				endTime.setText(currentSI.getEndTime());
				TextView location = (TextView) convertView
						.findViewById(R.id.list_course_location);
				location.setText(currentSI.getRoomCode());
				lastDate = currentDate;

			} else {

				convertView = infalInflater.inflate(
						R.layout.schedule_list_group, null);
				TextView courseNameText = (TextView) convertView
						.findViewById(R.id.list_course_name);
				courseNameText.setText(courseName);
				TextView startTime = (TextView) convertView
						.findViewById(R.id.list_course_start_time);
				startTime.setText(currentSI.getStartTime());
				TextView endTime = (TextView) convertView
						.findViewById(R.id.list_course_end_time);
				endTime.setText(currentSI.getEndTime());
				TextView location = (TextView) convertView
						.findViewById(R.id.list_course_location);
				location.setText(currentSI.getRoomCode());
			}

			return convertView;

		}

		@Override
		public View getChildView(int groupPosition, final int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			ArrayList<String> childTexts = (ArrayList<String>) getChild(
					groupPosition, childPosition);

			if (convertView == null) {
				LayoutInflater infalInflater = (LayoutInflater) getActivity()
						.getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
				convertView = infalInflater.inflate(
						R.layout.schedule_list_item, null);
			}
			
			ScheduleItem currentSI = (ScheduleItem) getGroup(groupPosition);
			location = currentSI.getRoomCode();
			
			TextView lector = (TextView) convertView
					.findViewById(R.id.list_course_child_lector);
			Button btnFind = (Button) convertView.findViewById(R.id.findButton);
			btnFind.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					try {
						String[] locs = location.split(" ");
						Log.i("UserInfo", "Button Find click" + locs[0]);
						RoomDbHandler dbHandler = new RoomDbHandler(getActivity());
						if (dbHandler.isRoomExists(locs[0])) {
							startNavigation(locs[0]);
						}
						else if (dbHandler.isRoomExistsAll(locs[0])) {
							//go to floor maps
							showFloorMap(dbHandler.getMapName());
							//Toast.makeText(getActivity(), "floorMapCode: "+dbHandler.getMapName(), Toast.LENGTH_LONG).show();
						}
						else
							Toast.makeText(getActivity(), getString(R.string.find_db_error), Toast.LENGTH_LONG).show();
					}
					catch (Exception e) {
						
					}
				}
			});
			lector.setText(childTexts.get(0));

			return convertView;
		}
		
		private void startNavigation(String roomNr) {

			Fragment fragment = new FragmentResult();
			Bundle args = new Bundle();
			args.putString(FragmentResult. ARG_ROOMNR, roomNr);
			//args.putInt(FragmentResult.ARG_BUILDINGPOS, spin_selected);
			fragment.setArguments(args);

			FragmentManager	 fragmentManager = getActivity().getSupportFragmentManager();

			FragmentTransaction fragmentTrans = fragmentManager.beginTransaction();	
			fragmentTrans.replace(R.id.content_frame, fragment);
			fragmentTrans.addToBackStack(null);
			fragmentTrans.commit();
		}
		
		private void showFloorMap(String floorMapCode) {
			Fragment fragment = new FragmentFloorMap();
			Bundle args = new Bundle();
			args.putString(FragmentFloorMap.ARG_FLOORMAP, floorMapCode);
			fragment.setArguments(args);

			FragmentManager	 fragmentManager = getActivity().getSupportFragmentManager();

			FragmentTransaction fragmentTrans = fragmentManager.beginTransaction();	
			fragmentTrans.replace(R.id.content_frame, fragment);
			fragmentTrans.addToBackStack(null);
			fragmentTrans.commit();		
		}
		
		@Override
		public boolean isChildSelectable(int i, int i1) {
			return true;
		}

	}

}
