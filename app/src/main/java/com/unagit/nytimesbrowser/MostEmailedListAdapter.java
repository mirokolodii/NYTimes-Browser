package com.unagit.nytimesbrowser;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MostEmailedListAdapter extends ArrayAdapter<Article>{
    MostEmailedListAdapter(Context context, ArrayList<Article> articles) {
        super(context, R.layout.list_view_article, articles);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Article article = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_article, parent, false);
        }
        TextView title = convertView.findViewById(R.id.article_title);
        TextView url = convertView.findViewById(R.id.article_url);
        title.setText(article.getTitle());
        url.setText(article.getUrl());

        return convertView;
    }
}
