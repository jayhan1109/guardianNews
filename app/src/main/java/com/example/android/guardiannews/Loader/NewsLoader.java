package com.example.android.guardiannews.Loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.android.guardiannews.GetNews;
import com.example.android.guardiannews.NewsClass.News;

import java.util.List;

/**
 * Created by jungh on 2/26/2018.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>>{

    String str;

    public NewsLoader(Context context,String str) {
        super(context);
        this.str=str;
    }

    @Override
    public List<News> loadInBackground() {
        if (str == null) {
            return null;
        }
        List<News> news = GetNews.extractNews(str);

        return news;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
