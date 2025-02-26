package com.msc.clashroyal.cards;

import com.msc.clashroyal.entity.Card;
import com.msc.clashroyal.entity.CardPlayer;

/**
 *
 * @author Michael
 */
public class FactoryCard {

    private static final RarityCard common = new CommonCard();
    private static final RarityCard rare = new RareCard();
    private static final RarityCard epic = new EpicCard();
    private static final RarityCard leg = new LegendCard();
    private static final RarityCard champ = new ChampCard();

    public static RarityCard getCarRarityCardPlayer(CardPlayer card) {
        switch (card.rarity) {
            case CommonCard.TYPE:
                return common.setCard(card);
            case RareCard.TYPE:
                return rare.setCard(card);
            case EpicCard.TYPE:
                return epic.setCard(card);
            case LegendCard.TYPE:
                return leg.setCard(card);
            case ChampCard.TYPE:
                return champ.setCard(card);
        }
        return null;
    }

    public static RarityCard getCarRarityCard(Card card) {
        switch (card.rarity) {
            case CommonCard.TYPE:
                return common;
            case RareCard.TYPE:
                return rare;
            case EpicCard.TYPE:
                return epic;
            case LegendCard.TYPE:
                return leg;
            case ChampCard.TYPE:
                return champ;
        }
        return null;
    }
    
}
