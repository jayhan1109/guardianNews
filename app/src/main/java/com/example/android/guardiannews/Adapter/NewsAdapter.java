package com.example.android.guardiannews.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.guardiannews.NewsClass.News;
import com.example.android.guardiannews.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by jungh on 2/26/2018.
 */

public class NewsAdapter extends ArrayAdapter<News> {

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

        TextView sectionName=(TextView)listItemView.findViewById(R.id.section_name);
        sectionName.setText(news.getmSectonName());

        TextView headLine=(TextView)listItemView.findViewById(R.id.head_line);
        headLine.setText(news.getmHead());

        TextView article=(TextView)listItemView.findViewById(R.id.type);
        article.setText(news.getmType());

        return listItemView;
    }
}
