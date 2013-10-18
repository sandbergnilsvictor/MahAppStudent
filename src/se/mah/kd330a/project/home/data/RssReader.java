package se.mah.kd330a.project.home.data;

import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class RssReader {
	
	private String rssUrl;
	
	public RssReader(String rssUrl) {
		this.rssUrl = rssUrl;
	}
	
	public ArrayList<NewsMah> getNews() throws Exception {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		
		RssParseHandler handler = new RssParseHandler();
		saxParser.parse(rssUrl, handler);
		return handler.getRssNews();
		
	}
	

}
