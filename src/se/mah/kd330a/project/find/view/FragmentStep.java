package se.mah.kd330a.project.find.view;

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

public class FragmentStep extends Fragment {
	public static final String ARG_PICNAME = "pic";
	public static final String ARG_TEXTTITLE = "title";
	public static final String ARG_TEXTCONTENT = "content";
	public static final String ARG_ARROW = "arrow";

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
			
			String txt = getString(R.string.find_navigationTitle) + " " + args.getString(ARG_TEXTTITLE);
			((TextView) rootView.findViewById(R.id.text_find_navigationTitle))
					.setText(txt);
			
			Log.i("project", "content" + args.getString(ARG_TEXTCONTENT));
			Log.i("project", "title" + args.getString(ARG_TEXTTITLE));
			
			
			
		}
		catch (Exception e) {
			strDown = -1;
			e.printStackTrace();
		}

		((ImageView) rootView.findViewById(R.id.img_find_navigation))
				.setImageDrawable(loadImage(args.getString(ARG_PICNAME)));
		
		((ImageView) rootView.findViewById(R.id.img_find_arrows))
				.setImageDrawable(loadImage(args.getString(ARG_ARROW)));
		
		return rootView;
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
