package com.mecodroid.notestrore.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NoteHelperDb extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "notelitedatabase";
    public static final String TABLE_NOTES = "notelitetable";
    public static final String KEY_ID = "id_note";
    public static final String KEY_TITLE = "title_note";
    public static final String KEY_SUBJECT = "subject_note";
    public static final String KEY_DEFDATE = "date_note";
    public static final String KEY_ENDATE = "dateenglish_note";
    public static final String KEY_ARDATE = "datearabic_note";
    public static final String KEY_COLOR = "color_note";


    public NoteHelperDb(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NOTES + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TITLE + " TEXT,"
                + KEY_SUBJECT + " TEXT, " + KEY_DEFDATE + " TEXT," + KEY_ENDATE + " TEXT,"
                + KEY_ARDATE + " TEXT," + KEY_COLOR + " INTEGER  )";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        // Create tables again
        onCreate(db);
    }

}