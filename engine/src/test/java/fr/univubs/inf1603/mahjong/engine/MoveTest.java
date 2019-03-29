
package fr.univubs.inf1603.mahjong.engine;

import java.util.HashMap;
import java.util.UUID;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author GUILLAUME Maxime
 */
public class MoveTest {
    
    public MoveTest() {
    }

    /**
     * Test of getPlayer method, of class Move.
     * @throws fr.univubs.inf1603.mahjong.engine.MoveException
     */
    @Test
    public void testGetSide() throws MoveException {
        System.out.println("getPlayer");
        HashMap<Integer,TileZone> hashTest = new HashMap<>();
        hashTest.put(4,new TileZone("Test",true));
        Move instance = new Move(Side.EAST, 0, hashTest);
        Side expResult = Side.EAST;
        Side result = instance.getSide();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPriority method, of class Move.
     * @throws fr.univubs.inf1603.mahjong.engine.MoveException
     */
    @Test
    public void testGetPriority() throws MoveException {
        System.out.println("getPriority");
        HashMap<Integer,TileZone> hashTest = new HashMap<>();
        hashTest.put(4,new TileZone("Test",true));
        Move instance = new Move(Side.EAST,0,hashTest);
        int expResult = 0;
        int result = instance.getPriority();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPath method, of class Move.
     * @throws fr.univubs.inf1603.mahjong.engine.MoveException
     */
    @Test
    public void testGetPath() throws MoveException {
        System.out.println("getPath");
        HashMap<Integer,TileZone> path = new HashMap<>();
        path.put(4,new TileZone("Test",true));
        Move instance = new Move(Side.EAST,0,path);
        HashMap<Integer, TileZone> result = instance.getPath();
        assertEquals(path, result);
    }

    /**
     * Test of getUUID method, of class Move.
     * @throws fr.univubs.inf1603.mahjong.engine.MoveException
     */
    @Test
    public void testGetUUID() throws MoveException {
        System.out.println("getUUID");
        UUID expResult = UUID.randomUUID();
        HashMap<Integer,TileZone> path = new HashMap<>();
        path.put(4,new TileZone("Test",true));
        Move instance = new Move(Side.EAST, 0, path, expResult);
        UUID result = instance.getUUID();
        assertEquals(expResult, result);
    }
    
}
