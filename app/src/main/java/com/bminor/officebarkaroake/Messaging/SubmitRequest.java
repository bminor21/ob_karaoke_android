package com.bminor.officebarkaroake.Messaging;

import android.util.Log;

import com.bminor.officebarkaroake.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SubmitRequest {

    private static final String TAG = "SubmitRequest";


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

    public boolean fetchResults( String song, String artist, String name ){

        try {
            String url = "http://officebarkaraoke.netne.net/request.php?";

            String temp = "song=" + song;
            temp = temp + "&artist=" + artist;
            temp = temp + "&name=" + name;

            url = url + StringUtils.replaceSpaceWithPercent(temp);

            String result = getUrlString( url );

            Log.i(TAG, "Fetched contents of URL:" + url );
            JSONObject jsonObject = new JSONObject( result );

            if( jsonObject.getBoolean("status") ){
                return true;
            } else {
                return false;
            }

        } catch (JSONException je) {
            Log.e(TAG, "Failed to parse JSON", je );
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch URL: ", ioe);
        }

        return false;
    }

}
