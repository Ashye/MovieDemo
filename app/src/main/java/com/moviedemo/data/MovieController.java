package com.moviedemo.data;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * Created by Administrator on 2015/9/1.
 */
public class MovieController extends UniversalDataController {


    public void detail(final Map<String, String> body, final OnDataFetched<MovieDetailItem> movieDetailItemListener) {
        this.universalDetailData(body, new UniversalDataDetailListener<JSONObject>() {
            @Override
            public void OnUniversalDataDetailFetched(JSONObject item, JSONObject extra) {
                if (movieDetailItemListener != null) {
                    movieDetailItemListener.onDataFetched(new MovieDetailItem(item));
                }
            }
        });
    }

//    public void detail(final Map<String, String> body, final OnDataFetched<MovieDetailItem> detailOnDataFetched) {
//        this.fetchItemDetailData(body, new OnRawDataFetched() {
//            @Override
//            public void onRawDataFetched(String jsonString) {
//            Log.d("MovieController", "" + jsonString);
//            MovieDetailItem detailItem = null;
//            if (isNotEmptyString(jsonString)) {
//                JSONObject jsonObject = JSON.parseObject(jsonString);
//                if (jsonObject.containsKey("result") && "ok".equals(jsonObject.getString("result"))) {
//                    if (jsonObject.containsKey("data")) {
//                        JSONObject jsonData = jsonObject.getJSONObject("data");
//                        detailItem = new MovieDetailItem(jsonData);
//                    }
//                }
//            }
//
//            if (detailOnDataFetched != null) {
//                detailOnDataFetched.onDataFetched(detailItem);
//            }
//            }
//        });
//    }
}
