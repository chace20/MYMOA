package com.uestc.mymoa.ui.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HeGang on 2015/7/28.
 */
public class PostDBHelper extends SQLiteOpenHelper {
    private static final String DB_CREATE = "create table " + PostStaticContact.DB_TABLE + " ( "
            + PostStaticContact.KEY_ID + " integer primary key autoincrement, "
            + PostStaticContact.KEY_TITLE + " text, "
            + PostStaticContact.KEY_ARTICLE + " text , )";

    public PostDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PostStaticContact.DB_TABLE);
        onCreate(db);
    }

}
