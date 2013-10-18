package se.mah.kd330a.project.home.data;

import java.util.ArrayList;


public class NewsFeedMahParser {
	
	public final int NUMBER_OF_NEWS_PRESENTED = 8;
	private ArrayList<NewsMah> listOfNewsMah;
	
	public NewsFeedMahParser() {	
		listOfNewsMah = new ArrayList<NewsMah>();
		generateNews();
	}

	private void generateNews() {
		for (int i = 0; i < NUMBER_OF_NEWS_PRESENTED; i++) {
			
		}
		
	}
	
	public ArrayList<NewsMah> getAllNews() {
		return listOfNewsMah;
	}
	
	
}
