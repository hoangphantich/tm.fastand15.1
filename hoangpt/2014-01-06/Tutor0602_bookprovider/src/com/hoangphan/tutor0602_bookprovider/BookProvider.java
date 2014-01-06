package com.hoangphan.tutor0602_bookprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class BookProvider extends ContentProvider {

	public static final String DATABASE_NAME = "Bookdemo";
	public static final String DATABASE_TABLE = "books";
	public static final int DATABASE_VERSION = 1;

	public static final String _ID = "_id";
	public static final String ISBN = "isbn";
	public static final String TITLE = "title";
	
	private SQLiteDatabase booksDB;
	
	public static UriMatcher uriMatcher;
	public static final String PROVIDER_NAME = "com.hoangphan.provider.Books";
	public static final String UriString = "content://" + PROVIDER_NAME+ "/books";
	public static final Uri CONTENT_URI = Uri.parse(UriString);

	/**
	 * type of uri get all and get 1
	 */
	public static final int BOOKS = 999;
	public static final int BOOK_ID = 1000;

	public static final String DATABASE_CREATE = "CREATE TABLE "
			+ DATABASE_TABLE + " " + "(" + _ID
			+ " integer primary key autoincrement, " + ISBN
			+ " text not null, " + TITLE + " text not null)";

	// using sqlite
	// can use nosql ->mongo, ...
	class BookHelper extends SQLiteOpenHelper {

		public BookHelper(Context context) {
	        super(context, DATABASE_NAME, null, DATABASE_VERSION);
	      }
		public BookHelper(Context context, String name, CursorFactory factory,
				int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
			onCreate(db);
		}
	}

	


	@Override
	public boolean onCreate() {
		//connect database
	    Context context = getContext();
	    BookHelper bookHelper = new BookHelper(context);
	    booksDB = bookHelper.getWritableDatabase();
	    return (booksDB == null) ? false : true;
	}

	/** init, before constructor */
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH); //regular expression
		uriMatcher.addURI(PROVIDER_NAME, "books", BOOKS); // all book
		uriMatcher.addURI(PROVIDER_NAME, "books/#", BOOK_ID); // 1 book
	}
	
	{
		String name = "Hoang";
	}

	@Override
	public String getType(Uri uri) {
		switch (uriMatcher.match(uri)) {
		case BOOKS:// ---get all books---
			return "vnd.android.cursor.dir/" + UriString + " ";
		case BOOK_ID: //get particular book
			return "vnd.android.cursor.item/" + UriString + " ";
		default:
			throw new IllegalArgumentException("Unsupported URI: " + uri);
		}
	}

	//CRUD create Retrive/read update delete
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// get all book (SELECT * FROM books)
	    SQLiteQueryBuilder sqlBuilder = new SQLiteQueryBuilder();
	    sqlBuilder.setTables(DATABASE_TABLE);

	    if (uriMatcher.match(uri) == BOOK_ID) {
	      //regular expression to get id of book pass
	      sqlBuilder.appendWhere(_ID + " = " + uri.getPathSegments().get(1));
	    }

	    if (sortOrder == null || sortOrder == "") {
	      sortOrder = TITLE;
	    }
	      
	    Cursor c = sqlBuilder.query(booksDB, projection, selection, selectionArgs,
	        null, null, sortOrder);

	    // force to write like this
	    c.setNotificationUri(getContext().getContentResolver(), uri);
	    return c;
	}
	
	@Override
	public Uri insert(Uri uri, ContentValues values) {
	    // insert 1 book to bookdb
	    long rowID = booksDB.insert(DATABASE_TABLE, "", values);

	    // check if insert ok
	    if (rowID > 0) {
	      Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
	      getContext().getContentResolver().notifyChange(_uri, null);
	      return _uri;
	    }
	    
	    throw new SQLException("Failed to insert row into " + uri);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
	    int count = 0;
	    switch (uriMatcher.match(uri)) {
	    case BOOKS:
	      count = booksDB.delete(DATABASE_TABLE, selection, selectionArgs);
	      break;
	    case BOOK_ID:
	      String id = uri.getPathSegments().get(1);
	      count = booksDB.delete(DATABASE_TABLE,
	          _ID + " = " + id
	              + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
	      break;
	    default:
	      throw new IllegalArgumentException("Unknown URI " + uri);
	    }
	    getContext().getContentResolver().notifyChange(uri, null);
	    return count;		
		
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
	    int count = 0;
	    switch (uriMatcher.match(uri)) {
	    case BOOKS:
	      count = booksDB.update(DATABASE_TABLE, values, selection, selectionArgs);
	      break;
	    case BOOK_ID:
	      count = booksDB.update(DATABASE_TABLE, values, _ID + " = "
	          + uri.getPathSegments().get(1)
	          + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""),
	          selectionArgs);
	      break;
	    default:
	      throw new IllegalArgumentException("Unknown URI " + uri);
	    }
	    getContext().getContentResolver().notifyChange(uri, null);
	    return count;
		
		
	}
}
