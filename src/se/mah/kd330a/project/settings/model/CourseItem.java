package se.mah.kd330a.project.settings.model;

import java.io.Serializable;

public class CourseItem implements Serializable{

	private static final long serialVersionUID = 1L;
	private String displayName; 
	private String kronoxId;
	private String itslId;
	
	public CourseItem (String displayName, String kronoxId, String itslId) {
		this.setDisplayName(displayName);
		this.setKronoxId(kronoxId);
		this.setItslId(itslId); 
	}
	
	public CourseItem() {
		
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getKronoxId() {
		return kronoxId;
	}

	public void setKronoxId(String kronoxId) {
		this.kronoxId = kronoxId;
	}

	public String getItslId() {
		return itslId;
	}

	public void setItslId(String itslId) {
		this.itslId = itslId;
	}
	

}
