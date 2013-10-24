package se.mah.kd330a.project.find;

import se.mah.kd330a.project.find.NavStepFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ResultPagerAdapter extends FragmentStatePagerAdapter {

	private PathToRoom mRoomDetails;

	public ResultPagerAdapter(FragmentManager fm, PathToRoom room) {
		super(fm);
		mRoomDetails = room;
	}

	@Override
	public Fragment getItem(int position) {
		Fragment fragment = new NavStepFragment();
		Bundle args = new Bundle();
		args.putString(NavStepFragment.ARG_PICNAME, mRoomDetails.getPath().get(position));
		args.putString(NavStepFragment.ARG_TEXTTITLE, mRoomDetails.getRoomName());
		args.putString(NavStepFragment.ARG_TEXTCONTENT, mRoomDetails.getTexts().get(position));
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public int getCount() {
		if (mRoomDetails != null)
			return mRoomDetails.getPath().size();
		else
			return 0;
	}

}
