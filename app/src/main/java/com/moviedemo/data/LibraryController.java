package com.moviedemo.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.moviedemo.protocol.SQLiteHelper;

import java.util.List;

/**
 * Created by Administrator on 2015/9/30.
 */
public class LibraryController {
    private static LibraryController ourInstance ;

    public static LibraryController getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new LibraryController(context);
        }
        return ourInstance;
    }


    private SQLiteHelper sqLiteHelper;


    private LibraryController(Context context) {
        this.sqLiteHelper = new SQLiteHelper(context);
    }

    public long saveItem(LibraryItem item) {
        SQLiteDatabase db = this.sqLiteHelper.getWritableDatabase();
        long ret = db.insert(sqLiteHelper.DB_NAME, null, item.contentValues());
        db.close();
        return ret;
    }

    public List<LibraryItem> loadItems() {
        SQLiteDatabase db = this.sqLiteHelper.getReadableDatabase();
        Cursor cursor = db.query(sqLiteHelper.DB_NAME, null, null);
    }

    public long deleteItem(LibraryItem item) {
        SQLiteDatabase db = this.sqLiteHelper.getWritableDatabase();
        long ret = db.delete(sqLiteHelper.DB_NAME, "homeUrl=?", new String[]{item.getHomeUrl()});
        db.close();
        return ret;
    }
}
