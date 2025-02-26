package com.msc.clashroyal.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Michael
 */
public class ApiFetcher {

    private static final String clashAPIPlayerURL = "https://api.clashroyale.com/v1/players/%23";
    private static final String clashAPICardsURL = "https://api.clashroyale.com/v1/cards";

    private final String tag;
    private final String token;

    public ApiFetcher(String tag, String token) {
        this.tag = tag;
        this.token = token;
    }

    private String getString(URL url) throws IOException {
        HttpsURLConnection myURLConnection = (HttpsURLConnection) url.openConnection();
        myURLConnection.setRequestProperty("Authorization", "Bearer " + token);
        InputStream is = myURLConnection.getInputStream();
        return IOUtils.toString(is, "UTF-8");
    }

    public String getPlayerInJson() throws IOException {
        URL url = new URL(clashAPIPlayerURL + tag);
        return getString(url);

    }

    public String getAllCards() throws IOException {
        URL url = new URL(clashAPICardsURL);
        return getString(url);
    }

}
