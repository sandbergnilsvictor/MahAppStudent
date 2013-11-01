package se.mah.kd330a.project.home.data;

import java.io.Serializable;

public class RSSItem implements Serializable {

	private static final long serialVersionUID = 1L;
	private String _title = null;
	private String _description = null;
	private String _date = null;
	private String _link = null;
	private String _creator = null;

	void setTitle(String title) {
		_title = title;
	}

	void setDescription(String description) {
		_description = description;
	}

	void setDate(String pubdate) {
		_date = pubdate;
	}


	public String getTitle() {
		return _title;
	}

	public String getDescription() {
		return _description;
	}

	public String getDate() {
		return _date;
	}


	public String getLink() {
		return _link;
	}

	public void setLink(String link) {
		this._link = link;
	}

	public String getCreator() {
		return _creator;
	}

	public void setCreator(String creator) {
		this._creator = creator;
	}

}
