package com.msc.clashroyal.cards;

import com.msc.clashroyal.entity.Card;
import com.msc.clashroyal.entity.Player;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Michael
 */
public class CardsUtils {

    public static List<Card> getAllCards(Player p) {
        List<Card> allcards = new ArrayList<>(p.cards.size() + p.supportCards.size());
        allcards.addAll(p.cards);
        allcards.addAll(p.supportCards);
        return allcards;
    }

}
