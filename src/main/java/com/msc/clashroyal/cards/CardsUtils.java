package com.msc.clashroyal.cards;

import com.msc.clashroyal.entity.CardPlayer;
import com.msc.clashroyal.entity.Player;
import java.util.List;

/**
 *
 * @author Michael
 */
public class CardsUtils {

    public static List<CardPlayer> getAllCards(Player p) {
        p.cards.addAll(p.supportCards);
        return p.cards;
    }

}
