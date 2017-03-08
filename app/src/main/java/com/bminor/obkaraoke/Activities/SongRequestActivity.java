package com.bminor.obkaraoke.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bminor.obkaraoke.Common;
import com.bminor.obkaraoke.Messaging.SubmitRequest;
import com.bminor.obkaraoke.R;
import com.bminor.obkaraoke.StringUtils;

public class SongRequestActivity extends AppCompatActivity {

    private static final String TAG = "songRequestActivity";

    private static String artist = "";
    private static String song = "";
    private static String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_request);

        Intent intent = getIntent();
        artist = intent.getStringExtra(PerformSearchActivity.artistName);
        song = intent.getStringExtra(PerformSearchActivity.songTitle);

        TextView songField = (TextView)findViewById(R.id.selectedSong);
        TextView artistField = (TextView) findViewById(R.id.selectedArtist);

        songField.setText(song);
        artistField.setText( artist );
    }

    public void sendMessage( View v ){

        EditText nameField = (EditText)findViewById(R.id.nameField);
        name = nameField.getText().toString();

        name.trim();
        name = StringUtils.removeIllegalCharacters( name );

        if( name.length() > 0 )
            new RequestSong( song , artist, name ).execute();
        else{
            String title = "Error";
            String message = "Please enter a valid name.";
            nameField.setText("");
            Common.showAlert( title, message, SongRequestActivity.this );
        }
    }

    private class RequestSong extends AsyncTask<Void, Void, Common.ResultType> {

        private String _song = "";
        private String _artist = "";
        private String _name = "";

        public RequestSong( String song, String artist, String name ){
            _song = song;
            _artist = artist;
            _name = name;
        }

        @Override
        protected Common.ResultType doInBackground(Void... params) {
            return new SubmitRequest().fetchResults(_song, _artist, _name);
        }

        @Override
        protected void onPostExecute( Common.ResultType result ){
            if(result == Common.ResultType.SUCCESS ){
                alertUser(result);
            } else {
                alertUser(result);
            }
        }

        protected void alertUser( Common.ResultType resultType ){
            String title = "", message = "";

            if( resultType == Common.ResultType.SUCCESS ){
                title = getString(R.string.success_title);
                message = getString(R.string.success_message);
            } else if( resultType == Common.ResultType.INACTIVE ){
                title = getString(R.string.inactive_title);
                message = getString(R.string.inactive_message);
            } else {
                title = getString(R.string.error_title);
                message = getString(R.string.error_message );
            }

            finishRequest(title, message);
        }

        private void finishRequest( String title, String message ){
            AlertDialog alertDialog = new AlertDialog.Builder( SongRequestActivity.this).create();
            alertDialog.setTitle(title);
            alertDialog.setMessage(message);
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Okay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    Intent homeIntent = new Intent( SongRequestActivity.this, MainActivity.class );
                    homeIntent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK );
                    startActivity( homeIntent );
                }
            });

            alertDialog.show();
        }
    }
}
