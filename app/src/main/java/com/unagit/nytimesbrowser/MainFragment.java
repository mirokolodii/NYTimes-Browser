package com.unagit.nytimesbrowser;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.unagit.nytimesbrowser.data.DataProvider;
import com.unagit.nytimesbrowser.helpers.Constants;
import com.unagit.nytimesbrowser.models.Article;
import com.unagit.nytimesbrowser.models.ArticleViewModel;

import java.util.List;

public class MainFragment extends Fragment {

    private final String LOG_TAG = this.getClass().getName();
    private int queryType;
    private ArticleViewModel mArticleViewModel;


    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        this.context = context;
        mArticleViewModel = ViewModelProviders.of((AppCompatActivity) context).get(ArticleViewModel.class);

        if (getArguments() != null) {
            queryType = getArguments().getInt(Constants.Queries.queryLabel);
            Log.d(this.getClass().getSimpleName(), "queryType: " + String.valueOf(queryType));
        } else {
            Log.d(this.getClass().getSimpleName(), "Arguments are empty");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_articles, container, false);
        ((MainActivity) getActivity()).showProgressBar(true);
        getData(rootView);
        return rootView;
    }

    private void getData(View view) {
        final MainListAdapter adapter = new MainListAdapter(getActivity());
        ListView listView = view.findViewById(R.id.articles_list_view);
        listView.setAdapter(adapter);

        final Button retryBtn = view.findViewById(R.id.retry_button);

        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mArticleViewModel.updateData();
                retryBtn.setVisibility(View.GONE);
                ((MainActivity) getActivity()).showProgressBar(true);
            }
        });

        mArticleViewModel.getFavorites().observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(@Nullable List<Article> articles) {
                adapter.setFavorites(articles);
            }
        });

        LiveData<List<Article>> articles = new LiveData<List<Article>>() {

        };

        switch (queryType) {
            case Constants.Tabs.MOST_EMAILED_TAB:
                articles = mArticleViewModel.getMostEmailed();
                break;
            case Constants.Tabs.MOST_SHARED_TAB:
                articles = mArticleViewModel.getMostShared();
                break;
            case Constants.Tabs.MOST_VIEWED_TAB:
                articles = mArticleViewModel.getMostViewed();
                break;
        }

        articles.observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(@Nullable List<Article> articles) {
                if(articles != null && articles.size() > 0) {
                    ((MainActivity) getActivity()).showProgressBar(false);
                }
                adapter.setArticles(articles);
            }
        });

        mArticleViewModel.getErrorMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if(s != null && !s.isEmpty()) {
                    ((MainActivity) getActivity()).showProgressBar(false);
                    Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                    retryBtn.setVisibility(View.VISIBLE);
                }
            }
        });


    }


}
