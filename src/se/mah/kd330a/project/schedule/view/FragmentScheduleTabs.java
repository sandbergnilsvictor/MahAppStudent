
package se.mah.kd330a.project.schedule.view;

import se.mah.kd330a.project.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentScheduleTabs extends Fragment {
	
	static final int NUM_ITEMS = 2;
	private FragmentTabHost mTabHost;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("onCreateView", "loaded");
        mTabHost = new FragmentTabHost(getActivity());
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.contentSchedule);
        mTabHost.addTab(mTabHost.newTabSpec("today").setIndicator("Today"),
                FragmentScheduleDay.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("week").setIndicator("Week"),
        		FragmentScheduleWeekPager.class, null); 
    	return mTabHost;
    }
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTabHost = null;
    }
}

