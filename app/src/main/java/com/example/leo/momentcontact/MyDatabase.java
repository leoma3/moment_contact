package com.example.leo.momentcontact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

/**
 * Created by LEO on 2016-03-05.
 */
public class MyDatabase {

    private Context context;
    private final MySQLiteHelper mySQLiteHelper;
    private SQLiteDatabase sqLiteDatabase;

    public MyDatabase (Context context) {
        this.context = context;
        mySQLiteHelper = new MySQLiteHelper(context);

        sqLiteDatabase = mySQLiteHelper.getWritableDatabase();

    }

    public long insertData (String name, String password
            , String stanleypark, String policestation
    )
    {

        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.NAME, name);
        contentValues.put(Constants.PASSWORD, password);
        contentValues.put(Constants.STANLEY_PARK, stanleypark);
        contentValues.put(Constants.POLICE_STATION, policestation);
        long id = sqLiteDatabase.insert(Constants.TABLE_NAME, null, contentValues);

        return id;
    }


    public Cursor getData()
    {

        String[] columns = {Constants.UID, Constants.NAME, Constants.PASSWORD
                , Constants.STANLEY_PARK, Constants.POLICE_STATION
        };
        Cursor cursor = sqLiteDatabase.query(Constants.TABLE_NAME, columns, null, null, null, null, null);
        return cursor;
    }

    public Cursor getUser(String who)
    {
        //select plants from database of type 'herb'

        String[] columns = {Constants.UID, Constants.NAME, Constants.PASSWORD
                , Constants.STANLEY_PARK, Constants.POLICE_STATION
        };

        String selection = Constants.NAME + "='" + who + "'";  //Type = 'herb'
        Cursor cursor = sqLiteDatabase.query(Constants.TABLE_NAME, columns, selection, null, null, null, null);

        return cursor;
    }

    public int updateRow (String who, String column, String newValue){
        ContentValues cv = new ContentValues();
        cv.put(column, newValue);
        String[] sa = {who};
        int affectedRows = sqLiteDatabase.update(Constants.TABLE_NAME, cv, Constants.NAME+"=?", sa);
        return affectedRows;
    }
}
