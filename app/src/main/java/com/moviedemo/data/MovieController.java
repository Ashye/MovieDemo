package com.moviedemo.data;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.moviedemo.protocol.DataController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/9/1.
 */
public class MovieController extends DataController {



    public void query(final String queryString, final OnDataFetched<SearchResultItem> resultItemOnDataFetched) {
        this.fetchQueryData(queryString, new OnRawDataFetched() {
            @Override
            public void onRawDataFetched(String jsonString) {
                Log.e("MovieController", ""+jsonString);
                List<SearchResultItem> items = null;
                if (isNotEmptyString(jsonString)) {
                    JSONObject jsonObject = JSON.parseObject(jsonString);
                    if (jsonObject.containsKey("result") && "ok".equals(jsonObject.getString("result"))) {
                        if (jsonObject.containsKey("data")) {
                            String data = jsonObject.getString("data");
                             items = JSON.parseArray(data, SearchResultItem.class);
                        }
                    }
                }

                if (resultItemOnDataFetched != null) {
                    if (items == null) {
                        items = new ArrayList<>();
                    }
                    resultItemOnDataFetched.onDataFetched(items);
                }
            }
        });
    }
}
