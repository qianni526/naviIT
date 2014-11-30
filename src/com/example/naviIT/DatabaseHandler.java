package com.example.naviIT;

import java.util.HashMap;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {
		
		//details of the sqlite database
		private static final int DATABASE_VERSION = 1;
		private static final String DATABASE_NAME = "android_api";
		private static final String TABLE_LOGIN = "login";

		// names of the columns in the table
		private static final String KEY_USERNAME = "username";
		private static final String KEY_PASSWORD = "password";
		private static final String KEY_ID = "id";
		

		public DatabaseHandler(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		// Creating Tables
		@Override
		public void onCreate(SQLiteDatabase db) {
			String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_LOGIN + "("
					+ KEY_ID + " INTEGER PRIMARY KEY," 
					+ KEY_USERNAME + " TEXT,"
					+ KEY_PASSWORD + " TEXT" + ")";
			db.execSQL(CREATE_LOGIN_TABLE);
		}

		// Upgrading database
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// Drop older table if existed
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);

			// Create tables again
			onCreate(db);
		}

		//add username and password to database
		public void addUser(String username, String password) {
			SQLiteDatabase db = this.getWritableDatabase();

			ContentValues values = new ContentValues();
			values.put(KEY_USERNAME, username); 
			values.put(KEY_PASSWORD, password); 

			// insertion
			db.insert(TABLE_LOGIN, null, values);
			//disconnect database
			db.close(); 
		}
		
		//retrieve user's data from login table
		public HashMap<String, String> getUserDetails(){
			HashMap<String,String> user = new HashMap<String,String>();
			String selectQuery = "SELECT  * FROM " + TABLE_LOGIN;
			 
	        SQLiteDatabase db = this.getReadableDatabase();
	        Cursor cursor = db.rawQuery(selectQuery, null);
	        // Move to first row
	        cursor.moveToFirst();
	        if(cursor.getCount() > 0){
	        	user.put("username", cursor.getString(1));
	        	user.put("password", cursor.getString(2));
	        }
	        cursor.close();
	        db.close();
			
			return user;
		}

		//check user login status
		//if there are one row in the table, user will not need to login again 
		//unless they logout
		public int getRowCount() {
			String countQuery = "SELECT  * FROM " + TABLE_LOGIN;
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery(countQuery, null);
			int rowCount = cursor.getCount();
			db.close();
			cursor.close();
			
			return rowCount;
		}
		
		//after user logout, the row in the table will be deleted
		//to ensure that login page will appear next time when the user go to portal
		public void resetTables(){
			SQLiteDatabase db = this.getWritableDatabase();
			// delete row
			db.delete(TABLE_LOGIN, null, null);
			db.close();
		}
		
		//get username for other process to run
		public String  getUsername(){
		    String Table_Name= TABLE_LOGIN;
		    String selectQuery = "SELECT  username FROM " + Table_Name;
		          SQLiteDatabase db = this.getReadableDatabase();
		    Cursor cursor = db.rawQuery(selectQuery, null);
		             String data = null;
		    if (cursor.moveToFirst()) {
		        do {
		           data = cursor.getString(cursor.getColumnIndex(KEY_USERNAME));
		        } while (cursor.moveToNext());
		    }
		    db.close();
		    return data;
		}

}
