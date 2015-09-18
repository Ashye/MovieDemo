package com.moviedemo.data;

import com.alibaba.fastjson.JSONObject;
import com.moviedemo.protocol.MediaData;

/**
 * Created by Administrator on 2015/9/18.
 */
public class TVDetailItem extends MediaData {


    public TVDetailItem() {
        super();
    }

    public TVDetailItem(JSONObject data) {
        super(data);
    }

    public String getEpisodes() {
        return this.getStringValue("episodes");
    }

    public String getTVStation() {
        return this.getStringValue("TVStation");
    }

    public String getPlayPlatform() {
        return this.getStringValue("platform");
    }
}
