package fr.univubs.inf1603.mahjong.engine;

import java.util.HashMap;

/**
 * Implementation of the {@link PatternIdentifier} interface used to
 * identify all the patterns in the international ruleset.
 * It contains the enumeration of all the possible patterns
 * @author Anton Cosnefroy
 */
class InternationalPatternIdentifer implements PatternIdentifier {

    /**
     * The enumeration of all the possible patterns in the international ruleset.
     */
    public enum InternationalPattern implements AbstractPattern {

        BIG_FOUR_WINDS      (88, getBigFourWinds),
        BIG_THREE_DRAGONS   (88, getBigThreeDragon),
        FOUR_KONGS          (88, getFourKong),
        SEVEN_SHIFTED_PAIRS (88, getSevenShiftedPair),
        ALL_GREEN           (88, getAllGreen),
        NINE_GATES          (88, getNineGate),
        THIRTEEN_ORPHANS    (88, getThirteenOrphans),

        LITTLE_FOUR_WINDS   (64, getLittleFourWinds),
        LITTLE_THREE_DRAGONS(64, getLittleThreeDragon),
        ALL_HONORS          (64, getAllHonor),
        PURE_TERMINAL_CHOWS (64, getPureTerminalChows),
        ALL_TERMINALS       (64, getAllTerminals),
        FOUR_CONCEALED_PUNGS(64, getFourConcealedPungs),

        QUADRUPLE_CHOW          (48, getQuadrupleChows),
        FOUR_PURE_SHIFTED_PUNGS (48, getFourPureShiftedPungs),

        FOUR_PURE_SHIFTED_CHOWS (32, getFourPureShiftedChows),
        ALL_TERMINALS_AND_HONORS(32, getAllTerminalAndHonors),
        THREE_KONGS             (32, getThreeKongs),

        PURE_TRIPLE_CHOW                (24, getPureTripleChows),
        ALL_EVEN_PUNGS                  (24, getAllEvenPungs),
        PURE_SHIFTED_PUNGS              (24, getPureShiftedPungs),
        SEVEN_PAIRS                     (24, getSevenPairs),
        FULL_FLUSH                      (24, getFullFlush),
        UPPER_TILES                     (24, getUpperTiles),
        MIDDLE_TILES                    (24, getMiddleTiles),
        LOWER_TILES                     (24, getLowerTiles),
        GREATER_HONORS_AND_KNITTED_TILES(24, getGreaterHonorsAndKnittedTiles),

        PURE_SHIFTED_CHOWS          (16, getPureShiftedChows),
        PURE_STRAIGHT               (16, getPureStraight),
        THREE_SUITED_TERMINAL_CHOWS (16, getThreeSuitedTerminalChows),
        TRIPLE_PUNG                 (16, getTriplePung),
        THREE_CONCEALED_PUNGS       (16, getThreeConcealedPungs),
        ALL_FIVES                   (16, getAllFives),

        BIG_THREE_WINDS                 (12, getBigThreeWinds),
        KNITTED_STRAIGHT                (12, getKnittedStraight),
        UPPER_FOUR                      (12, getUpperFour),
        LOWER_FOUR                      (12, getLowerFour),
        LESSER_HONORS_AND_KNITTED_TILES (12, getLesserHonorsAndKnittedTiles),

        MIXED_TRIPLE_CHOWS          (8, getMixedTripleChows),
        MIXED_STRAIGHT              (8, getMixedStraight),
        MIXED_SHIFTED_PUNGS         (8, getMixedShiftedPungs),
        TWO_CONCEALED_KONGS         (8, getTwoConcealedKongs),
        LAST_TILE_DRAW              (8, getLastTileDraw),
        LAST_TILE_CLAIM             (8, getLastTileClaim),
        OUT_WITH_REPLACEMENT_TILE   (8, getOutWithReplacementTile),
        ROBBING_THE_KONG            (8, getRobbingTheKong),
        REVERSIBLE_TILES            (8, getReversibleTiles),
        CHICKEN_HAND                (8, getChickenHand),

        TWO_DRAGON_PUNGS    (6, getTwoDragonPungs),
        MIXED_SHIFTED_CHOWS (6, getMixedShiftedChows),
        ALL_PUNGS           (6, getAllPungs),
        HALF_FLUSH          (6, getHalfFlush),
        ALL_TYPES           (6, getAllTypes),
        MELDED_HAND         (6, getMeldedHand),

        TWO_MELDED_KONGS        (4, getTwoMeldedKongs),
        OUTSIDE_HAND            (4, getOutsideHand),
        FULLY_CONCEALED_HAND    (4, getFullyConcealedHand),
        LAST_TILE               (4, getLastTile),

        DRAGON_PUNG         (2, getDragonPung),
        PREVALENT_WIND      (2, getPrevalentWind),
        SEAT_WIND           (2, getSeatWind),
        ALL_CHOWS           (2, getAllChows),
        DOUBLE_PUNG         (2, getDoublePung),
        TWO_CONCEALED_PUNGS (2, getTwoConcealedPungs),
        CONCEALED_KONG      (2, getConcealedKong),
        ALL_SIMPLES         (2, getAllSimples),
        CONCEALED_HAND      (2, getConcealedHand),
        TITLE_HOG           (2, getTitleHog),

        PURE_DOUBLE_CHOW            (1, getPureDoubleChow),
        MIXED_DOUBLE_CHOW           (1, getMixedDoubleChow),
        SHORT_STRAIGHT              (1, getShortStraight),
        TWO_TERMINAL_CHOWS          (1, getTwoTerminalChows),
        PUNG_OF_TERMINALS_OR_HONORS (1, getPungOfTerminalsOrHonors),
        MELDED_KONG                 (1, getMeldedKong),
        ONE_VOIDED_SUIT             (1, getOneVoidedSuit),
        NO_HONORS                   (1, getNoHonors),
        EDGE_WAIT                   (1, getEdgeWait),
        CLOSED_WAIT                 (1, getClosedWait),
        SINGLE_WAIT                 (1, getSingleWait),
        SELF_DRAW                   (1, getSelfDraw),
        FLOWER_TILES                (1, getFlowerTiles);

        /**
         * Number of point a pattern is worth
         */
        private final int value;
        /**
         * {@link PatternIdentifier} implementation used to identify a specific pattern.
         * This field could be implemented directly instead of using static Objects but
         * would make the file rather unreadable
         */
        private final PatternIdentifier identifier;

        InternationalPattern(int value, PatternIdentifier identifier) {
            this.value = value;
            this.identifier = identifier;
        }

        @Override
        public int getValue() {
            return this.value;
        }

        @Override
        public PatternIdentifier getIdentifier() {
            return this.identifier;
        }

    }

    /**
     * Identifier used to look for the {@link InternationalPattern#BIG_FOUR_WINDS BIG_FOUR_WINDS} pattern
     */
    private static PatternIdentifier getBigFourWinds = new PatternIdentifier() {
        @Override
        public HashMap<AbstractPattern, AbstractTile> identify(HandSituation situation) {
            //TODO: translate from python identifan.py file
            return null;
        }
    };

    //TODO: add the remaining identifiers as private static PatternIdentifier anonymous

    @Override
    public HashMap<AbstractPattern, AbstractTile> identify(HandSituation situation) {
        HashMap<AbstractPattern, AbstractTile> identifiedCombinations = null;

        for (InternationalPattern pattern: InternationalPattern.values()) {
            identifiedCombinations.putAll(pattern.getIdentifier().identify(situation));
        }

        return identifiedCombinations;
    }
}
