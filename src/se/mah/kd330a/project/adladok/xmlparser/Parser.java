package se.mah.kd330a.project.adladok.xmlparser;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringWriter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlSerializer;

import se.mah.kd330a.project.adladok.model.Course;
import se.mah.kd330a.project.adladok.model.Me;

import android.content.Context;
import android.util.Log;
import android.util.Xml;

public class Parser {

    //"givenname""lastname""displayname""mahmail""mahisstaff""mahisstudent"
	//"courses""displaynamesv""course""courseid""displaynameen""regcode""program""term"
	
	/**
	 * Uppdate Me and courses from XML
	 * @param xml string
	 * */
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
				if (s.equals("True")||s.equals("true")){
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
				if (s.equals("True")||s.equals("true")){
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
				try{
					course.setColor(Integer.parseInt(parser.getValue(e2, "color")));
				}catch(Exception e7){}
				Me.addCourse(course);
			}
		}
    }
	
	/**
	 * Gett XML from file in localStorage
	 * @param file File 
	 * */
	public static String getXmlFromFile(File file) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try{
			InputStream  inputStream = new FileInputStream(file);
		    int i = inputStream.read();
		    while (i != -1)
		    {
		        byteArrayOutputStream.write(i);
		        i = inputStream.read();
		    }
		    inputStream.close();
		} catch (Exception e) {} 
	    return byteArrayOutputStream.toString();
	}

	/**
	 * Creates a XML file from Me and Courses
	 *
	 * */
	public static String writeXml(){
	    XmlSerializer serializer = Xml.newSerializer();
	    StringWriter writer = new StringWriter();
	    try {
	        serializer.setOutput(writer);
	        serializer.startDocument("UTF-8", true);
	        serializer.startTag("","user");
        	serializer.startTag("", "givenname");
        	serializer.text(Me.getFirstName());
        	serializer.endTag("", "givenname");
        	serializer.startTag("", "lastname");
        	serializer.text(Me.getLastName());
        	serializer.endTag("", "lastname");
        	serializer.startTag("", "displayname");
        	serializer.text(Me.getDispayName());
        	serializer.endTag("", "displayname");
        	serializer.startTag("", "mahmail");
        	serializer.text(Me.getEmail());
        	serializer.endTag("", "mahmail");
        	serializer.startTag("", "mahisstudent");
        	serializer.text(String.valueOf(Me.isStudent()));
        	serializer.endTag("", "mahisstudent");
        	serializer.startTag("", "mahisstaff");
        	serializer.text(String.valueOf(Me.isStudent()));
        	serializer.endTag("", "mahisstaff");
	        serializer.startTag("", "courses");
	        for (Course course: Me.getCourses()){
	        	serializer.startTag("", "course");
	        	serializer.startTag("", "displaynamesv");
	        	serializer.text(course.getDisplaynameSv());
	        	serializer.endTag("", "displaynamesv");
	        	serializer.startTag("", "displaynameen");
	        	serializer.text(course.getDisplaynameEn());
	        	serializer.endTag("", "displaynameen");
	        	serializer.startTag("", "regcode");
	        	serializer.text(course.getRegCode());
	        	serializer.endTag("", "regcode");
	        	serializer.startTag("", "program");
	        	serializer.text(course.getProgram());
	        	serializer.endTag("", "program");
	        	serializer.startTag("", "term");
	        	serializer.text(course.getProgram());
	        	serializer.endTag("", "term");
	        	serializer.startTag("", "color");
	        	serializer.text(String.valueOf(course.getColor()));
	        	serializer.endTag("", "color");
	            serializer.endTag("", "course");
	        }
	        serializer.endTag("", "courses");
	        serializer.endTag("","user");
	        serializer.endDocument();
	        return writer.toString();
	    } catch (Exception e) {
	        return"<user></user>";
	    } 
	}

}
