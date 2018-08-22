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
//        String[] testStringArray = {"title1", "title2", "title3"};
//        ArrayList<String> testStringArray = new ArrayList<>();
//        testStringArray.add("title1");
//        testStringArray.add("title2");
//        testStringArray.add("title3");
        String[] testStringArray = new String[] {
                "Android List View",
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View"
        };
        Log.d(LOG_TAG, testStringArray.toString());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.context,
                android.R.layout.simple_list_item_1, testStringArray);
        ListView listView = view.findViewById(R.id.most_emailed_list_view);
        listView.setAdapter(adapter);
//        testStringArray.add("title4");
    }
}
