package tuat.crocotitle.save.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
 * Author	: Le Vinh - Vietnam
 * Time	 	: 25/6/2013
 * Name		: DBAdapter Class
 * Function	: Process with SQLite
 * Others	: 
 */

public class DBAdapter {
	// Database name & version
	private static final String DATABASE_NAME 	 = "qlfile.db";
	private static final int	DATABASE_VERSION = 1;
 
    // Table and Columns name
    private static final String TABLE_NAME   	= "tbfile";
    public  static final String COLUMN_ID 		= "id";
    public  static final String COLUMN_FILENAME = "filename";
    public  static final String COLUMN_RATING 	= "rating";
    public  static final String COLUMN_DATE 	= "date";
    public  static final String COLUMN_PROGRAM 	= "program"; // content of Program
 
    // Other objects
    private static Context context;
    static  SQLiteDatabase dataFile;
    private OpenHelper 	   openHelper;
    
    // Contructor
    public DBAdapter(Context c)
    {
    	DBAdapter.context = c;
    }

    // Open and connect to database
    public DBAdapter open() throws SQLException{
    	openHelper = new OpenHelper(context);
    	dataFile   = openHelper.getWritableDatabase();
    	return this;
    }
    
    // Close connection with database
    public void close(){
    	openHelper.close();
    }
 
    // Insert a new record to table
    public int insertRecord(String fileName, int rating, String date, String program)
    {
    	// Insert
    	ContentValues temp = new ContentValues();
    	temp.put(COLUMN_FILENAME, fileName);
    	temp.put(COLUMN_RATING, rating);
    	temp.put(COLUMN_DATE, date);
    	temp.put(COLUMN_PROGRAM, program);
    	dataFile.insert(TABLE_NAME, null, temp);
    	
    	// Return id the newest record
    	Cursor cursor = dataFile.query(TABLE_NAME, new String[]{COLUMN_ID}, null, null, null, null, null);
    	cursor.moveToLast();
    	return cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
    }
 
    // Select Query
    public String selectData()
    {
    	String[] columns = new String[] { COLUMN_ID, COLUMN_FILENAME, COLUMN_RATING, COLUMN_DATE};
    	Cursor c = dataFile.query(TABLE_NAME, columns, null, null, null, null, null);
    	
    	String data   = "";
    	int _id 	  = c.getColumnIndex(COLUMN_ID);
        int _filename = c.getColumnIndex(COLUMN_FILENAME);
        int _rating   = c.getColumnIndex(COLUMN_RATING);
        int _date 	  = c.getColumnIndex(COLUMN_DATE);
        
        // Repeat to get Data
        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
        {
        	data  = data + " " + c.getString(_id)
		        	+ " - filename:" + c.getString(_filename)
		            + " - rating:" + c.getString(_rating)
		            + " - date:" + c.getString(_date) + "\n";
        }
        c.close();
        return data;
    	
    }
    
    public Cursor getAll(String selection, String orderBy)
    {
    	String[] columns = new String[]{COLUMN_ID, COLUMN_FILENAME, COLUMN_RATING, COLUMN_DATE};
    	Cursor cursor = dataFile.query(TABLE_NAME, columns, selection, null, null, null, orderBy);
    	//query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy)
    	return cursor;
    }
    
    public Cursor getJsonProgram(String selection) // Lay noi dung chuong trinh theo ID
    {
    	String[] columns = new String[]{COLUMN_ID, COLUMN_PROGRAM};
    	Cursor cursor = dataFile.query(TABLE_NAME, columns, selection, null, null, null, null);
    	return cursor;
    }
    
    // Delete a record by id
    public int deleteRecord(int id)
    {
    	return dataFile.delete(TABLE_NAME, COLUMN_ID + "='"+id+"'", null);
    } 
    // Delete all record
    public int deleteTable() {
        return dataFile.delete(TABLE_NAME, null, null);
    }
    
    // Update Name and Rating record by ID 
    public boolean updateRecord(int id, String fileName, int rating)
    {
    	ContentValues tmp = new ContentValues();
    	tmp.put(COLUMN_FILENAME, fileName);
    	tmp.put(COLUMN_RATING, rating);
    	long kq = dataFile.update(TABLE_NAME, tmp, COLUMN_ID + "='"+id+"'", null);
    	if(kq == 0) return false;
        else return true;
    }
 
    //---------------- class OpenHelper ------------------
    private static class OpenHelper extends SQLiteOpenHelper
    {
    	// Constructor
    	public OpenHelper(Context context){
    		super(context, DATABASE_NAME, null, DATABASE_VERSION);
    	}

    	// Create a new database
    	@Override
		public void onCreate(SQLiteDatabase db) {
			String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (";
			query 		+= COLUMN_ID 		+ " INTEGER PRIMARY KEY AUTOINCREMENT, ";
			query 		+= COLUMN_FILENAME 	+ " TEXT NOT NULL, ";
			query 		+= COLUMN_RATING 	+ " INTEGER NOT NULL, ";
			query 		+= COLUMN_DATE 		+ " TEXT NOT NULL, ";
			query 		+= COLUMN_PROGRAM	+ " TEXT NOT NULL);";
			db.execSQL(query);			
		}

    	/*Kiểm tra phiên bản database nếu khác sẽ thay đổi*/
    	@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);			
		}
    }
}
