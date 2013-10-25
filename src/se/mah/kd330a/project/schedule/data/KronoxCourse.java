package se.mah.kd330a.project.schedule.data;


public class KronoxCourse {
	private final String code;
	private final String name;
	public KronoxCourse(String code) {
		assert code.matches("\\w+-\\d+-\\w+");
		this.code = code;
		this.name = code;
	}
	public KronoxCourse(String code, String name) {
		assert code.matches("\\w+-\\d+-\\w+");
		this.code = code;
		this.name = name;
	}
	/**
	 * @return Returns all three parts of the course code bound together by
	 *         dashes. For example: "KD330A-20132-62311"
	 */
	public String getFullCode() {
		return code;
	}
	public String getName() {
		return name;
	}
}
