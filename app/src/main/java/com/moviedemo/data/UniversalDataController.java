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


    protected interface UniversalDataDetailListener<T> {
        void OnUniversalDataDetailFetched(T item, T extra);
    }

    protected void universalDetailData(final Map<String, String> body, final  UniversalDataDetailListener<JSONObject> detailListener) {
        this.fetchItemDetailData(body, new OnRawDataFetched() {
            @Override
            public void onRawDataFetched(String jsonString) {
                Log.d("universalDetailData", "" + jsonString);
                JSONObject detailItem = null;
                JSONObject extraItem = null;
                if (isNotEmptyString(jsonString)) {
                    JSONObject jsonObject = JSON.parseObject(jsonString);
                    if (jsonObject.containsKey("result") && "ok".equals(jsonObject.getString("result"))) {
                        if (jsonObject.containsKey("data")) {
                            detailItem = jsonObject.getJSONObject("data");
                        }
                    }

                    if (jsonObject.containsKey("extra")) {
                        extraItem = jsonObject.getJSONObject("extra");
                    }
                }

                if (detailListener != null) {
                    detailListener.OnUniversalDataDetailFetched(detailItem, extraItem);
                }
            }
        });
    }



//    private void detail(final Map<String, String> body, final OnDataFetched<MovieDetailItem> detailOnDataFetched) {
//        this.fetchItemDetailData(body, new OnRawDataFetched() {
//            @Override
//            public void onRawDataFetched(String jsonString) {
//                Log.d("MovieController", "" + jsonString);
//                MovieDetailItem detailItem = null;
//                if (isNotEmptyString(jsonString)) {
//                    JSONObject jsonObject = JSON.parseObject(jsonString);
//                    if (jsonObject.containsKey("result") && "ok".equals(jsonObject.getString("result"))) {
//                        if (jsonObject.containsKey("data")) {
//                            JSONObject jsonData = jsonObject.getJSONObject("data");
//                            detailItem = new MovieDetailItem(jsonData);
//                        }
//                    }
//                }
//
//                if (detailOnDataFetched != null) {
//                    detailOnDataFetched.onDataFetched(detailItem);
//                }
//            }
//        });
//    }
}
