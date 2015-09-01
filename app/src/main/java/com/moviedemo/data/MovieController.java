package com.moviedemo.data;

import android.util.Log;

import com.moviedemo.protocol.DataController;

import java.util.List;

/**
 * Created by Administrator on 2015/9/1.
 */
public class MovieController extends DataController {




    public void query(final String queryString) {
        this.fetchQueryData(queryString, new OnDataFetched() {
            @Override
            public void onFetchDataFinished(String jsonString) {
                Log.e("MovieController", "onFetchDataFinished:"+jsonString);
            }
        });
    }

}
