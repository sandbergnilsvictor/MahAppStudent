package se.mah.kd330a.project.itsl;

import se.mah.kd330a.project.R;
import java.util.ArrayList;
import java.util.Date;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
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
		Log.e(TAG, "feedmanager done, latest update " + latestUpdate);

		if (articles.isEmpty())
		{
			Log.e(TAG, fm.getClass().toString() + " returned 0 articles, are we online?");
		}
		else
		{
			Log.e(TAG, "feedmanager has articles: " + articles.size());
			ArrayList<Article> newArticles = new ArrayList<Article>();
			for (Article a : articles)
			{
				if (a.getArticlePubDate().compareTo(latestUpdate) > 0)
				{
					Log.i(TAG, "new article: " + a.getArticleHeader());
					newArticles.add(a);
				}
			}
			Log.e(TAG, "we have new articles: " + newArticles.size());
			
			//if (newArticles.size() > 0)
				createNotification(newArticles);
		}
	}

	private void createNotification(ArrayList<Article> articles)
	{
		//invoking the default notification service
		Notification.Builder mBuilder = new Notification.Builder(this);
		mBuilder.setContentTitle("New Message");
		mBuilder.setTicker("New Itslearning post");
		mBuilder.setSmallIcon(R.drawable.ic_launcher);
		mBuilder.setAutoCancel(true);

		// Add Big View Specific Configuration 
		Notification.InboxStyle inboxStyle = new Notification.InboxStyle();

		// Sets a title for the Inbox style big view
		inboxStyle.setBigContentTitle("News from Itslearning");
		
		Log.e(TAG, "we have new articles 2: " + articles.size());

		// Moves events into the big view
		for (Article a : articles)
			inboxStyle.addLine(a.getArticleHeader());

		inboxStyle.addLine("TEST!!!");
		inboxStyle.addLine("TEST IGEN!!!");
		mBuilder.setStyle(inboxStyle);

		// Creates an explicit intent in the app
		Intent resultIntent = new Intent(this, se.mah.kd330a.project.framework.MainActivity.class);

		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		stackBuilder.addParentStack(se.mah.kd330a.project.framework.MainActivity.class);

		// ads the intent that starts the activity to the top of the stack
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

		mBuilder.setContentIntent(resultPendingIntent);

		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		// mId allows you to update the notification later on.
		mNotificationManager.notify(0, mBuilder.build());

	}
}
