package se.mah.kd330a.project.find;

import se.mah.kd330a.project.R;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class NavStepFragment extends Fragment {
	public static final String ARG_PICNAME = "pic";
	public static final String ARG_TEXTTITLE = "title";
	public static final String ARG_TEXTCONTENT = "content";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_screen_find_step, container, false);
		Bundle args = getArguments();

		int strDown;

		try {
			strDown = getResources().getIdentifier(args.getString(ARG_TEXTCONTENT), 
					"string", getActivity().getPackageName());
			((TextView) rootView.findViewById(R.id.text_find_navigationContent))
					.setText(getString(strDown));
		}
		catch (Exception e) {
			strDown = -1;
			e.printStackTrace();
		}

		((ImageView) rootView.findViewById(R.id.img_find_navigation))
				.setImageDrawable(loadImageFromAssets(args.getString(ARG_PICNAME)));
		
		return rootView;
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
