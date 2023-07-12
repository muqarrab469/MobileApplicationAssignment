package com.example.mobileapplicationassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DB extends SQLiteOpenHelper {

    private static final String TABLE_NAME="history";
    private static final String YOURS="yours";
    private static final String RIGHT="right";
    private static  final String COUNT="right_count";


    public DBHelper(Context context){
        super(context,TABLE_NAME,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                YOURS + " TEXT, " +
                RIGHT +" TEXT, " +
                COUNT + " INTEGER)";

        db.execSQL(createTable);
    }

    public void AddHistory(String right_answers,String your_answers,int cn){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();

        values.put(YOURS,your_answers);
        values.put(RIGHT,right_answers);
        values.put(COUNT,cn);

        db.insert(TABLE_NAME,null,values);

        db.close();
    }

    public ArrayList<HistorStringModel>GetHistory(){
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME,null);

        ArrayList<HistorStringModel>historStringModels=new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                historStringModels.add(new HistorStringModel(cursor.getString(1),cursor.getString(2),cursor.getInt(3)));
            }while(cursor.moveToNext());
        }

        cursor.close();
        return historStringModels;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
