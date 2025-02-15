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

    public ChampCard(Card card) {
        super(card);
    }

    @Override
    public int getMaxPOForOneCard() {
        return max_champion_po;
    }

    @Override
    public boolean haveEnoughCardForNextStep() {
        return super.haveEnoughCardForNextStep(carte_champion_nb);
    }

    @Override
    public int getPOForNextStep() {
        return super.getPOForNextStep(carte_champion_po);
    }

    @Override
    public int getManyCardCost() {
        return super.getHowMis(carte_champion_po);
    }

    @Override
    public int getNbMaxCard() {
        return max_champion_carte;
    }

    @Override
    public int getNbDeCarteMis() {
        return super.getHowMis(carte_champion_nb);
    }

    @Override
    public String getRarityType() {
        return TYPE;
    }

  
    
}
