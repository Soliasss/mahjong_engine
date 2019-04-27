package fr.univubs.inf1603.mahjong.engine.rule;

import org.junit.jupiter.api.Test;

import java.util.Collection;

class InternationalPatternsTest {

    @Test
    void bigFourWind(){
        System.out.println("bigFourWindTest");

        PlayerSet set = null;
        //TODO: forge PlayerSet

        Collection<IdentifiedPattern> patterns = InternationalPatterns.BIG_FOUR_WINDS.identify(set);

        //TODO: make verifications on patterns
    }

    /* i dont know about this */

//    void indentifyTest(){
//        //Création de la winningTile
//        GameTile winningTile = new GameTile(14, InternationalTiles.BAMBOO_1);
//        //Création de la factory
//        AbstractCombinationFactory newCombiFact = new InternationalCombinationFactory();
//        //Création de la main
//        Collection<Combination> hand = new ArrayList<>();
//        GameTile dot1 = new GameTile(0,InternationalTiles.DOT_1);
//        GameTile dot2 = new GameTile(1,InternationalTiles.DOT_2);
//        GameTile dot3 = new GameTile(2,InternationalTiles.DOT_3);
//        GameTile dot6 = new GameTile(3,InternationalTiles.DOT_6);
//        GameTile dot7 = new GameTile(4,InternationalTiles.DOT_7);
//        GameTile dot8 = new GameTile(5,InternationalTiles.DOT_8);
//        GameTile bamboo2 = new GameTile(6,InternationalTiles.BAMBOO_2);
//        GameTile bamboo2bis = new GameTile(7,InternationalTiles.BAMBOO_2);
//
//        Combination combiDots = null;
//        Combination combiDotsBis = null;
//        Combination combiBamboos = null;
//        try {
//            combiDots = newCombiFact.newCombination(dot1,dot2,dot3);
//            combiDotsBis = newCombiFact.newCombination(dot6,dot7,dot8);
//            combiBamboos = newCombiFact.newCombination(bamboo2,bamboo2bis);
//        } catch (RulesException ex) {
//            Logger.getLogger(InternationalPatternListTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        hand.add(combiDots);
//        hand.add(combiDotsBis);
//        hand.add(combiBamboos);
//        //Création des melds
//        Collection<Combination> melds = new ArrayList<>();
//        GameTile carac1 = new GameTile(8,InternationalTiles.CHARACTER_1);
//        GameTile carac1bis = new GameTile(9,InternationalTiles.CHARACTER_1);
//        GameTile carac1ter = new GameTile(10,InternationalTiles.CHARACTER_1);
//        GameTile dragonG = new GameTile(11,InternationalTiles.DRAGON_RED);
//        GameTile dragonW = new GameTile(12,InternationalTiles.DRAGON_RED);
//        GameTile dragonR = new GameTile(13,InternationalTiles.DRAGON_RED);
//
//        Combination combiCars = null;
//        Combination combiDragons = null;
//        try {
//            combiCars = newCombiFact.newCombination(carac1,carac1bis,carac1ter);
//            combiDragons = newCombiFact.newCombination(dragonG,dragonR,dragonW);
//        } catch (RulesException ex) {
//            Logger.getLogger(InternationalPatternListTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        melds.add(combiCars);
//        melds.add(combiDragons);
//        //Création des cachés et des honneurs
//        Collection<Combination> concealed = new ArrayList();
//        Collection<GameTile> supremeHonors = new ArrayList();
//        //Création d'un set
//        PlayerSet setTest = new PlayerSet(winningTile, hand, concealed, melds, supremeHonors, false, false, Wind.SOUTH, Wind.EAST);
//
//        IdentifiablePattern idP = new IdentifiablePattern() {
//            @Override
//            public int getValue() {
//                return 1;
//            }
//
//            @Override
//            public Collection<IdentifiedPattern> identify(PlayerSet setTest) {
//
//                Collection<Combination> allCombinations = setTest.getAllCombinations();
//                Collection<GameTile> pungFound = new ArrayList<>();
//                ArrayList<IdentifiedPattern> toReturn = new ArrayList<>();
//
//                boolean isFind = false;
//                Pattern windPattern = Pattern.compile("W.");
//
//                for (Combination currentCombi: allCombinations) {
//
//                    AbstractTile tile = currentCombi.getTiles()[0].getTile();
//                    Matcher matcher = windPattern.matcher(tile.toNormalizedName());
//
//                    if ( (currentCombi.isPung() || currentCombi.isKong()) && (matcher.matches() || tile.isMajor()) ){
//                        isFind = true;
//                        pungFound.addAll(Arrays.asList(currentCombi.getTiles()));
//                        break;
//                    }
//
//                }
//                if (isFind){
//                    IdentifiedPattern pattern = new IdentifiedPattern(this, pungFound);
//                    toReturn.add(pattern);
//                }
//                return toReturn;
//            }
//        };
//
//        //Appel de la méthode d'identification par rapport au set créé
//        Collection<IdentifiedPattern> pattern = idP.identify(setTest);
//        //ArrayList de retour pour transformer un IdentifiedPattern en GameTile
//
//        for(IdentifiedPattern iP : pattern){
//            //Le but est le suivant : chaque tuiles du patterns identifié doivent être comparées à la combinaison créée
//            //Soit : Combination combiCars = newCombiFact.newCombination(carac1,carac1bis,carac1ter);
//            //Doit être retrouvé dans l'IdentifiedPattern pour le assertEquals
//            //Donc que le contenu de toTest soit égale à combiCars de notre test
//            //toTest.addAll(iP.getTiles());
//            Collection<GameTile> tiles = iP.getTiles();
//            Collection<GameTile> combiTiles = new ArrayList();
//            combiTiles.addAll(Arrays.asList(combiCars.getTiles()));
//            assertEquals(combiTiles,tiles);
//        }
//
//        assertEquals(idP.getValue(),1);

//    }


}