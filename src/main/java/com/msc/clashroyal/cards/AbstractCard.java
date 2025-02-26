package com.msc.clashroyal.cards;

import com.msc.clashroyal.entity.Card;
import com.msc.clashroyal.entity.CardPlayer;

/**
 *
 * @author Michael
 */
abstract class AbstractCard implements RarityCard {

    protected CardPlayer card;
    protected String type;
    protected Tableaux tab;

    public AbstractCard(String type, Tableaux tab) {
        this.type = type;
        this.tab = tab;
    }

    public RarityCard setCard(CardPlayer c) {
        this.card = c;
        return this;
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

    @Override
    public int getMaxPOForOneCard() {
        return tab.max_po;
    }

    @Override
    public boolean haveEnoughCardForNextStep() {
        return haveEnoughCardForNextStep(this.tab.nb);
    }

    @Override
    public int getPOForNextStep() {
        return getPOForNextStep(this.tab.po);
    }

    @Override
    public int getManyCardCost() {
        return getHowMis(this.tab.po);
    }

    @Override
    public int getNbMaxCard() {
        return this.tab.max_nb;
    }

    @Override
    public int getNbDeCarteMis() {
        return getHowMis(this.tab.nb);
    }

    @Override
    public String getRarityType() {
        return type;
    }

    protected static class Tableaux {

        public int po[];
        public int nb[];
        public int max_po;
        public int max_nb;

        public Tableaux(int po[], int nb[], int maxPo, int maxNb) {
            this.po = po;
            this.nb = nb;
            this.max_po = maxPo;
            this.max_nb = maxNb;
        }
    }

}
