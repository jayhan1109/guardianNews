package com.example.android.guardiannews.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.guardiannews.R;

/**
 * Created by jungh on 2/27/2018.
 */

public class CategoryAdapter extends ArrayAdapter<String> {
    String[] category;

    public CategoryAdapter(@NonNull Context context, @NonNull String[] objects) {
        super(context, 0, objects);
        category=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView=convertView;
        if(listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.category_list,parent,false);
        }

        TextView textView=(TextView)listItemView.findViewById(R.id.category_list);
        textView.setText(category[position]);

        return listItemView;
    }
}
