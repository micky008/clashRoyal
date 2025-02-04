/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.msc.clashroyal;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

/**
 *
 * @author Michael
 */
public class ClashRoyalMain {

    public long calculCost(Card card, int[] tab) {
        long res = 0;
        int max = card.level > card.maxLevel ? card.maxLevel : card.level;
        for (int i = 0; i < max; i++) {
            res += tab[i];
        }
        //System.out.printf("%s (%s) level: %d: %d\n", card.name, card.rarity, card.level, res);
        return res;
    }

    public long calculCartePo(Card card, int[] tabPo, int[] tabCarte) {
        long res = 0;
        if (card.level >= card.maxLevel) {
            return 0;
        }
        if (card.count < tabCarte[card.level + 1]) {
            return 0;
        }
        System.out.printf("%s (%s) level: %d: ", card.name, card.rarity, card.level);
        for (int i = card.level; i < card.level + 1; i++) {
            res += tabPo[i];
        }
        System.out.printf("total %d\n", res);
        return res;
    }

    public List<Card> merge(Player p) {
        List<Card> allcards = new ArrayList<>(p.cards.size() + p.supportCards.size());
        allcards.addAll(p.cards);
        allcards.addAll(p.supportCards);
        return allcards;
    }

    public int howManyCards(List<Card> cards, String rarity) {
        int res = 0;
        for (Card c : cards) {
            if (c.rarity.equals(rarity)) {
                res += 1;
            }
        }
        return res;
    }

    public void go(String args[]) throws IOException {
        URL url = new URL("https://api.clashroyale.com/v1/players/%23TAG"); 
        HttpsURLConnection myURLConnection = (HttpsURLConnection) url.openConnection();
        myURLConnection.setRequestProperty("Authorization", "Bearer TOKEN_API");
        InputStream is = myURLConnection.getInputStream();
        Gson gson = new Gson();
        String json = IOUtils.toString(is, "UTF-8");
        Player player = gson.fromJson(json, Player.class);
        long costpayed = 0;
        List<Card> allcarts = merge(player);
        int nbCommon = RefCards.max_commune_po * howManyCards(allcarts, "common");
        int nbRare = RefCards.max_rare_po * howManyCards(allcarts, "rare");
        int nbEpic = RefCards.max_epic_po * howManyCards(allcarts, "epic");
        int nbLegend = RefCards.max_legendaire_po * howManyCards(allcarts, "legendary");
        int nbChamp = RefCards.max_champion_po * howManyCards(allcarts, "champion");
        long costAllMax = (nbCommon + nbRare + nbEpic + nbLegend + nbChamp);
        long todayPo = 0;
        for (Card card : allcarts) {
            switch (card.rarity) {
                case "common":
                    costpayed += calculCost(card, RefCards.carte_commune_po);
                    todayPo += calculCartePo(card, RefCards.carte_commune_po, RefCards.carte_commune_nb);
                    break;
                case "rare":
                    costpayed += calculCost(card, RefCards.carte_rare_po);
                    todayPo += calculCartePo(card, RefCards.carte_rare_po, RefCards.carte_rare_nb);
                    break;
                case "epic":
                    costpayed += calculCost(card, RefCards.carte_epic_po);
                    todayPo += calculCartePo(card, RefCards.carte_epic_po, RefCards.carte_epic_nb);
                    break;
                case "legendary":
                    costpayed += calculCost(card, RefCards.carte_legendaire_po);
                    todayPo += calculCartePo(card, RefCards.carte_legendaire_po, RefCards.carte_lengendaire_nb);
                    break;
                case "champion":
                    costpayed += calculCost(card, RefCards.carte_champion_po);
                    todayPo += calculCartePo(card, RefCards.carte_champion_po, RefCards.carte_champion_nb);
                    break;
            }
        }
         System.out.println();
        System.out.println("Cout Max de tts les Cartes: " + costAllMax);
        System.out.println("Thunes deja investie: " + costpayed);
        System.out.println("Reste a mettre: " + (costAllMax - costpayed));
        System.out.println();
        System.out.println("Total de po a mettre pour carte passable: " + todayPo);

    }

    public static void main(String[] args) throws IOException {
        new ClashRoyalMain().go(args);
    }
}
