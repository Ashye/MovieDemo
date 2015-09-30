package com.moviedemo;


import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.moviedemo.Tool.Tool;
import com.moviedemo.data.MovieController;
import com.moviedemo.data.MovieDetailItem;
import com.moviedemo.data.SearchResultItem;
import com.moviedemo.data.TVController;
import com.moviedemo.data.TVDetailItem;
import com.moviedemo.data.UniversalDataController;
import com.moviedemo.protocol.DataController;

import java.util.HashMap;
import java.util.Map;

public class MediaDetailActivity extends AppCompatActivity {

    private SearchResultItem currItem;
    private MovieController movieController;
    private TVController    tvController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_detail);

        this.getIntentData();
        this.initActionBar();
        this.initViewWidget();

        this.fetchItemDetailData();
    }

    private void getIntentData() {
        Bundle bundle = this.getIntent().getExtras();
        if (bundle == null) {
            this.finish();
        }else {
            String data = bundle.getString("data");
            this.currItem = new SearchResultItem(JSON.parseObject(data));
//            Log.d("curritem", "type:"+this.currItem.getType());
        }
    }

    private void initActionBar() {
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(this.currItem.getName() +" "+ this.getResources().getString(R.string.detail));
    }

    private ImageView cover;
    private TextView name;
    private TextView category;
    private TextView area;
    private TextView director;
    private TextView episodes;
    private TextView tvStation;
    private TextView actor;
    private TextView releaseDate;
    private TextView summary;
    private TextView watchTimestamp;
    private TextView favorMark;
    private void initViewWidget() {
        this.cover = (ImageView) findViewById(R.id.detail_cover);
        this.name = (TextView) findViewById(R.id.detail_name);
        this.category = (TextView) findViewById(R.id.detail_category);
        this.area = (TextView) findViewById(R.id.detail_area);
        this.director = (TextView) findViewById(R.id.detail_director);
        this.actor = (TextView) findViewById(R.id.detail_actor);
        this.releaseDate = (TextView) findViewById(R.id.detail_releasedDate);

        if (this.currItem.isTV()) {
            this.episodes = (TextView) findViewById(R.id.detail_episodes);
            this.episodes.setVisibility(View.VISIBLE);
            this.tvStation = (TextView) findViewById(R.id.detail_TVStation);
            this.tvStation.setVisibility(View.VISIBLE);
        }

        this.summary = (TextView) findViewById(R.id.detail_summary_content);
        this.watchTimestamp = (TextView) findViewById(R.id.detail_last_watch_timestamp);
        this.favorMark = (TextView) findViewById(R.id.detail_favor_mark);
    }

    private void fetchItemDetailData() {
        if (this.currItem.isTV()) {
            this.tvController = new TVController();
            this.fetchTVDetail();
        }else if (this.currItem.isMovie()) {
            this.movieController = new MovieController();
            this.fetchMovieDetail();
        }
    }

    private void fetchTVDetail() {
        Map<String, String> body = new HashMap<String, String>();
        body.put("url", this.currItem.getHomeUrl());
        body.put("type", this.currItem.getType());
        this.tvController.detail(body, new DataController.OnDataFetched<TVDetailItem>() {
            @Override
            public void onDataFetched(TVDetailItem item) {
                MediaDetailActivity.this.name.setText(item.getName());
                MediaDetailActivity.this.category.setText("类型: "+item.getCategory());
                MediaDetailActivity.this.area.setText("地区: "+item.getArea());
                MediaDetailActivity.this.actor.setText("演员: "+item.getActor());
                MediaDetailActivity.this.director.setText("导演: "+item.getDirector());
                MediaDetailActivity.this.releaseDate.setText("首播日期: "+item.getReleasedDate());
                MediaDetailActivity.this.episodes.setText("集数: "+item.getEpisodes());
                String tvstation = item.getTVStation();
                if (Tool.notEmptyString(tvstation)) {
                    MediaDetailActivity.this.tvStation.setText("电视台: "+item.getTVStation());
                }else {
                    MediaDetailActivity.this.tvStation.setText("电视台: ");
                }
                MediaDetailActivity.this.summary.setText(item.getSummary());
            }
        });
    }

    private void fetchMovieDetail() {
        Map<String, String> body = new HashMap<String, String>();
        body.put("url", this.currItem.getHomeUrl());
        body.put("type", this.currItem.getType());

        this.movieController.detail(body, new DataController.OnDataFetched<MovieDetailItem>() {
            @Override
            public void onDataFetched(MovieDetailItem item) {
                MediaDetailActivity.this.name.setText(item.getName());
                MediaDetailActivity.this.category.setText("类型: "+item.getCategory());
                MediaDetailActivity.this.area.setText("地区: "+item.getArea());
                MediaDetailActivity.this.actor.setText("演员: "+item.getActor());
                MediaDetailActivity.this.director.setText("导演: "+item.getDirector());
                MediaDetailActivity.this.releaseDate.setText("上映日期: "+item.getReleasedDate());
                MediaDetailActivity.this.summary.setText(item.getSummary());
            }
        });
    }

    private void addFavorMark() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_media_detail, menu);
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
