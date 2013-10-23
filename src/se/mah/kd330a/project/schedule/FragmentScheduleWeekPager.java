package se.mah.kd330a.project.schedule;

import se.mah.kd330a.project.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentScheduleWeekPager extends Fragment {
	
	static final int NUM_ITEMS = 10;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("onCreateView", "loaded");
    	View result = inflater.inflate(R.layout.view_pager_fragment, container, false);
    	ViewPager pager=(ViewPager)result.findViewById(R.id.pager);
    	ViewPagerAdapter viewPagerAdapter = buildAdapter();   	
    	pager.setAdapter(viewPagerAdapter);
    	
        return(result);
    }
    
    private ViewPagerAdapter buildAdapter() {
        return(new ViewPagerAdapter(getChildFragmentManager()));
      }


    public static class ViewPagerAdapter extends FragmentPagerAdapter {
        public ViewPagerAdapter(FragmentManager fm) {
        	super(fm);
        }
        

        @Override
        public Fragment getItem(int num) {
        	Log.i("getItem", Integer.toString(num));
        	return FragmentScheduleWeek1.newInstance(num);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }
    }
}

