package com.msc.clashroyal.cards;

import com.msc.clashroyal.entity.Card;

/**
 *
 * @author Michael
 */
public class FactoryCard {

    public static RarityCard getCarRarityCard(Card card) {
        switch (card.rarity) {
            case CommonCard.TYPE:
                return new CommonCard(card);
            case RareCard.TYPE:
                return new RareCard(card);
            case EpicCard.TYPE:
                return new EpicCard(card);
            case LegendCard.TYPE:
                return new LegendCard(card);
            case ChampCard.TYPE:
                return new ChampCard(card);
        }
        return null;
    }

}
