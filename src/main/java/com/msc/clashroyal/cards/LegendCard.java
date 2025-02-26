package com.msc.clashroyal.cards;

/**
 *
 * @author Michael
 */
class LegendCard extends AbstractCard {

    private static final int carte_legendaire_po[] = {0, 5000, 15000, 35000, 75000, 100000};
    private static final int carte_lengendaire_nb[] = {0, 1, 2, 4, 6, 10, 20};
    private static final int max_legendaire_po = 230000;
    private static final int max_legendaire_carte = 43;
    public static final String TYPE = "legendary";

    private static Tableaux tab = new Tableaux(carte_legendaire_po, carte_lengendaire_nb, max_legendaire_po, max_legendaire_carte);

    public LegendCard() {
        super(TYPE, tab);
    }

}
