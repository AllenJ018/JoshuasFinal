package edu.mssu.cis385.afinal;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.PrivilegedAction;

public class NetworkUtils {
    private static final String LOG_TAG =
            NetworkUtils.class.getSimpleName();


    private static final String ITEM_BASE_URL =  "https://api.guildwars2.com/v2/items?lang=en&page_size=200";
    // Parameter for the search string.
    private static final String START_PAGE = "page";






    static String getItemInfo(String queryString){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String itemJSONString = null;
        StringBuilder builder = new StringBuilder();

        try {
                Uri builtURI = Uri.parse(ITEM_BASE_URL).buildUpon()
                        .appendQueryParameter(START_PAGE, queryString)
                        .build();
            Log.d(LOG_TAG, builtURI.toString());

                URL requestURL = new URL(builtURI.toString());

                urlConnection = (HttpURLConnection) requestURL.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();


                // Get the InputStream.
                InputStream inputStream = urlConnection.getInputStream();


                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);

                    Log.d(LOG_TAG, line);
                }
            if (builder.length() == 0) {
                Log.d(LOG_TAG, builder.toString());
                return null;
            }
            itemJSONString = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return itemJSONString;

    }



}
