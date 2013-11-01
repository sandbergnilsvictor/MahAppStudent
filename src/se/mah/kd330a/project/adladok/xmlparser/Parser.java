package se.mah.kd330a.project.adladok.xmlparser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import se.mah.kd330a.project.adladok.model.Course;
import se.mah.kd330a.project.adladok.model.Me;

import android.util.Log;

public class Parser {

    
	public static void updateMeFromADandLADOK(String xmlFromWebservice) throws Exception{
        XMLParser parser = new XMLParser();
		if (xmlFromWebservice!=null){
			Document doc = parser.getDomElement(xmlFromWebservice); // getting DOM element
			//firstname
			NodeList nl = doc.getElementsByTagName("givenname");
			Element e = (Element)nl.item(0);
			if (e!=null){
				Me.setFirstName(parser.getElementValue(e));
			}
			//lastname
			nl = doc.getElementsByTagName("lastname");
			e = (Element)nl.item(0);
			if (e!=null){
				Me.setLastName(parser.getElementValue(e));
			}
			//displayname
			nl = doc.getElementsByTagName("displayname");
			e = (Element)nl.item(0);
			if (e!=null){
				Me.setDispayName(parser.getElementValue(e));
			}
			//email
			nl = doc.getElementsByTagName("mahmail");
			e = (Element)nl.item(0);
			if (e!=null){
				Me.setEmail(parser.getElementValue(e));
			}
			//isstaff
			nl = doc.getElementsByTagName("mahisstaff");
			e = (Element)nl.item(0);
			if (e!=null){
				String s = parser.getElementValue(e);
				if (s.equals("True")){
					Me.setIsStaff(true);
				}else{
					Me.setIsStaff(false);
				}
			}
			//isstudent
			nl = doc.getElementsByTagName("mahisstudent");
			e = (Element)nl.item(0);
			if (e!=null){
				String s = parser.getElementValue(e);
				if (s.equals("True")){
					Me.setIsStudent(true);
				}else{
					Me.setIsStudent(false);
				}
			}
			// add the courses......
			nl = doc.getElementsByTagName("courses");
			e = (Element) nl.item(0);			
			//Get the first station
			NodeList courseNode = e.getElementsByTagName("course");
			for (int j =0;j < courseNode.getLength();j++){
				Element e2 = (Element) courseNode.item(j);
				Course course = new Course(parser.getValue(e2, "displaynamesv"), parser.getValue(e2, "courseid"));
				course.setDisplaynameen(parser.getValue(e2,"displaynameen"));
				course.setRegCode(parser.getValue(e2,"regcode"));
				course.setProgram(parser.getValue(e2,"program"));
				course.setTerm(parser.getValue(e2,"term"));
				Me.addCourse(course);
			}
		}
    }
}
