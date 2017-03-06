package com.bminor.officebarkaroake.Messaging;

import android.util.Log;

import com.bminor.officebarkaroake.SongInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class QueryFetcher {

    private static final String TAG = "QueryFetcher";


    public byte[] getUrlBytes(String urlSpec ) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();

        try{
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();

            if( connection.getResponseCode() != HttpURLConnection.HTTP_OK ){
                throw new IOException( connection.getResponseMessage() + ": with " + urlSpec );
            }

            int bytesRead = 0;
            byte [] buffer = new byte[1024];
            while( (bytesRead =in.read(buffer)) > 0 ){
                out.write(buffer, 0, bytesRead);
            }

            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    public String getUrlString(String urlspec) throws IOException {
        return new String( getUrlBytes(urlspec));
    }

    public List<SongInfo> fetchResults( String type, String query ){

        List<SongInfo> songs = new ArrayList<>();

        try {
            String url = "https://obkaraoke.herokuapp.com?" + type + "=" + query;

            Log.d( "URL", url );
            String result = getUrlString( url );

            JSONArray jsonArray = new JSONArray( result );
            parseSongs( songs, jsonArray );
        } catch (JSONException je) {
        } catch (IOException ioe) {
        }

        return songs;
    }

    private void parseSongs(List<SongInfo> songs, JSONArray songJsonArray ) throws IOException, JSONException {
        for( int i = 0; i < songJsonArray.length(); i++ ){
            JSONObject jsonSongObject = songJsonArray.getJSONObject(i);

            SongInfo info = new SongInfo();
            info.set_artist( jsonSongObject.getString("artist"));
            info.set_song( jsonSongObject.getString("title") );

            songs.add( info );
        }
    }

}
