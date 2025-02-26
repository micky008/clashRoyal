/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.msc.clashroyal;

import com.msc.clashroyal.entity.Card;
import com.msc.clashroyal.entity.Player;
import java.io.IOException;
import java.util.List;


import org.apache.commons.io.IOUtils;

import com.msc.clashroyal.cards.CardsUtils;
import com.msc.clashroyal.cards.FactoryCard;
import com.msc.clashroyal.cards.RarityCard;
import com.msc.clashroyal.entity.CardPlayer;
import com.msc.clashroyal.player.PlayerFetcher;
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
 *
 * @author Michael
 */
public class ClashRoyalSOLID {

    public void go(String args[]) throws IOException {
        PlayerFetcher joueur = new PlayerFetcher(args[0], args[1]);
        Player player = joueur.getPlayer();
        List<CardPlayer> allPlayerCards = CardsUtils.getAllCards(player);
        long todayPo = 0;
        long costpayed = 0;
        long costAllMax = 0;

        List<Card> allcards = joueur.getAllCards();
        for (Card c : allcards) {
            RarityCard card = FactoryCard.getCarRarityCard(c);
            costAllMax += card.getMaxPOForOneCard();
        }
        
        for (CardPlayer c : allPlayerCards) {
            RarityCard card = FactoryCard.getCarRarityCardPlayer(c);
            costpayed += card.getManyCardCost();
            if (card.haveEnoughCardForNextStep()) {
                todayPo += card.getPOForNextStep();
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
        new ClashRoyalSOLID().go(newArg);
    }
}
