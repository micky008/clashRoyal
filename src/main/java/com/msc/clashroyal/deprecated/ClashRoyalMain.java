/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.msc.clashroyal.deprecated;

import com.msc.clashroyal.entity.Card;
import com.msc.clashroyal.entity.Player;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.Reader;
import java.util.Properties;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.Option;

/**
 * Ne pas utiliser priviligié @see com.msc.clashroyal.ClashRoyalSOLID.
 * Je le garde pour me rappeler de comment j'ai fait le 1er jet.
 * @author Michael
 */
@Deprecated
public class ClashRoyalMain {

    public long calculCost(Card card, int[] tab) {
        long res = 0;
        int max = card.level > card.maxLevel ? card.maxLevel : card.level;
        for (int i = 0; i < max; i++) {
            res += tab[i];
        }
        return res;
    }

    public long calculCartePo(Card card, int[] tabPo, int[] tabCarte) {
        long res = 0;
        if (card.level >= card.maxLevel) {
            return 0;
        }
        //nombre de carte < prochain palier
        if (card.count < tabCarte[card.level + 1]) {
            return 0;
        }
        return tabPo[card.level];
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
        URL url = new URL("https://api.clashroyale.com/v1/players/%23" + args[0]);
        HttpsURLConnection myURLConnection = (HttpsURLConnection) url.openConnection();
        myURLConnection.setRequestProperty("Authorization", "Bearer " + args[1]);
        InputStream is = myURLConnection.getInputStream();
        String json = IOUtils.toString(is, "UTF-8");
        Gson gson = new Gson();
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
        System.out.print(player.name);
        System.out.println();
        System.out.print("Cout Max de tts les Cartes: " + String.format("%,8d%n", costAllMax));
        System.out.print("Thunes deja investie: " + String.format("%,8d%n", costpayed));
        System.out.print("Reste a mettre: " + String.format("%,8d%n", (costAllMax - costpayed)));
        System.out.println();
        System.out.print("Total de po a mettre pour carte passable: " + String.format("%,8d%n", todayPo));

    }

    public static void main(String[] args) throws Exception {
        // create the parser
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        Options options = new Options();

        Option help = Option.builder("h").longOpt("help").desc("affiche cette aide").build();
        Option file = Option.builder("f").longOpt("file").argName("propertieFile").hasArg().desc("lit un fichier .properties les clés sont : \"tag\" (sans le #) et \"token\" (JWT)").build();
        Option tag = Option.builder("t").longOpt("tag").argName("tag").hasArg().desc("le tag d'un joueur mais sans le #").build();
        Option key = Option.builder("k").longOpt("token").argName("token").hasArg().desc("le token (JWT) de l'api clash royale").build();

        options.addOption(help);
        options.addOption(file);
        options.addOption(tag);
        options.addOption(key);

        CommandLine line = parser.parse(options, args);
        if (args.length == 0 || line.hasOption("help")) {
            formatter.printHelp("java -jar clashroyal.jar", options);
            return;
        }

        String[] newArg = new String[2];
        if (line.hasOption("file")) {
            Properties p = new Properties();
            Reader reader = new FileReader(line.getOptionValue("file"));
            p.load(reader);
            IOUtils.closeQuietly(reader);
            newArg[0] = p.get("tag").toString();
            newArg[1] = p.get("token").toString();
        }
        if (line.hasOption("tag")) {
            newArg[0] = line.getOptionValue("tag");
        }
        if (line.hasOption("token")) {
            newArg[1] = line.getOptionValue("token");
        }
        if (newArg[0].isEmpty() && newArg[1].isEmpty()) {
            System.out.println("Il faut soit -f ou -t et -k obligatoire");
            formatter.printHelp("java -jar clashroyal.jar", options);
            return;
        }
        new ClashRoyalMain().go(newArg);
    }
}
