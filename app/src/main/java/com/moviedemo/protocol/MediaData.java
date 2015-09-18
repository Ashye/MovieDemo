package com.moviedemo.protocol;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Administrator on 2015/9/18.
 */
public abstract class MediaData extends DataBase {

    public MediaData(){
        super();
    }

    public MediaData(JSONObject data) {
        super(data);
    }

    public String getName() {
        return this.getStringValue("name");
    }

    public String getCover() {
        return this.getStringValue("cover");
    }

    public String getCategory() {
        return this.getStringValue("category");
    }

    public String getArea() {
        return this.getStringValue("area");
    }

    public String getDirector() {
        return this.getStringValue("director");
    }

    public String getWriter() {
        return this.getStringValue("writer");
    }

    public String getActor() {
        return this.getStringValue("actor");
    }

    public String getSummary() {
        return this.getStringValue("summary");
    }

    public String getPoster() {
        return this.getStringValue("poster");
    }

    public String getReleasedDate() {
        return this.getStringValue("releasedDate");
    }
}
