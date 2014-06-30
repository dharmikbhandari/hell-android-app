package com.dharmik.myhell;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

//http://hmkcode.com/android-simple-sqlite-database-tutorial/
public class HellSqlLiteHelper extends SQLiteOpenHelper {

	// Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "HellDB";
    
    private static final String TABLE_NAME="Expense";
    
    private static final String COLUMN_NAME_ID="Id";
    private static final String COLUMN_NAME_USER_ID="UserId";
    private static final String COLUMN_NAME_CATEGORY_ID="CategoryId";
    private static final String COLUMN_NAME_AMOUNT="Amount";
    private static final String COLUMN_NAME_REMARK="Remark";
    private static final String COLUMN_NAME_CREATED_DATE="CreatedDate";
    
    private static final String[] COLUMNS = {
    	COLUMN_NAME_ID,
    	COLUMN_NAME_USER_ID,
    	COLUMN_NAME_CATEGORY_ID,
    	COLUMN_NAME_AMOUNT,
    	COLUMN_NAME_REMARK,
    	COLUMN_NAME_CREATED_DATE
    	};
    
 
    public HellSqlLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);  
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
    	//TODO Use private variables here
        // SQL statement to create book table
        String createTable = "CREATE TABLE Expense ( Id INTEGER PRIMARY KEY AUTOINCREMENT,UserId INTEGER, CategoryId INTEGER, Amount REAL, Remark TEXT,CreatedDate TEXT)";
 
        // create books table
        db.execSQL(createTable);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS Expense");
 
        // create fresh books table
        this.onCreate(db);
    }
    
    public void InsertExpense(Expense expense){
        
    	//for logging
    	Log.d("Expense:", expense.toString()); 

    	// 1. get reference to writable DB
    	SQLiteDatabase db = this.getWritableDatabase();

    	//2. create ContentValues to add key "column"/value
    	ContentValues values = new ContentValues();
    	values.put(COLUMN_NAME_USER_ID, expense.getUserId());
    	values.put(COLUMN_NAME_CATEGORY_ID, expense.getCategoryId());
    	values.put(COLUMN_NAME_AMOUNT, expense.getAmount());
    	values.put(COLUMN_NAME_REMARK, expense.getRemark());
    	values.put(COLUMN_NAME_CREATED_DATE, expense.getCreatedDate().toString());
    	 

    	// 3. insert
    	db.insert(TABLE_NAME, // table
        null, //nullColumnHack
        values); // key/value -> keys = column names/ values = column values

        // 4. close
    	db.close(); 
}

    public Expense GetExpense(int id){
    	 
        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();
     
        // 2. build query
        Cursor cursor = 
                db.query(TABLE_NAME, // a. table
                COLUMNS, // b. column names
                " id = ?", // c. selections 
                new String[] { String.valueOf(id) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
     
        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();
     
        // 4. build expense object
        Expense expense = new Expense();
        expense.setId(Integer.parseInt(cursor.getString(0)));
        expense.setUserId(cursor.getInt(1));
        expense.setCategoryId(cursor.getInt(2));
        expense.setAmount(cursor.getFloat(3));
        expense.setRemark(cursor.getString(4));
        expense.setCreatedDate(Date.valueOf(cursor.getString(5)));
     
        //log 
        Log.d("GetExpense("+id+")", expense.toString());
     
        // 5. return expense
        return expense;
    }

    public List<Expense> GetAllExpenses() {
        List<Expense> expenses = new LinkedList<Expense>();
  
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_NAME;
  
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
  
        // 3. go over each row, build book and add it to list
        Expense expense = null;
        if (cursor.moveToFirst()) {
            do {
            	expense = new Expense();
                expense.setId(Integer.parseInt(cursor.getString(0)));
                expense.setUserId(cursor.getInt(1));
                expense.setCategoryId(cursor.getInt(2));
                expense.setAmount(cursor.getFloat(3));
                expense.setRemark(cursor.getString(4));
                expense.setCreatedDate(Date.valueOf(cursor.getString(5)));
  
                // Add expense to expenses
                expenses.add(expense);
            } while (cursor.moveToNext());
        }
  
        Log.d("GetAllExpenses()", expenses.toString());
  
        // return expenses
        return expenses;
    }
    
    public int UpdateExpense(Expense expense) {
    	 
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
     
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_USER_ID, expense.getUserId());
    	values.put(COLUMN_NAME_CATEGORY_ID, expense.getCategoryId());
    	values.put(COLUMN_NAME_AMOUNT, expense.getAmount());
    	values.put(COLUMN_NAME_REMARK, expense.getRemark());
    	values.put(COLUMN_NAME_CREATED_DATE, expense.getCreatedDate().toString());
    	 
        // 3. updating row
        int i = db.update(TABLE_NAME, //table
                values, // column/value
                COLUMN_NAME_ID+" = ?", // selections
                new String[] { String.valueOf(expense.getId()) }); //selection args
     
        // 4. close
        db.close();
     
        return i;
     
    }

    public void DeleteExpense(Expense expense) {
    	 
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
 
        // 2. delete
        db.delete(TABLE_NAME, //table name
                COLUMN_NAME_ID+" = ?",  // selections
                new String[] { String.valueOf(expense.getId()) }); //selections args
 
        // 3. close
        db.close();
 
        //log
        Log.d("DeleteExpense", expense.toString());
 
    }
}
