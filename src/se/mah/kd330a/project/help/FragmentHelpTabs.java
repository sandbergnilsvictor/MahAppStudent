
package se.mah.kd330a.project.help;

import se.mah.kd330a.project.R;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("ResourceAsColor")
public class FragmentHelpTabs extends Fragment {
	
	static final int NUM_ITEMS = 2;
	private FragmentTabHost mTabHost;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("onCreateView", "loaded");
        mTabHost = new FragmentTabHost(getActivity());
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.contentSchedule);
        mTabHost.addTab(mTabHost.newTabSpec("about").setIndicator("About"),
                FragmentHelp.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("credit").setIndicator("Credit"),
        		FragmentCredit.class, null); 
       
    	return mTabHost;
    }
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTabHost = null;
    }
}

