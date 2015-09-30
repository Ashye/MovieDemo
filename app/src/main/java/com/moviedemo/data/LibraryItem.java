package com.moviedemo.data;

import android.content.ContentValues;

/**
 * Created by Administrator on 2015/9/30.
 */
public class LibraryItem {
    private String homeUrl;
    private String cover;
    private String name;
    private String type;
    private String typeName;
    private String updateTimestamp;
    private boolean favorMark;


    public LibraryItem(String homeUrl, String cover, String name, String type, String typeName) {
        this.homeUrl = homeUrl;
        this.cover = cover;
        this.name = name;
        this.type = type;
        this.typeName = typeName;
    }


    public String getHomeUrl() {
        return this.homeUrl;
    }

    public String getCover() {
        return cover;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getUpdateTimestamp() {
        return updateTimestamp;
    }

    public boolean isFavorMark() {
        return favorMark;
    }


    public ContentValues contentValues() {
        ContentValues values = new ContentValues();
        // "create table library(
// homeUrl varchar(100) not null ,
// cover varchar(200),
// name varchar(50)," +
// type varchar(50),
// typeName varchar(50),
// timestamp varchar(50),
// favorMark int);";

        values.put("homeUrl", this.homeUrl);
        values.put("cover", this.cover);
        values.put("name", this.name);
        values.put("type", this.type);
        values.put("typeName", this.typeName);
        values.put("timestamp", this.updateTimestamp);
        values.put("favorMark", this.favorMark);
        return values;
    }
}
