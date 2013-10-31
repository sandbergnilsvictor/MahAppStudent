package se.mah.kd330a.project.find.view;

import java.util.Arrays;

import se.mah.kd330a.project.R;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentBuilding extends Fragment {
	public static final String ARG_BUILDING = "building";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		ViewGroup rootView = (ViewGroup) inflater
				.inflate(R.layout.fragment_screen_find_building, container, false);
		return rootView;
	}

	@Override
	public void onStart() {
		super.onStart();
		Bundle args = getArguments();
		
		String buildingCode = args.getString(ARG_BUILDING);
		String[] buildings = getResources().getStringArray(R.array.find_building_code_array);
		int pos = Arrays.asList(buildings).indexOf(buildingCode);
		
		buildings = getResources().getStringArray(R.array.find_building_array);
		((TextView) getView().findViewById(R.id.text_find_startTitle)).setText(buildings[pos]);
		
		Log.i("project", "FragmentBuilding " + buildingCode);
	
		ImageView imgBuilding = (ImageView) getView().findViewById(R.id.img_find_navigation);
		imgBuilding.setImageDrawable(loadImage(buildingCode));
	}
	
	private Drawable loadImage(String pic) {
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
