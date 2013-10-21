package se.mah.kd330a.project.find;

import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;

/*
 *	Database handler class for searching for rooms 
 *	The database created when the app runs the first time 
 *	and it adds all the data for the rooms too. (Need to update)
 *	With the function isRoomExists(String roomNr) can make a search
 *	in the database. If it returns true then with the appropriate 
 *	function you can access the stored data for the room.
 *	functions are: getRoomPathPics, getPathPicTexts, getMapName ...
 */

public class RoomDbHandler extends SQLiteOpenHelper {

	//private static final String LOG = "MAH RoomDbHandler";

	private static final String DATABASE_NAME = "find_roomsDB";
	private static final int DATABASE_VERSION = 1;

	private static final String TABLE_ROOMS = "rooms";

	private static final String ROW_ROOMNR = "roomNr";
	private static final String ROW_PATH = "path";
	private static final String ROW_TEXTS = "texts";
	private static final String ROW_X = "x";
	private static final String ROW_Y = "y";
	private static final String ROW_MAP = "map";

	static final String TABLE_CREATE = "CREATE TABLE rooms (roomNr TEXT, path TEXT, texts TEXT," + 
			" x INTEGER, y INTEGER, map TEXT);";

	private PathToRoom room;

	public RoomDbHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TABLE_CREATE);

		addRow(db, "K2C107", "K2_E1_C_S1", "first_sec_third_last", null, null, null);
		addRow(db, "K2C108", "K2_E1_C_S1", "first108_second_third123_last!!", null, null, null);
		addRow(db, "K2C109", "K2_E1_C_S1", "first_sec_third_last", null, null, null);
		addRow(db, "K2C110", "K2_E1_C_S1", "first_sec_third_last", null, null, null);
		addRow(db, "K2C117", "K2_E1_C_S1", "first_sec_third_last", null, null, null);
		addRow(db, "K2C119", "K2_E1_C_S1", "first_sec_third_last", null, null, null);
		addRow(db, "K2C121", "K2_E1_C_S1", "first_sec_third_last", null, null, null);
		addRow(db, "K2C122", "K2_E1_C_S1", "first_sec_third_last", null, null, null);
		addRow(db, "K2C123", "K2_E1_C_S1", "first_sec_third_last", null, null, null);
		addRow(db, "K2C124", "K2_E1_C_S1", "first_sec_third_last", null, null, null);
	}

	private void addRow(SQLiteDatabase db, String roomNr, String path, String texts, String x, String y, String map) {

		ContentValues values = new ContentValues();
		values.put(ROW_ROOMNR, roomNr); 
		values.put(ROW_PATH, path); 
		values.put(ROW_TEXTS, texts); 
		values.put(ROW_X, x); 
		values.put(ROW_Y, y); 
		values.put(ROW_MAP, map); 

		// Inserting Row
		db.insert(TABLE_ROOMS, null, values);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public boolean isRoomExists(String roomNr) {

		String selectQuery = "SELECT  * FROM " + TABLE_ROOMS + " WHERE "
				+ ROW_ROOMNR + " = '" + roomNr.toUpperCase(Locale.getDefault()) + "'";

		SQLiteDatabase db = this.getReadableDatabase();

		try {
			Cursor c = db.rawQuery(selectQuery, null);

			if (c != null) {
				c.moveToFirst();
				room = new PathToRoom(roomNr);
				room.setPath(c.getString(c.getColumnIndex(ROW_PATH)));
				room.setTextList(c.getString(c.getColumnIndex(ROW_TEXTS)));
				room.setMapPic(c.getString(c.getColumnIndex(ROW_MAP)));
				room.coord_x = c.getInt(c.getColumnIndex(ROW_X));
				room.coord_y = c.getInt(c.getColumnIndex(ROW_Y));
				return true;
			}
		}
		catch (Exception e) {
			db.close();
			e.printStackTrace();
		}
		return false;
	}

	public List<String> getPathImg() {
		if (room != null)
			return room.getPath();
		else
			return null;
	}

	public List<String> getPathImgTexts() {
		if (room != null)
			return room.getTexts();
		else
			return null;
	}

	public String getMapName() {
		if (room != null)
			return room.mapPic;
		else
			return null;
	}

	public String getRoomNr() {
		if (room != null)
			return room.roomNr;
		else
			return null;
	}

	public int getCoordX() {
		if (room != null)
			return room.coord_x;
		else
			return -1;
	}

	public int getCoordY() {
		if (room != null)
			return room.coord_y;
		else
			return -1;
	}
}
