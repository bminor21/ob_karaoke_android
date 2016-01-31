package com.bminor.officebarkaroake;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Brett on 1/12/16.
 * Some basic string util functions
 */
public class StringUtils {

    /**
     *   For URLs:
     *   Replaces white space characters with %20
     **/
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

    /**
     *
     *   Takes a string and strips it of all illegal search characters
     *   Returns the stripped string
     */

    public static String removeIllegalCharacters( String formatString ){
        String temp = "";

        Set resultSet = new HashSet();
        String string = "abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ'";

        for (int i = 0; i < string.length(); i++) {
            resultSet.add(new Character(string.charAt(i)));
        }

        for( int i = 0; i < formatString.length(); i++){
            char ind = formatString.charAt(i);

            if( resultSet.contains(ind) )
                temp += ind;
            else if( ind == '&' )
                temp += '+';
            else if( ind == ' ' )
                temp += "%20";
        }

        return temp;
    }

}
