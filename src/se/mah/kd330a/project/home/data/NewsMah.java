package se.mah.kd330a.project.home.data;

public class NewsMah {
	
	private String title;
	private String link;
	private String description;
	private String creator;
	private String pubDate;
	
	public NewsMah() {
		
	}
	
	public NewsMah(String title, String link, String description, String creator, String pubDate) {
		this.title = title;
		this.link = link;
		this.description = description;
		this.creator = creator;
		this.pubDate = pubDate;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

}
