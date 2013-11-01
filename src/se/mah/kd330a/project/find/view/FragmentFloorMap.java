package se.mah.kd330a.project.find.view;

import se.mah.kd330a.project.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class FragmentFloorMap extends Fragment{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		ViewGroup rootView = (ViewGroup) inflater
				.inflate(R.layout.fragment_screen_find_floor_maps, container, false);
		
		
		
		return rootView;
	}

	@Override
	public void onStart() {
		super.onStart();

		//creating spinners
		final Spinner spinnerBuilding = (Spinner) getView().findViewById(R.id.spinner_bilding);
		final Spinner spinnerFloor = (Spinner) getView().findViewById(R.id.spinner_floor);

		//first adapter
		ArrayAdapter<CharSequence> buildingAdapter = ArrayAdapter
				.createFromResource(getActivity(), R.array.find_building_array,
						android.R.layout.simple_spinner_item);
		buildingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerBuilding.setAdapter(buildingAdapter);

		//second adapter ---- change that is empty at beginning
		ArrayAdapter<CharSequence> floorAdapter = ArrayAdapter
				.createFromResource(getActivity(), R.array.find_floorEmpy_array,
						android.R.layout.simple_spinner_item);
		floorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerFloor.setAdapter(floorAdapter);
		
		// Called when a new item is selected (in the Building Spinner)
		spinnerBuilding.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				String buildingName = parent.getItemAtPosition(position).toString();
				Toast.makeText(getActivity(), "position: "+position, Toast.LENGTH_SHORT).show();
				//resetFloor(buildingName);
				resetFloor(position);	
			}

			public void onNothingSelected(AdapterView<?> parent) {
				// Do nothing, just another required interface callback
			}

			private void resetFloor(int position) {
				int floorArrey =  R.array.find_floorEmpy_array;
				switch(position){
				case 0:
					break;
				case 1: //Orkanen
					floorArrey = R.array.find_floor1_5_array;
					break;
				case 2: //Gäddan
					floorArrey = R.array.find_floorEmpy_array;
					break;
				case 3: //Kranen
					floorArrey = R.array.find_floorA_E_array;
					break;
				case 4: //Ubåtshallen
					floorArrey = R.array.find_floor1_5_array;
					break;
				case 5: //Klerken
					floorArrey = R.array.find_floor1_5_array;
					break;
				case 6: //University Hospital
					floorArrey = R.array.find_floor1_5_array;
					break;
				default:
					Toast.makeText(getActivity(), "Read error log", Toast.LENGTH_SHORT).show();
					Log.d("DEBUG", "a different spinner was selected");
					break;
				}
				
				/*
				if(buildingName.equals("Orkanen (Or)")) {
					floorArrey = R.array.find_floor1_5_array;
				}
				else if(buildingName.equals("Gäddan (G8)")) {
					floorArrey = R.array.find_floor1_5_array;
				}
				else if(buildingName.equals("Kranen (K2)")) {
					floorArrey = R.array.find_floorA_E_array;
				}
				else if(buildingName.equals("Ubåtshallen (K8)")) {
					floorArrey = R.array.find_floor1_5_array;
				}
				else if(buildingName.equals("Klerken (Kl)")) {
					floorArrey = R.array.find_floor1_5_array;
				}
				else if(buildingName.equals("University Hospital (Hs)")) {
					floorArrey = R.array.find_floor1_5_array;
				}*/

				ArrayAdapter<CharSequence> floorAdapter = ArrayAdapter
						.createFromResource(getActivity(), floorArrey,
								android.R.layout.simple_spinner_item);
				floorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spinnerFloor.setAdapter(floorAdapter);
			}
		});

		// Called when a new item is selected (in the Floor Spinner)
		spinnerFloor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				String floorName = parent.getItemAtPosition(position).toString();
				
				//here comes the code for showing the map	
				WebView webview = (WebView) getView().findViewById(R.id.webView_find_floor_map);
				webview.getSettings().setJavaScriptEnabled(true); 
				String pdf = "https://dl.dropboxusercontent.com/u/11605027/or1.pdf";
				webview.loadUrl("https://docs.google.com/viewer?url=" + pdf);
				
			}

			public void onNothingSelected(AdapterView<?> parent) {
				// Do nothing, just another required interface callback
			}
		});
	}
	
	

}
