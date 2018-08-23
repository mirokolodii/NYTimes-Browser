package com.unagit.nytimesbrowser;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.unagit.nytimesbrowser.Data.DataProvider;
import com.unagit.nytimesbrowser.Models.Article;

import java.util.ArrayList;
import java.util.List;

public class MostEmailedFragment extends Fragment implements DataProvider.CallbackResult {

    private final String LOG_TAG = this.getClass().getName();
    private Context context;
//    private List<Article> articles = new ArrayList<>();
//    private MostEmailedListAdapter adapter;
    View view;

    public MostEmailedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_most_emailed, container, false);
        listArticles(rootView);
        return rootView;
    }

    private void listArticles(View view) {
//        Log.d(LOG_TAG, testStringArray.toString());

//        articles.add(new Article("title1", "url1"));
//        articles.add(new Article("title2", "url2"));
//        articles.add(new Article("title3", "url3"));
//        adapter = new MostEmailedListAdapter(this.context, articles);
//        ListView listView = view.findViewById(R.id.most_emailed_list_view);
//        listView.setAdapter(adapter);

        this.view = view;
        // Get data from provider
        DataProvider provider = new DataProvider();
        provider.fetchData(this);
    }

    @Override
    public void onCallbackResultsReceived(List<Article> results) {
//        articles = results;
//        if(adapter != null) {
//            adapter.notifyDataSetChanged();

//        }

//        for (int i = 0; i<results.size(); i++) {
//            articles.add(results.get(i));
//        }
//        adapter.notifyDataSetChanged();

        if(view != null) {
            MostEmailedListAdapter adapter = new MostEmailedListAdapter(this.context, results);
            ListView listView = view.findViewById(R.id.most_emailed_list_view);
            listView.setAdapter(adapter);
        }

    }
}
