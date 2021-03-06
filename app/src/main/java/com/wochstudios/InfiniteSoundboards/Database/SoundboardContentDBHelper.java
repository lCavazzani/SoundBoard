package com.wochstudios.InfiniteSoundboards.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dave on 8/6/2015.
 */
public class SoundboardContentDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Soundboard.db";

    public SoundboardContentDBHelper(Context context ){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SoundboardContract.SoundboardsTable.SQL_CREATE_TABLE_SOUNDBOARDS);
        db.execSQL(SoundboardContract.SoundsTable.SQL_CREATE_TABLE_SOUNDS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SoundboardContract.SoundsTable.SQL_DELETE_TABLE_SOUNDS);
        db.execSQL(SoundboardContract.SoundboardsTable.SQL_DELETE_TABLE_SOUNDBOARDS);
        onCreate(db);
    }
}
