package com.moviedemo.protocol;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Administrator on 2015/9/16.
 * 服务器返回单个数据的基类封装，采用的是 Json 格式，将服务器端数据原封不动的存放在 Json 中，
 *
 * 各子类只需要增加构造方法，以及相应的属性的 Getter 方法，传入属性名，即可获得该属性的值，如果没有
 * 属性，则返回 null 值
 */
public abstract class DataBase {
    protected JSONObject jsonData;


    public DataBase() {
        this.jsonData = new JSONObject();
    }

    public DataBase(JSONObject data) {
        if (data == null) {
            this.jsonData = new JSONObject();
        }else {
            this.jsonData = data;
        }
    }


    protected String getStringValue(String key) {
        return this.jsonData.getString(key);
    }

    public JSONObject getOriginalData() {
        return this.jsonData;
    }

    public boolean isMovie() {
        return "movie".equals(this.jsonData.getString("type"));
    }

    public boolean isTV() {
        return "tv".equals(this.jsonData.getString("type"));
    }
}
