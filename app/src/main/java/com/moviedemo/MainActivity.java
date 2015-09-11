package com.moviedemo;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
//import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.moviedemo.fragment.FavorsFragment;
import com.moviedemo.fragment.LibraryFragment;

public class MainActivity extends AppCompatActivity implements FavorsFragment.OnFragmentInteractionListener,LibraryFragment.OnFragmentInteractionListener,View.OnClickListener {

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
        return true;
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

        return super.onOptionsItemSelected(item);
    }
}
