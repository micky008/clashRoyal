package com.msc.clashroyal.cards;

import com.msc.clashroyal.entity.Card;

/**
 *
 * @author Michael
 */
class EpicCard extends AbstractCard {

    private static final int carte_epic_po[] = {0, 400, 2000, 4000, 8000, 15000, 35000, 75000, 100000};
    private static final int carte_epic_nb[] = {0, 1, 2, 4, 10, 20, 40, 50, 100, 200};
    private static final int max_epic_po = 239400;
    private static final int max_epic_carte = 427;
    public static final String TYPE = "epic";

    private static Tableaux tab = new Tableaux(carte_epic_po, carte_epic_nb, max_epic_po, max_epic_carte);

    public EpicCard(Card card) {
        super(card, TYPE, tab);
    }

}
