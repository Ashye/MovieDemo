package com.moviedemo.protocol;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


/**
 * Created by Administrator on 2015/9/16.
 * 服务器返回多个数据的基类封装，采用的是 JsonArray 格式，将服务器端数据原封不动的存放在 JsonArray 中，
 *
 * 各子类需要增加构造方法，并自行实现获取单个元素的抽象方法
 */
public abstract class DataArrayBase<T extends DataBase> {

    private JSONArray jsonArrayData;

    abstract public T get(int id);


    public DataArrayBase() {
        this.jsonArrayData = new JSONArray();
    }

    public DataArrayBase(JSONArray resultArrayData) {
        this.jsonArrayData = resultArrayData;
    }

    protected JSONObject getJsonItem(int id) {
        return this.jsonArrayData.getJSONObject(id);
    }

    public int size() {
        return this.jsonArrayData.size();
    }

    public void replaceAll(DataArrayBase result) {
        this.jsonArrayData.clear();
        this.jsonArrayData.addAll(result.jsonArrayData);
    }
}
