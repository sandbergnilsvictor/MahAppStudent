package se.mah.kd330a.project.adladok.model;

public class Course {
	 private String displaynamesv;
	 private String displaynameen;
	 private String courseID;
	 private String regCode;
	 private String program;
	 private String term;
	 
	 
	 public Course(String displayName, String courseID) {
		this.displaynamesv = displayName;
		this.courseID = courseID;
	}
	     public String getDisplaynameEn() {
			return displaynameen;
		}
		public void setDisplaynameen(String displaynameEn) {
			this.displaynameen = displaynameEn;
		}
		
		/**The course ID reused every year format KD330A*/
		public String getCourseID() {
			return courseID;
		}
		public void setCourseID(String courseID) {
			this.courseID = courseID;
		}

		/**the 6 numbered code used for identify a course a certain year*/ 
		public String getRegCode() {
			return regCode;
		}
		public void setRegCode(String regCode) {
			this.regCode = regCode;
		}
		public String getProgram() {
			return program;
		}
		public void setProgram(String program) {
			this.program = program;
		}
		public String getDisplaynameSv() {
			return displaynamesv;
		}
		public void setDisplaynameSv(String displayname) {
			this.displaynamesv = displayname;
		}

		/**Term in the format YYYYT where T is 1 for VT and 2 for HT*/
		public void setTerm(String term) {
			this.term = term;
		}
		public String getTerm() {
			return this.term;
		}

	}

