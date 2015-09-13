package com.moviedemo;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.moviedemo.fragment.FavorsFragment;
import com.moviedemo.fragment.LibraryFragment;
import com.moviedemo.search.SearchActionListener;

public class MainActivity extends AppCompatActivity
        implements FavorsFragment.OnFragmentInteractionListener
        ,LibraryFragment.OnFragmentInteractionListener
        ,View.OnClickListener {

    private ImageView mTabFavors;
    private ImageView mTabLibrary;

    private FavorsFragment favorsFragment;
    private LibraryFragment libraryFragment;


    private SearchView searchView;


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        this.initSearchMenu(menu);

        return true;
    }

    private SearchActionListener queryTextListener;
    private void initSearchMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_search);
        this.searchView = (SearchView) MenuItemCompat.getActionView(item);
        this.searchView.setQueryHint(this.getResources().getString(R.string.query_hint));
        this.searchView.setIconifiedByDefault(true);

        this.queryTextListener = new SearchActionListener();
        this.searchView.setOnQueryTextListener(this.queryTextListener);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_search:
                this.searchView.setIconified(false);
                break;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
