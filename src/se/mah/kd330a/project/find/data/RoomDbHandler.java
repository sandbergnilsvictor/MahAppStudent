package se.mah.kd330a.project.find.data;

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
	private static final String ROW_ARROWS = "arrows";
	private static final String ROW_X = "x";
	private static final String ROW_Y = "y";
	private static final String ROW_MAP = "map";

	static final String TABLE_CREATE = "CREATE TABLE rooms (roomNr TEXT, path TEXT, texts TEXT, arrows TEXT," + 
			" x INTEGER, y INTEGER, map TEXT);";

	private PathToRoom room;

	public RoomDbHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TABLE_CREATE);

		//addRow(db, "K2C107", "K2_E1_C_S1", "first_sec_third_last", null, null, null);

		addRow(db, "K2C107", "k2_k2e1_k2e1centerleft_k2e1cdirectleft", "k2_k2e1_k2e1centerleft_k2e1cdirectleftleft", "icarrowhere_icarrowup_icarrowleft_icarrowleft", null, null, null);
		addRow(db, "K2C108", "k2_k2e1_k2e1centerleft_k2e1cdirectleft", "k2_k2e1_k2e1centerleft_k2e1cdirectleftleft", "icarrowhere_icarrowup_icarrowleft_icarrowleft", null, null, null);
		addRow(db, "K2C109", "k2_k2e1_k2e1centerleft_k2e1cdirectleft", "k2_k2e1_k2e1centerleft_k2e1cdirectleftleft", "icarrowhere_icarrowup_icarrowleft_icarrowleft", null, null, null);
		addRow(db, "K2C110", "k2_k2e1_k2e1centerleft_k2e1cdirectleft", "k2_k2e1_k2e1centerleft_k2e1cdirectleftleft", "icarrowhere_icarrowup_icarrowleft_icarrowleft", null, null, null);
		addRow(db, "K2C117", "k2_k2e1_k2e1centerleft_k2e1cdirectleft", "k2_k2e1_k2e1centerleft_k2e1cdirectleftright", "icarrowhere_icarrowup_icarrowleft_icarrowright", null, null, null);
		addRow(db, "K2C119", "k2_k2e1_k2e1centerleft_k2e1cdirectleft", "k2_k2e1_k2e1centerleft_k2e1cdirectleftright", "icarrowhere_icarrowup_icarrowleft_icarrowright", null, null, null);
		addRow(db, "K2C121", "k2_k2e1_k2e1centerleft_k2e1cdirectleft", "k2_k2e1_k2e1centerleft_k2e1cdirectleftright", "icarrowhere_icarrowup_icarrowleft_icarrowright", null, null, null);
		addRow(db, "K2C122", "k2_k2e1_k2e1centerleft_k2e1cdirectleft", "k2_k2e1_k2e1centerleft_k2e1cdirectleftright", "icarrowhere_icarrowup_icarrowleft_icarrowright", null, null, null);
		addRow(db, "K2C123", "k2_k2e1_k2e1centerleft_k2e1cdirectleft", "k2_k2e1_k2e1centerleft_k2e1cdirectleftright", "icarrowhere_icarrowup_icarrowleft_icarrowright", null, null, null);
		addRow(db, "K2C124A", "k2_k2e1_k2e1centerleft_k2e1cdirectleft", "k2_k2e1_k2e1centerleft_k2e1cdirectleftright", "icarrowhere_icarrowup_icarrowleft_icarrowright", null, null, null);
		addRow(db, "K2C124B", "k2_k2e1_k2e1centerleft_k2e1cdirectleft", "k2_k2e1_k2e1centerleft_k2e1cdirectleftright", "icarrowhere_icarrowup_icarrowleft_icarrowright", null, null, null);
		addRow(db, "K2C133", "k2_k2e1_k2e1centerleft_k2e1cdirectleft", "k2_k2e1_k2e1centerleft_k2e1cdirectleftright", "icarrowhere_icarrowup_icarrowleft_icarrowright", null, null, null);
		addRow(db, "K2C135A", "k2_k2e1_k2e1centerleft_k2e1cdirectleft", "k2_k2e1_k2e1centerleft_k2e1cdirectleftright", "icarrowhere_icarrowup_icarrowleft_icarrowright", null, null, null);
		addRow(db, "K2C135B", "k2_k2e1_k2e1centerleft_k2e1cdirectleft", "k2_k2e1_k2e1centerleft_k2e1cdirectleftright", "icarrowhere_icarrowup_icarrowleft_icarrowright", null, null, null);
		addRow(db, "K2C136", "k2_k2e1_k2e1centerleft_k2e1cdirectleft", "k2_k2e1_k2e1centerleft_k2e1cdirectleftright", "icarrowhere_icarrowup_icarrowleft_icarrowright", null, null, null);
		addRow(db, "K2C138", "k2_k2e1_k2e1centerleft_k2e1cdirectleft", "k2_k2e1_k2e1centerleft_k2e1cdirectleftright", "icarrowhere_icarrowup_icarrowleft_icarrowright", null, null, null);
		addRow(db, "K2C307", "k2_k2e1_k2e1centerright_k2e1cdirectright", "k2_k2e1_k2e1centerright_k2e1cdirectrightleft", "icarrowhere_icarrowup_icarrowright_icarrowleft", null, null, null);
		addRow(db, "K2C308", "k2_k2e1_k2e1centerright_k2e1cdirectright", "k2_k2e1_k2e1centerright_k2e1cdirectrightleft", "icarrowhere_icarrowup_icarrowright_icarrowleft", null, null, null);
		addRow(db, "K2C309", "k2_k2e1_k2e1centerright_k2e1cdirectright", "k2_k2e1_k2e1centerright_k2e1cdirectrightleft", "icarrowhere_icarrowup_icarrowright_icarrowleft", null, null, null);
		addRow(db, "K2C310", "k2_k2e1_k2e1centerright_k2e1cdirectright", "k2_k2e1_k2e1centerright_k2e1cdirectrightleft", "icarrowhere_icarrowup_icarrowright_icarrowleft", null, null, null);
		addRow(db, "K2C311", "k2_k2e1_k2e1centerright_k2e1cdirectright", "k2_k2e1_k2e1centerright_k2e1cdirectrightleft", "icarrowhere_icarrowup_icarrowright_icarrowleft", null, null, null);
		addRow(db, "K2C314", "k2_k2e1_k2e1centerright_k2e1cdirectright", "k2_k2e1_k2e1centerright_k2e1cdirectrightleft", "icarrowhere_icarrowup_icarrowright_icarrowleft", null, null, null);
		addRow(db, "K2C316", "k2_k2e1_k2e1centerright_k2e1cdirectright", "k2_k2e1_k2e1centerright_k2e1cdirectrightleft", "icarrowhere_icarrowup_icarrowright_icarrowleft", null, null, null);
		addRow(db, "K2C320", "k2_k2e1_k2e1centerright_k2e1cdirectright", "k2_k2e1_k2e1centerright_k2e1cdirectrightleft", "icarrowhere_icarrowup_icarrowright_icarrowleft", null, null, null);
		addRow(db, "K2C323", "k2_k2e1_k2e1centerright_k2e1cdirectright", "k2_k2e1_k2e1centerright_k2e1cdirectrightleft", "icarrowhere_icarrowup_icarrowright_icarrowleft", null, null, null);
		addRow(db, "K2C325", "k2_k2e1_k2e1centerright_k2e1cdirectright", "k2_k2e1_k2e1centerright_k2e1cdirectrightleft", "icarrowhere_icarrowup_icarrowright_icarrowleft", null, null, null);
		addRow(db, "K2C332", "k2_k2e1_k2e1centerright_k2e1cdirectright", "k2_k2e1_k2e1centerright_k2e1cdirectrightright", "icarrowhere_icarrowup_icarrowright_icarrowright", null, null, null);
		addRow(db, "K2C333", "k2_k2e1_k2e1centerright_k2e1cdirectright", "k2_k2e1_k2e1centerright_k2e1cdirectrightright", "icarrowhere_icarrowup_icarrowright_icarrowright", null, null, null);
		addRow(db, "K2C339", "k2_k2e1_k2e1centerright_k2e1cdirectright", "k2_k2e1_k2e1centerright_k2e1cdirectrightright", "icarrowhere_icarrowup_icarrowright_icarrowright", null, null, null);
		addRow(db, "K2C340", "k2_k2e1_k2e1centerright_k2e1cdirectright", "k2_k2e1_k2e1centerright_k2e1cdirectrightright", "icarrowhere_icarrowup_icarrowright_icarrowright", null, null, null);
		addRow(db, "K2C341", "k2_k2e1_k2e1centerright_k2e1cdirectright", "k2_k2e1_k2e1centerright_k2e1cdirectrightright", "icarrowhere_icarrowup_icarrowright_icarrowright", null, null, null);
		addRow(db, "K2C342", "k2_k2e1_k2e1centerright_k2e1cdirectright", "k2_k2e1_k2e1centerright_k2e1cdirectrightright", "icarrowhere_icarrowup_icarrowright_icarrowright", null, null, null);
		addRow(db, "K2C343", "k2_k2e1_k2e1centerright_k2e1cdirectright", "k2_k2e1_k2e1centerright_k2e1cdirectrightright", "icarrowhere_icarrowup_icarrowright_icarrowright", null, null, null);
		addRow(db, "K2C345", "k2_k2e1_k2e1centerright_k2e1cdirectright", "k2_k2e1_k2e1centerright_k2e1cdirectrightleft", "icarrowhere_icarrowup_icarrowright_icarrowleft", null, null, null);
		addRow(db, "K2C346", "k2_k2e1_k2e1centerright_k2e1cdirectright", "k2_k2e1_k2e1centerright_k2e1cdirectrightleft", "icarrowhere_icarrowup_icarrowright_icarrowleft", null, null, null);
		addRow(db, "K2C347", "k2_k2e1_k2e1centerright_k2e1cdirectright", "k2_k2e1_k2e1centerright_k2e1cdirectrightleft", "icarrowhere_icarrowup_icarrowright_icarrowleft", null, null, null);
		addRow(db, "K2C348", "k2_k2e1_k2e1centerright_k2e1cdirectright", "k2_k2e1_k2e1centerright_k2e1cdirectrightleft", "icarrowhere_icarrowup_icarrowright_icarrowleft", null, null, null);
		addRow(db, "K2C349", "k2_k2e1_k2e1centerright_k2e1cdirectright", "k2_k2e1_k2e1centerright_k2e1cdirectrightleft", "icarrowhere_icarrowup_icarrowright_icarrowleft", null, null, null);
		addRow(db, "K2C350", "k2_k2e1_k2e1centerright_k2e1cdirectright", "k2_k2e1_k2e1centerright_k2e1cdirectrightleft", "icarrowhere_icarrowup_icarrowright_icarrowleft", null, null, null);
		addRow(db, "K2C351", "k2_k2e1_k2e1centerright_k2e1cdirectright", "k2_k2e1_k2e1centerright_k2e1cdirectrightleft", "icarrowhere_icarrowup_icarrowright_icarrowleft", null, null, null);
		addRow(db, "K2C352", "k2_k2e1_k2e1centerright_k2e1cdirectright", "k2_k2e1_k2e1centerright_k2e1cdirectrightleft", "icarrowhere_icarrowup_icarrowright_icarrowleft", null, null, null);
		addRow(db, "K2C353", "k2_k2e1_k2e1centerright_k2e1cdirectright", "k2_k2e1_k2e1centerright_k2e1cdirectrightleft", "icarrowhere_icarrowup_icarrowright_icarrowleft", null, null, null);
		addRow(db, "K2B105", "k2_k2e2_k2e2benter_k2e2bright", "k2_k2e2_k2e2benter_k2e2bright", "icarrowhere_icarrowhere_icarrowup_icarrowright", null, null, null);
		addRow(db, "K2B106", "k2_k2e2_k2e2benter_k2e2bleft", "k2_k2e2_k2e2benter_k2e2bleft", "icarrowhere_icarrowhere_icarrowup_icarrowleft", null, null, null);
		addRow(db, "K2B107", "k2_k2e2_k2e2benter_k2e2bleft", "k2_k2e2_k2e2benter_k2e2bleft", "icarrowhere_icarrowhere_icarrowup_icarrowleft", null, null, null);
		addRow(db, "K2B108", "k2_k2e2_k2e2benter_k2e2bleft", "k2_k2e2_k2e2benter_k2e2bleft", "icarrowhere_icarrowhere_icarrowup_icarrowleft", null, null, null);
		addRow(db, "K2B109", "k2_k2e2_k2e2benter_k2e2bleft", "k2_k2e2_k2e2benter_k2e2bleft", "icarrowhere_icarrowhere_icarrowup_icarrowleft", null, null, null);
		addRow(db, "K2B110", "k2_k2e3_k2e3benter_k2e3bright", "k2_k2e3_k2e3benter_k2e3bright", "icarrowhere_icarrowhere_icarrowup_icarrowright", null, null, null);
		addRow(db, "K2B111", "k2_k2e3_k2e3benter_k2e3bright", "k2_k2e3_k2e3benter_k2e3bright", "icarrowhere_icarrowhere_icarrowup_icarrowright", null, null, null);
		addRow(db, "K2B117", "k2_k2e3_k2e3benter_k2e3bleft", "k2_k2e3_k2e3benter_k2e3bleft", "icarrowhere_icarrowhere_icarrowup_icarrowleft", null, null, null);
		addRow(db, "K2B118", "k2_k2e3_k2e3benter_k2e3bleft", "k2_k2e3_k2e3benter_k2e3bleft", "icarrowhere_icarrowhere_icarrowup_icarrowleft", null, null, null);
		addRow(db, "K2B119", "k2_k2e3_k2e3benter_k2e3bleft", "k2_k2e3_k2e3benter_k2e3bleft", "icarrowhere_icarrowhere_icarrowup_icarrowleft", null, null, null);
		addRow(db, "K2B202", "k2_k2e3_k2e3benter_k2e3bleft", "k2_k2e3_k2e3benter_k2e3bleft", "icarrowhere_icarrowhere_icarrowup_icarrowleft", null, null, null);
		addRow(db, "K2B203", "k2_k2e3_k2e3benter_k2e3bleft", "k2_k2e3_k2e3benter_k2e3bleft", "icarrowhere_icarrowhere_icarrowup_icarrowleft", null, null, null);
		addRow(db, "K2B204", "k2_k2e3_k2e3benter_k2e3bleft", "k2_k2e3_k2e3benter_k2e3bleft", "icarrowhere_icarrowhere_icarrowup_icarrowleft", null, null, null);
		addRow(db, "K2B210", "k2_k2e3_k2e3benter_k2e3bleft", "k2_k2e3_k2e3benter_k2e3bleft", "icarrowhere_icarrowhere_icarrowup_icarrowleft", null, null, null);
		addRow(db, "K2B211", "k2_k2e3_k2e3benter_k2e3bleft", "k2_k2e3_k2e3benter_k2e3bleft", "icarrowhere_icarrowhere_icarrowup_icarrowleft", null, null, null);
		addRow(db, "K2B212", "k2_k2e3_k2e3benter_k2e3bleft", "k2_k2e3_k2e3benter_k2e3bleft", "icarrowhere_icarrowhere_icarrowup_icarrowleft", null, null, null);
		addRow(db, "K2B205", "k2_k2e4_k2e4benter_k2e4bright", "k2_k2e4_k2e4benter_k2e4bright", "icarrowhere_icarrowhere_icarrowup_icarrowright", null, null, null);
		addRow(db, "K2B206", "k2_k2e4_k2e4benter_k2e4bright", "k2_k2e4_k2e4benter_k2e4bright", "icarrowhere_icarrowhere_icarrowup_icarrowright", null, null, null);
		addRow(db, "K2B207", "k2_k2e4_k2e4benter_k2e4bright", "k2_k2e4_k2e4benter_k2e4bright", "icarrowhere_icarrowhere_icarrowup_icarrowright", null, null, null);
		addRow(db, "K2B208", "k2_k2e4_k2e4benter_k2e4bright", "k2_k2e4_k2e4benter_k2e4bright", "icarrowhere_icarrowhere_icarrowup_icarrowright", null, null, null);
		addRow(db, "K2B209", "k2_k2e4_k2e4benter_k2e4bright", "k2_k2e4_k2e4benter_k2e4bright", "icarrowhere_icarrowhere_icarrowup_icarrowright", null, null, null);
		addRow(db, "K2B302", "k2_k2e4_k2e4benter_k2e4bright", "k2_k2e4_k2e4benter_k2e4bright", "icarrowhere_icarrowhere_icarrowup_icarrowright", null, null, null);
		addRow(db, "K2B303", "k2_k2e4_k2e4benter_k2e4bright", "k2_k2e4_k2e4benter_k2e4bright", "icarrowhere_icarrowhere_icarrowup_icarrowright", null, null, null);
		addRow(db, "K2B304", "k2_k2e4_k2e4benter_k2e4bright", "k2_k2e4_k2e4benter_k2e4bright", "icarrowhere_icarrowhere_icarrowup_icarrowright", null, null, null);
		addRow(db, "K2B305", "k2_k2e4_k2e4benter_k2e4bright", "k2_k2e4_k2e4benter_k2e4bright", "icarrowhere_icarrowhere_icarrowup_icarrowright", null, null, null);
		addRow(db, "K2B306", "k2_k2e4_k2e4benter_k2e4bright", "k2_k2e4_k2e4benter_k2e4bright", "icarrowhere_icarrowhere_icarrowup_icarrowright", null, null, null);
		addRow(db, "K2B352", "k2_k2e4_k2e4benter_k2e4bleft", "k2_k2e4_k2e4benter_k2e4bleft", "icarrowhere_icarrowhere_icarrowup_icarrowleft", null, null, null);
		addRow(db, "K2B353", "k2_k2e4_k2e4benter_k2e4bleft", "k2_k2e4_k2e4benter_k2e4bleft", "icarrowhere_icarrowhere_icarrowup_icarrowleft", null, null, null);
		addRow(db, "K2A126", "k2_k2e2_k2e2aenter_k2e2astraight", "k2_k2e2_k2e2aenter_k2e2astraight", "icarrowhere_icarrowhere_icarrowup_icarrowup", null, null, null);
		addRow(db, "K2A129", "k2_k2e2_k2e2aenter_k2e2astraight", "k2_k2e2_k2e2aenter_k2e2astraight", "icarrowhere_icarrowhere_icarrowup_icarrowup", null, null, null);
		addRow(db, "K2A130", "k2_k2e2_k2e2aenter_k2e2astraight", "k2_k2e2_k2e2aenter_k2e2astraight", "icarrowhere_icarrowhere_icarrowup_icarrowup", null, null, null);
		addRow(db, "K2A131", "k2_k2e2_k2e2aenter_k2e2astraight", "k2_k2e2_k2e2aenter_k2e2astraight", "icarrowhere_icarrowhere_icarrowup_icarrowup", null, null, null);
		addRow(db, "K2A132", "k2_k2e2_k2e2aenter_k2e2astraight", "k2_k2e2_k2e2aenter_k2e2astraight", "icarrowhere_icarrowhere_icarrowup_icarrowup", null, null, null);
		addRow(db, "K2A104", "k2_k2e2_k2e2aenter_k2e2aleft", "k2_k2e2_k2e2aenter_k2e2aleft", "icarrowhere_icarrowhere_icarrowup_icarrowleft", null, null, null);
		addRow(db, "K2A134", "k2_k2e2_k2e2aenter_k2e2aleft", "k2_k2e2_k2e2aenter_k2e2aleft", "icarrowhere_icarrowhere_icarrowup_icarrowleft", null, null, null);
		addRow(db, "K2A136", "k2_k2e2_k2e2aenter_k2e2aleft", "k2_k2e2_k2e2aenter_k2e2aleft", "icarrowhere_icarrowhere_icarrowup_icarrowleft", null, null, null);
		addRow(db, "K2A133", "k2_k2e3_k2e3aenter_k2e3aright", "k2_k2e3_k2e3aenter_k2e3aright", "icarrowhere_icarrowhere_icarrowup_icarrowright", null, null, null);
		addRow(db, "K2A135", "k2_k2e3_k2e3aenter_k2e3aright", "k2_k2e3_k2e3aenter_k2e3aright", "icarrowhere_icarrowhere_icarrowup_icarrowright", null, null, null);
		addRow(db, "K2A137", "k2_k2e3_k2e3aenter_k2e3aright", "k2_k2e3_k2e3aenter_k2e3aright", "icarrowhere_icarrowhere_icarrowup_icarrowright", null, null, null);
		addRow(db, "K2A138", "k2_k2e3_k2e3aenter_k2e3aright", "k2_k2e3_k2e3aenter_k2e3aright", "icarrowhere_icarrowhere_icarrowup_icarrowright", null, null, null);
		addRow(db, "K2A139", "k2_k2e3_k2e3aenter_k2e3aright", "k2_k2e3_k2e3aenter_k2e3aright", "icarrowhere_icarrowhere_icarrowup_icarrowright", null, null, null);
		addRow(db, "K2A140", "k2_k2e3_k2e3aenter_k2e3aright", "k2_k2e3_k2e3aenter_k2e3aright", "icarrowhere_icarrowhere_icarrowup_icarrowright", null, null, null);
		addRow(db, "K2A146", "k2_k2e3_k2e3aenter_k2e3aright", "k2_k2e3_k2e3aenter_k2e3aright", "icarrowhere_icarrowhere_icarrowup_icarrowright", null, null, null);
		addRow(db, "K2A150", "k2_k2e3_k2e3aenter_k2e3aright", "k2_k2e3_k2e3aenter_k2e3aright", "icarrowhere_icarrowhere_icarrowup_icarrowright", null, null, null);
		addRow(db, "K2A164", "k2_k2e3_k2e3aenter_k2e3aright", "k2_k2e3_k2e3aenter_k2e3aright", "icarrowhere_icarrowhere_icarrowup_icarrowright", null, null, null);
		addRow(db, "K2A142", "k2_k2e3_k2e3aenter_k2e3aleft", "k2_k2e3_k2e3aenter_k2e3aleft", "icarrowhere_icarrowhere_icarrowup_icarrowleft", null, null, null);
		addRow(db, "K2A160", "k2_k2e3_k2e3aenter_k2e3aleft", "k2_k2e3_k2e3aenter_k2e3aleft", "icarrowhere_icarrowhere_icarrowup_icarrowleft", null, null, null);
		addRow(db, "K2A161", "k2_k2e3_k2e3aenter_k2e3aleft", "k2_k2e3_k2e3aenter_k2e3aleft", "icarrowhere_icarrowhere_icarrowup_icarrowleft", null, null, null);
		addRow(db, "K2A162", "k2_k2e3_k2e3aenter_k2e3aleft", "k2_k2e3_k2e3aenter_k2e3aleft", "icarrowhere_icarrowhere_icarrowup_icarrowleft", null, null, null);
		addRow(db, "K2A163", "k2_k2e3_k2e3aenter_k2e3aleft", "k2_k2e3_k2e3aenter_k2e3aleft", "icarrowhere_icarrowhere_icarrowup_icarrowleft", null, null, null);
		addRow(db, "K2A202", "k2_k2e3_k2e3aenter_k2e3aleft", "k2_k2e3_k2e3aenter_k2e3aleft", "icarrowhere_icarrowhere_icarrowup_icarrowleft", null, null, null);
		addRow(db, "K2A203", "k2_k2e3_k2e3aenter_k2e3aleft", "k2_k2e3_k2e3aenter_k2e3aleft", "icarrowhere_icarrowhere_icarrowup_icarrowleft", null, null, null);
		addRow(db, "K2A204", "k2_k2e3_k2e3aenter_k2e3aleft", "k2_k2e3_k2e3aenter_k2e3aleft", "icarrowhere_icarrowhere_icarrowup_icarrowleft", null, null, null);
		addRow(db, "K2A209", "k2_k2e3_k2e3aenter_k2e3aleft", "k2_k2e3_k2e3aenter_k2e3aleft", "icarrowhere_icarrowhere_icarrowup_icarrowleft", null, null, null);
		addRow(db, "K2A205", "k2_k2e4_k2e4aenter_k2e4astraight", "k2_k2e4_k2e4aenter_k2e4astraight", "icarrowhere_icarrowhere_icarrowup_icarrowup", null, null, null);
		addRow(db, "K2A206", "k2_k2e4_k2e4aenter_k2e4astraight", "k2_k2e4_k2e4aenter_k2e4astraight", "icarrowhere_icarrowhere_icarrowup_icarrowup", null, null, null);
		addRow(db, "K2A207", "k2_k2e4_k2e4aenter_k2e4astraight", "k2_k2e4_k2e4aenter_k2e4astraight", "icarrowhere_icarrowhere_icarrowup_icarrowup", null, null, null);
		addRow(db, "K2A302", "k2_k2e4_k2e4aenter_k2e4astraight", "k2_k2e4_k2e4aenter_k2e4astraight", "icarrowhere_icarrowhere_icarrowup_icarrowup", null, null, null);
		addRow(db, "K2A303", "k2_k2e4_k2e4aenter_k2e4astraight", "k2_k2e4_k2e4aenter_k2e4astraight", "icarrowhere_icarrowhere_icarrowup_icarrowup", null, null, null);
		addRow(db, "K2A304", "k2_k2e4_k2e4aenter_k2e4astraight", "k2_k2e4_k2e4aenter_k2e4astraight", "icarrowhere_icarrowhere_icarrowup_icarrowup", null, null, null);
		addRow(db, "K2A305", "k2_k2e4_k2e4aenter_k2e4astraight", "k2_k2e4_k2e4aenter_k2e4astraight", "icarrowhere_icarrowhere_icarrowup_icarrowup", null, null, null);
		addRow(db, "K2A306", "k2_k2e4_k2e4aenter_k2e4astraight", "k2_k2e4_k2e4aenter_k2e4astraight", "icarrowhere_icarrowhere_icarrowup_icarrowup", null, null, null);
		addRow(db, "K2A307", "k2_k2e4_k2e4aenter_k2e4astraight", "k2_k2e4_k2e4aenter_k2e4astraight", "icarrowhere_icarrowhere_icarrowup_icarrowup", null, null, null);
		addRow(db, "K2A308", "k2_k2e4_k2e4aenter_k2e4astraight", "k2_k2e4_k2e4aenter_k2e4astraight", "icarrowhere_icarrowhere_icarrowup_icarrowup", null, null, null);
		addRow(db, "K2A309", "k2_k2e4_k2e4aenter_k2e4astraight", "k2_k2e4_k2e4aenter_k2e4astraight", "icarrowhere_icarrowhere_icarrowup_icarrowup", null, null, null);
		addRow(db, "K2D155", "k2_k2e1_k2e1centerright_k2e1dstairs2", "k2_k2e1_k2e1dright_k2e1dstairs2bridge", "icarrowhere_icarrowup_icarrowright_icarrowup", null, null, null);
		addRow(db, "K2D156", "k2_k2e1_k2e1centerright_k2e1dstairs2", "k2_k2e1_k2e1dright_k2e1dstairs2bridge", "icarrowhere_icarrowup_icarrowright_icarrowup", null, null, null);
		addRow(db, "K2D157", "k2_k2e1_k2e1centerright_k2e1dstairs2", "k2_k2e1_k2e1dright_k2e1dstairs2bridge", "icarrowhere_icarrowup_icarrowright_icarrowup", null, null, null);
		addRow(db, "K2D202", "k2_k2e1_k2e1centerright_k2e1dstairs2", "k2_k2e1_k2e1dright_k2e1dstairs2up", "icarrowhere_icarrowup_icarrowright_icarrowup", null, null, null);
		addRow(db, "K2D204", "k2_k2e1_k2e1centerright_k2e1dstairs2", "k2_k2e1_k2e1dright_k2e1dstairs2up", "icarrowhere_icarrowup_icarrowright_icarrowup", null, null, null);
		addRow(db, "K2D205", "k2_k2e1_k2e1centerright_k2e1dstairs2", "k2_k2e1_k2e1dright_k2e1dstairs2up", "icarrowhere_icarrowup_icarrowright_icarrowup", null, null, null);
		addRow(db, "K2D213", "k2_k2e1_k2e1centerright_k2e1dstairs1", "k2_k2e1_k2e1dright_k2e1dstairs1up", "icarrowhere_icarrowup_icarrowright_icarrowup", null, null, null);
		addRow(db, "K2D214", "k2_k2e1_k2e1centerright_k2e1dstairs1", "k2_k2e1_k2e1dright_k2e1dstairs1up", "icarrowhere_icarrowup_icarrowright_icarrowup", null, null, null);
		addRow(db, "K2D137", "k2_k2e1_k2e1centerright_k2e1dstairs1", "k2_k2e1_k2e1dright_k2e1dstairs1bridge", "icarrowhere_icarrowup_icarrowright_icarrowup", null, null, null);
		addRow(db, "K2D140", "k2_k2e1_k2e1centerright_k2e1dstairs1", "k2_k2e1_k2e1dright_k2e1dstairs1bridge", "icarrowhere_icarrowup_icarrowright_icarrowup", null, null, null);
	}

	private void addRow(SQLiteDatabase db, String roomNr, String path, String texts, String arrows, String x, String y, String map) {

		ContentValues values = new ContentValues();
		values.put(ROW_ROOMNR, roomNr); 
		values.put(ROW_PATH, path); 
		values.put(ROW_TEXTS, texts); 
		values.put(ROW_ARROWS, arrows); 
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
				room.setArrowList(c.getString(c.getColumnIndex(ROW_ARROWS)));
				room.mMapPic = c.getString(c.getColumnIndex(ROW_MAP));
				room.mCoord_x = c.getInt(c.getColumnIndex(ROW_X));
				room.mCoord_y = c.getInt(c.getColumnIndex(ROW_Y));
				return true;
			}
		}
		catch (Exception e) {
			db.close();
			e.printStackTrace();
		}
		return false;
	}

	public PathToRoom getRoomDetails() {
		return room;
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
	
	public List<String> getArrows() {
		if (room != null)
			return room.getArrows();
		else
			return null;
	}
	
	public String getMapName() {
		if (room != null)
			return room.mMapPic;
		else
			return null;
	}

	public String getRoomNr() {
		if (room != null)
			return room.mRoomNr;
		else
			return null;
	}

	public int getCoordX() {
		if (room != null)
			return room.mCoord_x;
		else
			return -1;
	}

	public int getCoordY() {
		if (room != null)
			return room.mCoord_y;
		else
			return -1;
	}
}
