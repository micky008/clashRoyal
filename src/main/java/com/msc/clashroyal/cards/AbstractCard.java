package com.msc.clashroyal.cards;

import com.msc.clashroyal.entity.Card;

/**
 *
 * @author Michael
 */
abstract class AbstractCard implements RarityCard {

    protected Card card;

    public AbstractCard(Card card) {
        this.card = card;
    }

    /**
     * Fais double emplois ca depends du tableau. getManyCardCost et
     * getNbDeCarteMis
     *
     * @param tabCarte
     * @return
     */
    protected int getHowMis(int[] tabCarte) {
        int max = card.level > card.maxLevel ? card.maxLevel : card.level;
        int res = 0;
        for (int i = 0; i < max; i++) {
            res += tabCarte[i];
        }
        return res;
    }

    protected int getPOForNextStep(int[] tabPo) {
        return tabPo[card.level];
    }

    protected boolean haveEnoughCardForNextStep(int[] tabCarte) {
        if (card.level >= card.maxLevel) {
            return false;
        }
        //nombre de carte < prochain palier
        if (card.count < tabCarte[card.level + 1]) {
            return false;
        }
        return true;
    }

}
