package se.mah.kd330a.project.itsl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.mcsoxford.rss.RSSFeed;
import org.mcsoxford.rss.RSSItem;
import android.content.Context;
import android.util.Log;

/**
 * @author marcusmansson
 * 
 * FeedManager is responsible for managing several feeds, fetching them
 * sequentially and creating article objects of the items found.
 * 
 * FeedManager will return all articles to the registered
 * FeedCompleteListener when done.
 * 
 * Usage:
 * FeedManager fm = new FeedManager(this, this);
 * fm.addFeedURL(url); // for all urls you want to process, then
 * fm.processFeeds();
 * 
 */
public class FeedManager implements FeedDownloadTask.FeedCompleteListener
{
	private final String TAG = "FeedManager";
	private final String CACHE_FILENAME = "article_cache.ser";
	private FeedDownloadTask downloadTask;
	private FeedManagerDoneListener callbackHandler;
	private ArrayList<Article> articleList;
	private Context appContext;
	private int feedQueueCounter;
	private ArrayList<String> feedList;
		
	/*
	 * the listener must implement these methods
	 */
	public interface FeedManagerDoneListener
	{
		public void onFeedManagerDone(FeedManager fm, ArrayList<Article> articles);
		public void onFeedManagerProgress(FeedManager fm, int progress, int max);
	}

	public FeedManager(FeedManagerDoneListener callbackHandler, Context context)
	{
		appContext = context;
		articleList = new ArrayList<Article>();
		feedList = new ArrayList<String>();
		feedQueueCounter = 0;
		
		try
		{
			this.callbackHandler = (FeedManagerDoneListener) callbackHandler;
		}
		catch (ClassCastException e)
		{
			throw new ClassCastException(callbackHandler.toString()
					+ " must implement FeedManagerDoneListener");
		}
	}

	public void addFeedURL(String url) // throws MalformedURLException
	{
		Log.i(TAG, url);
		feedList.add(url);
	}

	public int queueSize()
	{
		return feedList.size();
	}

	public void removeFeedURL(String url)
	{
		feedList.remove(url);
	}

	/**
	 * prepare for re-processing of feeds list; clears article list and resets
	 * feed queue
	 */
	public void reset()
	{
		feedQueueCounter = 0;
		articleList.clear();
	}

	public ArrayList<Article> getArticles()
	{
		return articleList;
	}

	@Override
	public void onFeedComplete(RSSFeed feed)
	{
		if (downloadTask.hasException())
		{
			Log.e(TAG, downloadTask.getException().toString());
		}
		else
		{
			Article article;
			String feedDescription = feed.getTitle();
			
			for (RSSItem rssItem : feed.getItems())
			{
				article = new Article(rssItem);
				articleList.add(article);
				article.setArticleCourseCode(feedDescription);
			}
		}

		if (feedQueueCounter < feedList.size())
		{
			/*
			 *  process next feed in queue
			 */
			processFeeds();
		}
		else
		{
			/*
			 * all feeds have been read so let's reset counter. 
			 * this way it's possible to call processFeeds() 
			 * again if we just want to refresh articleList.
			 */
			feedQueueCounter = 0;

			/*
			 * sorts the list by date in descending order
			 */
			Collections.sort(articleList);

			try
			{
				saveCache();
			}
			catch (Exception e)
			{
				Log.e(TAG, e.toString());
			}

			/*
			 *  return the complete list of articles to the listener
			 *  when all items in the feed queue are processed
			 */
			callbackHandler.onFeedManagerDone(this, getArticles());
		}
	}

	/**
	 * downloads articles from one feed at a time, you must add feeds using
	 * addFeedURL(String url) before calling this method
	 */
	public void processFeeds()
	{
		if (feedList.isEmpty())
		{
			Log.e(TAG, "Feed list is empty, adding some feeds:");
			addFeedURL("https://mah.itslearning.com/Bulletin/RssFeed.aspx?LocationType=1&LocationID=18178&PersonId=25776&CustomerId=719&Guid=d50eaf8a1781e4c8c7cdc9086d1248b1&Culture=sv-SE");
			addFeedURL("https://mah.itslearning.com/Bulletin/RssFeed.aspx?LocationType=1&LocationID=16066&PersonId=71004&CustomerId=719&Guid=52845be1dfae034819b676d6d2b18733&Culture=sv-SE");
			addFeedURL("https://mah.itslearning.com/Bulletin/RssFeed.aspx?LocationType=1&LocationID=18190&PersonId=94952&CustomerId=719&Guid=96721ee137e0c918227093aa54f16f80&Culture=en-GB");
			addFeedURL("https://mah.itslearning.com/Dashboard/NotificationRss.aspx?LocationType=1&LocationID=18178&PersonId=25776&CustomerId=719&Guid=d50eaf8a1781e4c8c7cdc9086d1248b1&Culture=sv-SE");
		}

		/*
		 * notify the UI of update
		 */
		callbackHandler.onFeedManagerProgress(this, feedQueueCounter + 1, feedList.size());

		/* 
		 * there can only be one task at any time and it can only be used once
		 */
		downloadTask = new FeedDownloadTask(this);

		/* 
		 * we want to process the next url in queue but not pop it from the queue,
		 * in case we want to get all feeds again later (i.e. to refresh), that's
		 * why we use a counter to keep track of where in the queue we are 
		 */
		downloadTask.execute(feedList.get(feedQueueCounter++));
	}

	private void saveCache() throws Exception
	{
		/*
		 * don't overwrite saved data with nothing 
		 */
		if (!articleList.isEmpty())
		{
			FileOutputStream fos = appContext.openFileOutput(CACHE_FILENAME, Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(articleList);
			fos.close();
		}
		else
		{
			Log.e(TAG, "Nothing to save, are we online?");
		}
	}

	@SuppressWarnings("unchecked")
	public void loadCache()
	{
		/*
		 *  check for cache file
		 */
		if (appContext.getFileStreamPath(CACHE_FILENAME).exists())
		{
			/*
			 * load data
			 */
			try
			{
				FileInputStream fis = appContext.openFileInput(CACHE_FILENAME);
				ObjectInputStream ois = new ObjectInputStream(fis);
				articleList.clear();
				articleList.addAll((List<Article>) ois.readObject());
				fis.close();
			}
			catch (Exception e)
			{
				Log.e(TAG, e.toString());

				/*
				 *  something is probably wrong with the cache file so let's delete it
				 */
				deleteCache();
			}
		}
	}

	public void deleteCache()
	{
		Log.i(TAG, "Deleting file: " + appContext.getFileStreamPath(CACHE_FILENAME).toString());
		appContext.getFileStreamPath(CACHE_FILENAME).delete();
	}
}
