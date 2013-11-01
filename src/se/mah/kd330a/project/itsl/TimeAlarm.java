package se.mah.kd330a.project.itsl;

import se.mah.kd330a.project.R;
import java.util.ArrayList;
import java.util.Date;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;


public class TimeAlarm extends IntentService implements FeedManager.FeedManagerDoneListener
{
	private static final String TAG = "TimeAlarm";
	private Date latestUpdate;
	
	public TimeAlarm()
	{
		super(TAG);
	}

	@Override
	protected void onHandleIntent(Intent intent)
	{
		if (getApplicationContext() != null)
		{
			FeedManager feedManager = new FeedManager(this, getApplicationContext());
			feedManager.processFeeds();
			Log.e(TAG, "processing feeds");
		}
		else
		{
			Log.e(TAG, "getApplicationContext(); is null");
		}
	}

	public void onFeedManagerProgress(FeedManager fm, int progress, int max)
	{
		/*
		 * we don't care about progress here
		 */
	}

	public void onFeedManagerDone(FeedManager fm, ArrayList<Article> articles)
	{
		latestUpdate = Util.getLatestUpdate(getApplicationContext());

		if (articles.isEmpty())
		{
			Log.e(TAG, fm.getClass().toString() + " returned 0 articles, are we online?");
		}
		else
		{
			ArrayList<Article> newArticles = new ArrayList<Article>();
			for (Article a : articles)
			{
				if (a.getArticlePubDate().compareTo(latestUpdate) > 0)
				{
					Log.i(TAG, "adding article to newarticle-list: " + a.getArticleHeader());
					newArticles.add(a);
<<<<<<< HEAD
				}
			}
	
=======
			
>>>>>>> origin/master
			if (newArticles.size() > 0)
				createNotification(newArticles);
		}
	}

	private void createNotification(ArrayList<Article> articles)
	{
		Intent resultIntent = new Intent(this, se.mah.kd330a.project.framework.MainActivity.class);

		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		stackBuilder.addParentStack(se.mah.kd330a.project.framework.MainActivity.class);
		stackBuilder.addNextIntent(resultIntent);
		
		NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
		
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
		mBuilder.setSmallIcon(R.drawable.ic_menu_itsl);
		mBuilder.setContentTitle("ITs title");
		mBuilder.setTicker("ITs ticker");
		mBuilder.setAutoCancel(true);
		mBuilder.setContentInfo("Content info");
		mBuilder.setWhen(System.currentTimeMillis());
		mBuilder.setContentIntent(stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT));
		mBuilder.setStyle(inboxStyle);

		inboxStyle.setBigContentTitle("News from Itslearning");
		inboxStyle.setSummaryText("ITs summary");

		for (Article a : articles)
			inboxStyle.addLine(a.getArticleHeader());


		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(0, mBuilder.build());
	}
}
