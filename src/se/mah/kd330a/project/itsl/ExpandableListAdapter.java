package se.mah.kd330a.project.itsl;

import se.mah.kd330a.project.R;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author asampe, marcusmansson
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter
{
	private Context _context;
	private List<Article> _listDataHeader; // header titles
	private Date lastUpdate;
	
	// Temporary for testing - create arraylist of courses
	ArrayList<Course> theCourses = new ArrayList<Course>();

	public ExpandableListAdapter(Context context, List<Article> listDataHeader)
	{
		this._context = context;
		this._listDataHeader = listDataHeader;
		this.lastUpdate = Util.getLatestUpdate(_context);
		
		// Trying to get the colors from our xml file
		theCourses.add(new Course("1", context.getResources().getColor(R.color.blue)));
		theCourses.add(new Course("2", context.getResources().getColor(R.color.yellow)));
		theCourses.add(new Course("3", context.getResources().getColor(R.color.red)));
		theCourses.add(new Course("4", context.getResources().getColor(R.color.green)));
		theCourses.add(new Course("5", context.getResources().getColor(R.color.orange)));
	}

	public List<Article> getList()
	{
		return _listDataHeader;
	}

	public void setList(List<Article> list)
	{
		_listDataHeader = list;
		notifyDataSetInvalidated();
	}

	@Override
	public Object getChild(int groupPosition, int childPosititon)
	{
		return this._listDataHeader.get(groupPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition)
	{
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
	{
		if (!_listDataHeader.isEmpty())
		{
			if (convertView == null)
			{
				LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = infalInflater.inflate(R.layout.itsl_list_item, null);
			}

			TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);
			txtListChild.setText(this._listDataHeader.get(groupPosition).getArticleText());
			ImageView imgClrCode = (ImageView) convertView.findViewById(R.id.clrCode);

			/*
			 * Choose the right color
			 */
			for (Course c : theCourses)
			{
				if (this._listDataHeader.get(groupPosition).getArticleCourseCode().equals(c.getCourseCode()))
				{
					imgClrCode.setBackgroundColor(c.getColor());
				}
			}
		}

		return convertView;
	}

	@Override
	public Object getGroup(int groupPosition)
	{
		return this._listDataHeader.get(groupPosition);
	}

	@Override
	public int getGroupCount()
	{
		return this._listDataHeader.size();
	}

	@Override
	public long getGroupId(int groupPosition)
	{
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
	{
		if (!_listDataHeader.isEmpty())
		{
			if (convertView == null)
			{
				LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = infalInflater.inflate(R.layout.itsl_list_group, null);
			}

			Article headerTitle = (Article) getGroup(groupPosition);
			TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
			TextView lblListHeaderDate = (TextView) convertView.findViewById(R.id.lblListHeaderDate);
			TextView lblListHeaderText = (TextView) convertView.findViewById(R.id.lblListHeaderText);
			TextView lblListCode = (TextView) convertView.findViewById(R.id.lblListCode);
			ImageView imgClrCode = (ImageView) convertView.findViewById(R.id.clrCode);
			ImageView imgPointer = (ImageView) convertView.findViewById(R.id.icPointer);
			TextView txtClrLine = (TextView) convertView.findViewById(R.id.clrLine);

			lblListHeader.setText(headerTitle.getArticleHeader());
			lblListHeaderDate.setText(headerTitle.getArticleDate().toString());
			
			/*
			 * find out if this article is new since last time we started the app
			 */
			if (lastUpdate.getTime() < headerTitle.getArticlePubDate().getTime())
				lblListCode.setText("New");
			else
				lblListCode.setText("");
				
			/*
			 * If the summary text is visible = not expanded
			 */
			if (headerTitle.isTextVisible())
			{

				lblListHeaderText.setVisibility(View.VISIBLE);
				lblListHeaderText.setText(headerTitle.getArticleSummary());
				imgPointer.setVisibility(View.VISIBLE);
			}
			else
			{
				lblListHeaderText.setVisibility(View.GONE);
				imgPointer.setVisibility(View.GONE);

			}

			if (headerTitle.getArticleSummary().equals(headerTitle.getArticleText()))
			{
				convertView.setClickable(true);
				imgPointer.setVisibility(View.GONE);
			}
			else
			{
				convertView.setClickable(false);
			}

			/*
			 * Choose the right color
			 */

			for (Course c : theCourses)
			{
				if (headerTitle.getArticleCourseCode().equals(c.getCourseCode()))
				{
					imgClrCode.setBackgroundColor(c.getColor());
					txtClrLine.setBackgroundColor(c.getColor());
				}
			}

			/*
			 * filter example
			 */
			if (headerTitle.getArticleCourseCode().equals("320B"))
			{
				// convertView.setVisibility(View.GONE);

			}
		}
		return convertView;

	}

	@Override
	public boolean hasStableIds()
	{
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition)
	{
		return true;
	}

	@Override
	public void onGroupCollapsed(int groupPosition)
	{
		super.onGroupCollapsed(groupPosition);

		if (!_listDataHeader.isEmpty())
		{
			Article headerTitle = (Article) getGroup(groupPosition);
			headerTitle.setTextVisible(true);
		}
	}

	@Override
	public void onGroupExpanded(int groupPosition)
	{
		super.onGroupExpanded(groupPosition);

		if (!_listDataHeader.isEmpty())
		{
			Article headerTitle = (Article) getGroup(groupPosition);
			headerTitle.setTextVisible(false);
		}

	}

	@Override
	public int getChildrenCount(int groupPosition)
	{
		return 1;
	}
}
