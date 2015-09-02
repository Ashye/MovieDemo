package com.moviedemo;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;

import com.moviedemo.data.MovieController;
import com.moviedemo.data.SearchResultItem;
import com.moviedemo.protocol.DataController;
import com.moviedemo.search.SearchResultAdapter;


import java.util.ArrayList;
import java.util.List;

public class MainEntryActivity extends AppCompatActivity implements DataController.OnDataFetched<SearchResultItem> {

    private MovieController movieController;

    private SearchView mSearchView;
    private ListView mSearchListView;
    private List<SearchResultItem> mSearchResult;
    private SearchResultAdapter mSearchResultAdapter;
    private SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            Log.d("SearchInputListener", "" + query);
            movieController.query(query);
            InputMethodManager inputMethodManager = (InputMethodManager) MainEntryActivity.this.getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(mSearchView.getWindowToken(), 0);
            mSearchView.clearFocus();
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            Log.d("SearchInputListener", ""+newText);

            return true;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_entry);

        this.movieController = new MovieController();
        this.movieController.setSimpleMovieItemOnDataFetched(this);


    }


    @Override
    public void onDataFetched(List<SearchResultItem> items) {
        for (SearchResultItem item: items) {
            Log.e("ondatafetched", ""+item.getName());

        }
        if (items.size() >0) {
            this.mSearchResult.clear();
            this.mSearchResult.addAll(items);
            this.mSearchResultAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_entry, menu);

        this.initSearchView(menu);

        return true;
    }

    private void initSearchView(Menu menu) {
        MenuItem searchItem = menu.findItem(R.id.home_action_search);
//        searchItem.setVisible(false);
        this.mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        this.mSearchView.setQueryHint("电影名字、电视剧名字");
        this.mSearchView.setIconifiedByDefault(true);
        this.mSearchView.setOnQueryTextListener(this.queryTextListener);

        this.mSearchListView = (ListView) findViewById(R.id.home_search_result_lv);
        this.mSearchResult = new ArrayList<>();
        this.mSearchResultAdapter = new SearchResultAdapter(this, this.mSearchResult);
        this.mSearchListView.setAdapter(this.mSearchResultAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        switch (id) {
            case R.id.action_settings:
                return true;
            case R.id.home_action_search:
                this.mSearchView.requestFocus();

                return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
