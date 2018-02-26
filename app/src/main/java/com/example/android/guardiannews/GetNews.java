package com.example.android.guardiannews;

import android.util.Log;

import com.example.android.guardiannews.NewsClass.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jungh on 2/26/2018.
 */

public class GetNews {
    public static final String LOG_TAG = GetNews.class.getName();

    public static List<News> extractNews(String str) {
        URL url = null;
        String jsonResponse="";

        url = createURL(str);
        jsonResponse=makeHttpRequest(url);

        List<News> news=parsingJSON(jsonResponse);
        return news;
    }

    private static URL createURL(String str) {
        URL url = null;

        try {
            url = new URL(str);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "error with creating url", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url){
        String jsonResponse="";

        HttpURLConnection urlConnection=null;
        InputStream inputStream=null;

        try {
            urlConnection= (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if(urlConnection.getResponseCode()==200){
                inputStream=urlConnection.getInputStream();
                jsonResponse=readInputStream(inputStream);
            }
        } catch (IOException e) {
            Log.e(LOG_TAG,"error with opening URL connection");
        }
        return jsonResponse;
    }

    private static String readInputStream(InputStream inputStream) throws IOException {
        StringBuilder json=new StringBuilder();
        if(inputStream!=null){
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            String line=bufferedReader.readLine();
            while (line!=null){
                json.append(line);
                line=bufferedReader.readLine();
            }
        }
        return json.toString();
    }

    private static List<News> parsingJSON(String str){

        List<News> news=new ArrayList<>();

        try {
            JSONObject jsonObject=new JSONObject(str);
            JSONObject response=jsonObject.getJSONObject("response");
            JSONArray results=response.getJSONArray("results");
            for(int i=0;i<response.length();i++){
                String title=results.getJSONObject(i).getString("webTitle");
                String sectionName=results.getJSONObject(i).getString("sectionName");
                String type=results.getJSONObject(i).getString("type");
                news.add(new News(title,sectionName,type));
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG,"error with create jsonObject",e);
        }
        return news;
    }
}
