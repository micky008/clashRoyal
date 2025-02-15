package com.msc.clashroyal.cards;

/**
 *
 * @author Michael
 */
public interface RarityCard {

    /**
     * Retourne le prix max pour 1 carte.
     * @return 
     */
    int getMaxPOForOneCard();
    
    /**
     * Est ce que la carte a de quoi passer le prochain level ?
     * @return 
     */
    boolean haveEnoughCardForNextStep();
    /**
     * Combien coute le prochain level (va de paire avec haveEnoughForNextStep).
     * @return 
     */
    int getPOForNextStep();
    
    /**
     * Le cumul en PO de la carte deja mis.      
     * @return 
     */
    int getManyCardCost();
    
    /**
     * le nombre max de carte qu'il faut pour 1 type.
     * @return 
     */
    int getNbMaxCard();
    
    /**
     * Le nombre de carte deja mis.
     * @return 
     */
    int getNbDeCarteMis();
    
    /**
     * Retourne le type de rarete: common, rare, epic, etc...
     * @return 
     */
    String getRarityType();
}