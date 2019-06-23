package com.example.sqlitepractice;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ContactDBHelper extends SQLiteOpenHelper {

    public static final String DBTABASE_NAME = "contact_db";
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_TABLE_QUERY = "create table " + ContactContract.ContactEntry.TABLE_NAME +
            "(" + ContactContract.ContactEntry.CONTACT_ID + " number, " + ContactContract.ContactEntry.NAME + " text, "
            + ContactContract.ContactEntry.EMAIL + " text);";
    public static final String DROP_TABLE_QUERY = "drop table if exists " + ContactContract.ContactEntry.TABLE_NAME;

    public ContactDBHelper(Context context) {
        super(context, DBTABASE_NAME, null, DATABASE_VERSION);
        Log.d("Database operation", "Database created..");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_TABLE_QUERY);
        Log.d("Database Operation", "Table created...");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE_QUERY);
        onCreate(sqLiteDatabase);

    }

    /**
     * add contact in table
     *
     * @param id
     * @param name
     * @param email
     * @param sqLiteDatabase
     */
    public void addContact(int id, String name, String email, SQLiteDatabase sqLiteDatabase) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactContract.ContactEntry.CONTACT_ID, id);
        contentValues.put(ContactContract.ContactEntry.NAME, name);
        contentValues.put(ContactContract.ContactEntry.EMAIL, email);
        sqLiteDatabase.insert(ContactContract.ContactEntry.TABLE_NAME, null, contentValues);
        Log.d("Database Operation", "One raw inserted..");

    }
}
