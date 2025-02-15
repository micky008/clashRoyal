package com.msc.clashroyal.cards;

import com.msc.clashroyal.entity.Card;

/**
 *
 * @author Michael
 */
class CommonCard extends AbstractCard {

    private final static int carte_commune_po[] = {0, 5, 20, 50, 150, 400, 1000, 2000, 4000, 8000, 15000, 35000, 75000, 100000};
    private final static int carte_commune_nb[] = {0, 1, 2, 4, 10, 20, 50, 100, 200, 400, 800, 1000, 1500, 3000, 5000};
    private final static int max_commune_po = 240625;
    private final static int max_commune_carte = 12087;
    public static final String TYPE = "common";
    
    
    
    public CommonCard(Card card) {
        super(card);
    }

    @Override
    public int getMaxPOForOneCard() {
        return max_commune_po;
    }

    @Override
    public boolean haveEnoughCardForNextStep() {
        return super.haveEnoughCardForNextStep(carte_commune_nb);
    }

    @Override
    public int getPOForNextStep() {
        return super.getPOForNextStep(carte_commune_po);
    }

    @Override
    public int getManyCardCost() {
        return super.getHowMis(carte_commune_po);
    }

    @Override
    public int getNbMaxCard() {
        return max_commune_carte;
    }

    @Override
    public int getNbDeCarteMis() {
        return super.getHowMis(carte_commune_nb);
    }

    @Override
    public String getRarityType() {
        return TYPE;
    }
}
