
package se.mah.kd330a.project.faq;

import se.mah.kd330a.project.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentFaq extends Fragment {
	


	
	public FragmentFaq () {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {	
	
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { 
		
		ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_screen_faq, container, false);
		
		return rootView;
		
	}

	
}