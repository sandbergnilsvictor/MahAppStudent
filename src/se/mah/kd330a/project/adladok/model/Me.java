package se.mah.kd330a.project.adladok.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import se.mah.kd330a.project.adladok.xmlparser.Parser;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class Me {
	//Static variables there is only one Me
	private static List<Course> myCourses = new ArrayList<Course>();
	private static String firstName;
	private static String lastName;
	private static String email;
	private static String dispayName;
	private static boolean isStaff = false;
	private static boolean isStudent = false;
    private static String TAG ="UserInfo";
	private static String userID;
	private static String password;
	public static MyObservable observable = new MyObservable(); 

	public static void setPassword(String password) {
		Me.password = password;
	}
	
	
	public static String getUserID() {
		return userID;
	}
	public static void setUserID(String userID) {
		Me.userID = userID;
	}
	
	private Me() {
		//prevents anyone from doing instances
	}
	
	public static void restoreMe(Context c){
		//Read local storage
	}
	
	public static void saveMe(Context c){
		
	}
	
	public static void updateMe(){
		myCourses.clear(); //Perhaps not....
		doUpdate(userID, password);
	}
	
	public static void clearCourses(){
		myCourses.clear();
	}
	
	public static List<Course> getCourses(){
		return myCourses;
	}
	
	public static void addCourse(Course course) {
		//Here check if it exist already then update it............
		Me.myCourses.add(course);
	}
	
	public static String getFirstName() {
		return firstName;
	}
	public static void setFirstName(String firstName) {
		Me.firstName = firstName;
	}
	public static String getLastName() {
		return lastName;
	}
	public static void setLastName(String lastName) {
		Me.lastName = lastName;
	}
	public static String getEmail() {
		return email;
	}
	public static void setEmail(String email) {
		Me.email = email;
	}
	public static String getDispayName() {
		return dispayName;
	}
	public static void setDispayName(String dispayName) {
		Me.dispayName = dispayName;
	}

	public static boolean isStaff() {
		return Me.isStaff;
	}
	public static void setIsStaff(boolean isStaff) {
		Me.isStaff = isStaff;
	}
	public static boolean isStudent() {
		return isStudent;
	}
	public static void setIsStudent(boolean isStudent) {
		Me.isStudent = isStudent;
	}
	
//Part handling updateddd
	
    private static final String NAMESPACE = "http://mahapp.k3.mah.se/";
    private static final String URL = "http://195.178.234.7/mahapp/userinfo.asmx";
    private static AsyncTask<String, Void, Void> asyncTask= null;
  //Only one update at a time
    private static void doUpdate(String userID, String password){
    	if(asyncTask!=null){
	    	if (asyncTask.getStatus()==AsyncTask.Status.FINISHED){
	    		asyncTask = new AsyncCallGetUserInfo().execute(userID,password);
	    	}
    	}else{
    		asyncTask = new AsyncCallGetUserInfo().execute(userID,password);
    	}
    }
    
    private static class AsyncCallGetUserInfo extends AsyncTask<String, Void, Void> {
	        @Override
	        protected Void doInBackground(String... params) {
	        	Log.i(TAG,"Starting update");
	            //get the info from web service
	        	String userInfoAsXML = getUserInfoAsXML(params[0],params[1]);
	            //parse the XML it and update the class Me{
	        	try{
	        		Parser.updateMeFromADandLADOK(userInfoAsXML);
	        	}catch(Exception e){
	        		//Log.i("UserInfo",e.getMessage());
	        	}
	            return null;
	        }
	        @Override
	        protected void onPostExecute(Void result) {
	        	Log.i(TAG,"Update finished");
		        super.onPostExecute(result);
		        observable.setChanged();  //Tell that we made changes....
		        observable.notifyObservers();  //Notify all listeners...
	        }
	    }
	 
	 public static String getUserInfoAsXML(String loginID, String password){	   
	     Object result="";
		 try {
	        	SoapObject loginrequest = new SoapObject(NAMESPACE, "getUserInfo");
	            loginrequest.addProperty("username", loginID);
	            loginrequest.addProperty("password", password);
	            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	            envelope.dotNet=true;
	            envelope.setOutputSoapObject(loginrequest);
	            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
	            androidHttpTransport.call(NAMESPACE+"getUserInfo", envelope);
	            result = (Object)envelope.getResponse();
	        } catch (Exception e) {
	        	//Log.i(TAG,"LoginError: "+e.getMessage());
	       }
	        return result.toString();
	    }
	 
	 ///--- for observer pattern
	 
	 public static class MyObservable extends Observable{  //Must be here to get hold on the protected setChanged
		 @Override
		protected void setChanged() {
			super.setChanged();
		}
	 }
	 
}
