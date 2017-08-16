package com.example.shubham.bmicalculator;

/**
 * Created by Shubham on 7/11/2017.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Parth Gangar on 14-06-2017.
 */

public class DbHandler extends SQLiteOpenHelper{
    static String answer="";
    SQLiteDatabase db;
    Context context;
    DbHandler(Context context)
    {
        super(context,"personaldb",null,1);
        this.context=context;
        db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String i="create table personal(tdate TEXT,name TEXT)";
        db.execSQL(i);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void addDetails(String tdate,String name)
    {
        ContentValues cv=new ContentValues();
        cv.put("tdate",tdate);
        cv.put("name",name);
        long rid=db.insert("personal",null,cv);
        if(rid<0)
        {
            Toast.makeText(context, "Insert Issue", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(context, "Record Inserted", Toast.LENGTH_SHORT).show();

        viewStudent();
    }

    public void viewStudent()
    {
        StringBuffer sb=new StringBuffer("");
        Cursor cursor=db.query("personal",null,null,null,null,null,null);
        cursor.moveToFirst();

        if(cursor!=null && cursor.getCount()>0)
        {
            do
            {
                String tdate=cursor.getString(0);
                String text=cursor.getString(1);

                sb.append(text+" "+tdate);
            }while(cursor.moveToNext());

        }
        else
            Toast.makeText(context, "No results to show", Toast.LENGTH_SHORT).show();
        answer=answer+sb.toString();
    }
}


