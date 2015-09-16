package com.moviedemo.data;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.moviedemo.protocol.DataController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/1.
 */
public class MovieController extends DataController {



    public void query(final String queryString, final OnDataFetched<SearchResult> resultItemOnDataFetched) {
        this.fetchQueryData(queryString, new OnRawDataFetched() {
            @Override
            public void onRawDataFetched(String jsonString) {
                Log.d("MovieController", ""+jsonString);
                SearchResult searchResult = new SearchResult();
                if (isNotEmptyString(jsonString)) {
                    JSONObject jsonObject = JSON.parseObject(jsonString);
                    if (jsonObject.containsKey("result") && "ok".equals(jsonObject.getString("result"))) {
                        if (jsonObject.containsKey("data")) {
//                            String data = jsonObject.getString("data");
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

    public void detail(final Map<String, String> body, final OnDataFetched<MovieDetailItem> detailOnDataFetched) {
        this.fetchMovieDetail(body, new OnRawDataFetched() {
            @Override
            public void onRawDataFetched(String jsonString) {
                Log.d("MovieController", ""+jsonString);
                MovieDetailItem detailItem = new MovieDetailItem();
                if (isNotEmptyString(jsonString)) {
                    JSONObject jsonObject = JSON.parseObject(jsonString);
                    if (jsonObject.containsKey("result") && "ok".equals(jsonObject.getString("result"))) {
                        if (jsonObject.containsKey("data")) {
                            JSONObject jsonData = jsonObject.getJSONObject("data");
                            detailItem = new MovieDetailItem(jsonData);
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
