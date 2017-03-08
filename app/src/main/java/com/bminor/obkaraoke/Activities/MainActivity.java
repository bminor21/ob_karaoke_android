package com.bminor.obkaraoke.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bminor.obkaraoke.Common;
import com.bminor.obkaraoke.StringUtils;
import com.bminor.obkaraoke.*;

public class MainActivity extends AppCompatActivity {

    public final static String SEARCH_STRING = "com.bminor.officebarkaraoke.MESSAGE";
    public final static String SEARCH_TYPE = "com.bminor.officebarkaraoke.MESSAGE2";
//    public final static String TAG = "MAIN_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void sendMessage( View v ){
        Intent searchIntent = new Intent( MainActivity.this, PerformSearchActivity.class );
        EditText parm = (EditText)findViewById(R.id.editText_searchText);
        String queryString = parm.getText().toString();
        String typeString = determineCheckedButton();

        if( determineCheckedButton() == "all" )
            queryString = "true";

        queryString.trim();


        if ( queryString.length() == 0 ){
            String title = "Error";
            String message = "Search term cannot be empty.";
            Common.showAlert( title, message, MainActivity.this );
            return;
        }

        queryString = StringUtils.removeIllegalCharacters(queryString);
        if( queryString.length() == 0 ) {
            String title = "Error";
            String message = "Invalid search string.";
            Common.showAlert( title, message, MainActivity.this );
            parm.setText("");
            return;
        }

        searchIntent.putExtra(SEARCH_TYPE, StringUtils.replaceSpaceWithPercent(typeString) );
        searchIntent.putExtra(SEARCH_STRING, StringUtils.replaceSpaceWithPercent(queryString));

        startActivity(searchIntent);
    }

    /**
     * Determines the checked radio button from the radio group
     * Returns it's string value
     **/
    private String determineCheckedButton(){
        RadioGroup grp = (RadioGroup)findViewById(R.id.radioGroup);
        RadioButton btn = (RadioButton) findViewById( grp.getCheckedRadioButtonId() );

        if ( btn.getId()  == R.id.radioButton_artist )
            return "artist";
        else if( btn.getId()  == R.id.radioButton_song )
            return "song";
        else
            return "";
    }
}

