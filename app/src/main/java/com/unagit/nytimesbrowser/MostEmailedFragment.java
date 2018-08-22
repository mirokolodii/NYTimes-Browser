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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MostEmailedFragment extends Fragment {

    private final String LOG_TAG = this.getClass().getName();
    private Context context;

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

        MostEmailedListAdapter adapter = new MostEmailedListAdapter(this.context);
        ListView listView = view.findViewById(R.id.most_emailed_list_view);
        listView.setAdapter(adapter);
    }
}
