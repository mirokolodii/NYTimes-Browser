package com.unagit.nytimesbrowser;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.unagit.nytimesbrowser.helpers.Constants;
import com.unagit.nytimesbrowser.models.Article;
import com.unagit.nytimesbrowser.models.ArticleViewModel;

import java.util.List;

public class MainListAdapter extends ArrayAdapter<Article> {

    private List<Article> mArticles; // cached copy of articles
    private List<Article> mFavorites; // cached copy of favorites
    private ArticleViewModel mArticleViewModel;

    final String LOG_TAG = this.getClass().getSimpleName();

    MainListAdapter(Context context) {
        super(context, R.layout.list_view_article);
        mArticleViewModel = ViewModelProviders.of((AppCompatActivity) context).get(ArticleViewModel.class);


    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_article, parent, false);
        }

        if (mArticles != null) {
            final Article article = mArticles.get(position);

            TextView title = convertView.findViewById(R.id.article_title);
            TextView date = convertView.findViewById(R.id.article_date);
            TextView author = convertView.findViewById(R.id.article_author);
            final ImageView img = convertView.findViewById(R.id.image_add_favorite);

            title.setText(article.getTitle());
            author.setText(article.getAuthor());
            date.setText(article.getPublishedDate());

            final long id = article.getId();
            final boolean isFavorite = isFavorite(article);
            img.setImageResource(getImgId(isFavorite));
            // Add onClickListeners
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), "Position: " + position, Toast.LENGTH_SHORT).show();
                    showArticle(article);
                }
            });

            // Add/Remove article to/from favorites
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    img.setImageResource(getImgId(!isFavorite));
                    if(isFavorite) {
                        Toast.makeText(getContext(), "Remove id: " + id + " from favorites", Toast.LENGTH_SHORT).show();
                        mArticleViewModel.deleteFavorite(article);
                    } else {
                        Toast.makeText(getContext(), "Add id: " + id + " to favorites", Toast.LENGTH_SHORT).show();
                        mArticleViewModel.insertFavorite(article);
                    }
                }
            });

        }

        return convertView;
    }

    public void setArticles(List<Article> articles) {
        setArticlesAndFavorites(articles, articles);
    }

    public void setArticles(List<Article> articles, List<Article> favorites) {
        setArticlesAndFavorites(articles, favorites);
    }

    private void setArticlesAndFavorites(List<Article> articles, List<Article> favorites) {
        mArticles = articles;
        mFavorites = favorites;
        notifyDataSetChanged();
    }

    private void showArticle(Article article) {
        Intent intent = new Intent(getContext(), ArticleActivity.class);
        intent.putExtra(Constants.IntentArticleExtras.articleUrl, article.getUrl());
        getContext().startActivity(intent);
    }

    private boolean isFavorite(Article article) {
        for (Article fav : mFavorites) {
            if(article.getId() == fav.getId()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getCount() {
        return mArticles != null ? mArticles.size() : 0;
    }

    private int getImgId(boolean isFavorite) {
        return isFavorite ? R.drawable.ic_favorite : R.drawable.ic_favorite_border;
    }
}
