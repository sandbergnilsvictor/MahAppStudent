package se.mah.kd330a.project.find.view;

import se.mah.kd330a.project.R;
import se.mah.kd330a.project.find.data.GetImage;
import se.mah.kd330a.project.find.data.ImageLoader;
import se.mah.kd330a.project.find.data.ImageLoader.OnImageLoaderListener;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.support.v4.app.Fragment;
//import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


public class FragmentStep extends Fragment implements OnImageLoaderListener {
	public static final String ARG_PICNAME = "pic";
	public static final String ARG_TEXTTITLE = "title";
	public static final String ARG_TEXTCONTENT = "content";
	public static final String ARG_ARROW = "arrow";

	private String mImgName;
	private String mArrowName;
	private String mRoomNumber;
	private String mContentId;
	
	private ImageView imgNav;
	private ProgressBar prgBar;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		Bundle args = getArguments();
		View rootView = inflater.inflate(R.layout.fragment_screen_find_step, container, false);

		mImgName = args.getString(ARG_PICNAME);
		mArrowName = args.getString(ARG_ARROW);
		mRoomNumber = args.getString(ARG_TEXTTITLE);
		mContentId = args.getString(ARG_TEXTCONTENT);
		
		imgNav = (ImageView) rootView.findViewById(R.id.img_find_navigation);
		imgNav.setVisibility(View.INVISIBLE);
		prgBar = (ProgressBar) rootView.findViewById(R.id.pb_find_loading);
		prgBar.setVisibility(View.VISIBLE);
		
		String txt = getString(R.string.find_navigationTitle) + " " + mRoomNumber;
		((TextView) rootView.findViewById(R.id.text_find_navigationTitle))
				.setText(txt);
		
		int strDown;
		try {
			strDown = getResources().getIdentifier(mContentId, "string", getActivity().getPackageName());
			((TextView) rootView.findViewById(R.id.text_find_navigationContent)).setText(getString(strDown));
		}
		catch (Exception e) {
			strDown = -1;
			e.printStackTrace();
		}
		new ImageLoader(getActivity(), this).execute(mImgName);

		((ImageView) rootView.findViewById(R.id.img_find_arrows))
				.setImageDrawable(loadImage(mArrowName));

		return rootView;
	}

	private Drawable loadImage(String pic) {
		Drawable buffer = null;
		try {
			buffer = getResources().getDrawable(getResources()
					.getIdentifier(pic, "drawable", getActivity().getPackageName()));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer;
	}

	@Override
	public void onImageReceived(String fileName) {
		prgBar.setVisibility(View.INVISIBLE);
		imgNav.setImageBitmap(GetImage.getImageFromLocalStorage(fileName, getActivity()));
		imgNav.setVisibility(View.VISIBLE);
	}

}
