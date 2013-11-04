package se.mah.kd330a.project.find.data;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;

public class LoadImgFromNet {
	Context mContext;
	OnImageLoadedListener mListener;

	public interface OnImageLoadedListener {
		public void onImageRecieved(String imageName);

	}

	public LoadImgFromNet(Context c, Fragment frag) {
		mContext = c;
		try {
			mListener = (OnImageLoadedListener) frag;
		} catch (ClassCastException e) {
			throw new ClassCastException(mContext.toString()
					+ " must implement OnImageLoadedListener");
		}
	}

	public void loadImagesFromNet(String list, Context c) {
		new DownloadFilesTask().execute(list);
	}

	private class DownloadFilesTask extends AsyncTask<String, Void, Void> {
		String fileName;

		protected Void doInBackground(String... fileNames) {
			//for (int i = 0; i < fileNames.length; i++) {
			fileName = fileNames[0];
			
			if (!GetImage.doesFileExists(fileNames[0],mContext)){
				Log.i("project", "DownloadFilesTask " + fileName);
				GetImage.getImageFromNet(fileNames[0], true, mContext);		
			}
		//}
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		//mListener.onImageRecieved(fileName);
	}


}
}
