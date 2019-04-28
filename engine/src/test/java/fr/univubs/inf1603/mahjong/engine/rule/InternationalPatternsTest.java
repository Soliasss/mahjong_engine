package fr.univubs.inf1603.mahjong.engine.rule;

import fr.univubs.inf1603.mahjong.engine.game.GameTile;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

class InternationalPatternsTest {

    @Test
    void bigFourWind() {
        System.out.println("bigFourWindTest");

        PlayerSet set = null;
        //TODO: forge PlayerSet

        Collection<IdentifiedPattern> patterns = InternationalPatterns.BIG_FOUR_WINDS.identify(set);

        //TODO: make verifications on patterns
    }

    @Test
    void pungOfTerminalsOrHonors() {
        //Création de la winningTile
        GameTile winningTile = new GameTile(14, InternationalTiles.BAMBOO_1);
        //Création de la factory
        AbstractCombinationFactory newCombiFact = new InternationalCombinationFactory();
        //Création de la main
        Collection<Combination> hand = new ArrayList<>();
        GameTile dot1 = new GameTile(0, InternationalTiles.DOT_1);
        GameTile dot2 = new GameTile(1, InternationalTiles.DOT_2);
        GameTile dot3 = new GameTile(2, InternationalTiles.DOT_3);
        GameTile dot6 = new GameTile(3, InternationalTiles.DOT_6);
        GameTile dot7 = new GameTile(4, InternationalTiles.DOT_7);
        GameTile dot8 = new GameTile(5, InternationalTiles.DOT_8);
        GameTile bamboo2 = new GameTile(6, InternationalTiles.BAMBOO_2);
        GameTile bamboo2bis = new GameTile(7, InternationalTiles.BAMBOO_2);

        Combination combiDots = null;
        Combination combiDotsBis = null;
        Combination combiBamboos = null;
        try {
            combiDots = newCombiFact.newCombination(dot1, dot2, dot3);
            combiDotsBis = newCombiFact.newCombination(dot6, dot7, dot8);
            combiBamboos = newCombiFact.newCombination(bamboo2, bamboo2bis);
        } catch (RulesException ex) {
            Logger.getLogger(InternationalPatterns.class.getName()).log(Level.SEVERE, null, ex);
        }

        hand.add(combiDots);
        hand.add(combiDotsBis);
        hand.add(combiBamboos);
        //Création des melds
        Collection<Combination> melds = new ArrayList<>();
        GameTile carac1 = new GameTile(8, InternationalTiles.CHARACTER_1);
        GameTile carac1bis = new GameTile(9, InternationalTiles.CHARACTER_1);
        GameTile carac1ter = new GameTile(10, InternationalTiles.CHARACTER_1);
        GameTile dragonG = new GameTile(11, InternationalTiles.DRAGON_RED);
        GameTile dragonW = new GameTile(12, InternationalTiles.DRAGON_RED);
        GameTile dragonR = new GameTile(13, InternationalTiles.DRAGON_RED);

        try {
            Combination combiCars = newCombiFact.newCombination(carac1, carac1bis, carac1ter);
            Combination combiDragons = newCombiFact.newCombination(dragonG, dragonR, dragonW);

            melds.add(combiCars);
            melds.add(combiDragons);
            //Création des cachés et des honneurs
            Collection<Combination> concealed = new ArrayList<>();
            Collection<GameTile> supremeHonors = new ArrayList<>();
            //Création d'un set
            PlayerSet setTest = new PlayerSet(winningTile, hand, concealed, melds, supremeHonors, false, false, Wind.SOUTH, Wind.EAST);

            IdentifiablePattern idP = InternationalPatterns.PUNG_OF_TERMINALS_OR_HONORS;

            //Appel de la méthode d'identification par rapport au set créé
            Collection<IdentifiedPattern> pattern = idP.identify(setTest);
            //ArrayList de retour pour transformer un IdentifiedPattern en GameTile

            for (IdentifiedPattern iP : pattern) {
                //Le but est le suivant : chaque tuiles du patterns identifié doivent être comparées à la combinaison créée
                //Soit : Combination combiCars = newCombiFact.newCombination(carac1,carac1bis,carac1ter);
                //Doit être retrouvé dans l'IdentifiedPattern pour le assertEquals
                //Donc que le contenu de toTest soit égale à combiCars de notre test
                //toTest.addAll(iP.getTiles());
                Collection<GameTile> tiles = iP.getTiles();
                Collection<GameTile> combiTiles = new HashSet<>(Arrays.asList(combiCars.getTiles()));
                Assert.assertEquals(combiTiles, tiles);
            }
        } catch (RulesException ex) {
            Logger.getLogger(InternationalPatterns.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}