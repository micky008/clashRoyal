package com.msc.clashroyal.cards;

import com.msc.clashroyal.entity.Card;

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

    public RareCard(Card card) {
        super(card);
    }

    @Override
    public int getMaxPOForOneCard() {
        return max_rare_po;
    }

    @Override
    public boolean haveEnoughCardForNextStep() {
        return super.haveEnoughCardForNextStep(carte_rare_nb);
    }

    @Override
    public int getPOForNextStep() {
        return super.getPOForNextStep(carte_rare_po);
    }

    @Override
    public int getManyCardCost() {
        return super.getHowMis(carte_rare_po);
    }

    @Override
    public int getNbMaxCard() {
        return max_rare_carte;
    }

    @Override
    public int getNbDeCarteMis() {
        return super.getHowMis(carte_rare_nb);
    }

    @Override
    public String getRarityType() {
        return TYPE;
    }

}
