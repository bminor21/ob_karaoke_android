package com.bminor.officebarkaroake;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

        queryString.trim();

        if( determineCheckedButton() == "all" )
            queryString = "true";
        else if ( queryString.length() == 0 ){
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Error");
            alertDialog.setMessage("Search term cannot be empty");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Okay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            alertDialog.show();
            return;
        }

        searchIntent.putExtra(SEARCH_TYPE, StringUtils.replaceSpaceWithPercent(typeString) );
        searchIntent.putExtra(SEARCH_STRING, StringUtils.replaceSpaceWithPercent(queryString));

        startActivity(searchIntent);
    }

    private String determineCheckedButton(){
        RadioGroup grp = (RadioGroup)findViewById(R.id.radioGroup);
        RadioButton btn = (RadioButton) findViewById( grp.getCheckedRadioButtonId() );

        if( btn.getId() == R.id.radioButton_all )
            return "all";
        else if ( btn.getId()  == R.id.radioButton_artist )
            return "artist";
        else if( btn.getId()  == R.id.radioButton_song )
            return "song";
        else
            return "";
    }
}

