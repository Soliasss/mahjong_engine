package fr.univubs.inf1603.mahjong.engine.rule;

import fr.univubs.inf1603.mahjong.engine.game.GameTile;

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.*;

import java.util.ArrayList;

/**
 * Implementation of the {@link AbstractPatternList} for the international ruleset.
 * This class behaves like a singleton
 * @author Tristan Furno & Pierre Guriel & Anton Cosnefroy
 */
public class InternationalPatternList implements AbstractPatternList {

    public final static InternationalPatternList DEFAULT = new InternationalPatternList(InternationalPatterns.values());

    private IdentifiablePattern patterns[];

    public InternationalPatternList(IdentifiablePattern ... patterns) {
        this.patterns = patterns;
    }

    @Override
    
    public IdentifiablePattern[] getPatterns() {
        return this.patterns;
    }

    @Override
    public IdentifiablePattern getPattern(String name) {
        return InternationalPatterns.valueOf(name);
    }

    /**
     * The enumeration of all the possible patterns in the international ruleset.
     */
    private enum InternationalPatterns implements IdentifiablePattern {
        /**
         * 4 pungs des 4 vents
         */
        BIG_FOUR_WINDS {
            @Override
            public int getValue() {
                return 88;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<Combination> allCombinations = set.getAllCombinations();
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                Collection<GameTile> pungFound = new ArrayList<>();
                boolean isWest = false;
                boolean isEast = false;
                boolean isNorth = false;
                boolean isSouth = false;
                int nbCombination = 0;
                
                for (Combination temporaryCombination: allCombinations){
                    
                    if(temporaryCombination.isPung() || temporaryCombination.isKong()){
                        GameTile firstTile = temporaryCombination.getTiles()[0];
                        if(firstTile.getTile().toNormalizedName().equals("We")){
                            isEast = true;
                            pungFound.addAll(Arrays.asList(temporaryCombination.getTiles()));
                        }
                        else if(firstTile.getTile().toNormalizedName().equals("Ww")){
                            isWest = true;
                            pungFound.addAll(Arrays.asList(temporaryCombination.getTiles()));
                        }
                        else if(firstTile.getTile().toNormalizedName().equals("Ws")){
                            isSouth = true;
                            pungFound.addAll(Arrays.asList(temporaryCombination.getTiles()));
                        }
                        else if(firstTile.getTile().toNormalizedName().equals("Wn")){
                            isNorth = true;
                            pungFound.addAll(Arrays.asList(temporaryCombination.getTiles()));
                        }
                        nbCombination ++;
                    }
                    else if(nbCombination == 4 && temporaryCombination.isPair()){
                        pungFound.addAll(Arrays.asList(temporaryCombination.getTiles()));
                        nbCombination ++;
                    }
                }
                if(isEast && isWest && isSouth && isNorth && nbCombination == 5){
                    IdentifiedPattern pattern = new IdentifiedPattern(this, pungFound);
                    toReturn.add(pattern);
                }
                return toReturn;
            }
        },

        /**
         * 3 pungs des 3 dragons
         */
        BIG_THREE_DRAGONS {
            @Override
            public int getValue() {
                return 88;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<Combination> allCombinations = set.getAllCombinations();
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                Collection<GameTile> pungFound = new ArrayList<>();
                int nbCombination = 0;
                boolean isRed = false;
                boolean isWhite = false;
                boolean isGreen = false;
                for (Combination temporaryCombination: allCombinations){
                    
                    if(temporaryCombination.isPung() || temporaryCombination.isKong() || temporaryCombination.isChow()){
                        GameTile firstTile = temporaryCombination.getTiles()[0];
                        if(firstTile.getTile().toNormalizedName().equals("Dr") ){
                            isRed = true;
                        }
                        else if(firstTile.getTile().toNormalizedName().equals("Dw")){
                            isWhite = true;
                        }
                        else if(firstTile.getTile().toNormalizedName().equals("Dg")){
                            isGreen = true;
                        }
                        pungFound.addAll(Arrays.asList(temporaryCombination.getTiles()));
                        nbCombination++;
                    } else if (nbCombination == 4 && temporaryCombination.isPair()){
                        pungFound.addAll(Arrays.asList(temporaryCombination.getTiles()));
                        nbCombination ++;
                    }
                }
                if(isRed && isWhite && isGreen && nbCombination == 5){
                    IdentifiedPattern pattern = new IdentifiedPattern(this, pungFound);
                    toReturn.add(pattern);
                }
                return toReturn;
            }
        },
        /**
         * 4 kongs
         */
        FOUR_KONGS {
            @Override
            public int getValue() {
                return 88;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                ArrayList<IdentifiedPattern> result = new ArrayList<>();
                Collection<Combination> allCombinations = set.getAllCombinations();

                int nbOfCombination = 0;
                Collection<GameTile> kongsFound = new ArrayList<>();

                for (Combination currentCombi: allCombinations) {
                    if (currentCombi.isKong()){
                        nbOfCombination++;
                        kongsFound.addAll(Arrays.asList(currentCombi.getTiles()));
                    }
                    else if(nbOfCombination == 4 && currentCombi.isPair()){
                        nbOfCombination ++;
                        kongsFound.addAll(Arrays.asList(currentCombi.getTiles()));
                    }
                }

                if (nbOfCombination == 5){
                    IdentifiedPattern pattern = new IdentifiedPattern(this, kongsFound);
                    result.add(pattern);
                }

                return result;
            }
        },
        /**
         * 7 Pairs consécutives dans une famille
         */
        SEVEN_SHIFTED_PAIRS {
            @Override
            public int getValue() {
                return 88;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                ArrayList<IdentifiedPattern> result = new ArrayList<>();
                Collection<Combination> allCombinations = set.getAllCombinations();
                Collection<GameTile> pairsFound = new ArrayList<>();
                Combination lastCombi = null;
                boolean isSameFamily = true;
                int nbofPair = 0;
                
                for (Combination currentCombi: allCombinations) {
                    if(currentCombi.isPair()){
                        if(nbofPair > 0){
                            if(!currentCombi.getTiles()[0].getTile().getPrevious().equals(lastCombi.getTiles()[0].getTile())){
                                isSameFamily = false;
                                break;
                            }
                        }
                        nbofPair ++;
                        pairsFound.addAll(Arrays.asList(currentCombi.getTiles()));
                        lastCombi = currentCombi;
                       
                    } else {
                        break;
                    }
                    
                    
                    
                }
                if(nbofPair == 7 && isSameFamily) {
                    IdentifiedPattern pattern = new IdentifiedPattern(this, pairsFound);
                    result.add(pattern);
                }
                
                return result;
            }
        },


        ALL_GREEN {
            @Override
            public int getValue() {
                return 88;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        NINE_GATES {
            @Override
            public int getValue() {
                return 88;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        THIRTEEN_ORPHANS {
            @Override
            public int getValue() {
                return 88;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        /**
         * 3 pungs de vente et paire du 4ème
         */
        LITTLE_FOUR_WINDS {
            @Override
            public int getValue() {
                return 64;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<Combination> allCombinations = set.getAllCombinations();
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                Collection<GameTile> pungFound = new ArrayList<>();
                int windFound = 0;
                
                for (Combination temporaryCombination: allCombinations){
                    
                    if(temporaryCombination.isPung() || temporaryCombination.isKong()){
                        GameTile firstTile = temporaryCombination.getTiles()[0];
                        if(firstTile.getTile().toNormalizedName().equals("We")){
                            windFound++;
                            pungFound.addAll(Arrays.asList(temporaryCombination.getTiles()));
                        }
                        else if(firstTile.getTile().toNormalizedName().equals("Ww")){
                            windFound++;
                            pungFound.addAll(Arrays.asList(temporaryCombination.getTiles()));
                        }
                        else if(firstTile.getTile().toNormalizedName().equals("Ws")){
                            windFound++;
                            pungFound.addAll(Arrays.asList(temporaryCombination.getTiles()));
                        }
                        else if(firstTile.getTile().toNormalizedName().equals("Wn")){
                            windFound++;
                            pungFound.addAll(Arrays.asList(temporaryCombination.getTiles()));
                        }
                    }
                    else if(windFound == 3 && temporaryCombination.isPair()){
                        GameTile firstTile = temporaryCombination.getTiles()[0];
                        Pattern pattern = Pattern.compile("W(e|w|s|n)");
                        Matcher matcher = pattern.matcher(firstTile.getTile().toNormalizedName());
                        pungFound.addAll(Arrays.asList(temporaryCombination.getTiles()));
                        windFound++;
                    }
                }
                if(windFound == 4){
                    IdentifiedPattern pattern = new IdentifiedPattern(this, pungFound);
                    toReturn.add(pattern);
                }
                return toReturn;
            }
            
        },
        LITTLE_THREE_DRAGONS {
            @Override
            public int getValue() {
                return 64;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        ALL_HONORS {
            @Override
            public int getValue() {
                return 64;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        PURE_TERMINAL_CHOWS {
            @Override
            public int getValue() {
                return 64;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        /**
         * Uniquement des 1 et des 9
         */
        ALL_TERMINALS {
            @Override
            public int getValue() {
                return 64;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                ArrayList<IdentifiedPattern> result = new ArrayList<>();
                Collection<Combination> allCombinations = set.getAllCombinations();
                int nbOfCombination = 0;
                Collection<GameTile> tilesFound = new ArrayList<>();
                boolean isAllHog = true;
                boolean isAllMajor = true;//Ajout
                Combination lastCombi;
                
                for (Combination currentCombi: allCombinations) {
                    if (currentCombi.isPung() || currentCombi.isKong()){
                        GameTile currentTile = currentCombi.getTiles()[0];
                            if( ! ((CommonTile)currentTile.getTile()).isMajor()){ //Fix
                                isAllMajor = false;
                                break;
                            }
                        nbOfCombination++;
                        tilesFound.addAll(Arrays.asList(currentCombi.getTiles()));
                        lastCombi = currentCombi;
                    } else if(nbOfCombination == 4 && currentCombi.isPair()){
                        GameTile currentTile = currentCombi.getTiles()[0];
                            if( ! ((CommonTile)currentTile.getTile()).isMajor()){ //Fix
                                isAllMajor = false;
                                break;
                            }
                        tilesFound.addAll(Arrays.asList(currentCombi.getTiles()));
                        nbOfCombination++;
                    }
                }

                if (nbOfCombination == 5 && isAllMajor){
                    IdentifiedPattern pattern = new IdentifiedPattern(this, tilesFound);
                    result.add(pattern);
                }

                return result;
            }
        },

        /**
         * 4 pongs cachés
         */
        FOUR_CONCEALED_PUNGS {
            @Override
            public int getValue() {
                return 64;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                ArrayList<IdentifiedPattern> result = new ArrayList<>();
                Collection<Combination> allCombinations = set.getAllCombinations();
                boolean isConcealed = true;

                int nbOfPungConcealed = 0;
                Collection<GameTile> pungFound = new ArrayList<>();

                for (Combination currentCombi: allCombinations) {
                    if (currentCombi.isPung()){
                        nbOfPungConcealed++;
                        pungFound.addAll(Arrays.asList(currentCombi.getTiles()));
                    }
                }

                if (nbOfPungConcealed == 4){
                    IdentifiedPattern pattern = new IdentifiedPattern(this, pungFound);
                    result.add(pattern);
                }

                return result;
            }
        },
        /**
         * 4 chows identiques dane une famille
         */
        QUADRUPLE_CHOW {
            @Override
            public int getValue() {
                return 48;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                ArrayList<IdentifiedPattern> result = new ArrayList<>();
                Collection<Combination> allCombinations = set.getAllCombinations();
                int nbOfCombination = 0;
                boolean isSame = true;
                Collection<GameTile> chowFound = new ArrayList<>();
                Combination lastCombi = null;

                for (Combination currentCombi: allCombinations) {
                    if (currentCombi.isChow()){
                        if(nbOfCombination > 0){
                            if(!currentCombi.equals(lastCombi)){
                                isSame = false;
                                break;
                            }
                        }
                        nbOfCombination++;
                        chowFound.addAll(Arrays.asList(currentCombi.getTiles()));
                        lastCombi = currentCombi;
                    } else if(nbOfCombination == 4 && currentCombi.isPair()){
                        chowFound.addAll(Arrays.asList(currentCombi.getTiles()));
                        nbOfCombination ++;
                    }
                }

                if (nbOfCombination == 5 && isSame){
                    IdentifiedPattern pattern = new IdentifiedPattern(this, chowFound);
                    result.add(pattern);
                }

                return result;
            }
        },
        /**
         * 4 Pungs consécutifs dans une famille
         */
        FOUR_PURE_SHIFTED_PUNGS {
            @Override
            public int getValue() {
                return 48;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                ArrayList<IdentifiedPattern> result = new ArrayList<>();
                Collection<Combination> allCombinations = set.getAllCombinations();
                int nbOfCombination = 0;
                boolean isNextTile = true;
                Collection<GameTile> pungFound = new ArrayList<>();
                Combination lastCombi = null;

                for (Combination currentCombi: allCombinations) {
                    if (currentCombi.isPung()){
                        if(nbOfCombination > 0){
                            if(!currentCombi.getTiles()[0].getTile().equals(lastCombi.getTiles()[0].getTile().getNext())){
                                isNextTile = false;
                                break;
                            }
                        }
                        nbOfCombination++;
                        pungFound.addAll(Arrays.asList(currentCombi.getTiles()));
                        lastCombi = currentCombi;
                    } else if (nbOfCombination == 4 && currentCombi.isPair()){
                        pungFound.addAll(Arrays.asList(currentCombi.getTiles()));
                        nbOfCombination++;
                    }
                }

                if (nbOfCombination == 5 && isNextTile){
                    IdentifiedPattern pattern = new IdentifiedPattern(this, pungFound);
                    result.add(pattern);
                }

                return result;
            }
        },
        
        FOUR_PURE_SHIFTED_CHOWS {
            @Override
            public int getValue() {
                return 32;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        ALL_TERMINALS_AND_HONORS {
            @Override
            public int getValue() {
                return 32;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        THREE_KONGS {
            @Override
            public int getValue() {
                return 32;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },

        PURE_TRIPLE_CHOW {
            @Override
            public int getValue() {
                return 24;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        ALL_EVEN_PUNGS {
            @Override
            public int getValue() {
                return 24;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        PURE_SHIFTED_PUNGS {
            @Override
            public int getValue() {
                return 24;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        SEVEN_PAIRS {
            @Override
            public int getValue() {
                return 24;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        FULL_FLUSH {
            @Override
            public int getValue() {
                return 24;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        UPPER_TILES {
            @Override
            public int getValue() {
                return 24;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        MIDDLE_TILES {
            @Override
            public int getValue() {
                return 24;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        LOWER_TILES {
            @Override
            public int getValue() {
                return 24;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        GREATER_HONORS_AND_KNITTED_TILES {
            @Override
            public int getValue() {
                return 24;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        PURE_SHIFTED_CHOWS {
            @Override
            public int getValue() {
                return 16;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        PURE_STRAIGHT {
            @Override
            public int getValue() {
                return 16;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        THREE_SUITED_TERMINAL_CHOWS {
            @Override
            public int getValue() {
                return 16;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        TRIPLE_PUNG {
            @Override
            public int getValue() {
                return 16;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        THREE_CONCEALED_PUNGS {
            @Override
            public int getValue() {
                return 16;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        ALL_FIVES {
            @Override
            public int getValue() {
                return 16;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },

        BIG_THREE_WINDS {
            @Override
            public int getValue() {
                return 12;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        KNITTED_STRAIGHT {
            @Override
            public int getValue() {
                return 12;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        UPPER_FOUR {
            @Override
            public int getValue() {
                return 12;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        LOWER_FOUR {
            @Override
            public int getValue() {
                return 12;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        LESSER_HONORS_AND_KNITTED_TILES {
            @Override
            public int getValue() {
                return 12;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        /**
         * 3 Chows avec la même séquence numérique dans 3 familles.
         * ( 3-4-5 dans les 3 familles )
         */
        MIXED_TRIPLE_CHOWS {
            @Override
            public int getValue() {
                return 8;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        /**
         * Une suite (les tuiles de 1 à 9) composée de Chows dansles3 familles.
         * ( 1-2-3 / 4-5-6 / 7-8-9 => dans les 3 familles )
         */
        MIXED_STRAIGHT {
            @Override
            public int getValue() {
                return 8;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        /**
         * 3 Pungs (ou Kongs) dans 3 familles, dont les chiffres forment une séquence croissante de 1
         * ( b1b1b1 - c2c2c2 - d3d3d3 )
         */
        MIXED_SHIFTED_PUNGS {
            @Override
            public int getValue() {
                return 8;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        /**
         * Une main qui inclut 2 Kongs cachés.
         */
        TWO_CONCEALED_KONGS {
            @Override
            public int getValue() {
                return 8;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        /**
         * Finir (faire Mah-Jong) en piochant la dernière tuile du mur. 
         * (Non-cumulable avec le fan «Tirer Soi-même».)
         */
        LAST_TILE_DRAW {
            @Override
            public int getValue() {
                return 8;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        /**
         * Finir (faire Mah-Jong) sur la dernière tuile défaussée de la partie.
         */
        LAST_TILE_CLAIM {
            @Override
            public int getValue() {
                return 8;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        /**
         * Finir (faire Mah-Jong) grâce à la tuile de remplacement d'un Kong (la tuile de remplacement d’une fleur en est exclue). 
         * Si lors du remplacement après un Kong une fleur est piochée, et le remplacement de cette fleur donne la tuile gagnante, on peut compter le Fan «Tirer Soi-même» 
         * (mais pas le «Finir sur Kong»).
         */
        OUT_WITH_REPLACEMENT_TILE {
            @Override
            public int getValue() {
                return 8;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        /**
         * Finir avec la tuile, qu’un autre joueur rajoute à son Pung (pour le transformer en Kong). 
         * (Non-cumulable avec le fan «Dernière Tuile Existante»).
         */
        ROBBING_THE_KONG {
            @Override
            public int getValue() {
                return 8;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        /**
         * Une main composée uniquement des tuiles de symétrie centrale, i.e. 
         * les gravures restent inchangées quand on inverse ces tuiles. 
         * Ce sont les tuiles des 1,2,3,4,5,8 et 9 Cercles, des 2,4,5,6,8 et 9 Bambous et Dragon Blanc.
         */
        REVERSIBLE_TILES {
            @Override
            public int getValue() {
                return 8;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        /**
         * Une main qui ferait 0 point.
         */
        CHICKEN_HAND {
            @Override
            public int getValue() {
                return 8;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        /**
         * 2 pungs ou kong de dragons.
         */
        TWO_DRAGON_PUNGS {
            @Override
            public int getValue() {
                return 6;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        /**
         * 3 Chows dans les 3 familles, avec leurs séquences numériques en décalage égale au précédentde 1.
         * (1-2-3 / 2-3-4 / 3-4-5)
         */
        MIXED_SHIFTED_CHOWS {
            @Override
            public int getValue() {
                return 6;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        /**
         * Une main composée de 4 pungs (ou Kong)
         */
        ALL_PUNGS {
            @Override
            public int getValue() {
                return 6;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        /**
         * Une main composée uniquement de tuiles d’une des 3 familles avec des Honneurs.
         * 1 seule combinaison avec des honneurs.
         */
        HALF_FLUSH {
            @Override
            public int getValue() {
                return 6;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        /**
         * Une main dans laquelle chaque combinaison est issue de tuiles de types différents.
         * (Caractères, Bambous, Cercles, Vents, Dragons)
         */
        ALL_TYPES {
            @Override
            public int getValue() {
                return 6;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        /**
         * Tous les éléments de la main (Chows, Pungs, Kongs et la Paire) doivent tous avoir été achevés en intégrant des tuiles défaussées par les autres joueurs. 
         * Tous les éléments doivent être exposés, et le joueur finit sur la défausse d’un autre joueur en composant La Paire.
         */
        MELDED_HAND {
            @Override
            public int getValue() {
                return 6;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        /**
         * Finir avec 2 Kongs exposés.
         */
        TWO_MELDED_KONGS {
            @Override
            public int getValue() {
                return 4;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        /**
         * Une main qui comporte des tuiles terminales ou des honneurs dans chaque élément, y compris dans la pair.
         */
        OUTSIDE_HAND {
            @Override
            public int getValue() {
                return 4;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        /**
         * Une main entièrement cachée, finie avec une tuile du mur.
         */
        FULLY_CONCEALED_HAND {
            @Override
            public int getValue() {
                return 4;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        /**
         * Finir avec la 4ième tuile quand les 3 autres sont visibles
         * (dans la défausse ou dans les expositions) aux autres joueurs.
         */
        LAST_TILE {
            @Override
            public int getValue() {
                return 4;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        /**
         * Un pung de dragon. Peut être exposé ou caché.
         */
        DRAGON_PUNG {
            @Override
            public int getValue() {
                return 2;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        /**
         * Un pung de vent du tour. Peut être exposé ou caché.
         */
        PREVALENT_WIND {
            @Override
            public int getValue() {
                return 2;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        /**
         * Un pung de vent du joueur. Peut être exposé ou caché.
         */
        SEAT_WIND {
            @Override
            public int getValue() {
                return 2;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        /**
         * Une main composée uniquement de Chows et d'1 pair, sans honneur
         */
        ALL_CHOWS {
            @Override
            public int getValue() {
                return 2;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        /**
         * 2 Pungs composés des mêmes nombres dans deux familles différentes.
         */
        DOUBLE_PUNG {
            @Override
            public int getValue() {
                return 2;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        /**
         * 2 Pungs composés à partir des tuiles du mur et cachés.
         */
        TWO_CONCEALED_PUNGS {
            @Override
            public int getValue() {
                return 2;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        /**
         * Composé des 4 tuiles d’un chiffre ou d’un symbole, toutes piochées et déclaré en Kong
         */
        CONCEALED_KONG {
            @Override
            public int getValue() {
                return 2;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        /**
         * Une main composée sans Extrémités et sans Honneurs.
         */
        ALL_SIMPLES {
            @Override
            public int getValue() {
                return 2;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                ArrayList<IdentifiedPattern> result = new ArrayList<>();
                Collection<Combination> allCombinations = set.getAllCombinations();
                int nbOfCombination = 0;
                Collection<GameTile> tilesFound = new ArrayList<>();
                boolean isAllSimple = true;
                Combination lastCombi;
                
                for (Combination currentCombi: allCombinations) {
                    if (currentCombi.isChow() || currentCombi.isPung() || currentCombi.isKong()){
                        GameTile[] currentTiles = currentCombi.getTiles();
                        for(int i=0; i<currentTiles.length;i++){
                            Pattern pattern = Pattern.compile("(b|c|d)[2-8]");
                            Matcher matcher = pattern.matcher(currentTiles[i].getTile().toNormalizedName());//Fix
                            if(!matcher.matches()){
                                isAllSimple = false;
                                break;
                            }
                        }
                        if(!isAllSimple)break;
                        nbOfCombination++;
                        tilesFound.addAll(Arrays.asList(currentCombi.getTiles()));
                        lastCombi = currentCombi;
                    } else if(nbOfCombination == 4 && currentCombi.isPair()){
                        GameTile[] currentTiles = currentCombi.getTiles();
                        for(int i=0; i<currentTiles.length;i++){
                            Pattern pattern = Pattern.compile("(b|c|d)[2-8]");
                            Matcher matcher = pattern.matcher(currentTiles[i].getTile().toNormalizedName());//Fix
                            if(!matcher.matches()){
                                isAllSimple = false;
                                break;
                            }
                        }
                        if(!isAllSimple)break;
                        tilesFound.addAll(Arrays.asList(currentCombi.getTiles()));
                        nbOfCombination++;
                    }
                }

                if (nbOfCombination == 5 && isAllSimple){
                    IdentifiedPattern pattern = new IdentifiedPattern(this, tilesFound);
                    result.add(pattern);
                }

                return result;
            }
        },
        /**
         * Avoir une main cachée (pas de combinaisons exposées) et sortir sur l'écart d'un joueur
         */
        CONCEALED_HAND {
            @Override
            public int getValue() {
                return 2;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        /**
         * Une main qui inclut les 4 tuiles d’un chiffre ou d’un symbole, sans former un Kong.
         */
        TITLE_HOG {
            @Override
            public int getValue() {
                return 2;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                ArrayList<IdentifiedPattern> result = new ArrayList<>();

                return result;
            }
        },
        /**
         * Deux chows identiques
         */
        PURE_DOUBLE_CHOW {
            @Override
            public int getValue() {
                return 1;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                ArrayList<IdentifiedPattern> result = new ArrayList<>();
                Collection<Combination> allCombinations = set.getAllCombinations();
                int nbOfCombination = 0;
                boolean isSame = true;
                Collection<GameTile> chowFound = new ArrayList<>();
                Combination lastCombi = null;

                for (Combination currentCombi: allCombinations) {
                    if (currentCombi.isChow()){
                        if(nbOfCombination > 0){
                            if(!currentCombi.equals(lastCombi)){
                                isSame = false;
                                break;
                            }
                        }
                        nbOfCombination++;
                        chowFound.addAll(Arrays.asList(currentCombi.getTiles()));
                        lastCombi = currentCombi;
                    } 
                }

                if (nbOfCombination == 2 && isSame){
                    IdentifiedPattern pattern = new IdentifiedPattern(this, chowFound);
                    result.add(pattern);
                }

                return result;
            
            }
        },
        /**
         * Deux chows identiques dans deux familles différentes
         */
        MIXED_DOUBLE_CHOW {
            @Override
            public int getValue() {
                return 1;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                ArrayList<IdentifiedPattern> result = new ArrayList<>();
                Collection<Combination> allCombinations = set.getAllCombinations();
                int nbOfCombination = 0;
                boolean isDifferent = true;
                boolean isSameNumber = false;
                Collection<GameTile> chowFound = new ArrayList<>();
                Combination lastCombi = null;

                for (Combination currentCombi: allCombinations) {
                    if (currentCombi.isChow()){
                        if(nbOfCombination > 0){
                            if(((CommonTile)currentCombi.getTiles()[0].getTile()).getFamily().equals(((CommonTile)lastCombi.getTiles()[0].getTile()).getFamily())){
                                isDifferent = false;
                                break;
                            }
                            if(((CommonTile)currentCombi.getTiles()[0].getTile()).getNumber().equals(((CommonTile)lastCombi.getTiles()[0].getTile()).getNumber())){
                                isSameNumber = true;
                            }
                        }
                        nbOfCombination++;
                        chowFound.addAll(Arrays.asList(currentCombi.getTiles()));
                        lastCombi = currentCombi;
                    } 
                }

                if (nbOfCombination == 2 && isSameNumber && isDifferent){
                    IdentifiedPattern pattern = new IdentifiedPattern(this, chowFound);
                    result.add(pattern);
                }

                return result;
            
            }
        },
        /**
         * Deux chow consécutifs dans une famille (suite de 6 tuiles)
         */
        SHORT_STRAIGHT {
            @Override
            public int getValue() {
                return 1;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                ArrayList<IdentifiedPattern> result = new ArrayList<>();
                Collection<Combination> allCombinations = set.getAllCombinations();
                int nbOfCombination = 0;
                boolean isSame = true;
                Collection<GameTile> chowFound = new ArrayList<>();
                Combination lastCombi = null;

                for (Combination currentCombi: allCombinations) {
                    if (currentCombi.isChow()){
                        if(nbOfCombination > 0){
                            if(!currentCombi.getTiles()[0].getTile().getPrevious().equals(lastCombi.getTiles()[2].getTile())){
                                isSame = false;
                                break;
                            }
                        }
                        nbOfCombination++;
                        chowFound.addAll(Arrays.asList(currentCombi.getTiles()));
                        lastCombi = currentCombi;
                    } 
                }

                if (nbOfCombination == 2 && isSame){
                    IdentifiedPattern pattern = new IdentifiedPattern(this, chowFound);
                    result.add(pattern);
                }

                return result;
            }
        },
        /**
         * 1-2-3 et 7-8-9 dans une famille
         */
        TWO_TERMINAL_CHOWS {
            @Override
            public int getValue() {
                return 1;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                ArrayList<IdentifiedPattern> result = new ArrayList<>();
                Collection<Combination> allCombinations = set.getAllCombinations();
                int nbOfCombination = 0;
                
                Collection<GameTile> chowFound = new ArrayList<>();
                Combination lastCombi = null;

                for (Combination currentCombi: allCombinations) {
                    if (currentCombi.isChow()){
                        if(nbOfCombination == 0){
                            if(((CommonTile)currentCombi.getTiles()[0].getTile()).isMajor()){
                                nbOfCombination++;
                                lastCombi = currentCombi;
                                chowFound.addAll(Arrays.asList(currentCombi.getTiles()));
                            }
                        } else if(nbOfCombination == 1){
                            if(((CommonTile)currentCombi.getTiles()[2].getTile()).isMajor()
                            && ((CommonTile)currentCombi.getTiles()[0].getTile()).getFamily().equals(
                                ((CommonTile)lastCombi.getTiles()[0].getTile()).getFamily())){
                                nbOfCombination ++;
                                chowFound.addAll(Arrays.asList(currentCombi.getTiles()));
                                
                            }
                        } else { break;}    
                    } 
                }

                if (nbOfCombination == 2){
                    IdentifiedPattern pattern = new IdentifiedPattern(this, chowFound);
                    result.add(pattern);
                }

                return result;
            }
        },
        /**
         * 1 pung de 1, de 9 ou de Vent
         */
        PUNG_OF_TERMINALS_OR_HONORS {
            @Override
            public int getValue() {
                return 1;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                ArrayList<IdentifiedPattern> toReturn = new ArrayList<>();
                Collection<Combination> allCombinations = set.getAllCombinations();
                
                
                Collection<GameTile> pungFound = new ArrayList<>();
                
                boolean isFind = false;

                for (Combination currentCombi: allCombinations) {
                    GameTile tile = currentCombi.getTiles()[0];
                    if (currentCombi.isPung() && (
                        ((CommonTile)tile.getTile()).isMajor() ||
                       //tile.getTile().toNormalizedName().equals("Dr") ||
                       //tile.getTile().toNormalizedName().equals("Dg") ||
                       //tile.getTile().toNormalizedName().equals("Dw") ||
                       tile.getTile().toNormalizedName().equals("Wn") ||
                       tile.getTile().toNormalizedName().equals("Ww") ||
                       tile.getTile().toNormalizedName().equals("We") ||
                       tile.getTile().toNormalizedName().equals("Ws")
                    )){
                        isFind = true;
                        pungFound.addAll(Arrays.asList(currentCombi.getTiles()));
                        break;    
                    }
                    
                }
                if (isFind){
                    IdentifiedPattern pattern = new IdentifiedPattern(this, pungFound);
                    toReturn.add(pattern);
                }

                return toReturn;
            }
        },
        /**
        * 1 kong exposé
        */
        MELDED_KONG {
            @Override
            public int getValue() {
                return 1;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                ArrayList<IdentifiedPattern> toReturn = new ArrayList<>();
                Collection<Combination> allCombinations = set.getMelds(); //Fix
                Collection<GameTile> kongFound = new ArrayList<>();
                boolean isFind = false;
                for (Combination currentCombi: allCombinations) {
                    if(currentCombi.isKong()){
                        isFind = true;
                        kongFound.addAll(Arrays.asList(currentCombi.getTiles()));
                        break;  
                    }
                }
                if(isFind){
                    IdentifiedPattern pattern = new IdentifiedPattern(this, kongFound);
                    toReturn.add(pattern);
                }
                
                return toReturn;
            }
        },
        ONE_VOIDED_SUIT {
            @Override
            public int getValue() {
                return 1;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) { //Ajout
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                Collection<Combination> allHand = set.getHand();
                Collection<GameTile> tilesFound = new ArrayList();
                
                for (Combination currentCombi : allHand ){
                    if( currentCombi.isChow() || currentCombi.isPung() || currentCombi.isKong() ){
                        
                    }
                }
                
                return toReturn;
            }
        },
        /**
         * Uniquement des tuiles des familles
         */
        NO_HONORS {
            @Override
            public int getValue() {
                return 1;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                ArrayList<IdentifiedPattern> result = new ArrayList<>();
                //Changement
                Collection<Combination> allCombinations = set.getHand();
                int nbOfCombination = 0;
                Collection<GameTile> tilesFound = new ArrayList<>();
                boolean isAllHog = true;
                Combination lastCombi;
                
                for (Combination currentCombi: allCombinations) {
                    
                    if (currentCombi.isChow() || currentCombi.isPung() || currentCombi.isKong()){
                        GameTile[] currentTiles = currentCombi.getTiles();
                        for(int i=0; i<currentTiles.length;i++){
                            //Changement
                            Pattern pattern = Pattern.compile("(b|c|d)[1-9]");
                            Matcher matcher = pattern.matcher(currentTiles[i].getTile().toNormalizedName());
                            if(!matcher.matches()){
                                isAllHog = false;
                                break;
                            }
                        }
                        
                        if(!isAllHog)break;
                        nbOfCombination++;
                        tilesFound.addAll(Arrays.asList(currentCombi.getTiles()));
                        lastCombi = currentCombi;
                    }//Suppr condition
                }

                if (nbOfCombination == 4 && isAllHog){
                    IdentifiedPattern pattern = new IdentifiedPattern(this, tilesFound);
                    result.add(pattern);
                }

                return result;
            }
        },
        /**
         * Finir sur le 3 pour former 1-2-3 ousur le7 pour former 7-8-9. 
         * Ne peut pas être compté si l’attente s’avère multiple ou si ce Fan est combiné avec d’autres attentes.
         */
        EDGE_WAIT {
            @Override
            public int getValue() {
                return 1;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        /**
         * Finir sur la tuiledont le chiffre est au milieu duChowqui reste à compléter. 
         * Ne peut pas être compté si l’attente s’avère multiple ou si ce Fan est combiné avec d’autres attentes.
         */
        CLOSED_WAIT {
            @Override
            public int getValue() {
                return 1;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                return toReturn;
            }
        },
        /**
         * Attendre uniquement sur la tuile quicomplète la Paire. 
         * Ne peut pas être compté si l’attente s’avère multiple 
         * (par ex. la main contient 1-2-3-4 et le 1 et le 4 permettent de finir).
         */
        SINGLE_WAIT {
            @Override
            public int getValue() {
                return 1;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<IdentifiedPattern>();
                GameTile lastTile = set.getWinningTile();
                Collection <Combination> allHand = set.getHand();
                
                if(lastTile != null){
                    for (Combination combination : allHand) {
                        if(combination.isPair() && Arrays.asList(combination.getTiles()).contains(lastTile)){
                            Collection<GameTile> pair = new ArrayList<>(Arrays.asList(combination.getTiles())); 
                            IdentifiedPattern pattern = new IdentifiedPattern(this, pair);
                            toReturn.add(pattern);
                        }
                    }
                } //error: incompatible types: inferred type does not conform to upper bound(s)
                return toReturn;
            }
        },
        /**
         * Sortir avec une tuile poichée du mur
         */
        SELF_DRAW {
            @Override
            public int getValue() {
                return 1;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                //Ajout
                Collection<IdentifiedPattern> toReturn = new ArrayList<IdentifiedPattern>();
                Collection<Combination> allHand = set.getHand();
                
                if( set.isDrawnForWall() ){
                    ArrayList<GameTile> winTile = new ArrayList();
                    winTile.add(set.getWinningTile());
                    IdentifiedPattern pattern = new IdentifiedPattern(this, winTile);
                    toReturn.add(pattern);
                }
                return toReturn;
            }
        },
        /**
         * Fleur ou saison
         */
        FLOWER_TILES {
            @Override
            public int getValue() {
                return 1;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                Collection<IdentifiedPattern> toReturn = new ArrayList<>();
                Collection<GameTile> honorCollection = set.getSupremeHonors();
                
                IdentifiedPattern pattern = new IdentifiedPattern(this, honorCollection);
                toReturn.add(pattern);
                return toReturn;
            }
        }
    }
}
