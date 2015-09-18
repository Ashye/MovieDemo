package com.moviedemo.search;

import android.os.Handler;
import android.support.v7.widget.SearchView;
import android.util.Log;

import com.moviedemo.Tool.Tool;
import com.moviedemo.data.SearchResult;
import com.moviedemo.data.UniversalDataController;
import com.moviedemo.protocol.DataController;


/**
 * Created by ych on 9/13/15.
 */
public class SearchActionListener implements SearchView.OnQueryTextListener {

    private Handler handler;
    private UniversalDataController dataController;

    private OnSearchResultListener listener;


    public SearchActionListener() {
        this.dataController = new UniversalDataController();
        this.handler = new Handler();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    private String currText;
    @Override
    public boolean onQueryTextChange(final String newText) {
        this.currText = newText;
        this.queryTask(newText);
        return true;
    }

    private void queryTask(final String text) {
        this.handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Tool.notEmptyString(currText)) {
                    if (currText.equals(text)) {
                        doQuery(text);
                    }
                }
            }
        }, 1000);
    }

    private void doQuery(String key) {
        Log.e("doQuery", ""+key);
        this.dataController.query(key, new DataController.OnDataFetched<SearchResult>() {
            @Override
            public void onDataFetched(SearchResult item) {
                if (listener != null) {
                    listener.onSearchResultData(item);
                }
            }
        });
    }

    public void setListener(OnSearchResultListener listener) {
        this.listener = listener;
    }

    public interface OnSearchResultListener{
        void onSearchResultData(SearchResult items);
    }
}
