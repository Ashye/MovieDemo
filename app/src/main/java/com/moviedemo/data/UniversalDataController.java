package com.moviedemo.data;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.moviedemo.protocol.DataController;

import java.util.Map;

/**
 * Created by Administrator on 2015/9/18.
 */
public class UniversalDataController extends DataController {


    public void query(final String queryString, final OnDataFetched<SearchResult> resultItemOnDataFetched) {
        this.fetchQueryData(queryString, new OnRawDataFetched() {
            @Override
            public void onRawDataFetched(String jsonString) {
                Log.d("UniversalDataController", "" + jsonString);
                SearchResult searchResult = new SearchResult();
                if (isNotEmptyString(jsonString)) {
                    JSONObject jsonObject = JSON.parseObject(jsonString);
                    if (jsonObject.containsKey("result") && "ok".equals(jsonObject.getString("result"))) {
                        if (jsonObject.containsKey("data")) {
                            JSONArray resultJsonArray = jsonObject.getJSONArray("data");
                            searchResult = new SearchResult(resultJsonArray);
                        }
                    }
                }

                if (resultItemOnDataFetched != null) {
                    resultItemOnDataFetched.onDataFetched(searchResult);
                }
            }
        });
    }
}
