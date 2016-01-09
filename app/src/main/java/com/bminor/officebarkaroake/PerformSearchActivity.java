package com.bminor.officebarkaroake;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class PerformSearchActivity extends AppCompatActivity {

    private static final String TAG = "Main Activity";

    ListView listView;
    private List<SongInfo> sItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perform_search);

        Intent intent = getIntent();
        String searchString = intent.getStringExtra(MainActivity.SEARCH_STRING);
        String searchType = intent.getStringExtra(MainActivity.SEARCH_TYPE);

        listView = (ListView) findViewById(R.id.listView);

        new PerformSearch().execute();
    }

    public void parseResults( String result ){

    }

    private class PerformSearch extends AsyncTask<Void, Void, List<SongInfo>> {

        @Override
        protected List<SongInfo> doInBackground(Void... params) {
            return new QueryFetcher().fetchResults();
        }

        @Override
        protected void onPostExecute(List<SongInfo> songs){
            sItems = songs;
            setupAdapter();
        }
    }

    private void setupAdapter(){

        String[] values = new String[]{"Android List View",
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View",
                "Item 2",
                "Android List View",
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View",
                "Item 2"
        };

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        listView.setAdapter(adapter);
    }

}
