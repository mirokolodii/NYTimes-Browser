package com.unagit.nytimesbrowser;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.unagit.nytimesbrowser.models.Article;

import java.util.List;

public class MainListAdapter extends ArrayAdapter<Article> {
    MainListAdapter(Context context, List<Article> articles) {
        super(context, R.layout.list_view_article, articles);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Article article = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_article, parent, false);
        }
        TextView title = convertView.findViewById(R.id.article_title);
        TextView date = convertView.findViewById(R.id.article_date);
        TextView author = convertView.findViewById(R.id.article_author);
        ImageView img = convertView.findViewById(R.id.image_add_favorite);

        title.setText(article.getTitle());
        author.setText(article.getAuthor());
        date.setText(article.getPublishedDate());

        // Add onClickListeners
        final int pos = position;
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Position: " + pos, Toast.LENGTH_SHORT).show();
                showArticle(article);
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Add position: " + pos + " to favorites", Toast.LENGTH_LONG).show();
            }
        });


        return convertView;
    }

    private void showArticle(Article article) {
        // TODO: Open another activity


    }
}
