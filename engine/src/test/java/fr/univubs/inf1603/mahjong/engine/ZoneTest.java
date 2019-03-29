/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univubs.inf1603.mahjong.engine;


import java.util.ArrayList;
import java.util.UUID;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author maxime
 */
public class ZoneTest {
    
    public ZoneTest() {
    }

    

    /**
     * Test of getUUID method, of class Zone.
     * @throws fr.univubs.inf1603.mahjong.engine.ZoneException
     */
    @Test
    public void testGetUUID() throws ZoneException {
        System.out.println("getUUID");
        UUID expResult = UUID.randomUUID();
        ArrayList<GameZone> content = new ArrayList<>();
        content.add(new Zone("Zone1",false));
        Zone zone = new Zone("Test1",false,false,content,expResult);
        UUID result = zone.getUUID();
        assertEquals(expResult, result);
    }

    /**
     * Test of setHidden method, of class Zone.
     * @throws fr.univubs.inf1603.mahjong.engine.ZoneException
     */
    @Test
    public void testSetHidden() throws ZoneException {
        System.out.println("setHidden");
        Zone instance = new Zone("Test",true);
        boolean expResult = true;
        boolean result = instance.setHidden();
        assertEquals(expResult, result);
    }

    /**
     * Test of isHiddable method, of class Zone.
     * @throws fr.univubs.inf1603.mahjong.engine.ZoneException
     */
    @Test
    public void testIsHiddable() throws ZoneException {
        System.out.println("isHiddable");
        Zone instance = new Zone("Test",true);
        boolean expResult = true;
        boolean result = instance.isHideable();
        assertEquals(expResult, result);
    }

    /**
     * Test of isHidden method, of class Zone.
     * @throws fr.univubs.inf1603.mahjong.engine.ZoneException
     */
    @Test
    public void testIsHidden() throws ZoneException {
        System.out.println("isHidden");
        Zone instance = new Zone("Test",false);
        boolean expResult = false;
        boolean result = instance.isHidden();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Zone.
     * @throws fr.univubs.inf1603.mahjong.engine.ZoneException
     */
    @Test
    public void testSetName() throws ZoneException {
        System.out.println("setName");
        String newName = "TestName";
        Zone instance = new Zone("Test",false);
        instance.setName(newName);
        String result = instance.getName();
        assertEquals(newName, result);
    }


    /**
     * Test of getClone method, of class Zone.
     * @throws fr.univubs.inf1603.mahjong.engine.ZoneException
     */
    @Test
    public void testGetClone() throws ZoneException {
        System.out.println("getClone");
        ArrayList<GameZone> content = new ArrayList<>();
        Zone z1=new Zone("Zone1",false);
        z1.addZone(new Zone("Zone2",false));
        content.add(z1);
        content.add(new Zone("Zone2",false));
        Zone instance = new Zone("Test",false,false,content,UUID.randomUUID());
        Zone result = (Zone) instance.getClone();
        assertNotEquals(instance.getUUID(), result.getUUID());
        assertEquals(instance.getName(),result.getName());
        assertEquals(instance.isHidden(),result.isHidden());
        assertEquals(instance.isHideable(),result.isHideable());
        assertEquals(instance.getZones().size(), result.getZones().size());
        assertTrue(testCloneContent(instance.getZones(), result.getZones()));
        
    }
    
    private boolean testCloneContent(ArrayList<GameZone> list1, ArrayList<GameZone> list2) throws ZoneException{
        boolean ret=true;
        for (int i=0;i<list1.size();i++) {
            if (list1.get(i) instanceof Zone) {
                if(list1.get(i).getZones().isEmpty()){
                    if(!list1.get(i).getZones().equals(list2.get(i).getZones())){
                        ret=false;
                    }
                }
                else ret=testCloneContent(list1.get(i).getZones(), list2.get(i).getZones());
            }
            if (list1.get(i) instanceof TileZone) {
                if(!list1.get(i).getTiles().equals(list2.get(i).getTiles())){
                    ret=false;
                }
            }

        }
        return ret;
    }

    /**
     * Test of getZones method, of class Zone.
     * @throws fr.univubs.inf1603.mahjong.engine.ZoneException
     */
    @Test
    public void testGetZones() throws ZoneException {
        System.out.println("getZones");
        GameZone zone = new TileZone("Test", true);
        ArrayList<GameZone> expResult = new ArrayList<>();
        expResult.add(zone);
        Zone instance = new Zone("Test", true);
        instance.addZone(zone);
        ArrayList<GameZone> result = instance.getZones();
        assertTrue(result.equals(expResult));
    }

    /**
     * Test of getTiles method, of class Zone.
     * @throws fr.univubs.inf1603.mahjong.engine.ZoneException
     */
    @Test
    public void testGetTiles() throws ZoneException{
        System.out.println("getTiles");
        
        
        boolean bool=false;
        Zone instance = new Zone("Test", true);
        ArrayList<GameTile> result;
        try {
            result = instance.getTiles();
        } catch (ZoneException ex) {
            bool=true;
        }
        assertTrue(bool);
    }

    /**
     * Test of setZones method, of class Zone.
     * @throws fr.univubs.inf1603.mahjong.engine.ZoneException
     */
    @Test
    public void testSetZones() throws ZoneException {
        System.out.println("setZones");
        GameZone zone = new Zone("Test", true);
        ArrayList<GameZone> zones = new ArrayList<>();
        zones.add(zone);
        Zone instance = new Zone("Test", true);
        instance.setZones(zones);
        assertTrue(zones.equals(instance.getZones()));
    }

    /**
     * Test of setTiles method, of class Zone.
     * @throws fr.univubs.inf1603.mahjong.engine.ZoneException
     */
    @Test
    public void testSetTiles() throws ZoneException  {
        System.out.println("getTiles");        
        boolean bool=false;
        GameTile tile = new GameTile(0, new CommonTile(CommonTile.Family.CHARACTERS, CommonTile.Number.EIGHT));
        ArrayList<GameTile> expResult = new ArrayList<>();
        expResult.add(tile);
        Zone instance = new Zone("Test", true);
        try {
            instance.setTiles(expResult);
        } catch (ZoneException ex) {
            bool=true;
        }
        assertTrue(bool);
        
        
    }

    /**
     * Test of addZone method, of class Zone.
     * @throws fr.univubs.inf1603.mahjong.engine.ZoneException
     */
    @Test
    public void testAddZone() throws ZoneException {
        System.out.println("addZone");
        GameZone zone = new Zone("Test", true);
        Zone instance = new Zone("Test", true);
        boolean expResult = true;
        boolean result = instance.addZone(zone);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeZone method, of class Zone.
     * @throws fr.univubs.inf1603.mahjong.engine.ZoneException
     */
    @Test
    public void testRemoveZone() throws ZoneException {
        System.out.println("removeZone");
        GameZone zone = new Zone("Test", true);
        Zone instance = new Zone("Test", true);
        instance.addZone(zone);
        boolean expResult = true;
        boolean result = instance.removeZone(zone);
        assertEquals(expResult, result);
    }

    /**
     * Test of addTile method, of class Zone.
     * @throws fr.univubs.inf1603.mahjong.engine.ZoneException
     */
    @Test
    public void testAddTile() throws ZoneException {
        System.out.println("addTile");
        boolean bool=false;
        GameTile tile = new GameTile(0, new CommonTile(CommonTile.Family.CHARACTERS, CommonTile.Number.EIGHT));
        Zone instance = new Zone("Test",true);
        try {
            instance.addTile(tile);
        } catch (ZoneException ex) {
            bool=true;
        }
        assertTrue(bool);
    }

    /**
     * Test of removeTile method, of class Zone.
     * @throws fr.univubs.inf1603.mahjong.engine.ZoneException
     */
    @Test
    public void testRemoveTile() throws ZoneException {
        System.out.println("removeTile");
        boolean bool=false;
        GameTile tile = new GameTile(0, new CommonTile(CommonTile.Family.CHARACTERS, CommonTile.Number.EIGHT));
        Zone instance = new Zone("Test", true);
        try {
            instance.addTile(tile);
        } catch (ZoneException ex) {
            
        }
        try {
            instance.removeTile(tile);
        } catch (ZoneException ex) {
            bool=true;
        }
        assertTrue(bool);
    }

    /**
     * Test of getName method, of class Zone.
     * @throws fr.univubs.inf1603.mahjong.engine.ZoneException
     */
    @Test
    public void testGetName() throws ZoneException {
        System.out.println("getName");
        Zone instance = new Zone("Test",false);
        String expResult = "Test";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

   
        
}
