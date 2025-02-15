package com.msc.clashroyal.cards;

import com.msc.clashroyal.entity.Card;

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

    public LegendCard(Card card) {
        super(card);
    }

    @Override
    public int getMaxPOForOneCard() {
        return max_legendaire_po;
    }

    @Override
    public boolean haveEnoughCardForNextStep() {
        return super.haveEnoughCardForNextStep(carte_lengendaire_nb);
    }

    @Override
    public int getPOForNextStep() {
        return super.getPOForNextStep(carte_legendaire_po);
    }

    @Override
    public int getManyCardCost() {
        return super.getHowMis(carte_legendaire_po);
    }

    @Override
    public int getNbMaxCard() {
        return max_legendaire_carte;
    }

    @Override
    public int getNbDeCarteMis() {
        return super.getHowMis(carte_lengendaire_nb);
    }
    
    @Override
    public String getRarityType() {
        return TYPE;
    }
    
}
