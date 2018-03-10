package ro.ase.pdm.incercare4;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Anca on 1/12/2018.
 */

public class DbHelper  extends SQLiteOpenHelper {

    public static final String DB_NAME = "anca.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_NAME_USER = "USERS_TABLE";
    public static final String COLUMN_NAME_USER_ID = "USER_ID";
    public static final String COLUMN_NAME_USER_NAME = "USER_NAME";
    public static final String COLUMN_NAME_PASSWORD = "PASSWORD";
    public static final String COLUMN_NAME_LAST_NAME = "LAST_NAME";
    public static final String COLUMN_NAME_FIRST_NAME = "FIRST_NAME";
    public static final String COLUMN_NAME_PHONE = "PHONE";
    public static final String COLUMN_NAME_BIRTH_DATE = "BIRTH_DATE";



    public static final String TABLE_NAME_REPORT="REPORTS_TABLE";
    public static  final String COLUMN_REPORT_ID="REPORT_ID";
    public  static  final String COLUMN_TITLE="REPORT_TITLE";
    public static  final String COLUMN_DESCRIPTION="REPORT_DESCRIPTION";
    public static  final String COLUMN_LOCATION="REPORT_LOCATION";
    public  static  final String COLUMN_DEPARTMENT_RESPONSIBLE="DEPARTMENT_RESPONSIBLE";
    public static  final String COLUMN_DATE="REPORT_DATE";
    public  static  final String COLUMN_DATE_ADDED="REPORT_DATE_ADDED";


    public static final String CREATE_TABLE_USER =
            "CREATE TABLE " + TABLE_NAME_USER + " ( USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    COLUMN_NAME_USER_NAME + " TEXT NOT NULL,"+
                    COLUMN_NAME_LAST_NAME + " TEXT NOT NULL,"+
                    COLUMN_NAME_FIRST_NAME + " TEXT NOT NULL,"+
                    COLUMN_NAME_PHONE + " TEXT NOT NULL,"+
                    COLUMN_NAME_PASSWORD + " TEXT NOT NULL);";



    public static final String DROP_TABLE_USER ="DROP TABLE IF EXISTS " + TABLE_NAME_USER;

    public static  final String CREATE_TABLE_REPORT="CREATE TABLE "+ TABLE_NAME_REPORT + " ( " +
            COLUMN_REPORT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_TITLE +" TEXT, "+
            COLUMN_DESCRIPTION +" TEXT, " +
            COLUMN_DEPARTMENT_RESPONSIBLE + " TEXT, "+
            COLUMN_LOCATION + " TEXT, " +
            COLUMN_DATE +" TEXT,"+
            COLUMN_DATE_ADDED+ "TEXT, "+
            COLUMN_NAME_USER_ID +" INTEGER REFERENCES USERS_TABLE);";

    public  static  final String DROP_TABLE_REPORT="DROP TABLE IF EXISTS "+ TABLE_NAME_REPORT;


    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        //whenever this constructor is called my database will be created
        Log.d("SQL","On create called??");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
       db.execSQL(CREATE_TABLE_REPORT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_USER);
        db.execSQL(DROP_TABLE_REPORT);


        onCreate(db);
    }


    public boolean insertData(String title,String description,String location) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE,title);
        contentValues.put(COLUMN_DESCRIPTION,description);
        contentValues.put(COLUMN_LOCATION,location);
        long result = db.insert(TABLE_NAME_REPORT,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllReports(){
        SQLiteDatabase db=getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM " + TABLE_NAME_REPORT,null);

        return  res;

    }


    public void WriteToFile(JSONArray array){



    }
    public JSONArray getResults() {


        SQLiteDatabase db=getWritableDatabase();
        String searchQuery = "SELECT  * FROM ";
        Cursor cursor = db.rawQuery(searchQuery, null);

        JSONArray resultSet = new JSONArray();
        JSONObject returnObj = new JSONObject();

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {

            int totalColumn = cursor.getColumnCount();
            JSONObject rowObject = new JSONObject();

            for (int i = 0; i < totalColumn; i++) {
                if (cursor.getColumnName(i) != null) {

                    try {

                        if (cursor.getString(i) != null) {
                            Log.d("TAG_NAME", cursor.getString(i));
                            rowObject.put(cursor.getColumnName(i), cursor.getString(i));
                        } else {
                            rowObject.put(cursor.getColumnName(i), "");
                        }
                    } catch (Exception e) {
                        Log.d("TAG_NAME", e.getMessage());
                    }
                }

            }

            resultSet.put(rowObject);
            cursor.moveToNext();
        }

        cursor.close();
        Log.d("TAG_NAME", resultSet.toString());
        return resultSet;
    }





public boolean updateData(String id,String title,String desc,String location ){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_REPORT_ID,id);
        cv.put(COLUMN_TITLE,title);
        cv.put(COLUMN_DESCRIPTION,desc);
        cv.put(COLUMN_LOCATION,location);

        db.update(TABLE_NAME_REPORT,cv,"ID = ?",new String[] { id });

    return true;
}


public Integer deleteData(String id){
    SQLiteDatabase db=this.getWritableDatabase();
    return db.delete(TABLE_NAME_REPORT,"REPORT_ID =? ",new String[]{id});
}


}