package com.msc.clashroyal.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Michael
 */
public class ApiFetcher {

    private static final String clashAPIURL = "https://api.clashroyale.com/v1/players/%23";

    private final String tag;
    private final String token;

    public ApiFetcher(String tag, String token) {
        this.tag = tag;
        this.token = token;
    }

    public String getPlayerInJson() throws IOException {
        URL url = new URL(clashAPIURL + tag);
        HttpsURLConnection myURLConnection = (HttpsURLConnection) url.openConnection();
        myURLConnection.setRequestProperty("Authorization", "Bearer " + token);
        InputStream is = myURLConnection.getInputStream();
        return IOUtils.toString(is, "UTF-8");
    }

}
