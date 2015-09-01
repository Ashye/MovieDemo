package com.moviedemo.protocol;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2015/9/1.
 */
public abstract class DataController {

    private AsyncHttpClient mAsyncHttpClient;




    public interface OnDataFetched {
        void onFetchDataFinished(String jsonString);
    }

    {
        this.mAsyncHttpClient = new AsyncHttpClient();
    }


    protected void fetchHotMovie() {};

    protected void fetchComingMovie() {};

    protected void fetchQueryData(final String queryString, final OnDataFetched listener) {
        this.fetchData(Urls.query.concat(queryString), listener);
    };

    private void fetchData(final String url, final OnDataFetched listener) {
        this.mAsyncHttpClient.get(url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.e("DataController", "statusCode:"+statusCode);
                Log.e("DataController", ""+responseString);
                if (listener != null) {
                    listener.onFetchDataFinished(responseString);
                }
            }
        });
    }

    private interface Urls {
        String query = "http://www.verycd.com/search/entries/";
    }
}
