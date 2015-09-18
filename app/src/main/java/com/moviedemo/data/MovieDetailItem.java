package com.moviedemo.data;

import com.alibaba.fastjson.JSONObject;
import com.moviedemo.protocol.DataBase;
import com.moviedemo.protocol.MediaData;

/**
 * Created by Administrator on 2015/9/16.
 */
public class MovieDetailItem extends MediaData {


    public MovieDetailItem() {
        super();
    }

    public MovieDetailItem(JSONObject data) {
        super(data);
    }


}
