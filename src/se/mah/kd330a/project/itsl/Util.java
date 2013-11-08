package se.mah.kd330a.project.itsl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.provider.Browser;
import android.util.Log;

public class Util
{
	private static final String TAG = "Util";
	private static final String PREFS_NAME = "data_storage.dat";

	public static Date getLatestUpdate(Context context)
	{
		SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		Date date = new Date(settings.getLong("latestUpdate", 0));
		
		Log.i(TAG, "Latest update was: " + date.toString());
		return date;
	}

	public static void setLatestUpdate(Context context, Date date)
	{
		SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
		editor.putLong("latestUpdate", date.getTime());
		editor.commit();

		Log.i(TAG, "Latest update was set to: " + date.toString());
	}

	/**
	 * Look through the bookmarks in the browser for rss feeds
	 */
	public static List<String> getBrowserBookmarks(Context context)
	{
		ArrayList<String> urls = new ArrayList<String>();
		String[] proj = new String[] { Browser.BookmarkColumns.TITLE, Browser.BookmarkColumns.URL };
		String sel = Browser.BookmarkColumns.BOOKMARK + " = 1"; // 0 = history, 1 = bookmark
		Cursor mCur;
		
		try
		{
			mCur = context.getContentResolver().query(Browser.BOOKMARKS_URI, proj, sel, null, null);

			if (mCur != null && mCur.moveToFirst() && mCur.getCount() > 0)
			{
				String title;
				String url;

				do
				{
					title = mCur.getString(mCur.getColumnIndex(Browser.BookmarkColumns.TITLE));
					url = mCur.getString(mCur.getColumnIndex(Browser.BookmarkColumns.URL));

					/*
					 *  Check if the bookmark is an it's learning feed
					 */
					if (url.contains("https://mah.itslearning.com/"))
					{
						/*
						 * Course news - look for RssFeed, dashboard - look for NotificationRss
						 * url.contains("Rss") gives you both
						 */
						if (url.contains("Rss"))
						{
							Log.i(TAG, "Bookmark found: " + url);
							urls.add(url);
						}
					}
				} while (mCur.moveToNext());
				
			}
			
			if (mCur != null)
				mCur.close();
		}
		catch (Exception e)
		{
			Log.i(TAG, "getBrowserBookmarks(): handled " + e.toString());
		}

		return urls;
	}

}
