package com.moviedemo.protocol;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2015/9/30.
 */
public class SQLiteHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "library.db";
    private static final int version = 1;


    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, version);
    }


//    private String homeUrl;
//    private String cover;
//    private String name;
//    private String type;
//    private String typeName;
//    private String updateTimestamp;
//    private boolean favorMark;


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql_create = "create table library(homeUrl varchar(100) not null , cover varchar(200), name varchar(50)," +
                " type varchar(50), typeName varchar(50), timestamp varchar(50), favorMark int);";

        sqLiteDatabase.execSQL(sql_create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
