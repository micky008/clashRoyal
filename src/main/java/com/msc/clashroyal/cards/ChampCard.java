package com.msc.clashroyal.cards;

import com.msc.clashroyal.entity.Card;

/**
 *
 * @author Michael
 */
class ChampCard extends AbstractCard {

    private static final int carte_champion_po[] = {0, 35000, 75000, 100000};
    private static final int carte_champion_nb[] = {0, 1, 2, 8, 20};
    private static final int max_champion_po = 210000;
    private static final int max_champion_carte = 31;
    public static final String TYPE = "champion";

    private static Tableaux tab = new Tableaux(carte_champion_po, carte_champion_nb, max_champion_po, max_champion_carte);

    public ChampCard(Card card) {
        super(card, TYPE, tab);
    }

}
