package com.moviedemo;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.moviedemo.data.SearchResult;
import com.moviedemo.data.SearchResultItem;
import com.moviedemo.fragment.FavorsFragment;
import com.moviedemo.fragment.LibraryFragment;
import com.moviedemo.search.SearchResultAdapter;
import com.moviedemo.search.SearchActionListener;

public class MainActivity extends AppCompatActivity
        implements FavorsFragment.OnFragmentInteractionListener
        ,LibraryFragment.OnFragmentInteractionListener
        ,View.OnClickListener
        ,SearchActionListener.OnSearchResultListener {

    private ImageView mTabFavors;
    private ImageView mTabLibrary;

    private FavorsFragment favorsFragment;
    private LibraryFragment libraryFragment;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initTabs();
    }

    private void initTabs() {
        this.mTabFavors = (ImageView) findViewById(R.id.favor_tab_icon);
        this.mTabFavors.setOnClickListener(this);
        this.mTabLibrary = (ImageView) findViewById(R.id.library_tab_icon);
        this.mTabLibrary.setOnClickListener(this);

        this.setDefaultFragment();
    }

    private void setDefaultFragment() {
        this.setFavorsFragment();
    }

    private void setFavorsFragment() {
        if (this.favorsFragment == null) {
            this.favorsFragment = new FavorsFragment();
        }
        this.setFragment(this.favorsFragment);
    }

    private void setLibraryFragment() {
        if (this.libraryFragment == null) {
            this.libraryFragment = new LibraryFragment();
        }
        this.setFragment(this.libraryFragment);
    }

    private void setFragment(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.tab_fragment_content, fragment);
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.e("onFragmentInteraction", "sssssssss:" + uri);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.favor_tab_icon:
                this.setFavorsFragment();
                break;
            case R.id.library_tab_icon:
                this.setLibraryFragment();
                break;
        }
    }

    private SearchView searchView;
    private ListView searchResultListView;
    private SearchActionListener queryTextListener;
    private SearchResultAdapter searchResultAdapter;
    private SearchResult searchResults;

    private void initSearchMenu(Menu menu) {
        MenuItem searchMenu = menu.findItem(R.id.action_search);
        this.searchView = (SearchView) MenuItemCompat.getActionView(searchMenu);
        this.searchView.setQueryHint(this.getResources().getString(R.string.query_hint));
        this.searchView.setIconifiedByDefault(true);
        this.searchView.setImeOptions(EditorInfo.IME_ACTION_SEARCH);

        this.queryTextListener = new SearchActionListener();
        this.queryTextListener.setListener(this);
        this.searchView.setOnQueryTextListener(this.queryTextListener);
        this.searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                onSearchResultData(new SearchResult());
                return false;
            }
        });

        this.searchResultListView = (ListView) findViewById(R.id.search_result_list_view);
        this.searchResults = new SearchResult();
        this.searchResultAdapter = new SearchResultAdapter(this, this.searchResults);
        this.searchResultListView.setAdapter(this.searchResultAdapter);
        this.searchResultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                SearchResultItem item1 = searchResults.get(i);
                Intent detailIntent = new Intent(view.getContext(), MediaDetailActivity.class);
                detailIntent.putExtra("data", item1.getOriginalData().toJSONString());
                MainActivity.this.startActivity(detailIntent);
            }
        });
    }

    @Override
    public void onSearchResultData(SearchResult items) {
        Log.d("onSearchResultData", "result size:"+items.size());
        if (items != null) {
            this.searchResults.replaceAll(items);
            this.searchResultAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        this.initSearchMenu(menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_search:
                return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
