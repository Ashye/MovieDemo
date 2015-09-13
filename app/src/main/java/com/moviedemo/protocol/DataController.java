package com.moviedemo.protocol;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import com.moviedemo.Tool.Tool;

import org.apache.http.Header;

import java.util.List;

/**
 * Created by Administrator on 2015/9/1.
 */
public abstract class DataController {

    private AsyncHttpClient mAsyncHttpClient;




    public interface OnRawDataFetched {
        void onRawDataFetched(String jsonString);
    }

    public interface OnDataFetched<T> {
        void onDataFetched (List<T> items);
    }

    {
        this.mAsyncHttpClient = new AsyncHttpClient();
    }


    protected void fetchHotMovie() {};

    protected void fetchComingMovie() {};

    protected void fetchQueryData(final String queryString, final OnRawDataFetched listener) {
        this.fetchData(Urls.query.concat(queryString), listener);
    };

    private void fetchData(final String url, final OnRawDataFetched listener) {
        this.mAsyncHttpClient.get(url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e("DataController", "statusCode:" + statusCode);
//                Log.e("DataController", "responseString:" + responseString);

                if (listener != null) {
                    listener.onRawDataFetched(responseString);
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.e("DataController", "statusCode:" + statusCode);
//                Log.e("DataController", ""+responseString);
                if (listener != null) {
                    listener.onRawDataFetched(responseString);
                }
            }
        });
    }

    protected boolean isNotEmptyString(String string) {
        return Tool.notEmptyString(string);
    }

    private interface Urls {
        String query = "http://www.playaround.tk/search?query=";
    }
}
