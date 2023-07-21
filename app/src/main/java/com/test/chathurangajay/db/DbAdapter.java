package com.test.chathurangajay.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.test.chathurangajay.model.UserData;

import java.util.ArrayList;

public class DbAdapter {

    DbHelper dbHelper;

    public DbAdapter(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void insertData(UserData userData){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id",userData.getId());
        cv.put("email",userData.getEmail());
        cv.put("name",userData.getName());
        cv.put("dob",userData.getDob());
        cv.put("gender",userData.getGender());
        cv.put("company",userData.getCompany());
        cv.put("position",userData.getPosition());
        db.insert("User",null,cv);
        db.close();
    }

    @SuppressLint("Range")

    public ArrayList<UserData> getDataById(String id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("User", new String[]{"id", "email","name","dob","gender","company","position"},"id = ?",new String[]{id},null,null,null);
        ArrayList<UserData> userDatas = new ArrayList<UserData>();
        while (cursor.moveToNext()){
            String uid = cursor.getString(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String dob = cursor.getString(cursor.getColumnIndex("dob"));
            String gender = cursor.getString(cursor.getColumnIndex("gender"));
            String company = cursor.getString(cursor.getColumnIndex("company"));
            String position = cursor.getString(cursor.getColumnIndex("position"));

           userDatas.add(new UserData(uid,email,name,dob,gender,company,position));
        }
        db.close();
        return userDatas;
    }

    static class DbHelper extends SQLiteOpenHelper{

        private static final String DATABASE_NAME = "cbatest";
        private static final int DB_VERSION = 1;
        private Context context;

        public DbHelper(@Nullable Context context) {
            super(context, DATABASE_NAME, null, DB_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL("CREATE TABLE IF NOT EXISTS User(id VARCHAR(128) PRIMARY KEY, name VARCHAR(128) NOT NULL, email VARCHAR(128) NOT NULL, dob VARCHAR(128) NOT NULL, gender VARCHAR(128) NOT NULL, company VARCHAR(128) NOT NULL, position VARCHAR(128) NOT NULL)");
            }catch (Exception e){
                Log.e("DB",e.toString());
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try{
                db.execSQL("DROP TABLE IF EXISTS User");
                onCreate(db);
            }catch (Exception e){
                Log.e("DB",e.toString());
            }
        }
    }



}
