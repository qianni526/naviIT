package com.example.naviIT;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper{

	 // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "naviIT";
    
    // Paths table name
    private static final String TABLE_PATHS = "paths";
    // Regions table name
    private static final String TABLE_REGIONS = "regions";

    // Paths Table Columns names
    private static final String KEY_PATH_ID = "pathID";
    private static final String KEY_CURRENT = "currentRegion";
    private static final String KEY_NEXT = "nextRegion";
    private static final String KEY_DEGREE = "degree";
    private static final String KEY_STEPCOUNT = "stepcount";
    
    // Regions Table Columns names
    private static final String KEY_REGION_ID = "regionID";
    private static final String KEY_REGION_BLOCK = "block";
    private static final String KEY_REGION_FLOOR = "floor";
    private static final String KEY_REGION_NEIGHBOUR = "neighbour";
    private static final String KEY_REGION_X = "x";
    private static final String KEY_REGION_Y = "y";
    

    private static final String[] PATH_COLUMNS = {KEY_PATH_ID,KEY_CURRENT,KEY_NEXT, KEY_DEGREE, KEY_STEPCOUNT};
    private static final String[] REGION_COLUMNS = {KEY_REGION_ID,KEY_REGION_BLOCK, KEY_REGION_FLOOR, KEY_REGION_NEIGHBOUR, KEY_REGION_X, KEY_REGION_Y};
    
 
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);  
    }
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		 // SQL statement to create path table
        String CREATE_PATH_TABLE = "CREATE TABLE paths ( " +
                "pathID INTEGER PRIMARY KEY AUTOINCREMENT, " + 
                "currentRegion VARCHAR, "+
                "nextRegion VARCHAR, "+"degree FLOAT, "+"stepcount INT )";
        
        String CREATE_REGION_TABLE = "CREATE TABLE regions ( " +
                "regionID VARCHAR PRIMARY KEY, " + 
                "block VARCHAR, "+
                "floor INT, "+"neighbour VARCHAR, "+"x INT, "+" y INT )";
        
        // create books table
        db.execSQL(CREATE_PATH_TABLE);
        db.execSQL(CREATE_REGION_TABLE);
        
        //Insert path to database
        //Block A G Floor path
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('1', '1_1', 205, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('1_1', '1', 25, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('1_1', '1_0', 294, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('1_0', '1_1', 112, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('1_0', '1_3', 283, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('1_3', '1_0', 122, 4); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('1_0', '1_4', 42, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('1_4', '1_0', 225, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('1_4', '1_5', 42, 7); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('1_5', '1_4', 225, 7); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('1_5', '1_6', 42, 8); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('1_6', '1_5', 225, 8); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('1_6', '1_11', 295, 5); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('1_11', '1_6', 114, 5); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('1_11', '1_10', 295, 4); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('1_10', '1_11', 114, 4); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('1_6', '1_14', 102, 7); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('1_14', '1_6', 288, 7); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('1_1', '1_2', 112, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('1_2', '1_1', 294, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('1_2', '1_7', 28, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('1_7', '1_2', 211, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('1_7', '1_8', 28, 7); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('1_8', '1_7', 211, 7); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('1_8', '1_9', 28, 8); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('1_9', '1_8', 211, 8); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('1_9', '1_12', 124, 5); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('1_12', '1_9', 303, 5); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('1_12', '1_13', 124, 4); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('1_13', '1_12', 303, 4); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('1_14', '1_9', 124, 7); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('1_9', '1_14', 295, 7); ");
      //Staircase path (G and First Floor)
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('1', '1a', 112, 5); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('1a', '2_1', 205, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('1', '1b', 294, 5); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('1b', '2_1', 205, 5); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_1', '1c', 25, 15); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('1c', '1', 205, 5); ");
        
        //Block A First Floor Path
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_1', '2_0', 287, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_0', '2_1', 102, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_1', '2_2', 102, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_2', '2_1', 287, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_0', '2_4', 287, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_4', '2_0', 102, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_4', '2_5', 28, 7); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_5', '2_4', 210, 7); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_5', '2_6', 28, 7); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_6', '2_5', 210, 7); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_6', '2_18', 292, 5); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_18', '2_6', 106, 5); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_18', '2_17', 292, 5); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_17', '2_18', 106, 5); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_6', '2_7', 28, 4); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_7', '2_6', 210, 4); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_7', '2_8', 28, 5); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_8', '2_7', 210, 5); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_7', '2_15', 111, 5); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_15', '2_7', 289, 5); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_15', '2_16', 111, 7); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_16', '2_13', 111, 5); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_16', '2_15', 289, 7); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_13', '2_16', 289, 5); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_13', '2_14', 25, 5); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_14', '2_13', 192, 5); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_13', '2_12', 188, 4); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_12', '2_13', 25, 4); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_12', '2_19', 116, 5); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_19', '2_12', 294, 5); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_19', '2_20', 116, 5); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_20', '2_19', 294, 5); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_12', '2_11', 188, 7); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_11', '2_12', 25, 7); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_11', '2_10', 188, 7); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_10', '2_11', 25, 7); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_10', '2_9', 188, 4); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_9', '2_10', 25, 4); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_2', '2_10', 102, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_10', '2_2', 287, 6); ");
      //Staircase path (First and Second Floor)
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_1', '2', 25, 15); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2', '2a', 287, 15); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2', '2b', 102, 15); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2a', '3_1', 210, 15); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2b', '3_1', 210, 15); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_1', '2c', 25, 15); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2c', '3_1', 205, 15); ");

        //Block A Second Floor Path
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_1', '3_0', 296, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_0', '3_1', 108, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_1', '3_2', 108, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_2', '3_1', 296, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_0', '3_3', 34, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_3', '3_0', 215, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_3', '3_5', 34, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_5', '3_3', 215, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_3', '3_4', 296, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_4', '3_3', 108, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_5', '3_9', 288, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_9', '3_5', 103, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_9', '3_8', 288, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_8', '3_9', 103, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_8', '3_11', 25, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_11', '3_8', 193, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_11', '3_12', 25, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_12', '3_11', 193, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_12', '3_13', 25, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_13', '3_12', 193, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_13', '3_14', 114, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_14', '3_13', 290, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_14', '3_15', 114, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_15', '3_14', 290, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_15', '3_16', 114, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_16', '3_15', 290, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_16', '3_17', 114, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_17', '3_16', 290, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_17', '3_18', 203, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_18', '3_17', 25, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_18', '3_19', 203, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_19', '3_18', 25, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_19', '3_10', 203, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_10', '3_19', 25, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_10', '3_7', 288, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_7', '3_10', 103, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_7', '3_6', 195, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_6', '3_7', 34, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_6', '3_2', 195, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_2', '3_6', 34, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_6', '3_20', 108, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_20', '3_6', 296, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_20', '3_21', 108, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_21', '3_20', 296, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_21', '3_22', 25, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('3_22', '3_21', 196, 6); ");

        //Block B Ground Floor Path
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4A', '4_0', 102, 15); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_0', '4A', 287, 15); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_0', '4_1', 102, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_1', '4_0', 285, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_1', '4_6', 25, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_6', '4_1', 210, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_6', '4_5', 102, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_5', '4_6', 287, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_5', '4_4', 25, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_4', '4_5', 210, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_4', '4_3', 25, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_3', '4_4', 210, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_3', '4_2', 102, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_2', '4_3', 287, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_6', '4_7', 287, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_7', '4_6', 102, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_7', '4_8', 287, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_8', '4_7', 102, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_8', '4_9', 287, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_9', '4_8', 102, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_9', '4_16', 25, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_16', '4_9', 210, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_16', '4_13', 25, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_13', '4_16', 210, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_13', '4_14', 287, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_14', '4_13', 102, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_14', '4_15', 287, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_15', '4_14', 102, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_13', '4_12', 25, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_12', '4_13', 210, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_12', '4_11', 25, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_11', '4_12', 210, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_11', '4_10', 102, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_10', '4_11', 287, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_9', '4_19', 210, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_19', '4_9', 25, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_19', '4_20', 210, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_20', '4_19', 25, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_20', '4_21', 210, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_21', '4_20', 25, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_21', '4_22', 102, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_22', '4_21', 287, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_21', '4_23', 210, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_23', '4_21', 25, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_23', '4_24', 287, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_24', '4_23', 102, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_24', '4_25', 300, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_25', '4_24', 110, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_25', '4_26', 300, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_26', '4_25', 110, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_26', '4_27', 300, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_27', '4_26', 110, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_27', '4_28', 300, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_28', '4_27', 110, 10); ");

        //Staircase path (Block B Ground and First Floor)
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4A', '5A', 287, 15); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5A', '4A', 102, 15); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5A', '5_28', 25, 15); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_28', '5A', 210, 15); ");
        
        //Block B First Floor Path
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_0', '5_1', 60, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_1', '5_0', 245, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_1', '5_2', 60, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_2', '5_1', 245, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_2', '5_3', 60, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_3', '5_2', 245, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_3', '5_7', 102, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_7', '5_3', 287, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_7', '5_6', 25, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_6', '5_7', 210, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_6', '5C', 25, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5C', '5_6', 210, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_6', '5_16', 102, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_16', '5_6', 287, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_16', '5_17', 102, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_17', '5_16', 287, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_17', '5_18', 102, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_18', '5_17', 287, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_18', '5_19', 102, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_19', '5_18', 287, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_19', '5_20', 102, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_20', '5_19', 287, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_20', '5_21', 210, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_21', '5_20', 25, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_21', '5_22', 210, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_22', '5_21', 25, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_22', '5_23', 210, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_23', '5_22', 25, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_23', '5_24', 210, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_24', '5_23', 25, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_24', '5_25', 210, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_25', '5_24', 25, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_25', '5_26', 287, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_26', '5_25', 102, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_26', '5_27', 287, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_27', '5_26', 102, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_27', '5_28', 287, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_28', '5_27', 102, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_28', '5_11', 287, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_11', '5_28', 102, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_7', '5_8', 210, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_8', '5_7', 25, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_8', '5_9', 210, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_9', '5_8', 25, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_9', '5_5', 287, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_5', '5_9', 102, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_5', '5_4', 287, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_4', '5_5', 102, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_9', '5_10', 210, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_10', '5_9', 25, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_10', '5_11', 210, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_11', '5_10', 25, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_11', '5_12', 210, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_12', '5_11', 25, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_12', '5_13', 210, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_13', '5_12', 25, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_13', '5_14', 210, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_14', '5_13', 25, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_14', '5_15', 102, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_15', '5_14', 287, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_14', '5D', 210, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_D', '5_14', 25, 10); ");
        
        //Staircase path (Block B First and Second Floor)
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5_23', '5B', 102, 15); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5B', '5_23', 287, 15); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('5B', '6A', 25 , 15); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6A', '5B', 210 , 15); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6A', '6_6', 287 , 15); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_6', '6A', 102 , 15); ");

        //Block B Second Floor Path
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_0', '6B', 25, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6B', '6_0', 210, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_0', '6_1', 102, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_1', '6_0', 287, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_1', '6_2', 102, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_2', '6_1', 287, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_2', '6_3', 102, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_3', '6_2', 287, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_3', '6_4', 102, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_4', '6_3', 287, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_4', '6_5', 210, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_5', '6_4', 25, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_5', '6_27', 210, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_27', '6_5', 25, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_27', '6_7', 102, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_7', '6_27', 287, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_27', '6_6', 210, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_6', '6_27', 25, 6); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_6', '6_8', 210, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_8', '6_6', 25, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_8', '6_9', 210, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_9', '6_8', 25, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_9', '6_10', 210, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_10', '6_9', 25, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_9', '6_11', 287, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_11', '6_9', 102, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_11', '6_12', 287, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_12', '6_11', 102, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_12', '6_13', 287, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_13', '6_12', 102, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_13', '6_16', 287, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_16', '6_13', 102, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_0', '6_14', 210, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_14', '6_0', 25, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_14', '6_15', 210, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_15', '6_14', 25, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_15', '6_16', 210, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_16', '6_15', 25, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_16', '6_17', 210, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_17', '6_16', 25, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_17', '6_18', 210, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_18', '6_17', 25, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_18', '6_19', 210, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_19', '6_18', 25, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_19', '6_20', 102, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_20', '6_19', 287, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_20', '6D', 25, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6D', '6_20', 210, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_19', '6C', 210, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6C', '6_19', 25, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_15', '6_21', 287, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_21', '6_15', 102, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_21', '6_22', 270, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_22', '6_21', 60, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_22', '6_23', 270, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_23', '6_22', 60, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_23', '6_24', 210, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_24', '6_23', 25, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_24', '6_25', 200, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_25', '6_24', 20, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_25', '6_26', 110, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_26', '6_25', 295, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_26', '6_17', 102, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_17', '6_26', 287, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_15', '6_28', 102, 10); ");
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_28', '6_15', 287, 10); ");

        //Paths between Block A and Block B
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('1_10', '4_2', 287, 25); "); // Block A g floor to block b g floor
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('4_2', '1_10', 102, 25); "); // Block B g floor to block a g floor
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('2_17', '6_7', 287, 25); "); // Block A First floor to block b Second floor
        db.execSQL("INSERT INTO "+TABLE_PATHS+"("+KEY_CURRENT+","+KEY_NEXT+","+KEY_DEGREE+"," +KEY_STEPCOUNT+") values('6_7', '2_17', 102, 25); "); // Block B Second floor to block a First floor
        
        
        //Insert region to database
        //Block A G Floor Region
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('1', 'A', 1, '1_1', 360, 496); "); //get(0)
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('1_0', 'A', 1, '1_1,1_3,1_4', 247, 545); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('1_1', 'A', 1, '1,1_0,1_2', 360, 545); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('1_2', 'A', 1, '1_1,1_7', 470, 545); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('1_3', 'A', 1, '1_0', 179, 545); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('1_4', 'A', 1, '1_0,1_5', 247, 477); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('1_5', 'A', 1, '1_4,1_6', 247, 388); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('1_6', 'A', 1, '1_5,1_11,1_14', 247, 273); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('1_7', 'A', 1, '1_2,1_8', 470, 477); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('1_8', 'A', 1, '1_7,1_9', 470, 388); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('1_9', 'A', 1, '1_8,1_12,1_14', 470, 273); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('1_10', 'A', 1, '1_11', 67, 273); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('1_11', 'A', 1, '1_6,1_10', 170, 273); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('1_12', 'A', 1, '1_9,1_13', 542, 273); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('1_13', 'A', 1, '1_12', 636, 273); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('1_14', 'A', 1, '1_6,1_9', 360, 273); ");
     
        //Block A First Floor Region
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('2', 'A', 2, '2_1', 352, 480); "); //get(16)
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('2_0', 'A', 2, '2_1,2_3,2_4', 263, 550); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('2_1', 'A', 2, '2,2_0,2_2', 352, 550); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('2_2', 'A', 2,	'2_1,2_9,2_10',	441,550); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('2_3', 'A', 2,	'2_0,2_4',	212, 602); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('2_4', 'A', 2,	'2_0,2_3,2_5',	212, 550); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('2_5', 'A', 2,	'2_4,2_6',	212, 413); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('2_6', 'A', 2,	'2_5,2_7,2_18',	212, 279); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('2_7', 'A', 2,	'2_6,2_8,2_15',	212, 235); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('2_8', 'A', 2,	'2_7',	212, 106); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('2_9', 'A', 2,	'2_2,2_10',	495, 603); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('2_10', 'A', 2, '2_2,2_9,2_11', 495, 550); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('2_11', 'A', 2, '2_10,2_12', 495, 419); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('2_12', 'A', 2, '2_11,2_13,2_19', 495, 279); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('2_13', 'A', 2, '2_12,2_14,2_16', 495, 235); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('2_14', 'A', 2, '2_13', 495, 79); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('2_15', 'A', 2, '2_7,2_16',	285, 235); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('2_16', 'A', 2, '2_13,2_15', 409, 235); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('2_17', 'A', 2, '2_18',	46, 279); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('2_18', 'A', 2, '2_6,2_17', 136, 279); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('2_19', 'A', 2, '2_12,2_20', 554, 279); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('2_20', 'A', 2, '2_19', 651, 279); ");

        //Block A Second Floor Region
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('3', 'A', 3, '3_1', 353, 483); ");    //get(38)  
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('3_0', 'A', 3, '3_1,3_3', 214, 527); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('3_1', 'A', 3, '3,3_0,3_2', 353, 527); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('3_2', 'A', 3, '3_1,3_6', 486, 527); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('3_3', 'A', 3, '3_0,3_4,3_5', 214, 444); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('3_4', 'A', 3, '3_3', 107, 444); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('3_5', 'A', 3, '3_3,3_9', 214, 290); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('3_6', 'A', 3, '3_2,3_7,3_20', 486, 495); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('3_7', 'A', 3, '3_6,3_10', 486, 290); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('3_8', 'A', 3, '3_9,3_11', 112, 290); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('3_9', 'A', 3, '3_5,3_8', 161, 290); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('3_10', 'A', 3, '3_7,3_19', 593, 290); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('3_11', 'A', 3, '3_8,3_12', 112, 229); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('3_12', 'A', 3, '3_11,3_13', 112, 147); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('3_13', 'A', 3, '3_12,3_14', 112, 84); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('3_14', 'A', 3, '3_13,3_15', 250, 84); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('3_15', 'A', 3, '3_14,3_16', 353, 84); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('3_16', 'A', 3, '3_15,3_17', 451, 84); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('3_17', 'A', 3, '3_16,3_18', 593, 84); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('3_18', 'A', 3, '3_17,3_19', 593, 143); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('3_19', 'A', 3, '3_10,3_18', 593, 226); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('3_20', 'A', 3, '3_6,3_21', 542, 495); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('3_21', 'A', 3, '3_20,3_22', 600, 495); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('3_22', 'A', 3, '3_21', 600, 397); "); //get(61)
                
        //Block B Ground Floor Region
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('4A', 'B', 4, '4_0', 399, 432); "); //get(62)
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('4B', 'B', 4, '4_2', 660, 267); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('4C', 'B', 4, '4_11', 328, 50); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('4D', 'B', 4, '4_22', 357, 544); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('4_0', 'B', 4, '4A,4_1,4_7', 451, 432); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('4_1', 'B', 4, '4_0,4_6', 523, 432); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('4_2', 'B', 4, '4B,4_3', 660, 235); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('4_3', 'B', 4, '4_2,4_4', 607, 235); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('4_4', 'B', 4, '4_3,4_5', 607, 314); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('4_5', 'B', 4, '4_4,4_6', 607, 361); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('4_6', 'B', 4, '4_1,4_5,4_7', 523, 361); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('4_7', 'B', 4, '4_0,4_6,4_8,4_18', 451, 361); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('4_8', 'B', 4, '4_7,4_9', 399, 361); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('4_9', 'B', 4, '4_8,4_16,4_19', 328, 361); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('4_10', 'B', 4, '4_11', 399, 91); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('4_11', 'B', 4, '4C,4_10,4_12', 328, 91); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('4_12', 'B', 4, '4_11,4_13', 328, 141); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('4_13', 'B', 4, '4_12,4_14,4_16', 328, 240); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('4_14', 'B', 4, '4_13,4_15', 277, 240); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('4_15', 'B', 4, '4_14', 232, 260); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('4_16', 'B', 4, '4_9,4_13', 328, 305); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('4_17', 'B', 4, '4_18', 451, 278); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('4_18', 'B', 4, '4_7,4_17', 451, 326); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('4_19', 'B', 4, '4_9,4_20', 328, 406); ");//get(85)
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('4_20', 'B', 4, '4_19,4_21', 328, 468); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('4_21', 'B', 4, '4_20,4_22,4_23', 328, 572); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('4_22', 'B', 4, '4D,4_21', 357, 572); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('4_23', 'B', 4, '4_21,4_24', 328, 633); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('4_24', 'B', 4, '4_23,4_25', 244, 633); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('4_25', 'B', 4, '4_24,4_26', 165, 604); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('4_26', 'B', 4, '4_25,4_27', 101, 555); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('4_27', 'B', 4, '4_26,4_28', 52, 496); ");
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('4_28', 'B', 4, '4_27', 27, 434); ");
       
        //Block B First Floor Region
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('5A', 'B', 5, '5_28', 385, 424); "); //get(95)
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('5B', 'B', 5, '5_23', 651, 281); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('5C', 'B', 5, '5_6', 329, 62); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('5D', 'B', 5, '5_14', 327, 619); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('5E', 'B', 5, '5_15', 357, 556); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('5_0', 'B', 5, '5_1', 129, 224); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('5_1', 'B', 5, '5_0,5_2', 166, 185); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('5_2', 'B', 5, '5_1,5_3', 222, 150); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('5_3', 'B', 5, '5_2,5_7', 280, 129); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('5_4', 'B', 5, '5_5', 227, 226); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('5_5', 'B', 5, '5_4,5_9', 290, 229); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('5_6', 'B', 5, '5C,5_7,5_16', 329, 101); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('5_7', 'B', 5, '5_3,5_6,5_8', 329, 120); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('5_8', 'B', 5, '5_7,5_9', 329, 162); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('5_9', 'B', 5, '5_5,5_8,5_10', 329, 218); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('5_10', 'B', 5, '5_9,5_11', 329, 287); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('5_11', 'B', 5, '5_10,5_12,5_28', 329, 372); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('5_12', 'B', 5, '5_11,5_13', 329, 403); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('5_13', 'B', 5, '5_12,5_14', 329, 424); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('5_14', 'B', 5, '5D,5_13,5_15', 329, 579); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('5_15', 'B', 5, '5E,5_14', 357, 579); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('5_16', 'B', 5, '5_6,5_17', 366, 101); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('5_17', 'B', 5, '5_16,5_18', 417, 101); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('5_18', 'B', 5, '5_17,5_19', 468, 101); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('5_19', 'B', 5, '5_18,5_20', 521, 101); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('5_20', 'B', 5, '5_19,5_21', 599, 101); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('5_21', 'B', 5, '5_20,5_22', 599, 149); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('5_22', 'B', 5, '5_21,5_23', 599, 214); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('5_23', 'B', 5, '5_22,5_24', 599, 277); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('5_24', 'B', 5, '5_23,5_25', 599, 335); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('5_25', 'B', 5, '5_24,5_26', 599, 378); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('5_26', 'B', 5, '5_25,5_27', 515, 378); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('5_27', 'B', 5, '5_26', 515, 378); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('5_28', 'B', 5, '5A,5_11,5_27', 374, 378); "); 
        
        //Block B Second Floor Region
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('6A', 'B', 6, '6_6', 598, 236); "); //get(129)
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('6B', 'B', 6, '6_0', 333, 348); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('6C', 'B', 6, '6_19', 332, 566); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('6D', 'B', 6, '6_20', 361, 494); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('6_0', 'B', 6, '6B,6_1,6_14', 332, 84); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('6_1', 'B', 6, '6_0,6_2', 369, 84); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('6_2', 'B', 6, '6_1,6_3', 434, 84); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('6_3', 'B', 6, '6_2,6_4', 512, 84); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('6_4', 'B', 6, '6_3,6_5', 564, 84); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('6_5', 'B', 6, '6_4,6_27', 564, 156); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('6_6', 'B', 6, '6_8,6_27', 564, 236); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('6_7', 'B', 6, '6_27', 598, 207); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('6_27', 'B', 6, '6_5,6_7,6_6', 564, 207); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('6_8', 'B', 6, '6_6,6_9', 564, 287); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('6_9', 'B', 6, '6_8,6_10,6_11', 564, 335); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('6_10', 'B', 6, '6_9', 564, 369); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('6_11', 'B', 6, '6_9,6_12', 512, 335); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('6_12', 'B', 6, '6_11,6_13', 441, 335); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('6_13', 'B', 6, '6_12,6_16', 376, 335); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('6_14', 'B', 6, '6_0,6_15', 332, 191); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('6_15', 'B', 6, '6_14,6_16,6_21,6_28', 332, 237); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('6_16', 'B', 6, '6_15,6_17', 332, 331); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('6_17', 'B', 6, '6_16,6_18,6_26', 332, 413); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('6_18', 'B', 6, '6_17,6_19', 332, 452); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('6_19', 'B', 6, '6C,6_18,6_20', 332, 527); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('6_20', 'B', 6, '6D,6_19', 361, 530); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('6_21', 'B', 6, '6_15,6_22', 296, 245); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('6_22', 'B', 6, '6_21,6_23', 257, 267); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('6_23', 'B', 6, '6_22,6_24', 241, 298); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('6_24', 'B', 6, '6_23,6_25', 238, 340); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('6_25', 'B', 6, '6_24,6_26', 256, 382); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('6_26', 'B', 6, '6_17,6_25', 297, 406); "); 
        db.execSQL("INSERT INTO "+TABLE_REGIONS+"("+KEY_REGION_ID+","+KEY_REGION_BLOCK+","+KEY_REGION_FLOOR+","+KEY_REGION_NEIGHBOUR+","+KEY_REGION_X+","+KEY_REGION_Y+") values('6_28', 'B', 6, '6_15', 418, 237); "); 

        
        
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older paths table if existed
        db.execSQL("DROP TABLE IF EXISTS paths");
        db.execSQL("DROP TABLE IF EXISTS regions");
 
        // create fresh paths table
        this.onCreate(db);
	}
	
	public void addPath(Path path){
        //for logging
		Log.d("addPath", path.toString()); 
		
		// 1. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();
		
		// 2. create ContentValues to add key "column"/value
		ContentValues values = new ContentValues();
		values.put(KEY_CURRENT, path.getCurrentRegion()); // get current region 
		values.put(KEY_NEXT, path.getNextRegion()); // get next region
		values.put(KEY_DEGREE, path.getDegree()); // get degree 
		values.put(KEY_STEPCOUNT, path.getStepCount()); // get stepcount
		
		// 3. insert
		db.insert(TABLE_PATHS, // table
		        null, //nullColumnHack
		        values); // key/value -> keys = column names/ values = column values
		
		// 4. close
		db.close(); 
	}
	
	public Path getPath(int id){
		 
	    // 1. get reference to readable DB
	    SQLiteDatabase db = this.getReadableDatabase();
	 
	    // 2. build query
	    Cursor cursor = 
	            db.query(TABLE_PATHS, // a. table
	            PATH_COLUMNS, // b. column names
	            " id = ?", // c. selections 
	            new String[] { String.valueOf(id) }, // d. selections args
	            null, // e. group by
	            null, // f. having
	            null, // g. order by
	            null); // h. limit
	 
	    // 3. if we got results get the first one
	    if (cursor != null)
	        cursor.moveToFirst();
	 
	    // 4. build path object
	    //Path path = new Path(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), Float.parseFloat(cursor.getString(3)), Integer.parseInt(cursor.getString(4)));
	    Path path = new Path(cursor.getString(1), cursor.getString(2), Float.parseFloat(cursor.getString(3)), Integer.parseInt(cursor.getString(4)));
	    path.setPathID(Integer.parseInt(cursor.getString(0)));
	    path.setCurrentRegion(cursor.getString(1));
	    path.setNextRegion(cursor.getString(2));
	    path.setDegree(Float.parseFloat(cursor.getString(3)));
	    path.setStepCount(Integer.parseInt(cursor.getString(4)));
	 	
	    
	    //log 
	    Log.d("getPath("+id+")", path.toString());
	 
	    // 5. return book
	    return path;
	}
	
	public ArrayList<Path> getAllPaths() {
	       ArrayList<Path> paths = new ArrayList<Path>();
	 
	       // 1. build the query
	       String query = "SELECT  * FROM " + TABLE_PATHS;
	 
	       // 2. get reference to writable DB
	       SQLiteDatabase db = this.getWritableDatabase();
	       Cursor cursor = db.rawQuery(query, null);
	 
	       // 3. go over each row, build book and add it to list
	       Path path = null;
	       if (cursor.moveToFirst()) {
	           do {
	               //path = new Path(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), Float.parseFloat(cursor.getString(3)), Integer.parseInt(cursor.getString(4)));
	               path = new Path(cursor.getString(1), cursor.getString(2), Float.parseFloat(cursor.getString(3)), Integer.parseInt(cursor.getString(4)));
		       	    path.setPathID(Integer.parseInt(cursor.getString(0)));
		       	    path.setCurrentRegion(cursor.getString(1));
		       	    path.setNextRegion(cursor.getString(2));
		       	    path.setDegree(Float.parseFloat(cursor.getString(3)));
		       	    path.setStepCount(Integer.parseInt(cursor.getString(4)));
	               
	               // Add path to paths
	               paths.add(path);
	           } while (cursor.moveToNext());
	       }
	 
	       Log.d("getAllPaths()", paths.toString());
	 
	       // return books
	       return paths;
	   }
	
	public int updatePath(Path path) {
		 
	    // 1. get reference to writable DB
	    SQLiteDatabase db = this.getWritableDatabase();
	 
	    // 2. create ContentValues to add key "column"/value
	    ContentValues values = new ContentValues();
	    values.put(KEY_CURRENT, path.getCurrentRegion()); // get current region 
		values.put(KEY_NEXT, path.getNextRegion()); // get next region
		values.put(KEY_DEGREE, path.getDegree()); // get degree 
		values.put(KEY_STEPCOUNT, path.getStepCount()); // get stepcount
	 
	    // 3. updating row
	    int i = db.update(TABLE_PATHS, //table
	            values, // column/value
	            KEY_PATH_ID+" = ?", // selections
	            new String[] { String.valueOf(path.getPathID()) }); //selection args
	 
	    // 4. close
	    db.close();
	 
	    return i;
	 
	}
	
	public void deletePath(Path path) {
		 
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
 
        // 2. delete
        db.delete(TABLE_PATHS, //table name
                KEY_PATH_ID+" = ?",  // selections
                new String[] { String.valueOf(path.getPathID()) }); //selections args
 
        // 3. close
        db.close();
 
        //log
    Log.d("deletePath", path.toString());
 
    }
	
	public void addRegion(Region region){
        //for logging
		Log.d("addRegion", region.toString()); 
		
		// 1. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();
		
		// 2. create ContentValues to add key "column"/value
		ContentValues values = new ContentValues();
		
		values.put(KEY_REGION_ID, region.getRegionId()); // get regionID 
		values.put(KEY_REGION_BLOCK, region.getBlock()); // get block 
		values.put(KEY_REGION_FLOOR, region.getFloor()); // get floor 
		values.put(KEY_REGION_NEIGHBOUR, region.getNeighbour()); // get neighbour 
		values.put(KEY_REGION_X, region.getX()); // get X 
		values.put(KEY_REGION_Y, region.getY()); // get Y 
		
		// 3. insert
		db.insertWithOnConflict(TABLE_REGIONS, null, values, SQLiteDatabase.CONFLICT_REPLACE);
		/*
		db.insert(TABLE_REGIONS, // table
		        null, //nullColumnHack
		        values); // key/value -> keys = column names/ values = column values
		*/
		
		
		// 4. close
		db.close(); 
	}
	
	public Region getRegion(int id){
		 
	    // 1. get reference to readable DB
	    SQLiteDatabase db = this.getReadableDatabase();
	 
	    // 2. build query
	    Cursor cursor = 
	            db.query(TABLE_REGIONS, // a. table
	            REGION_COLUMNS, // b. column names
	            " id = ?", // c. selections 
	            new String[] { String.valueOf(id) }, // d. selections args
	            null, // e. group by
	            null, // f. having
	            null, // g. order by
	            null); // h. limit
	 
	    // 3. if we got results get the first one
	    if (cursor != null)
	        cursor.moveToFirst();
	 
	    // 4. build region object
	   
	    Region region = new Region(cursor.getString(0), cursor.getString(1), Integer.parseInt(cursor.getString(2)), cursor.getString(3), Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5)));
	    
	    //log 
	    Log.d("getRegion("+id+")", region.toString());
	 
	    // 5. return book
	    return region;
	}
	
	public ArrayList<Region> getAllRegions() {
		ArrayList<Region> regions = new ArrayList<Region>();
	 
	       // 1. build the query
	       String query = "SELECT  * FROM " + TABLE_REGIONS;
	 
	       // 2. get reference to writable DB
	       SQLiteDatabase db = this.getWritableDatabase();
	       Cursor cursor = db.rawQuery(query, null);
	 
	       // 3. go over each row, build book and add it to list
	       Region region = null;
	       if (cursor.moveToFirst()) {
	           do {
	        	   region = new Region(cursor.getString(0), cursor.getString(1), Integer.parseInt(cursor.getString(2)), cursor.getString(3), Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5)));
	        	   
	               // Add region to regions
	               regions.add(region);
	           } while (cursor.moveToNext());
	       }
	 
	       Log.d("getAllRegions()", regions.toString());
	 
	       // return books
	       return regions;
	   }

		public void deleteRegion(Region region) {
			 
	        // 1. get reference to writable DB
	        SQLiteDatabase db = this.getWritableDatabase();
	 
	        // 2. delete
	        db.delete(TABLE_REGIONS, //table name
	                KEY_PATH_ID+" = ?",  // selections
	                new String[] { String.valueOf(region.getRegionId()) }); //selections args
	 
	        // 3. close
	        db.close();
	 
	        //log
	    Log.d("deletePath", region.toString());
	 
	    }
	
}
