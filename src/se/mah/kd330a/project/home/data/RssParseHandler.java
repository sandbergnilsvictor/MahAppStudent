package se.mah.kd330a.project.home.data;

import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class RssParseHandler extends DefaultHandler  {
	
	private NewsMah currentNews;
	private ArrayList<NewsMah> news;
	private boolean title;
	private boolean link;
	private boolean description;
	private boolean creator;
	private boolean pubDate;
	
	StringBuilder sb;
	
	public RssParseHandler() {
		news = new ArrayList<NewsMah>();
	}
	
	public ArrayList<NewsMah> getRssNews() {
		return news;
	}
	
	
	
	
	@Override
	public void startElement (String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if ("item".equals(qName)) {
			currentNews = new NewsMah();
			Log.i("startElement", "start item");
		} else if ("title".equals(qName)) {
			title = true;
		} else if ("link".equals(qName)) {
			link = true;
		} else if ("description".equals(qName)) {
			description = true;
		} else if ("pubDate".equals(qName)) {
			pubDate = true;
		} else if ("dc:creator".equals(qName)) {
			creator = true;
		}
		
		
		
	}
	
	
	@Override
	public void endElement (String uri, String localName, String qName) throws SAXException {
		if ("item".equals(qName)) {
			news.add(currentNews);
			currentNews = null;
			Log.i("endElement", "end item");
		} else if ("title".equals(qName)) {
			title = false;
		} else if ("link".equals(qName)) {
			link = false;
		} else if ("description".equals(qName)) {
			description = false;
		} else if ("pubDate".equals(qName)) {
			pubDate = false;
		} else if ("dc:creator".equals(qName)) {
			creator = false;
		}
	}
	
	@Override
	public void characters (char[] ch, int start, int length) throws SAXException {
		if(currentNews != null) {
		
			if(title) {
				currentNews.setTitle(new String(ch, start, length));
			} else if (link) {
				currentNews.setLink(new String(ch, start, length));
			
			} else if (description) {
				currentNews.setDescription(new String(ch, start, length));
			
			} else if (pubDate) {
				currentNews.setPubDate(new String(ch, start, length));
			
			} else if (creator) {
				currentNews.setCreator(new String(ch, start, length));
			} 
		} 
		
	}
	
	
}
