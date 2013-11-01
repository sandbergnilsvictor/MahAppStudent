package se.mah.kd330a.project.home.data;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

public class RSSFeed implements Serializable {

	private static final long serialVersionUID = 1L;
	private int _itemcount = 0;
	private List<RSSItem> _itemlist;

	RSSFeed() {
		_itemlist = new Vector<RSSItem>();
	}

	void addItem(RSSItem item) {
		_itemlist.add(item);
		_itemcount++;
	}

	public RSSItem getItem(int location) throws Exception {
		RSSItem tmp = _itemlist.get(location);
		if (tmp == null)
			throw new Exception("Out of bounds");
		
		return tmp;
	}

	public int getItemCount() {
		// or you could return _itemlist.size() // mm
		return _itemcount;
	}

}
