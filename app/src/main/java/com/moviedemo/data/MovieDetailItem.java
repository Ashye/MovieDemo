package com.moviedemo.data;

import com.alibaba.fastjson.JSONObject;
import com.moviedemo.protocol.DataBase;

/**
 * Created by Administrator on 2015/9/16.
 */
public class MovieDetailItem extends DataBase {


    public MovieDetailItem() {
        super();
    }

    public MovieDetailItem(JSONObject data) {
        super(data);
    }


    public String getName() {
        return this.getStringValue("title");
    }

    public String getType() {
        return this.getStringValue("类型");
    }

    public String getActors() {
        return this.getStringValue("演员");
    }

    public String getFirstShow() {
        return this.getStringValue("首播时间");
    }

    public String getEpisode() {
        return this.getStringValue("集数");
    }

    public String getUpdated() {
        return this.getStringValue("updated");
    }

}
