
package se.mah.kd330a.project.faq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;




import se.mah.kd330a.project.faq.ExpandableListFaqAdapter;
import se.mah.kd330a.project.R;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

public class FragmentFaq extends Fragment {
	
	ExpandableListFaqAdapter listAdapter;
	
	ExpandableListView expListFaqView;
	List<String> listDataHeader;
	List<String> listDataHeader2;
	List<Integer> imageHeader;
	HashMap<String, List<String>> listDataChild;

	
	public FragmentFaq () {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {	
	
		super.onCreate(savedInstanceState);
		
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { 
		
		ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.faq_activity_main, container, false);
		

		// get the listview
		expListFaqView = (ExpandableListView) rootView.findViewById(R.id.lvExp);
		
		// preparing list data
		prepareListData();

		listAdapter = new ExpandableListFaqAdapter(getActivity(), imageHeader, listDataHeader, listDataHeader2,
				listDataChild);

		// setting list adapter
		
		       expListFaqView.setIndicatorBounds(0, 300);
		  

		expListFaqView.setAdapter(listAdapter);
		
		
		
		expListFaqView.setOnChildClickListener(new OnChildClickListener() {
			String[] htmlListIt = getResources().getStringArray(
					R.array.html_links_it);
			String[] housingList = getResources().getStringArray(
					R.array.student_housing);
			String[] studentServiceList = getResources().getStringArray(
					R.array.student_service);
			String[] careerServiceList = getResources().getStringArray(
					R.array.career_service);
			String[] newStudentList = getResources().getStringArray(
					R.array.new_student);
			

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				final int selectedChild = (int) listAdapter.getChildId(
						groupPosition, childPosition);
				final int selectedGroup = (int) listAdapter
						.getGroupId(groupPosition);
				if (selectedGroup == 0 && selectedChild == 0) {

					Uri uriUrl = Uri.parse(htmlListIt[0]);
					Intent launchBrowser = new Intent(Intent.ACTION_VIEW,
							uriUrl);
					startActivity(launchBrowser);
				}
				if (selectedGroup == 0 && selectedChild == 1) {
					Uri uriUrl = Uri.parse(htmlListIt[1]);
					Intent launchBrowser = new Intent(Intent.ACTION_VIEW,
							uriUrl);
					startActivity(launchBrowser);

				}
				if (selectedGroup == 0 && selectedChild == 2) {
					Uri uriUrl = Uri.parse(htmlListIt[2]);
					Intent launchBrowser = new Intent(Intent.ACTION_VIEW,
							uriUrl);
					startActivity(launchBrowser);

				}
				if (selectedGroup == 1 && selectedChild == 0) {
					Uri uriUrl = Uri.parse(housingList[0]);
					Intent launchBrowser = new Intent(Intent.ACTION_VIEW,
							uriUrl);
					startActivity(launchBrowser);

				}
				if (selectedGroup == 1 && selectedChild == 1) {
					Uri uriUrl = Uri.parse(housingList[1]);
					Intent launchBrowser = new Intent(Intent.ACTION_VIEW,
							uriUrl);
					startActivity(launchBrowser);

				}
				if (selectedGroup == 2 && selectedChild == 0) {
					Uri uriUrl = Uri.parse(studentServiceList[0]);
					Intent launchBrowser = new Intent(Intent.ACTION_VIEW,
							uriUrl);
					startActivity(launchBrowser);

				}
				if (selectedGroup == 3 && selectedChild == 0) {
					Uri uriUrl = Uri.parse(careerServiceList[0]);
					Intent launchBrowser = new Intent(Intent.ACTION_VIEW,
							uriUrl);
					startActivity(launchBrowser);

				}
				if (selectedGroup == 3 && selectedChild == 1) {
					Uri uriUrl = Uri.parse(careerServiceList[1]);
					Intent launchBrowser = new Intent(Intent.ACTION_VIEW,
							uriUrl);
					startActivity(launchBrowser);

				}
				if (selectedGroup == 3 && selectedChild == 2) {
					Uri uriUrl = Uri.parse(careerServiceList[2]);
					Intent launchBrowser = new Intent(Intent.ACTION_VIEW,
							uriUrl);
					startActivity(launchBrowser);

				}
				if (selectedGroup == 4 && selectedChild == 0) {
					Uri uriUrl = Uri.parse(newStudentList[0]);
					Intent launchBrowser = new Intent(Intent.ACTION_VIEW,
							uriUrl);
					startActivity(launchBrowser);

				}
				if (selectedGroup == 4 && selectedChild == 1) {
					Uri uriUrl = Uri.parse(newStudentList[1]);
					Intent launchBrowser = new Intent(Intent.ACTION_VIEW,
							uriUrl);
					startActivity(launchBrowser);

				}
				if (selectedGroup == 4 && selectedChild == 2) {
					Uri uriUrl = Uri.parse(newStudentList[2]);
					Intent launchBrowser = new Intent(Intent.ACTION_VIEW,
							uriUrl);
					startActivity(launchBrowser);

				}
				if (selectedGroup == 4 && selectedChild == 3) {
					Uri uriUrl = Uri.parse(newStudentList[3]);
					Intent launchBrowser = new Intent(Intent.ACTION_VIEW,
							uriUrl);
					startActivity(launchBrowser);

				}
				if (selectedGroup == 4 && selectedChild == 4) {
					Uri uriUrl = Uri.parse(newStudentList[4]);
					Intent launchBrowser = new Intent(Intent.ACTION_VIEW,
							uriUrl);
					startActivity(launchBrowser);

				}
				return false;
			}
		});
		
		return rootView;
		
	}
	private void prepareListData() {
		listDataHeader = new ArrayList<String>(); 
		listDataHeader2 = new ArrayList<String>();
		imageHeader = new ArrayList<Integer>();
		listDataChild = new HashMap<String, List<String>>();
		String[] faqHeaders = getResources().getStringArray(R.array.faqOptions);
		String [] faqSubs = getResources().getStringArray(R.array.faqSubOptions);
		imageHeader.add(R.drawable.itsupport_lager);
		imageHeader.add(R.drawable.housing);
		imageHeader.add(R.drawable.studentservice);
		imageHeader.add(R.drawable.careerservice);
		imageHeader.add(R.drawable.newstudent);
		
		String[] itOptions = getResources().getStringArray(
				R.array.itSupportOptions);
		String[] housingOptions = getResources().getStringArray(
				R.array.housingOptions);
		String[] studentOptions = getResources().getStringArray(
				R.array.studentOptions);
		String[] careerOptions = getResources().getStringArray(
				R.array.careerOptions);
		String[] newStudentOptions = getResources().getStringArray(
				R.array.newStudentOptions);
		
		// Adding group Data
		listDataHeader.add(faqHeaders[0]);
		listDataHeader.add(faqHeaders[1]);
		listDataHeader.add(faqHeaders[2]);
		listDataHeader.add(faqHeaders[3]);
		listDataHeader.add(faqHeaders[4]);
		// Adding subgroup Data
		listDataHeader2.add(faqSubs[0]);
		listDataHeader2.add(faqSubs[1]);
		listDataHeader2.add(faqSubs[2]);
		listDataHeader2.add(faqSubs[3]);
		listDataHeader2.add(faqSubs[4]);
		// Adding images to FaqList
	

		// Adding child data
		List<String> itSupport = new ArrayList<String>();
		itSupport.add(itOptions[0]);
		itSupport.add(itOptions[1]);
		itSupport.add(itOptions[2]);

		List<String> housing = new ArrayList<String>();
		housing.add(housingOptions[0]);
		housing.add(housingOptions[1]);

		List<String> studentService = new ArrayList<String>();
		studentService.add(studentOptions[0]);

		List<String> careerService = new ArrayList<String>();
		careerService.add(careerOptions[0]);
		careerService.add(careerOptions[1]);
		careerService.add(careerOptions[2]);

		List<String> newStudent = new ArrayList<String>();
		newStudent.add(newStudentOptions[0]);
		newStudent.add(newStudentOptions[1]);
		newStudent.add(newStudentOptions[2]);
		newStudent.add(newStudentOptions[3]);
		newStudent.add(newStudentOptions[4]);

		listDataChild.put(listDataHeader.get(0), itSupport); // Header, Child
																// data
		listDataChild.put(listDataHeader.get(1), housing);
		listDataChild.put(listDataHeader.get(2), studentService);
		listDataChild.put(listDataHeader.get(3), careerService);
		listDataChild.put(listDataHeader.get(4), newStudent);
	}

	
}