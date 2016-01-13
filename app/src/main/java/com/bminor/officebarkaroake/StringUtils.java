package com.bminor.officebarkaroake;

/**
 * Created by Brett on 1/12/16.
 * Some basic string util functions
 */
public class StringUtils {

    public static String replaceSpaceWithPercent( String formatString ){
        String temp = "";

        for( int i = 0; i < formatString.length(); i++){
            if( formatString.charAt(i) == ' ' )
                temp += "%20";
            else
                temp += formatString.charAt(i);
        }

        return temp;

    }

}
