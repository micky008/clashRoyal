package com.msc.clashroyal.player;

import com.google.gson.Gson;
import com.msc.clashroyal.entity.Player;
import com.msc.clashroyal.utils.ApiFetcher;
import java.io.IOException;

/**
 *
 * @author Michael
 */
public class PlayerFetcher {

    private String tag;
    private String token;

    public PlayerFetcher(String tag, String token) {
        this.tag = tag;
        if (tag.startsWith("#")) {
            this.tag = tag.substring(1);
        }
        this.token = token;
    }

    public Player getPlayer() throws IOException {
        ApiFetcher c = new ApiFetcher(tag, token); // a mettre dans le constructor
        String json = c.getPlayerInJson();
        Gson gson = new Gson();
        return gson.fromJson(json, Player.class);
    }

}
