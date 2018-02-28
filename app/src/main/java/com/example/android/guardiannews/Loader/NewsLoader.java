package com.example.android.guardiannews.Loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.example.android.guardiannews.GetNews;
import com.example.android.guardiannews.NewsClass.News;

import java.util.List;

/**
 * Created by jungh on 2/26/2018.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>>{

    String str;
    private static final String LOG_TAG=NewsLoader.class.getName();

    public NewsLoader(Context context,String str) {
        super(context);
        this.str=str;

        Log.v(LOG_TAG,"NewsLoader Constructor");

    }

    @Override
    public List<News> loadInBackground() {
        if (str == null) {
            return null;
        }
        List<News> news = GetNews.extractNews(str);

        Log.v(LOG_TAG,"loadInBackground");

        return news;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
        Log.v(LOG_TAG,"onSrartLoading");

    }
}
