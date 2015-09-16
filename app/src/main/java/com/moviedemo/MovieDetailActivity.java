package com.moviedemo;


import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.moviedemo.data.MovieController;
import com.moviedemo.data.MovieDetailItem;
import com.moviedemo.protocol.DataController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieDetailActivity extends AppCompatActivity {

    private String MovieName;
    private String MovieHomeUrl;

    private MovieController movieController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        this.getIntentData();
        this.initActionBar();

        this.movieController = new MovieController();

        this.fetchMovieDetail();
    }

    private void getIntentData() {
        Bundle bundle = this.getIntent().getExtras();
        if (bundle == null) {
            this.finish();
        }else {
            this.MovieName = bundle.getString("name");
            this.MovieHomeUrl = bundle.getString("url");
        }
    }

    private void initActionBar() {
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(this.MovieName +" "+ this.getResources().getString(R.string.detail));
    }

    private void fetchMovieDetail() {
        Map<String, String> body = new HashMap<String, String>();
        body.put("url", this.MovieHomeUrl);
        this.movieController.detail(body, new DataController.OnDataFetched<MovieDetailItem>() {
            @Override
            public void onDataFetched(List<MovieDetailItem> items) {
                for (MovieDetailItem item:items) {
                    Log.e("fetchMovieDetail", ""+item.getName());
                    Log.e("fetchMovieDetail", ""+item.getFirstShow());
                    Log.e("fetchMovieDetail", ""+item.getEpisode());
                    Log.e("fetchMovieDetail", ""+item.getUpdated());

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie_detail, menu);
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
        }else if (id == android.R.id.home) {
            this.finish();
        }



        return true;
    }
}
