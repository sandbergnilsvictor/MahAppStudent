package se.mah.kd330a.project.framework;




import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import se.mah.kd330a.project.R;
import se.mah.kd330a.project.home.data.*;

import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;



public class SplashActivity extends Activity {

    //how long until we go to the next activity
    protected int _splashTime = 4000;
    private String RSSNEWSFEEDURL = "http://www.mah.se/Nyheter/RSS/Nyheter-fran-Malmo-hogskola/";
    RSSFeed feed;
    FileOutputStream fout = null;
    ObjectOutputStream out = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		if (conMgr.getActiveNetworkInfo() == null
				&& !conMgr.getActiveNetworkInfo().isConnected()
				&& !conMgr.getActiveNetworkInfo().isAvailable()) {
			// No connectivity - Show alert
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(
					"Unable to reach server, \nPlease check your connectivity.")
					.setTitle("Itslearning")
					.setCancelable(false)
					.setPositiveButton("Exit",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int id) {
									finish();
								}
							});

			AlertDialog alert = builder.create();
			alert.show();

		} else {
        new GetDataTask().execute();
		}
       
    }
    
    private class GetDataTask extends AsyncTask<Void, Void, String[]> {
    			

		@Override
		protected String[] doInBackground(Void... params) {
			// Simulates a background job.
			try {
			DOMParser myParser = new DOMParser();
			feed = myParser.parseXml(RSSNEWSFEEDURL);
			} catch (Exception e) {
				
			}
			return null;

		}

		@Override
		protected void onPostExecute(String[] result) {
			try {
				fout = openFileOutput("filename", Context.MODE_PRIVATE);
		        out = new ObjectOutputStream(fout);
		        out.writeObject(feed);
		        out.close();
		        fout.close();
		        
		    } catch (IOException ioe) {
		        System.out.println("Error in save method");

		    } finally {
		
		   
			Intent intent = new Intent(SplashActivity.this, MainActivity.class);
			startActivity(intent);
			Log.i("onPostExecute", Integer.toString(feed.getItemCount()));
			finish();
		    }
		}
    
			
			
			
		
	}
}
