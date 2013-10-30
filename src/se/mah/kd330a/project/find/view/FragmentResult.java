package se.mah.kd330a.project.find.view;

import se.mah.kd330a.project.R;
import se.mah.kd330a.project.find.data.ResultPagerAdapter;
import se.mah.kd330a.project.find.data.RoomDbHandler;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
//import android.widget.Toast;

public class FragmentResult extends Fragment {

	public static final String FIND_EXTRA_ROOMNR = "roomNr";
	public static final String FIND_SAVE_CURRENT = "currPic";

	private RoomDbHandler mDbHandler;

	ResultPagerAdapter mResultPagerAdapter;
	ViewPager mViewPager;

	private RadioGroup radioG;

	private OnClickListener rb_OnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			mViewPager.setCurrentItem(Integer.parseInt(v.getTag().toString()));
			//Toast.makeText(getActivity(), radioPicButton.getTag().toString(), Toast.LENGTH_SHORT).show();			
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		ViewGroup rootView = (ViewGroup) inflater
				.inflate(R.layout.fragment_screen_find_result, container, false);
		Bundle args = getArguments();
		String roomNr = args.getString(FIND_EXTRA_ROOMNR);

		if (roomNr != null) {

			mDbHandler = new RoomDbHandler(getActivity());
			if (mDbHandler.isRoomExists(roomNr))
				Log.i("project", mDbHandler.getRoomNr());
		}
		
		return rootView;
	}

	@Override
	public void onStart() {
		super.onStart();

		mResultPagerAdapter = new ResultPagerAdapter(getChildFragmentManager(), 
				mDbHandler.getRoomDetails());
		mViewPager = (ViewPager) getView().findViewById(R.id.vp_Find_Pager);
		mViewPager.setAdapter(mResultPagerAdapter);

		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {

				RadioButton radioB = (RadioButton) radioG.findViewWithTag(position);
				radioB.setChecked(true);
				//Toast.makeText(getActivity(), "rb_Find_Radio" + position, Toast.LENGTH_SHORT).show();
			}
		});

		radioG = (RadioGroup) getView().findViewById(R.id.rg_Find_Pager_Indicator);
		RadioButton radioB;
		
		if (radioG.getChildCount() == 0) {
			
			for (int i = 0; i < mResultPagerAdapter.getCount(); i++) {

				radioB = new RadioButton(getActivity());
				radioB.setTag(i);
				radioB.setButtonDrawable(R.drawable.find_page_indicator);
				radioB.setFocusable(false);
				radioB.setChecked(false);   
				radioB.setOnClickListener(rb_OnClick);
				radioG.addView(radioB);

			}

			((RadioButton)radioG.findViewWithTag(0)).setChecked(true);
		}
		else {
			radioB = (RadioButton) getView().findViewById(radioG.getCheckedRadioButtonId());
			mViewPager.setCurrentItem(Integer.parseInt(radioB.getTag().toString()));
		}

			/*

		RadioButton radioB = (RadioButton) getView().findViewById(R.id.rb_Find_Radio0);
		radioB.setOnClickListener(rb_OnClick);

		radioB = (RadioButton) getView().findViewById(R.id.rb_Find_Radio1);
		radioB.setOnClickListener(rb_OnClick);

		radioB = (RadioButton) getView().findViewById(R.id.rb_Find_Radio2);
		radioB.setOnClickListener(rb_OnClick);

		radioB = (RadioButton) getView().findViewById(R.id.rb_Find_Radio3);
		radioB.setOnClickListener(rb_OnClick);


		radioB = (RadioButton) getView().findViewById(radioG.getCheckedRadioButtonId());
		mViewPager.setCurrentItem(Integer.parseInt(radioB.getTag().toString()));*/
	}

}
