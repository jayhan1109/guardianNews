package com.example.android.guardiannews.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.guardiannews.NewsClass.News;
import com.example.android.guardiannews.R;

import java.util.List;

/**
 * Created by jungh on 2/26/2018.
 */

public class NewsAdapter extends ArrayAdapter<News> {

    public static int count =1;

    public NewsAdapter(@NonNull Context context, @NonNull List<News> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView=convertView;
        if(listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.news_list,parent,false);
        }


        News news=getItem(position);

        TextView listNumber=(TextView)listItemView.findViewById(R.id.section_name);
        listNumber.setText(String.valueOf(news.getmNumber()));

        TextView headLine=(TextView)listItemView.findViewById(R.id.head_line);
        headLine.setText(news.getmHead());

        TextView article=(TextView)listItemView.findViewById(R.id.date);
        SpannableStringBuilder sb=new SpannableStringBuilder(news.getmDate());
        StyleSpan styleSpanItalic=new StyleSpan(Typeface.ITALIC);
        int tmp=news.getmDate().indexOf(" ");
        sb.setSpan(styleSpanItalic,0,tmp-1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        article.setText(sb);

        Log.v("NewsAdapter","getView");


        return listItemView;
    }
}
