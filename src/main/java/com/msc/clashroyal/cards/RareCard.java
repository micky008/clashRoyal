package com.msc.clashroyal.cards;

/**
 *
 * @author Michael
 */
class RareCard extends AbstractCard {

    private static final int carte_rare_po[] = {0, 50, 150, 400, 1000, 2000, 4000, 8000, 15000, 35000, 75000, 100000};
    private static final int carte_rare_nb[] = {0, 1, 2, 4, 10, 20, 50, 100, 200, 400, 500, 750, 1250};
    private static final int max_rare_po = 240600;
    private static final int max_rare_carte = 3287;
    public static final String TYPE = "rare";

    private static Tableaux tab = new Tableaux(carte_rare_po, carte_rare_nb, max_rare_po, max_rare_carte);

    public RareCard() {
        super(TYPE, tab);
    }

}
