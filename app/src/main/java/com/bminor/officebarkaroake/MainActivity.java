package com.bminor.officebarkaroake;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    public final static String SEARCH_STRING = "com.bminor.officebarkaraoke.MESSAGE";
    public final static String SEARCH_TYPE = "com.bminor.officebarkaraoke.MESSAGE2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessage( View v ){

        determineCheckedButton();

        Intent searchIntent = new Intent( MainActivity.this, PerformSearchActivity.class );
        EditText parm = (EditText)findViewById(R.id.editText_searchText);
        searchIntent.putExtra(SEARCH_STRING, parm.getText().toString());
        searchIntent.putExtra(SEARCH_TYPE, determineCheckedButton() );
        startActivity(searchIntent);
    }

    private String determineCheckedButton(){
        RadioGroup grp = (RadioGroup)findViewById(R.id.radioGroup);
        RadioButton btn = (RadioButton) findViewById( grp.getCheckedRadioButtonId() );

        if( btn.getId() == R.id.radioButton_all )
            return "";
        else if ( btn.getId()  == R.id.radioButton_artist )
            return "artist";
        else if( btn.getId()  == R.id.radioButton_song )
            return "song";
        else
            return "";
    }
}

