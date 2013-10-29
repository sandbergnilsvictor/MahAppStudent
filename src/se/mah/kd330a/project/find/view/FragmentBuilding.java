package se.mah.kd330a.project.find.view;

import se.mah.kd330a.project.R;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class FragmentBuilding extends Fragment {
	public static final String ARG_BUILDING = "building";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		ViewGroup rootView = (ViewGroup) inflater
				.inflate(R.layout.fragment_screen_find_step, container, false);
		return rootView;
	}

	@Override
	public void onStart() {
		super.onStart();
		Bundle args = getArguments();
		
		ImageView imgBuilding = (ImageView) getView().findViewById(R.id.img_find_navigation);
		imgBuilding.setImageDrawable(loadImageFromAssets(args.getString(ARG_BUILDING)));
	}
	
	private Drawable loadImageFromAssets(String pic) {
		Drawable buffer = null;
		try {
			buffer = getResources().getDrawable(getResources()
					.getIdentifier(pic, "drawable", getActivity().getPackageName()));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return buffer;
	}
}
