
package se.mah.kd330a.project.framework;


import se.mah.kd330a.project.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FragmentHome extends Fragment {
	

	public FragmentHome () {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	 
		ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_screen_home, container, false);
		return rootView;
		
	}
	
	

}