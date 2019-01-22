package com.system.perfect.moviecatalogs2;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.system.perfect.moviecatalogs2.adapter.NowPlayingAdapter;
import com.system.perfect.moviecatalogs2.allmovies.AllMoviesFragment;
import com.system.perfect.moviecatalogs2.model.NowPlayingMovie;
import com.system.perfect.moviecatalogs2.fragment.NowPlayingFragment;
import com.system.perfect.moviecatalogs2.upcoming.UpcomingFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private PageAdapter mPageAdapter;
    private ViewPager mViewPager;

    private RecyclerView rvNowPlaying;
    final ArrayList<NowPlayingMovie> nowPlayingMovies = new ArrayList<>();
    private NowPlayingAdapter npAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPageAdapter = new PageAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.container);
        setViewPager(mViewPager);
        TabLayout tab = findViewById(R.id.tabs);
        tab.setupWithViewPager(mViewPager);

        // Now Playing
        rvNowPlaying = findViewById(R.id.rv_now_playing);
        rvNowPlaying.setHasFixedSize(true);
        requestMovieData("nowplaying");
        showNowPlayingList();

    }

    public void showNowPlayingList(){
        rvNowPlaying.setLayoutManager(new GridLayoutManager(this,2));
        npAdapter = new NowPlayingAdapter(this);
        npAdapter.setMovieList(nowPlayingMovies);
        rvNowPlaying.setAdapter(npAdapter);
    }

    private void requestMovieData(final String option) {
        String API = BuildConfig.TMDB_API_KEY;
        String url = "";
        switch (option){
            case "nowplaying":
                url = "https://api.themoviedb.org/3/movie/now_playing?api_key=" + API ;
                break;
            case "upcoming":
                url = "https://api.themoviedb.org/3/movie/upcoming?api_key=" + API ;
                break;
            case "allmovies":
                url = "https://api.themoviedb.org/3/movie/popular?api_key=" + API ;
                break;
        }

        //RequestQueue initialized
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        StringRequest mStringRequest = new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray data = obj.getJSONArray("results");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject movie = data.getJSONObject(i);
                        switch (option){
                            case "nowplaying":
                                NowPlayingMovie item = new NowPlayingMovie(movie);
                                nowPlayingMovies.add(item);
                                break;
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "ErrorResponse :" + error.toString());
            }
        });
        mRequestQueue.add(mStringRequest);
    }

    private void setViewPager(ViewPager vPager){
        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager());
        pageAdapter.addFragment(new NowPlayingFragment(),"Now Playing");
        pageAdapter.addFragment(new UpcomingFragment(),"Upcoming");
        pageAdapter.addFragment(new AllMoviesFragment(),"All Movies");
        vPager.setAdapter(pageAdapter);
    }

}
