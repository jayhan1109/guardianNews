package com.example.android.guardiannews;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.android.guardiannews.Adapter.NewsAdapter;
import com.example.android.guardiannews.Loader.NewsLoader;
import com.example.android.guardiannews.NewsClass.News;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>>{

    private static final String API_URL="http://content.guardianapis.com/search?q=debates&api-key=test";
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.list);
        newsAdapter = new NewsAdapter(this, new ArrayList<News>());
        listView.setAdapter(newsAdapter);



        ConnectivityManager cm =(ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if(isConnected) {
            LoaderManager loaderManager=getLoaderManager();
            loaderManager.initLoader(0,null,this);
        }

    }

    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        return new NewsLoader(this,API_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> news) {
        newsAdapter.addAll(news);
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        newsAdapter.clear();
    }

}
