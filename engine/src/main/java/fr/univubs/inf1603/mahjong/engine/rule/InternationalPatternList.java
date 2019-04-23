package fr.univubs.inf1603.mahjong.engine.rule;

import fr.univubs.inf1603.mahjong.engine.game.GameTile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Implementation of the {@link AbstractPatternList} for the international ruleset.
 * This class behaves like a singleton
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

        BIG_FOUR_WINDS {
            @Override
            public int getValue() {
                return 88;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        BIG_THREE_DRAGONS {
            @Override
            public int getValue() {
                return 88;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        FOUR_KONGS {
            @Override
            public int getValue() {
                return 88;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                ArrayList<IdentifiedPattern> result = new ArrayList<>();
                Collection<Combination> allCombinations = set.getAllCombinations();

                int nbOfKong = 0;
                Collection<GameTile> kongsFound = new ArrayList<>();

                for (Combination currentCombi: allCombinations) {
                    if (currentCombi.isKong()){
                        nbOfKong++;
                        kongsFound.addAll(Arrays.asList(currentCombi.getTiles()));
                    }
                }

                if (nbOfKong == 4){
                    IdentifiedPattern pattern = new IdentifiedPattern(this, kongsFound);
                    result.add(pattern);
                }

                return result;
            }
        },
        SEVEN_SHIFTED_PAIRS {
            @Override
            public int getValue() {
                return 88;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        ALL_GREEN {
            @Override
            public int getValue() {
                return 88;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        NINE_GATES {
            @Override
            public int getValue() {
                return 88;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        THIRTEEN_ORPHANS {
            @Override
            public int getValue() {
                return 88;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        LITTLE_FOUR_WINDS {
            @Override
            public int getValue() {
                return 64;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        LITTLE_THREE_DRAGONS {
            @Override
            public int getValue() {
                return 64;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        ALL_HONORS {
            @Override
            public int getValue() {
                return 64;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        PURE_TERMINAL_CHOWS {
            @Override
            public int getValue() {
                return 64;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        ALL_TERMINALS {
            @Override
            public int getValue() {
                return 64;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        FOUR_CONCEALED_PUNGS {
            @Override
            public int getValue() {
                return 64;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        QUADRUPLE_CHOW {
            @Override
            public int getValue() {
                return 48;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        FOUR_PURE_SHIFTED_PUNGS {
            @Override
            public int getValue() {
                return 48;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        FOUR_PURE_SHIFTED_CHOWS {
            @Override
            public int getValue() {
                return 32;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        ALL_TERMINALS_AND_HONORS {
            @Override
            public int getValue() {
                return 32;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        THREE_KONGS {
            @Override
            public int getValue() {
                return 32;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },

        PURE_TRIPLE_CHOW {
            @Override
            public int getValue() {
                return 24;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        ALL_EVEN_PUNGS {
            @Override
            public int getValue() {
                return 24;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        PURE_SHIFTED_PUNGS {
            @Override
            public int getValue() {
                return 24;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        SEVEN_PAIRS {
            @Override
            public int getValue() {
                return 24;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        FULL_FLUSH {
            @Override
            public int getValue() {
                return 24;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        UPPER_TILES {
            @Override
            public int getValue() {
                return 24;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        MIDDLE_TILES {
            @Override
            public int getValue() {
                return 24;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        LOWER_TILES {
            @Override
            public int getValue() {
                return 24;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        GREATER_HONORS_AND_KNITTED_TILES {
            @Override
            public int getValue() {
                return 24;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        PURE_SHIFTED_CHOWS {
            @Override
            public int getValue() {
                return 16;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        PURE_STRAIGHT {
            @Override
            public int getValue() {
                return 16;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        THREE_SUITED_TERMINAL_CHOWS {
            @Override
            public int getValue() {
                return 16;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        TRIPLE_PUNG {
            @Override
            public int getValue() {
                return 16;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        THREE_CONCEALED_PUNGS {
            @Override
            public int getValue() {
                return 16;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        ALL_FIVES {
            @Override
            public int getValue() {
                return 16;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },

        BIG_THREE_WINDS {
            @Override
            public int getValue() {
                return 12;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        KNITTED_STRAIGHT {
            @Override
            public int getValue() {
                return 12;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        UPPER_FOUR {
            @Override
            public int getValue() {
                return 12;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        LOWER_FOUR {
            @Override
            public int getValue() {
                return 12;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        LESSER_HONORS_AND_KNITTED_TILES {
            @Override
            public int getValue() {
                return 12;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        MIXED_TRIPLE_CHOWS {
            @Override
            public int getValue() {
                return 8;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        MIXED_STRAIGHT {
            @Override
            public int getValue() {
                return 8;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        MIXED_SHIFTED_PUNGS {
            @Override
            public int getValue() {
                return 8;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        TWO_CONCEALED_KONGS {
            @Override
            public int getValue() {
                return 8;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        LAST_TILE_DRAW {
            @Override
            public int getValue() {
                return 8;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        LAST_TILE_CLAIM {
            @Override
            public int getValue() {
                return 8;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        OUT_WITH_REPLACEMENT_TILE {
            @Override
            public int getValue() {
                return 8;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        ROBBING_THE_KONG {
            @Override
            public int getValue() {
                return 8;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        REVERSIBLE_TILES {
            @Override
            public int getValue() {
                return 8;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        CHICKEN_HAND {
            @Override
            public int getValue() {
                return 8;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        TWO_DRAGON_PUNGS {
            @Override
            public int getValue() {
                return 6;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        MIXED_SHIFTED_CHOWS {
            @Override
            public int getValue() {
                return 6;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        ALL_PUNGS {
            @Override
            public int getValue() {
                return 6;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        HALF_FLUSH {
            @Override
            public int getValue() {
                return 6;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        ALL_TYPES {
            @Override
            public int getValue() {
                return 6;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        MELDED_HAND {
            @Override
            public int getValue() {
                return 6;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },

        TWO_MELDED_KONGS {
            @Override
            public int getValue() {
                return 4;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        OUTSIDE_HAND {
            @Override
            public int getValue() {
                return 4;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        FULLY_CONCEALED_HAND {
            @Override
            public int getValue() {
                return 4;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        LAST_TILE {
            @Override
            public int getValue() {
                return 4;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        DRAGON_PUNG {
            @Override
            public int getValue() {
                return 2;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        PREVALENT_WIND {
            @Override
            public int getValue() {
                return 2;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        SEAT_WIND {
            @Override
            public int getValue() {
                return 2;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        ALL_CHOWS {
            @Override
            public int getValue() {
                return 2;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        DOUBLE_PUNG {
            @Override
            public int getValue() {
                return 2;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        TWO_CONCEALED_PUNGS {
            @Override
            public int getValue() {
                return 2;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        CONCEALED_KONG {
            @Override
            public int getValue() {
                return 2;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        ALL_SIMPLES {
            @Override
            public int getValue() {
                return 2;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        CONCEALED_HAND {
            @Override
            public int getValue() {
                return 2;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        TITLE_HOG {
            @Override
            public int getValue() {
                return 2;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        PURE_DOUBLE_CHOW {
            @Override
            public int getValue() {
                return 1;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        MIXED_DOUBLE_CHOW {
            @Override
            public int getValue() {
                return 1;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        SHORT_STRAIGHT {
            @Override
            public int getValue() {
                return 1;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        TWO_TERMINAL_CHOWS {
            @Override
            public int getValue() {
                return 1;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        PUNG_OF_TERMINALS_OR_HONORS {
            @Override
            public int getValue() {
                return 1;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        MELDED_KONG {
            @Override
            public int getValue() {
                return 1;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        ONE_VOIDED_SUIT {
            @Override
            public int getValue() {
                return 1;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        NO_HONORS {
            @Override
            public int getValue() {
                return 1;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        EDGE_WAIT {
            @Override
            public int getValue() {
                return 1;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        CLOSED_WAIT {
            @Override
            public int getValue() {
                return 1;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        SINGLE_WAIT {
            @Override
            public int getValue() {
                return 1;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        SELF_DRAW {
            @Override
            public int getValue() {
                return 1;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        },
        FLOWER_TILES {
            @Override
            public int getValue() {
                return 1;
            }

            @Override
            public Collection<IdentifiedPattern> identify(PlayerSet set) {
                return null;
            }
        }
    }
}
