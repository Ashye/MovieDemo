package com.moviedemo.data;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


import java.util.Map;

/**
 * Created by Administrator on 2015/9/18.
 */
public class TVController extends UniversalDataController {

    public void detail(final Map<String, String> body, final OnDataFetched<TVDetailItem> detailOnDataFetched) {
        this.fetchItemDetailData(body, new OnRawDataFetched() {
            @Override
            public void onRawDataFetched(String jsonString) {
            Log.d("MovieController", "" + jsonString);
            TVDetailItem detailItem = null;
            if (isNotEmptyString(jsonString)) {
                JSONObject jsonObject = JSON.parseObject(jsonString);
                if (jsonObject.containsKey("result") && "ok".equals(jsonObject.getString("result"))) {
                    if (jsonObject.containsKey("data")) {
                        JSONObject jsonData = jsonObject.getJSONObject("data");
                        detailItem = new TVDetailItem(jsonData);
                    }
                }
            }

            if (detailOnDataFetched != null) {
                detailOnDataFetched.onDataFetched(detailItem);
            }
            }
        });
    }

}
