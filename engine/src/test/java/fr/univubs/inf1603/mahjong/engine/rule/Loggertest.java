/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univubs.inf1603.mahjong.engine.rule;

import fr.univubs.inf1603.mahjong.Wind;
import fr.univubs.inf1603.mahjong.engine.game.GameTile;
import static fr.univubs.inf1603.mahjong.engine.game.GameTile.ROOTLOGGER;
import fr.univubs.inf1603.mahjong.engine.game.MahjongTileZone;
import fr.univubs.inf1603.mahjong.engine.game.TileZoneIdentifier;
import fr.univubs.inf1603.mahjong.engine.rule.CommonTile;
import java.io.IOException;
import java.util.ArrayDeque;
import org.apache.log4j.*;

/**
 *
 * @author DELL
 */
public class Loggertest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        StringBuilder motif = new StringBuilder();
        
        motif.append("%d{ISO8601} | %-5p | %-30C{2} | %-24M | %-3L | %m%n");
        
        PatternLayout layout = new PatternLayout(motif.toString());
        
        ConsoleAppender ca = new ConsoleAppender(layout, "System.out");
        
        // engine.log ?
        FileAppender fa = new FileAppender(layout, "./engine.log");
        
        fa.setAppend(false);
        fa.activateOptions();
        ca.activateOptions();
        
        ROOTLOGGER.addAppender(fa);
        ROOTLOGGER.addAppender(ca);
        ROOTLOGGER.setLevel(Level.DEBUG);
        
        // Logger
        Logger LOGGER = Logger.getLogger(Loggertest.class.getName());
        
        /* 1- GameTile */
        
        LOGGER.info("LOGGING : GameTile");
        GameTile gt = new GameTile(0, null);
        CommonTile c = new CommonTile(CommonTile.Family.BAMBOO, CommonTile.Number.NINE);
        
        // getTile()
        gt.getTile();
        LOGGER.info("End of getTile() method");
        
        // getGameID()
        gt.getGameID();
        LOGGER.info("End of getGameID() method");
        
        // getUUID()        
        gt.getUUID();
        LOGGER.info("End of getUUID() method");
        
        // clone()
        gt.clone();
        
        
        
        // setTile()
        gt.setTile(c);
        
        // toString()
        gt.toString();
        LOGGER.info("End of toString() method");
        
        // getPropertyChangeSupport()
        gt.getPropertyChangeSupport();
        LOGGER.info("End of getPropertyChangeSupport() method");
        
        /* 2- MahjongTileZone */
        
        LOGGER.info("LOGGING : MahjongTileZone");
        TileZoneIdentifier identifier = null;
        MahjongTileZone mahjong1 = new MahjongTileZone(identifier);
        
        // addTile(GameTile)
        mahjong1.addTile(gt);
        
        // removeTile(GameTile)
        mahjong1.removeTile(gt);
        
        // getUUID()
        mahjong1.getUUID();
        LOGGER.info("End of getUUID() method");
         
        // getPropertyChangeSupport()
        mahjong1.getPropertyChangeSupport();
        LOGGER.info("End of getPropertyChangeSupport() method");
        
        // getTiles()
        mahjong1.getTiles();
        LOGGER.info("End of getTiles() method");
        
        //getIdentifier()
        mahjong1.getIdentifier();
        LOGGER.info("End of getIdentifier() method");
        
        // clone()
        try
        {
            gt.clone();
        }
        catch(UnsupportedOperationException ex)
        {
           LOGGER.info("Exception : " + ex.getMessage());
        }
        
        // 2- TileZoneIdentifier
        
        LOGGER.info("LOGGING : TileZoneIdentifier");
        TileZoneIdentifier identfier2 = TileZoneIdentifier.DiscardEast;
            
        // getNormalizedNameFromIdentifier(TileZoneIdentifier identifier)
        TileZoneIdentifier.getNormalizedNameFromIdentifier(identfier2);
        LOGGER.info("End of getNormalizedNameFromIdentifier(TileZoneIdentifier identifier)");
        
        // getIdentifierFromNormalizedName(String normalizedName)
        /*try
        {
            TileZoneIdentifier.getIdentifierFromNormalizedName("A");
        }
        catch(UnsupportedOperationException ex)
        {
           LOGGER.info("Exception : " + ex.getMessage());
        }*/
        
        // 3- Chow 
        
        // 4- CombinationFactory
        
        // 5- CommonTile
        /*
        LOGGER.info("LOGGING : CommonTile");
        CommonTile comm = new CommonTile(CommonTile.Family.BAMBOO, CommonTile.Number.EIGHT);
        
        // getFamily()
        comm.getFamily();
        LOGGER.info("End of getFamily() method");
    
        // getNumber()
        comm.getNumber();
        LOGGER.info("End of getNumber() method");
    
        // isMajor()
        comm.isMajor();
        LOGGER.info("End of isMajor() method");
        
        // toNormalizedName()
        comm.toNormalizedName();
        LOGGER.info("End of toNormalizedName() method");
        
        // toString()
        comm.toString();
        LOGGER.info("End of toString() method");*/
        
        // 6- FlowerTile
        /*LOGGER.info("LOGGING : FlowerTile");
        FlowerTile Ftile = new FlowerTile(FlowerTile.Flower.ORCHID);
    
        // getFlower()
        Ftile.getFlower();
        LOGGER.info("End of getFlower() method");
        
        // toNormalizedName()
        Ftile.toNormalizedName();
        LOGGER.info("End of toNormalizedName() method");
        
        // toString()
        Ftile.toString();
        LOGGER.info("End of toString() method");
        */
        /*
        // 7- IdentifiedPattern
        LOGGER.info("LOGGING : IdentifiedPattern");
        IdentifiedPattern id = null;
        IdentifiedPattern identP = new IdentifiedPattern((IdentifiablePattern) id);
        
        identP.getPattern();
        LOGGER.info("End of getPattern() method");
        
        identP.getTiles();
        LOGGER.info("End of getTiles() method");
        */
        // 8- InternationalPatternList
        /*LOGGER.info("LOGGING : InternationalPatternList");
        InternationalPatternList interp = null;
                
        //interp.getPatterns();
        //LOGGER.info("End of getPatterns() method");
        
        interp.getPattern("XX");
        LOGGER.info("End of getPattern() method");
        */
        /*
        LOGGER.info("LOGGING : Kong");
        GameTile gtable[] = new GameTile[2];
        
        gtable[0] = new GameTile(13, c);
        gtable[1] = new GameTile(12, c);
        
        Kong k = new Kong(gtable);
        
        k.getTiles();
        LOGGER.info("End of getTiles()");
        
        k.isKong();
        LOGGER.info("End of isKong()");
        */
        //k.isValid(gtable);
        //LOGGER.info("End of isValid(GameTile[] tiles)");
        /* playerSet
        PlayerSituation situation = null;
        Collection<Combination> hand = null;

        PlayerSet player = new PlayerSet(situation, hand);
        
        player.getAllCombinations();
        LOGGER.info("End of getAllCombinations()");
        */
        LOGGER.info("LOGGING : StartingWall");
        GameTile gtdeque = new GameTile(1, null);
        ArrayDeque<GameTile>cut = new ArrayDeque<GameTile>();
        StartingWall start = new StartingWall(Wind.EAST, 12, cut);
        
        start.getCut();
        LOGGER.info("End of getCut()");
        
        start.getStartingHeap();
        LOGGER.info("End of getStartingHeap()");
        
        start.getStartingSide();
        LOGGER.info("End of getStartingSide()");
        
        start.setCut(cut);
        LOGGER.info("End of setCut(ArrayDeque<GameTile>)");
        
        start.setStartingHeap(12);
        LOGGER.info("End of setStartingHeap(int)");
        
        start.setStartingSide(Wind.EAST);
        LOGGER.info("End of setStartingSide(Wind)");
    }
}
