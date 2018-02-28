package com.example.android.guardiannews;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.guardiannews.Adapter.CategoryAdapter;
import com.example.android.guardiannews.Adapter.NewsAdapter;
import com.example.android.guardiannews.Loader.NewsLoader;
import com.example.android.guardiannews.NewsClass.News;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    private static final String LOG_TAG = MainActivity.class.getName();
    private static final String API_FIRST = "http://content.guardianapis.com/search?q=international&api-key=test";
    private static final String API_URL = "http://content.guardianapis.com/search";
    private NewsAdapter newsAdapter;
    private CategoryAdapter categoryAdapter;
    private ListView drawerList;
    private StringBuilder category = new StringBuilder();
    private static int count = 0;
    private TextView mEmptyStateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView = (ListView) findViewById(R.id.list);
        listView.setEmptyView(mEmptyStateView);
        newsAdapter = new NewsAdapter(this, new ArrayList<News>());
        listView.setAdapter(newsAdapter);

        // add Navigation Bar
        final String[] items = getResources().getStringArray(R.array.news_category);
        categoryAdapter = new CategoryAdapter(this, items);
        drawerList = (ListView) findViewById(R.id.drawer_menulist);
        drawerList.setEmptyView(mEmptyStateView);
        drawerList.setAdapter(categoryAdapter);

        drawerList.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                switch (position) {
                    case 0:
                        category.replace(0, category.length(), "World");
                        break;
                    case 1:
                        category.replace(0, category.length(), "politics");
                        break;
                    case 2:
                        category.replace(0, category.length(), "cities");
                        break;
                    case 3:
                        category.replace(0, category.length(), "football");
                        break;
                    case 4:
                        category.replace(0, category.length(), "music");
                        break;
                    case 5:
                        category.replace(0, category.length(), "culture");
                        break;
                    case 6:
                        category.replace(0, category.length(), "fashion");
                        break;
                    case 7:
                        category.replace(0, category.length(), "games");
                        break;
                }

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
                newsAdapter.clear();
                newsAdapter.addAll();
                NewsAdapter.count = 1;
                getLoaderManager().restartLoader(0, null, MainActivity.this);
                drawer.closeDrawer(Gravity.LEFT);
            }
        });

        // Check if Internet is connected
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(0, null, this);
        } else {
            findViewById(R.id.loading_spinner).setVisibility(View.GONE);
            mEmptyStateView.setText(R.string.no_internet_connection);
        }

        // Link to article's URL
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                News news = newsAdapter.getItem(i);
                Uri webpage = Uri.parse(news.getmUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivity(intent);
            }
        });


    }

    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        if (count == 0) {
            count++;
            return new NewsLoader(this, API_FIRST);
        }
        Uri baseUri = Uri.parse(API_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("section", category.toString());
        uriBuilder.appendQueryParameter("api-key","test");
        Log.v("MainActivity", uriBuilder.toString());
        ProgressBar loading_spinner = (ProgressBar) findViewById(R.id.loading_spinner);
        loading_spinner.setVisibility(View.VISIBLE);

        Log.v(LOG_TAG, "onCreateLoader");


        return new NewsLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> news) {
        ProgressBar loading_spinner = (ProgressBar) findViewById(R.id.loading_spinner);
        loading_spinner.setVisibility(View.GONE);


            newsAdapter.clear();
        if (news != null && !news.isEmpty())
            newsAdapter.addAll(news);
        count = 1;
        NewsAdapter.count = 1;

        Log.v(LOG_TAG, "onLoadFinished");
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        newsAdapter.clear();
        Log.v(LOG_TAG, "onLoaderReset");

    }


    // Navigation bar called by button


}


