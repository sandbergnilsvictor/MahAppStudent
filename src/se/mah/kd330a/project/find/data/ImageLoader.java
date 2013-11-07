package se.mah.kd330a.project.find.data;

import android.content.Context;
import android.graphics.Bitmap;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;

public class ImageLoader extends AsyncTask<String, Void, Bitmap> {
	Context mContext;
	String mLoadingFile;
	OnImageLoaderListener mListener;
	
	public interface OnImageLoaderListener {
		public void onImageReceived(String fileName);
	}
	
	public ImageLoader(Context context, Fragment frag) {
		mContext = context;
		try {
			mListener = (OnImageLoaderListener) frag;
		} catch (ClassCastException e) {
			throw new ClassCastException(frag.toString()
					+ " must implement OnImageLoaderListener");
		}
	}
	
	@Override
	protected Bitmap doInBackground(String... params) {
		mLoadingFile = params[0];
		if (!GetImage.doesFileExists(mLoadingFile, mContext)){
			Log.i("project", "DownloadFilesTask " + mLoadingFile);
			GetImage.getImageFromNet(mLoadingFile, true, mContext);		
		}
		return GetImage.getImageFromLocalStorage(mLoadingFile, mContext);
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		if (result != null)
			mListener.onImageReceived(mLoadingFile);
   	 
		super.onPostExecute(result);
	}

}


