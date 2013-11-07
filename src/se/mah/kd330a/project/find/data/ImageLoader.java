package se.mah.kd330a.project.find.data;

import android.content.Context;
import android.graphics.Bitmap;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

public class ImageLoader extends AsyncTask<String, Void, Bitmap> {
	Context mContext;
	String mLoadingFile;
	ImageView mImagePlace;
	
	public ImageLoader(Context context, ImageView imagePlace) {
		mContext = context;
		mImagePlace = imagePlace;
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
			mImagePlace.setImageBitmap(result);
   	 
		super.onPostExecute(result);
	}
	
	
	
	
	
	/*Context mContext;
	String mLoadingFile;
	
	public ImageLoader(Context context) {
		super(context);
		
		mContext = getContext();
		//mLoadingFile = imgFile;
	}

	@Override
	public Bitmap loadInBackground() {
		if (!GetImage.doesFileExists(mLoadingFile, mContext)){
			Log.i("project", "DownloadFilesTask " + mLoadingFile);
			GetImage.getImageFromNet(mLoadingFile, true, mContext);		
		}
		return GetImage.getImageFromLocalStorage(mLoadingFile, mContext);
		
	}	*/

}


