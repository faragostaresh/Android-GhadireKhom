package com.ghadirekhom.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "ghadir";

	// Contacts table name
	private static final String TABLE_STORY = "story";

	// Contacts Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_CID = "cid";
	private static final String KEY_TITLE = "title";
	private static final String KEY_BODY = "body";
	private static final String KEY_IMAGE = "image";
	private static final String KEY_DATE = "date";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_STORY + "(" + KEY_ID
				+ " INTEGER PRIMARY KEY," + KEY_CID + " INTEGER," + KEY_TITLE
				+ " TEXT," + KEY_BODY + " TEXT, " + KEY_IMAGE + " TEXT, "
				+ KEY_DATE + " TEXT" + ")";
		db.execSQL(CREATE_ITEM_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_STORY);

		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new item
	public void addItem(Story item) {
		SQLiteDatabase db = this.getWritableDatabase();
		// Check this id exit or not
		Cursor cursor = db.query(TABLE_STORY, new String[] { KEY_ID, KEY_CID,
				KEY_TITLE, KEY_BODY, KEY_IMAGE, KEY_DATE }, KEY_ID + "=?",
				new String[] { String.valueOf(item.getId()) }, null, null,
				null, null);
		
		ContentValues values = new ContentValues();
		// Set Items
		values.put(KEY_ID, item.getId());
		values.put(KEY_CID, item.getCid());
		values.put(KEY_TITLE, item.getTitle());
		values.put(KEY_BODY, item.getBody());
		values.put(KEY_IMAGE, item.getImage());
		values.put(KEY_DATE, item.getDate());
		
		if (cursor != null) {
			// Inserting Row
			db.insert(TABLE_STORY, null, values);
		} else {
			db.update(TABLE_STORY, values, KEY_ID + " = ?", new String[] { String.valueOf(item.getId()) });
		}
		// Closing database connection
		db.close();
	}
	

	// Updating single contact
	public int updateItem(Story item) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		// Set Items
		values.put(KEY_ID, item.getId());
		values.put(KEY_CID, item.getCid());
		values.put(KEY_TITLE, item.getTitle());
		values.put(KEY_BODY, item.getBody());
		values.put(KEY_IMAGE, item.getImage());
		values.put(KEY_DATE, item.getDate());
		// updating row
		return db.update(TABLE_STORY, values, KEY_ID + " = ?",
				new String[] { String.valueOf(item.getId()) });
	}

	// Getting single item
	public Story getItem(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_STORY, new String[] { KEY_ID, KEY_CID,
				KEY_TITLE, KEY_BODY, KEY_IMAGE, KEY_DATE }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Story story = new Story(Integer.parseInt(cursor.getString(0)),
				Integer.parseInt(cursor.getString(1)), cursor.getString(2),
				cursor.getString(3), cursor.getString(4), cursor.getString(5));
		// return contact
		return story;
	}

	// Getting All Contacts
	public List<Story> getAllItemCid(int cid) {
		List<Story> itemList = new ArrayList<Story>();
		// Select All Query
		if (cid > 0) {
			String selectQuery = "SELECT * FROM " + TABLE_STORY + " WHERE cid = "
					+ cid + " ORDER BY " + KEY_ID + " DESC";

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					Story story = new Story();
					story.setId(Integer.parseInt(cursor.getString(0)));
					story.setCid(Integer.parseInt(cursor.getString(1)));
					story.setTitle(cursor.getString(2));
					story.setBody(cursor.getString(3));
					story.setImage(cursor.getString(4));
					story.setDate(cursor.getString(5));
					// Adding contact to list
					itemList.add(story);
				} while (cursor.moveToNext());
			}
			db.close();
			// return contact list
			return itemList;
			
			
		} else {
			String selectQuery = "SELECT * FROM " + TABLE_STORY + " ORDER BY " + KEY_ID + " DESC";

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					Story story = new Story();
					story.setId(Integer.parseInt(cursor.getString(0)));
					story.setCid(Integer.parseInt(cursor.getString(1)));
					story.setTitle(cursor.getString(2));
					story.setBody(cursor.getString(3));
					story.setImage(cursor.getString(4));
					story.setDate(cursor.getString(5));
					// Adding contact to list
					itemList.add(story);
				} while (cursor.moveToNext());
			}
			db.close();
			// return contact list
			return itemList;
			
			
		}
		

	}

	public int getStoryCount(int cid) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor dataCount = db.rawQuery("select count(*) from " + TABLE_STORY + " WHERE cid = " + cid,
				null);
		dataCount.moveToFirst();
		int jcount = dataCount.getInt(0);
		dataCount.close();
		return jcount;
	}

	public int getStoryLastId(int cid) {

		String selectQuery = "SELECT " + KEY_ID + " FROM " + TABLE_STORY
				+ " WHERE cid = " + cid + " ORDER BY " + KEY_ID + " DESC";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor lastId = db.rawQuery(selectQuery, null);

		if (lastId.moveToFirst()) {
			return Integer.parseInt(lastId.getString(0));
		} else {
			return 1;
		}
	}
}
