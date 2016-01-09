package com.bminor.officebarkaroake;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PerformSearchActivity extends AppCompatActivity {

    private static final String TAG = "Main Activity";

    ListView listView;

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

    private class PerformSearch extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
//            try {
//                String result = new QueryFetcher().getUrlString("https://api.github.com/users/mralexgray/repos");
//                Log.i(TAG, "Fetched contents of URL:" + result);
//            } catch (IOException ioe) {
//                Log.e(TAG, "Failed ot fetch URL: ", ioe);
//            }
            new QueryFetcher().fetchResults();

            return null;
        }
    }

}
