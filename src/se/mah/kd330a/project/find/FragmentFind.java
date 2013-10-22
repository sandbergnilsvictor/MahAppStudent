
package se.mah.kd330a.project.find;

import se.mah.kd330a.project.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class FragmentFind extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup) inflater
				.inflate(R.layout.fragment_screen_find, container, false);
		return rootView;
	}

	@Override
	public void onStart() {
		super.onStart();
		
		Button btn_Search = (Button) getView().findViewById(R.id.btn_Find_Search);
		btn_Search.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				EditText edtRoomNr = (EditText) getView().findViewById(R.id.edt_Find_RoomNr);
				
				Fragment fragment = new FragmentResult();
				Bundle args = new Bundle();
				args.putString(FragmentResult.FIND_EXTRA_ROOMNR, edtRoomNr.getText().toString());
				fragment.setArguments(args);
				
				FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
				fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
				
				/*Intent intent = new Intent(getActivity(), ResultActivity.class);
				intent.putExtra(ResultActivity.FIND_EXTRA_ROOMNR, edtRoomNr.getText().toString());
				startActivity(intent);*/
			}
		});
	}

	
	
}