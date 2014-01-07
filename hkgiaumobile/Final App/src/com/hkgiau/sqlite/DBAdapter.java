package com.hkgiau.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {
	//constant
	public static final String KEY_ID = "id";
	public static final String KEY_NAME = "name";
	private static final String KEY_PASS = "pass";
	private static final String KEY_FULLNAME = "fullname";
	private static final String KEY_EMAIL = "email";
	private static final String KEY_STATUS = "status";
	private static final String KEY_PERMIT = "permit";

	public static final String DB_NAME = "user.db";
	public static final String DB_TABLE = "tb_user";
	public static final int DB_VERSION = 1;

	public static final String SQL_CREATE = "CREATE table " + DB_TABLE
			+ " (" + KEY_ID +" integer primary key autoincrement, " 
			+ KEY_NAME + " text not null, "  
			+ KEY_PASS	+ " text not null, " 
			+ KEY_FULLNAME + " text not null, "  
			+ KEY_EMAIL + " text not null, "			
			+ KEY_STATUS + " integer, "
			+ KEY_PERMIT +" integer);";	
	
	//attribute
	SQLiteDatabase db;
	DbHelper helper;
	Context app;
	
	//constructor
	public DBAdapter(Context app) {
		// TODO Auto-generated constructor stub
		super();
		this.app=app;
		open();
	}
	
	public final class DbHelper extends SQLiteOpenHelper
	{		
		public DbHelper(Context context, String name, CursorFactory factory,
				int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(SQL_CREATE);
		}
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub			
		}		
	}
	/**
	 * 1- Connect to database
	 * 2- Sql query
	 * 3- Close database
	 */
	public DBAdapter open()
	{
		helper=new DbHelper(app, DB_NAME, null, DB_VERSION);
		db=helper.getReadableDatabase();
		return this;		
	}
	public void close()
	{
		helper.close(); 
	}
	public long createUser(String name, String pass, String fullname, String email,
			int status, int permit) {
		// bundle
		ContentValues initValue = new ContentValues();
		initValue.put(KEY_NAME, name);
		initValue.put(KEY_PASS, pass);
		initValue.put(KEY_FULLNAME, fullname);
		initValue.put(KEY_EMAIL, email);
		initValue.put(KEY_STATUS, status);
		initValue.put(KEY_PERMIT, permit);
		long result = db.insert(DB_TABLE, null, initValue);
		return result;
	}

	public Cursor getAllUsers() {
		return db.query(DB_TABLE, new String[] { KEY_ID, KEY_NAME, KEY_PASS, 
				KEY_FULLNAME, KEY_EMAIL, KEY_STATUS, KEY_PERMIT }, null,
				null, null, null, null);
	}
	public int getUser(String name, String pass)
	{
		String sql="SELECT * FROM " + DB_TABLE + " WHERE "+ KEY_NAME+"='"+name
				+"' and "+ KEY_PASS + "='"+ pass +"' and " + KEY_STATUS+"=1";
		Cursor cur=db.rawQuery(sql, null);
		return cur.getCount();
		
	}
	public int getUser(String name)
	{		
		Cursor cur= db.query(DB_TABLE, new String[] {KEY_NAME}, KEY_NAME+"=?",
				new String[] {name}, null, null, null);
		return cur.getCount();
		
	}
	public int getPermit(String name, String pass)
	{
		String sql="SELECT * FROM " + DB_TABLE + " WHERE "+ KEY_NAME+"='"+name
				+"' and "+ KEY_PASS + "='"+ pass +"' and " + KEY_STATUS+"=1";
		Cursor cur=db.rawQuery(sql, null);
		cur.moveToFirst();
		return cur.getInt(6);
		
	}
	public long deleteUser(long id) {
		long result = db.delete(DB_TABLE, KEY_ID + "=" + id, null);
		return result;
	}
	
}
