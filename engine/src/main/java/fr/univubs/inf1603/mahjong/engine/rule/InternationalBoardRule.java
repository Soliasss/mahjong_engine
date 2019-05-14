package fr.univubs.inf1603.mahjong.engine.rule;

import fr.univubs.inf1603.mahjong.Wind;
import fr.univubs.inf1603.mahjong.engine.game.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * International Board Rule Manage The Board
 * Les règles international de plateau sont inscrites ici.
 */
public class InternationalBoardRule implements BoardRule {

    private int firstDieRoll = 0;

    public InternationalBoardRule() {
        this.firstDieRoll = 0;
    }

    @Override
    public Wind[] getPlayerOrder() {
        Wind[] internationalOrder = {Wind.EAST, Wind.NORTH, Wind.WEST, Wind.SOUTH};
        Wind[] playerOrder = new Wind[4];
        Random randomGenerator = new Random();
        int a = randomGenerator.nextInt(3);
        int b = 0;
        while (a < 4) {
            playerOrder[a] = internationalOrder[b];
            a++;
            b++;
        }
        a = 0;
        while (b < 4) {
            playerOrder[a] = internationalOrder[b];
            a++;
            b++;
        }
        return playerOrder;
    }

    /**
     * Retourne le vent ou se trouve la breche
     *
     * @return Le vent ou se trouve la breche
     */
    public Wind chooseWindWall() {
        Wind ret = null;
        Random randomGenerator = new Random();
        int random = randomGenerator.nextInt(11) + 2;
        this.firstDieRoll = random;
        switch (random) {
            case 5:
            case 9:
                ret = Wind.EAST;
                break;
            case 2:
            case 6:
            case 10:
                ret = Wind.SOUTH;
                break;
            case 3:
            case 7:
            case 11:
                ret = Wind.WEST;
                break;
            case 4:
            case 8:
            case 12:
                ret = Wind.NORTH;
        }
        return ret;
    }

    /**
     * Retourne l'index ou se trouve la breche
     *
     * @return L'index ou se trouve la breche
     */
    public int chooseStartingHeap() {
        Random randomGenerator = new Random();
        int random = randomGenerator.nextInt(11) + 2;
        int startingHeap = random + this.firstDieRoll;
        return startingHeap;
    }

    @Override
    public StartingWall buildWall() {
        ArrayDeque<GameTile> listTile = new ArrayDeque<GameTile>();

        ArrayList<AbstractTile> allTiles = new ArrayList<AbstractTile>();

        for (InternationalTiles a : InternationalTiles.values()) {
            if (!(a.getTile() instanceof SupremeHonor)) {
                for (int i = 0; i < 4; i++) {
                    allTiles.add(a.getTile());
                }
            } else {
                allTiles.add(a.getTile());
            }
        }
        int i = 0;
        while (!allTiles.isEmpty() && i < 144) {
            Random randomGenerator = new Random();
            int random = randomGenerator.nextInt(allTiles.size());
            listTile.add(new GameTile(i, allTiles.get(random)));
            allTiles.remove(random);
            i++;
        }
        try {
            if (i < 143) throw new RulesException("All the tiles are not defined");
        } catch (RulesException ex) {
            Logger.getLogger(InternationalBoardRule.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new StartingWall(this.chooseWindWall(), this.chooseStartingHeap(), listTile);
    }

    @Override
    public MahjongBoard distributeTiles(StartingWall startingWall) {
        MahjongBoard board = new MahjongBoard(startingWall.getStartingSide());
        try {
            board.getTileZone(TileZoneIdentifier.Wall).getTiles().addAll(startingWall.getCut());
        } catch (GameException ex) {
            Logger.getLogger(InternationalBoardRule.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Wind wind : Wind.values()) {//This is the draw moves they draw 13 tiles for each player in one swipe
            try {
                HashMap<Integer, TileZoneIdentifier> path = new HashMap<Integer, TileZoneIdentifier>();
                for (int j = 0; j < 13; j++) {
                    Integer idGameTile = board.getTileZone(TileZoneIdentifier.Wall).getTiles().get(j).getGameID();
                    path.put(idGameTile, TileZoneIdentifier.getIdentifierFromNormalizedName("Hand" + wind.getName()));
                }
                HashMap<Integer, Boolean> visible = new HashMap<Integer, Boolean>();
                for (Integer inte : path.keySet()) {
                    visible.put(inte, false);
                }
                board.applyMove(new Move(wind, 0, path, visible));
            } catch (GameException e) {
                Logger.getLogger(InternationalBoardRule.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        //Pioche la 14 tuile du joueur placer a l EST
        try {
            HashMap<Integer, TileZoneIdentifier> path = new HashMap<>();
            Integer idGameTile;
            idGameTile = board.getTileZone(TileZoneIdentifier.Wall).getTiles().get(0).getGameID();
            path.put(idGameTile, TileZoneIdentifier.HandEast);
            HashMap<Integer, Boolean> visible = new HashMap<Integer, Boolean>();
            for (Integer inte : path.keySet()) {
                visible.put(inte, false);
            }
            board.applyMove(new Move(Wind.EAST, 0, path, visible));
        } catch (GameException ex) {
            Logger.getLogger(InternationalBoardRule.class.getName()).log(Level.SEVERE, null, ex);
        }
        return board;
    }

    @Override
    public boolean isMoveValid(MahjongBoard board, Move lastMove, Move move) {
        return findValidMoves(board, lastMove).get(move.getWind()).contains(move) ||
                (move.getPriority() == -1 && move.getPath().isEmpty() && move.getPubliclyVisible().isEmpty());
    }

    /**
     * Recherche la premiere zone de meld disponible d'un joueur
     *
     * @param board Le board du jeu
     * @param wind  Le vent du joueur dont on veut vérifier les zones de meld disponible
     * @return Un TileZoneIdentifier representant la zone disponible
     * @throws GameException
     */
    private TileZoneIdentifier getMeldAvailable(MahjongBoard board, Wind wind) throws GameException {
        TileZoneIdentifier tzi = null;

        if (board.getTileZone("Meld" + wind.getName() + "0").getTiles().isEmpty()) {
            tzi = board.getTileZone("Meld" + wind.getName() + "0").getIdentifier();
        } else if (board.getTileZone("Meld" + wind.getName() + "1").getTiles().isEmpty()) {
            tzi = board.getTileZone("Meld" + wind.getName() + "1").getIdentifier();
        } else if (board.getTileZone("Meld" + wind.getName() + "2").getTiles().isEmpty()) {
            tzi = board.getTileZone("Meld" + wind.getName() + "2").getIdentifier();
        } else if (board.getTileZone("Meld" + wind.getName() + "3").getTiles().isEmpty()) {
            tzi = board.getTileZone("Meld" + wind.getName() + "3").getIdentifier();
        } else {
            throw new GameException("It should already be a Majhong");
        }
        return tzi;
    }

    /**
     * Renvoie tous les mouvements de defausse possible
     *
     * @param board Le board de la partie
     * @param wind  Le vent du joueur dont on veut trouver les defausse possibles
     * @return Une liste vide si pas de tuile dans la main ou contenant les mouvements de defausse possible
     * @throws GameException
     */
    public ArrayList<Move> possibleDiscardMoves(MahjongBoard board, Wind wind) throws GameException {
        ArrayList<Move> moves = new ArrayList<>();
        TileZoneIdentifier tziDiscard = board.getTileZone("Discard" + wind.getName()).getIdentifier();
        HashMap<Integer, TileZoneIdentifier> path = new HashMap<>();
        HashMap<Integer, Boolean> visible = new HashMap<>();

        for (GameTileInterface gti : board.getTileZone("Hand" + wind.getName()).getTiles()) {
            path.clear();
            visible.clear();

            path.put(gti.getGameID(), tziDiscard);
            visible.put(gti.getGameID(), true);

            moves.add(new Move(wind, 0, path, visible));
        }
        return moves;
    }

    /**
     * Verifie si un joueur peut faire une combinaison de 3-4 tuiles
     *
     * @param board Le board de la partie
     * @param wind  Le vent du joueur dont on veut verifier les moves possibles
     * @return Une liste vide si pas de movements ou contenant les Move possibles
     * @throws GameException
     */
    public ArrayList<Move> possibleMeldMoves(MahjongBoard board, Wind wind) throws GameException {
        Combination combi;
        AbstractCombinationFactory factory = new InternationalCombinationFactory();
        ArrayList<Move> moves = new ArrayList<>();

        TileZoneIdentifier tzi = getMeldAvailable(board, wind);

        Collection<GameTileInterface> hand = board.getTileZone("Hand" + wind.getName()).getTiles();

        for (GameTileInterface tile1 : hand) {
            for (GameTileInterface tile2 : hand) {
                for (GameTileInterface tile3 : hand) {
                    try {
                        combi = factory.newCombination(tile1, tile2, tile3);

                        if (combi.isPung() || combi.isChow()) {

                            for (GameTileInterface tile : board.getTileZone("Hand" + wind.getName()).getTiles()) {
                                if (tile != tile1 && tile != tile2 && tile != tile3) {
                                    Move meldMove = newMultipleTileMove(wind, 1, tzi, tile1, tile2, tile3);
                                    addDiscardToMove(wind, meldMove, tile);
                                    moves.add(new Move(meldMove));
                                }
                            }
                        }

                        for (GameTileInterface tile4 : hand) {
                            combi = factory.newCombination(tile1, tile2, tile3, tile4);
                            if (combi.isKong()) {

                                Move kongMove = newMultipleTileMove(wind, 0, tzi, tile1, tile2, tile3, tile4);

                                addDrawToMove(wind, getLastTileID(board), kongMove);

                                moves.add(new Move(kongMove));
                            }
                        }
                    } catch (RulesException ignored) {
                    }
                }
            }
        }
        return moves;
    }

    /**
     * Donne les pung et kong faisables en volant une tuile defaussee
     *
     * @param board    Le board du game
     * @param wind     Le vent du joueur dont on veut verifier les vols possibles
     * @param gameTile La tuile defaussee
     * @param tiles    La main du joueur dont on veut verifier les vols possibles
     * @return Une liste de Move vide si pas de mouvement ou contenant les moves de vols possibles
     * @throws GameException
     */
    private ArrayList<Move> possiblePungSteal(MahjongBoard board, Wind wind, GameTileInterface gameTile,
                                              Collection<GameTileInterface> tiles) throws GameException {
        Combination combi;
        AbstractCombinationFactory factory = new InternationalCombinationFactory();
        TileZoneIdentifier tzi = getMeldAvailable(board, wind);
        ArrayList<Move> movesSteal = new ArrayList<>();
        for (GameTileInterface tile1 : tiles) {
            for (GameTileInterface tile2 : tiles) {
                try {
                    combi = factory.newCombination(tile1, tile2, gameTile);
                    if (combi.isPung()) {

                        Move pungStealed;
                        for (GameTileInterface tile : board.getTileZone("Hand" + wind.getName()).getTiles()) {
                            if (tile != tile1 && tile != tile2 && tile != gameTile) {
                                pungStealed = newMultipleTileMove(wind, 1, tzi, tile1, tile2, gameTile);

                                addDiscardToMove(wind, pungStealed, tile);
                                movesSteal.add(pungStealed);
                            }
                        }
                    }

                    for (GameTileInterface tile3 : tiles) {
                        combi = factory.newCombination(tile1, tile2, tile3, gameTile);
                        if (combi.isKong()) {
                            Move kongStealed;

                            kongStealed = newMultipleTileMove(wind, 0, tzi, tile1, tile2, tile3, gameTile);

                            addDrawToMove(wind, getLastTileID(board), kongStealed);

                            movesSteal.add(new Move(kongStealed));
                        }
                    }

                } catch (RulesException ignored) {
                }

            }
        }
        return movesSteal;
    }

    /**
     * Donne les chow faisables en volant une tuile defaussee
     *
     * @param board    Le board du game
     * @param wind     Le vent du joueur dont on veut verifier les vols possibles
     * @param gameTile La tuile defaussee
     * @param tiles    La main du joueur dont on veut verifier les vols possibles
     * @return Une liste de Move vide si pas de mouvement ou contenant les moves de vols possibles
     * @throws GameException
     */
    private ArrayList<Move> possibleChowSteal(MahjongBoard board, Wind wind, GameTileInterface
            gameTile, Collection<GameTileInterface> tiles) throws GameException {
        Combination combi = null;
        AbstractCombinationFactory factory = new InternationalCombinationFactory();
        TileZoneIdentifier tzi = getMeldAvailable(board, wind);
        ArrayList<Move> movesSteal = new ArrayList<>();
        for (GameTileInterface tile1 : tiles) {
            for (GameTileInterface tile2 : tiles) {

                try {
                    combi = factory.newCombination(tile1, tile2, gameTile);
                } catch (RulesException e1) {
                    try {
                        combi = factory.newCombination(tile1, gameTile, tile2);
                    } catch (RulesException e2) {
                        try {
                            combi = factory.newCombination(gameTile, tile1, tile2);
                        } catch (RulesException ignored) {
                        }
                    }
                }
                if (combi != null && combi.isChow()) {
                    Move chowSteal;

                    for (GameTileInterface tile : board.getTileZone("Hand" + wind.getName()).getTiles()) {
                        if (tile != tile1 && tile != tile2 && tile != gameTile) {
                            chowSteal = newMultipleTileMove(wind, 2, tzi, tile1, tile2, gameTile);

                            addDiscardToMove(wind, chowSteal, tile);
                            movesSteal.add(chowSteal);
                        }
                    }


                }

            }
        }
        return movesSteal;
    }

    private Move newMultipleTileMove(Wind wind, int priority, TileZoneIdentifier tzi, GameTileInterface... gameTiles) {
        HashMap<Integer, TileZoneIdentifier> path = new HashMap<>();
        HashMap<Integer, Boolean> visible = new HashMap<>();
        for (GameTileInterface tile : gameTiles) {
            path.put(tile.getGameID(), tzi);
            visible.put(tile.getGameID(), true);
        }

        return new Move(wind, priority, path, visible);
    }

    private void addDrawToMove(Wind wind, int tileID, Move kongMove) throws GameException {
        kongMove.getPath().put(tileID, TileZoneIdentifier.getIdentifierFromNormalizedName("Hand" + wind.getName()));
        kongMove.getPubliclyVisible().put(tileID, false);
    }

    private void addDiscardToMove(Wind wind, Move chowSteal, GameTileInterface... tiles) throws ZoneException {
        for (GameTileInterface tile : tiles) {
            chowSteal.getPath().put(tile.getGameID(), TileZoneIdentifier.getIdentifierFromNormalizedName("Discard" + wind.getName()));
            chowSteal.getPubliclyVisible().put(tile.getGameID(), true);
        }
    }

    private int getFirstTileID(Board board) {
        int gameID = 0;

        try {
            gameID = board.getTileZone(TileZoneIdentifier.Wall).getTiles().get(0).getGameID();
        } catch (GameException ignored) {
        }

        return gameID;
    }

    private int getLastTileID(Board board) {
        int gameID = 0;

        try {
            int boardSize = board.getTileZone(TileZoneIdentifier.Wall).getTiles().size();
            gameID = board.getTileZone(TileZoneIdentifier.Wall).getTiles().get(boardSize - 1).getGameID();
        } catch (GameException ignored) {
        }

        return gameID;
    }

    @Override
    public EnumMap<Wind, Collection<Move>> findValidMoves(MahjongBoard board, Move lastMove) {
        EnumMap<Wind, Collection<Move>> windMoves = new EnumMap<>(Wind.class);
        for (Wind w : Wind.values())
            windMoves.put(w, new HashSet<>());

        HashMap<Integer, TileZoneIdentifier> currPath = new HashMap<>();
        HashMap<Integer, Boolean> currVisibility = new HashMap<>();
        Collection<Move> currMoves = new HashSet<>();
        Collection<GameTileInterface> currHand = new HashSet<>();

        if (lastMove == null) {
            /* this is the first move of the game */
            Wind drawWind = Wind.EAST;

            int firstWallTileID = getFirstTileID(board);
            currPath.put(firstWallTileID, TileZoneIdentifier.HandEast);
            currVisibility.put(firstWallTileID, false);

            currMoves.add(new Move(drawWind, 0, currPath, currVisibility));
            windMoves.put(drawWind, currMoves);

        } else {
            lastMove.getPath().forEach((gameID, zoneIdentifier) -> {
                currMoves.clear();
                currHand.clear();

                /* we check what the last move is */
                switch (zoneIdentifier) {

                    /* draw move */
                    case HandEast:
                    case HandSouth:
                    case HandWest:
                    case HandNorth: {
                        Wind drawnWind = lastMove.getWind();
                        try {
                            /* the player can discard a tile from their hand */
                            currMoves.addAll(possibleDiscardMoves(board, drawnWind));

                            /* or play a meld from their hand */
                            currMoves.addAll(possibleMeldMoves(board, drawnWind));

                        } catch (GameException e) {
                            Logger.getLogger(InternationalBoardRule.class.getName()).log(Level.SEVERE, null, e);
                        }

                        windMoves.get(drawnWind).addAll(currMoves);

                        break;
                    }

                    /* discard move */
                    case DiscardEast:
                    case DiscardNorth:
                    case DiscardWest:
                    case DiscardSouth: {
                        /*
                         * the next players can use the discarded tile to create a pung or a kong
                         * additionally, the very next player can also use it to create a chow or
                         * can draw from the wall if no-one claims the discarded tile.
                         */
                        try {
                            Wind chowStealWind = Wind.values()[(lastMove.getWind().ordinal() + 1) % Wind.values().length];

                            /* chowStealWind can steal for a chow */
                            currHand.addAll(board.getTileZone("Hand" + chowStealWind.getName()).getTiles());
                            currMoves.addAll(possibleChowSteal(board, chowStealWind, board.getTile(gameID), currHand));

                            /* chowStealWind can draw from the wall */
                            int firstWallTileID = getFirstTileID(board);
                            currPath.clear();
                            currVisibility.clear();

                            currPath.put(firstWallTileID, TileZoneIdentifier.getIdentifierFromNormalizedName("Hand" + chowStealWind.getName()));
                            currVisibility.put(firstWallTileID, false);

                            currMoves.add(new Move(chowStealWind, 3, currPath, currVisibility));

                            /* adding all moves for chowStealWind */
                            windMoves.get(chowStealWind).addAll(currMoves);

                            /* all the winds can steal for a pung/kong */
                            for (Wind pungStealWind : Wind.values()) {
                                if (pungStealWind != lastMove.getWind()) {
                                    currHand.clear();
                                    currMoves.clear();

                                    currHand.addAll(board.getTileZone("Hand" + pungStealWind.getName()).getTiles());

                                    currMoves.addAll(possiblePungSteal(board, pungStealWind, board.getTile(gameID), currHand));

                                    /* adding all moves for pungStealWind*/
                                    windMoves.get(pungStealWind).addAll(currMoves);
                                }
                            }
                        } catch (GameException e) {
                            Logger.getLogger(InternationalBoardRule.class.getName()).log(Level.SEVERE, null, e);
                        }
                        break;
                    }

                    /* supreme move */
                    case SupremeEast:
                    case SupremeSouth:
                    case SupremeWest:
                    case SupremeNorth: {
                        /* the player must draw from the end of the wall */
                        Wind drawWind = lastMove.getWind();

                        try {
                            int lastWallTileID = getLastTileID(board);

                            currPath.put(lastWallTileID, TileZoneIdentifier.getIdentifierFromNormalizedName("Hand" + drawWind.getName()));

                            currVisibility.put(lastWallTileID, false);
                            currMoves.add(new Move(drawWind, 0, currPath, currVisibility));
                        } catch (GameException e) {
                            Logger.getLogger(InternationalBoardRule.class.getName()).log(Level.SEVERE, null, e);
                        }

                        windMoves.get(drawWind).addAll(currMoves);

                        break;
                    }

                    /* meld move */
                    case MeldEast0:
                    case MeldEast1:
                    case MeldEast2:
                    case MeldEast3:
                    case MeldSouth0:
                    case MeldSouth1:
                    case MeldSouth2:
                    case MeldSouth3:
                    case MeldWest0:
                    case MeldWest1:
                    case MeldWest2:
                    case MeldWest3:
                    case MeldNorth0:
                    case MeldNorth1:
                    case MeldNorth2:
                    case MeldNorth3:

                        /* no tile should go into the wall */
                    case Wall:
                        break;

                }
            });
        }
        return windMoves;
    }

    @Override
    public boolean isGameFinished(MahjongBoard board, Move lastMove) {
        if (lastMove == null) return false;
        boolean finished = false;
        try {
            finished = board.getTileZone(TileZoneIdentifier.Wall).getTiles().isEmpty() || lastMove.getPath().isEmpty();
        } catch (GameException ex) {
            Logger.getLogger(InternationalBoardRule.class.getName()).log(Level.SEVERE, null, ex);
        }

        return finished;
    }
}
