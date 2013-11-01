package se.mah.kd330a.project.find.data;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

public class PathToRoom {

	String mRoomNr = null;
	List <String> mPath = null;
	List <String> mTexts = null;
	List <String> mArrows = null;
	int mCoord_x = -1;
	int mCoord_y = -1;
	String mMapPic = null;
	String mRoomName;

	public PathToRoom(String room) {
		mRoomNr = room;
	}

	public void setPath(String pathString) {
		mPath = new ArrayList<String>();
		String[] strPath = pathString.split("_");

		for(String str: strPath)
			mPath.add(str);
	}

	public List<String> getPath() {
		return mPath;
	}

	public void setTextList(String textString) {
		mTexts = new ArrayList<String>();
		String[] strTexts = textString.split("_");

		for(String str: strTexts)
			mTexts.add(str);

		Log.i("project", mTexts.toString());
	}

	public List<String> getTexts() {
		return mTexts;
	}
	
	public void setArrowList(String arrowString) {
		mArrows = new ArrayList<String>();
		String[] strArrow = arrowString.split("_");

		for(String str: strArrow)
			mArrows.add(str);
	}

	public List<String> getArrows() {
		return mArrows;
	}

	public String getRoomName() {
		return mRoomNr;
	}

}
