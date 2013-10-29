package se.mah.kd330a.project.find.data;

import se.mah.kd330a.project.find.view.FragmentStep;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ResultPagerAdapter extends FragmentPagerAdapter {

	private PathToRoom mRoomDetails;

	public ResultPagerAdapter(FragmentManager fm, PathToRoom room) {
		super(fm);
		mRoomDetails = room;
	}

	@Override
	public Fragment getItem(int position) {
		Fragment fragment = new FragmentStep();
		Bundle args = new Bundle();
		args.putString(FragmentStep.ARG_PICNAME, mRoomDetails.getPath().get(position));
		args.putString(FragmentStep.ARG_TEXTTITLE, mRoomDetails.getRoomName());
		args.putString(FragmentStep.ARG_TEXTCONTENT, mRoomDetails.getTexts().get(position));
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
