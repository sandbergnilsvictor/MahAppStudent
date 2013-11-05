package se.mah.kd330a.project.find.view;

import java.util.Arrays;
import se.mah.kd330a.project.R;
import se.mah.kd330a.project.find.data.ImageLoader;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FragmentBuilding extends Fragment {
	public static final String ARG_BUILDING = "building";
	private String buildingCode;
	
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
		
		buildingCode = args.getString(ARG_BUILDING);
		String[] buildings = getResources().getStringArray(R.array.find_building_code_array);
		final int pos = Arrays.asList(buildings).indexOf(buildingCode);
		
		buildings = getResources().getStringArray(R.array.find_building_array);
		((TextView) getView().findViewById(R.id.text_find_startTitle)).setText(buildings[pos]);
		
		Log.i("project", "FragmentBuilding " + buildingCode);
	
		ImageView imgBuilding = (ImageView) getView().findViewById(R.id.img_find_navigation);
		new ImageLoader(getActivity(), imgBuilding).execute(buildingCode + ".jpg");
		
		LinearLayout llFloor = (LinearLayout) getView().findViewById(R.id.ll_find_floormap);
		llFloor.setClickable(true);
		llFloor.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Fragment fragment = new FragmentFloorMap();
				//Bundle args = new Bundle();
				//args.putString(FragmentBuilding.ARG_BUILDING, buildingCode);
				//fragment.setArguments(args);

				FragmentManager	 fragmentManager = getActivity().getSupportFragmentManager();

				FragmentTransaction fragmentTrans = fragmentManager.beginTransaction();	
				fragmentTrans.replace(R.id.content_frame, fragment);
				fragmentTrans.addToBackStack(null);
				fragmentTrans.commit();
			}
		});
	
		llFloor = (LinearLayout) getView().findViewById(R.id.ll_find_google);
		llFloor.setClickable(true);
		llFloor.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String[] buildingNames = getResources().getStringArray(R.array.find_building_array);
				String location = buildingNames[pos];

				if(location.equals("Klerken (Kl)"))
					location = "Jan Waldenströms gata 25";
				else if(location.equals("University Hospital (Hs)"))
					location = "Carl Gustafs väg 34";

				//getting the google map
				Intent i = new Intent(android.content.Intent.ACTION_VIEW,
						Uri.parse("geo:0,0?q="+location+"+Malmö+Sweden"));

				startActivity(i);
			}});
	}
}
