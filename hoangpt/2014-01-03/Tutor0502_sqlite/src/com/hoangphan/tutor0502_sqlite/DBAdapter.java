package com.hoangphan.tutor0502_sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

@SuppressLint("NewApi")
public class DBAdapter {
	/**
	 * constant 2 column _id: autoincrement, name: string
	 */
	public static final String KEY_ID = "_id";
	public static final String KEY_NAME = "name";

	public static final String DB_NAME = "demodb";
	public static final String DB_TABLE = "user";
	public static final int DB_VERSION = 5;

	public static final String DB_CREATE = "create table " + DB_TABLE
			+ " (_id integer primary key autoincrement, "
			+ "name text not null);";

	/**
	 * properties
	 * 
	 * @author hoangpt
	 */
	SQLiteDatabase db;
	DbHelper helper;
	Context app;

	public DBAdapter(Context app) {
		super();
		this.app = app;
		open(); //connect to database
	}

	public final class DbHelper extends SQLiteOpenHelper {

		public DbHelper(Context context, String name, CursorFactory factory,
				int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// CREATE DATABASE
			db.execSQL(DB_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// uninstall, install, upgrade app

		}
	}

	/**
	 * 1- connect to database 2- exec query 3- close
	 * 
	 * @return
	 */
	public DBAdapter open() {
		// init helper
		helper = new DbHelper(app, DB_NAME, null, DB_VERSION);
		db = helper.getWritableDatabase();
		return this;
	}

	public void close() {
		helper.close();
	}

	public long createUser(String name) {
		// bundle
		ContentValues initValue = new ContentValues();
		initValue.put(KEY_NAME, name);
		long result = db.insert(DB_TABLE, null, initValue);
		return result;
	}

	public Cursor getAllUsers() {
		return db.query(DB_TABLE, new String[] { KEY_ID, KEY_NAME }, null,
				null, null, null, null);
	}

	public long deleteUser(long id) {
		long result = db.delete(DB_TABLE, KEY_ID + "=" + id, null);
		return result;
	}

}
