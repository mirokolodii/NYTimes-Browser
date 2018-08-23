package com.unagit.nytimesbrowser;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.unagit.nytimesbrowser.data.DataProvider;
import com.unagit.nytimesbrowser.helpers.Constants;
import com.unagit.nytimesbrowser.models.Article;

import java.util.List;

public class MainFragment extends Fragment implements DataProvider.CallbackResult {

    private final String LOG_TAG = this.getClass().getName();
    private Context context;
    private int queryType;
    View view;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

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
        listArticles(rootView);
        return rootView;
    }

    private void listArticles(View view) {
        this.view = view;
        // Get data from provider, which will be received via callback method onCallbackResultsReceived
        DataProvider provider = new DataProvider();
        provider.fetchData(this, this.context, this.queryType);
    }

    @Override
    public void onCallbackResultsReceived(List<Article> results) {
        if(view != null) {
            MainListAdapter adapter = new MainListAdapter(this.context, results);
            ListView listView = view.findViewById(R.id.articles_list_view);
            listView.setAdapter(adapter);
        }

    }
}
