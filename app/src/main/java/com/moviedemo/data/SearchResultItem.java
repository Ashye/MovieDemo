package com.moviedemo.data;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.moviedemo.protocol.DataBase;

/**
 * Created by Administrator on 2015/9/16.
 */
public class SearchResultItem extends DataBase{


    public SearchResultItem() {
        super();
    }

    public SearchResultItem(JSONObject data) {
        super(data);
    }


    public String getName() {
        return this.getStringValue("name");
    }

    public String getType() {
        return this.getStringValue("type");
    }

    public String getActors() {
        return this.getStringValue("actor");
    }

    public String getHomeUrl() {
        return this.getStringValue("homeUrl");
    }
}
