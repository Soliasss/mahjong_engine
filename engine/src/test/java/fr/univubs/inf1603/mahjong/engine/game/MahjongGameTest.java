/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univubs.inf1603.mahjong.engine.game;

import fr.univubs.inf1603.mahjong.engine.rule.GameRule;
import fr.univubs.inf1603.mahjong.engine.rule.Wind;
import java.beans.PropertyChangeSupport;
import java.time.Duration;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author purpl
 */
public class MahjongGameTest {
    
    /**
     * Test of launchGame method, of class MahjongGame.
     */
    @Test
    public void testLaunchGame() {
        System.out.println("launchGame");
        MahjongGame instance = null;
        instance.launchGame();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerMove method, of class MahjongGame.
     */
    @Test
    public void testRegisterMove() throws Exception {
        System.out.println("registerMove");
        Move move = null;
        MahjongGame instance = null;
        instance.registerMove(move);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBoard method, of class MahjongGame.
     */
    @Test
    public void testGetBoard() throws Exception {
        System.out.println("getBoard");
        Wind wind = null;
        MahjongGame instance = null;
        Board expResult = null;
        Board result = instance.getBoard(wind);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayerPoints method, of class MahjongGame.
     */
    @Test
    public void testGetPlayerPoints_int() {
        System.out.println("getPlayerPoints");
        int player = 0;
        MahjongGame instance = null;
        int expResult = 0;
        int result = instance.getPlayerPoints(player);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPossibleMoves method, of class MahjongGame.
     */
    @Test
    public void testGetPossibleMoves_0args() {
        System.out.println("getPossibleMoves");
        MahjongGame instance = null;
        ArrayList<Move> expResult = null;
        ArrayList<Move> result = instance.getPossibleMoves();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class MahjongGame.
     */
    @Test
    public void testClone() {
        System.out.println("clone");
        MahjongGame instance = null;
        MahjongGame expResult = null;
        MahjongGame result = instance.clone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStealingTime method, of class MahjongGame.
     */
    @Test
    public void testGetStealingTime() {
        System.out.println("getStealingTime");
        MahjongGame instance = null;
        Duration expResult = null;
        Duration result = instance.getStealingTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayingTime method, of class MahjongGame.
     */
    @Test
    public void testGetPlayingTime() {
        System.out.println("getPlayingTime");
        MahjongGame instance = null;
        Duration expResult = null;
        Duration result = instance.getPlayingTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastPlayedMove method, of class MahjongGame.
     */
    @Test
    public void testGetLastPlayedMove() {
        System.out.println("getLastPlayedMove");
        MahjongGame instance = null;
        Move expResult = null;
        Move result = instance.getLastPlayedMove();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUUID method, of class MahjongGame.
     */
    @Test
    public void testGetUUID() {
        System.out.println("getUUID");
        MahjongGame instance = null;
        UUID expResult = null;
        UUID result = instance.getUUID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPropertyChangeSupport method, of class MahjongGame.
     */
    @Test
    public void testGetPropertyChangeSupport() {
        System.out.println("getPropertyChangeSupport");
        MahjongGame instance = null;
        PropertyChangeSupport expResult = null;
        PropertyChangeSupport result = instance.getPropertyChangeSupport();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRule method, of class MahjongGame.
     */
    @Test
    public void testGetRule() {
        System.out.println("getRule");
        MahjongGame instance = null;
        GameRule expResult = null;
        GameRule result = instance.getRule();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentwind method, of class MahjongGame.
     */
    @Test
    public void testGetCurrentwind(){
        System.out.println("getCurrentwind");
        MahjongGame instance = null;
        Wind expResult = null;
        Wind result=null;
        try {
            result = instance.getCurrentwind();
        } catch (GameException ex) {
            Logger.getLogger(MahjongGameTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayerPoints method, of class MahjongGame.
     */
    @Test
    public void testGetPlayerPoints_Wind() {
        System.out.println("getPlayerPoints");
        Wind wind = null;
        MahjongGame instance = null;
        int expResult = 0;
        int result = instance.getPlayerPoints(wind);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllPlayerPoints method, of class MahjongGame.
     */
    @Test
    public void testGetAllPlayerPoints() {
        System.out.println("getAllPlayerPoints");
        MahjongGame instance = null;
        int[] expResult = null;
        int[] result = instance.getAllPlayerPoints();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayerWind method, of class MahjongGame.
     */
    @Test
    public void testGetPlayerWind() throws Exception {
        System.out.println("getPlayerWind");
        int player = 0;
        MahjongGame instance = null;
        Wind expResult = null;
        Wind result = instance.getPlayerWind(player);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayerFromWind method, of class MahjongGame.
     */
    @Test
    public void testGetPlayerFromWind() throws Exception {
        System.out.println("getPlayerFromWind");
        Wind wind = null;
        MahjongGame instance = null;
        int expResult = 0;
        int result = instance.getPlayerFromWind(wind);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayerWinds method, of class MahjongGame.
     */
    @Test
    public void testGetPlayerWinds() throws Exception {
        System.out.println("getPlayerWinds");
        MahjongGame instance = null;//new MahjongGame();
        Wind[] expResult = null;
        Wind[] result = instance.getPlayerWinds();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPossibleMoves method, of class MahjongGame.
     */
    @Test
    public void testGetPossibleMoves_Wind(){
        System.out.println("getPossibleMoves");
        Wind wind = null;
        MahjongGame instance = null;
        ArrayList<Move> expResult = null;
        ArrayList<Move> result=null;
        try {
            result = instance.getPossibleMoves(wind);
        } catch (GameException ex) {
            fail("MahjongBoard threw an exception :"+ex);
        }        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPossibleMoves method, of class MahjongGame.
     */
    @Test
    public void testGetPossibleMoves_int(){
        System.out.println("getPossibleMoves");
        int player = 0;
        MahjongGame instance = null;
        ArrayList<Move> expResult = null;
        ArrayList<Move> result=null;
        try {
            result = instance.getPossibleMoves(player);
        } catch (GameException ex) {
            fail("MahjongBoard threw an exception :"+ex);
        }
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
