package se.mah.kd330a.project.itsl;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import org.mcsoxford.rss.RSSItem;

/*
 * @author asampe
 * 
 * @author marcusmansson
 * 
 * this is a wrapper class for RSSItem, adding features such as making the
 * articles sortable, and method for getting a summary text for large content
 * 
 * version history:
 * 0.7.2 added Serializable, changed constructor // marcusmansson
 * 
 */
public class Article implements Comparable<Article>, Serializable
{
	private static final long serialVersionUID = 1L;
	private static final int maxSummaryLength = 80;
	private transient RSSItem rssItem;
	private boolean textVisible;
	private String articleCourseCode;
	private String articleHeader;
	private String articleDate;
	private Date articlePubDate;
	private String articleText;

	public Article(RSSItem item)
	{
		super();

		rssItem = item;
		textVisible = true;

		articleCourseCode = "";
		articlePubDate = rssItem.getPubDate();
		articleDate = articlePubDate.toString();
		articleHeader = rssItem.getTitle();

		/*
		 * "It's learning" puts the content in the <description> and leaves
		 * <content> empty...
		 */

		if (rssItem.getContent() != null)
		{
			articleText = android.text.Html.fromHtml(rssItem.getContent()).toString();
		}
		else
		{
			articleText = android.text.Html.fromHtml(rssItem.getDescription()).toString();
		}
	}

	public String getArticleHeader()
	{
		return articleHeader;
	}

	public String getArticleDate()
	{
		return articleDate;
	}

	public Date getArticlePubDate()
	{
		return articlePubDate;
	}

	public String getArticleText()
	{
		return articleText;
	}

	public String getArticleSummary()
	{
		String summary = getArticleText();

		if (summary.length() > maxSummaryLength)
		{
			return summary.substring(0, maxSummaryLength) + "...";
		}
		else
		{
			return summary;
		}
	}

	public boolean isTextVisible()
	{
		return textVisible;
	}

	public void setTextVisible(boolean value)
	{
		textVisible = value;
	}

	public String getArticleCourseCode()
	{
		return articleCourseCode;
	}

	public void setArticleCourseCode(String courseCode)
	{
		articleCourseCode = courseCode;
	}

	public String toString()
	{
		return getArticleHeader();
	}

	@Override
	public int compareTo(Article another)
	{
		return another.getArticlePubDate().compareTo(getArticlePubDate());
	}

	private void writeObject(java.io.ObjectOutputStream out) throws IOException
	{
		out.writeBoolean(textVisible);
		out.writeObject(articleCourseCode);
		out.writeObject(articleHeader);
		out.writeObject(articleDate);
		out.writeObject(articlePubDate);
		out.writeObject(articleText);
	}

	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException
	{
		textVisible = in.readBoolean();
		articleCourseCode = (String) in.readObject();
		articleHeader = (String) in.readObject();
		articleDate = (String) in.readObject();
		articlePubDate = (Date) in.readObject();
		articleText = (String) in.readObject();
	}

}
