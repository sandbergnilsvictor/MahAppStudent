package se.mah.kd330a.project.schedule.view;

import java.util.ArrayList;

import se.mah.kd330a.project.R;
import se.mah.kd330a.project.home.NextClassWidget;
import se.mah.kd330a.project.schedule.data.ParseData;
import se.mah.kd330a.project.schedule.model.ScheduleItem;
import se.mah.kd330a.project.schedule.model.ScheduleWeek;
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
	
	
	private static ArrayList<ScheduleWeek> myScheduleInWeeks;
	private ParseData parseData;
	private static int num_items = 0;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {	
		parseData = new ParseData();
		myScheduleInWeeks = parseData.getParsedDataFromKronoxByWeek();
		num_items = myScheduleInWeeks.size();
		Log.i("onCreate in FragmentScheduleWeekPager", Integer.toString(num_items));
		super.onCreate(savedInstanceState);
	}
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
        	return FragmentScheduleWeek.newInstance(myScheduleInWeeks.get(num), num);
        }

        @Override
        public int getCount() {
            return num_items;
        }
    }
}

