package se.mah.kd330a.project.find;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

public class PathToRoom {

	String roomNr = null;
	List <String> path = null;
	List <String> texts = null;
	int coord_x = -1;
	int coord_y = -1;
	String mapPic = null;

	public PathToRoom(String room) {
		roomNr = room;
	}

	public void setPath(String pathString) {
		path = new ArrayList<String>();
		String[] strPath = pathString.split("_");

		for(String str: strPath)
			path.add(str);
		}

		public List<String> getPath() {
			return path;
		}

		public void setTextList(String textString) {
			texts = new ArrayList<String>();
			String[] strTexts = textString.split("_");

			for(String str: strTexts)
				texts.add(str);
			
			Log.i("project", texts.toString());
		}

		public List<String> getTexts() {
			return texts;
		}

		public void setMapPic(String map) {

		}

	}
