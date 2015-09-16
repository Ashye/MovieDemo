package com.moviedemo.data;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Administrator on 2015/9/16.
 */
public class MovieDetailItem {
    private JSONObject detailData;


    public MovieDetailItem() {
        this.detailData = new JSONObject();
    }

    public MovieDetailItem(JSONObject data) {
        if (data == null) {
            this.detailData = new JSONObject();
        }else {
            this.detailData = data;
        }
    }


    public String getName() {
        return this.getValue("title");
    }

    public String getType() {
        return this.getValue("类型");
    }

    public String getActors() {
        return this.getValue("演员");
    }

    public String getFirstShow() {
        return this.getValue("首播时间");
    }

    public String getEpisode() {
        return this.getValue("集数");
    }

    public String getUpdated() {
        return this.getValue("updated");
    }

    private String getValue(String key) {
        return this.detailData.getString(key);
    }
}
