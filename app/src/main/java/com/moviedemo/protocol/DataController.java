package com.moviedemo.protocol;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.moviedemo.Tool.Tool;

import org.apache.http.Header;

import java.util.List;
import java.util.Map;

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
        this.mAsyncHttpClient.addHeader("Content-Type", "application/x-www-form-urlencoded");
        this.mAsyncHttpClient.setConnectTimeout(60000);
        this.mAsyncHttpClient.setTimeout(90000);
        this.mAsyncHttpClient.setResponseTimeout(30000);
        this.mAsyncHttpClient.setURLEncodingEnabled(false);
        this.mAsyncHttpClient.setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.0");
    }


    protected void fetchHotMovie() {};

    protected void fetchComingMovie() {};

    protected void fetchQueryData(final String queryString, final OnRawDataFetched listener) {
        this.fetchDataByGet(Urls.getQuery().concat(queryString), listener);
    };

    protected void fetchMovieDetail(final Map<String, String> postData, final OnRawDataFetched listener) {
        this.fetchDataByPost(Urls.getDetail(), postData, listener);
    }

    private void fetchDataByPost(final String url, final Map<String, String> postData, final OnRawDataFetched listener) {

        this.mAsyncHttpClient.post(url, this.setRequestParams(postData), new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("DataController", "fetchDataByPost statusCode:" + statusCode);
                if (listener != null) {
                    listener.onRawDataFetched(responseString);
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d("DataController", "fetchDataByPost statusCode:" + statusCode);
                if (listener != null) {
                    listener.onRawDataFetched(responseString);
                }
            }
        });
    }

    private RequestParams setRequestParams(final Map<String, String> postData) {
        if (postData != null && !postData.isEmpty()) {
            RequestParams requestParams = new RequestParams(postData);
            return requestParams;
        }else {
            return null;
        }
    }

    private void fetchDataByGet(final String url, final OnRawDataFetched listener) {
        this.mAsyncHttpClient.get(url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("DataController", "fetchDataByGet statusCode:" + statusCode);

                if (listener != null) {
                    listener.onRawDataFetched(responseString);
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d("DataController", "fetchDataByGet statusCode:" + statusCode);
                if (listener != null) {
                    listener.onRawDataFetched(responseString);
                }
            }
        });
    }

    protected boolean isNotEmptyString(String string) {
        return Tool.notEmptyString(string);
    }

    static abstract class Urls {
        public static String host = "http://192.168.1.119:8080";

//        public static String host = "http://www.playaround.tk";
        public static String query = "/movies/search?query=";
        public static String detail = "/movies/detail";

//        {
//            host = testHost;
//        }

        public static String getQuery() {
            return host + query;
        }

        public static String getDetail() {
            return host + detail;
        }
    }
}

