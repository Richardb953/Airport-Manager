/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.airport.dao.impl;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import cz.muni.airport.dao.StewardDAO;
import cz.muni.airport.model.Steward;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.springframework.test.context.ContextConfiguration;

/**
 *
 * @author Karolína Božková
 */
//@ContextConfiguration(locations = {"classpath:WEB-INF/applicationContext.xml"})
public class StewardDAOImplTest {
    
    //@Autowired(required = true)
    private StewardDAO stewardDAO;
    
    public StewardDAOImplTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:WEB-INF/applicationContext.xml");
        stewardDAO = ctx.getBean(StewardDAO.class);
    }

    /**
     * Test of addSteward method, of class StewardDAOImpl.
     */
    @Test
    public void testAddSteward() {
        System.out.println("testAddSteward()");
        Steward steward = new Steward();
        steward.setFirstName("Monika");
        steward.setLastName("Ross");
        stewardDAO.addSteward(steward);
        
        assertEquals(1,stewardDAO.getAllStewards().size());
        
    }
    
    /**
     * Test of addNullSteward method, of class StewardDAOImpl.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddSteward_null() {
        Steward steward = null;
        stewardDAO.addSteward(steward);
        
    }
    
    /**
     * 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveSteward_null(){
        Steward steward = null;
        stewardDAO.removeSteward(steward);
    }
    
    /**
     * Test of removeSteward method, of class StewardDAOImpl.
     */
    @Test
    public void testRemoveSteward() {
        //setup
        Steward s1 = new Steward();
        s1.setFirstName("Alice");
        s1.setLastName("Dunham");
        Steward s2 = new Steward();
        s2.setFirstName("Dylan");
        s2.setLastName("Bob");
        stewardDAO.addSteward(s1);
        stewardDAO.addSteward(s2);
        
        stewardDAO.removeSteward(s2);
        
        assertEquals(1, stewardDAO.getAllStewards().size());
    }

    /**
     * Test of updateSteward method, of class StewardDAOImpl.
     */
    @Test(expected = HibernateOptimisticLockingFailureException.class)
    public void testUpdateSteward_ID() {   System.out.println("testUpdateStewardID()------------------");   
        //setup
        Steward s1 = new Steward();
        s1.setFirstName("Alice");
        s1.setLastName("Dunham");
        Steward s2 = new Steward();
        s2.setFirstName("Dylan");
        s2.setLastName("Bob");
        stewardDAO.addSteward(s1);
        stewardDAO.addSteward(s2);
        
        Steward toChange = stewardDAO.getStewardByName(s2.getFirstName(), s2.getLastName()).get(0);
        System.out.println(toChange.toString());
        
        toChange.setId((long) 99);
        stewardDAO.updateSteward(toChange);
                
    }
    
    /**
     * 
     */
    @Test()
    public void testUpdateSteward() {   System.out.println("testUpdateSteward()------------------");   
        //setup
        Steward s1 = new Steward();
        s1.setFirstName("Alice");
        s1.setLastName("Dunham");
        Steward s2 = new Steward();
        s2.setFirstName("Dylan");
        s2.setLastName("Bob");
        stewardDAO.addSteward(s1);
        stewardDAO.addSteward(s2);
        
        Steward unchanged = stewardDAO.getStewardByName(s1.getFirstName(), s1.getLastName()).get(0);
        Long unchangedID = unchanged.getId();
        Steward toChange = stewardDAO.getStewardByName(s2.getFirstName(), s2.getLastName()).get(0);
        Long toChangeID = toChange.getId();
        
        //firstName
        toChange.setFirstName("Don");
        stewardDAO.updateSteward(toChange);
        assertEquals("Don", stewardDAO.getSteward(toChangeID).getFirstName());
        
        //lastName
        toChange.setLastName("Qichote");
        stewardDAO.updateSteward(toChange);
        assertEquals("Qichote", stewardDAO.getSteward(toChangeID).getLastName());
        
        //others unchanged
        assertEquals(unchanged, stewardDAO.getSteward(unchangedID));
                
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateSteward_Null(){ System.out.println("testUpdateStewardNull()");
        Steward s = null;
        stewardDAO.updateSteward(s);
    }

    /**
     * Test of getSteward method, of class StewardDAOImpl.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetSteward_Null() { System.out.println("testGetStewardNull()");
        Long id = null;
        Steward steward = stewardDAO.getSteward(id);
    }
    
    @Test
    public void testGetSteward_nonexistent(){ System.out.println("testGetStewardNonexistent()");
        //empty db
        Steward s = stewardDAO.getSteward((long) 3 );
        assertNull(s);
        
    }
    
    @Test
    public void testGetSteward(){ System.out.println("testGetSteward()");
        //setup
        Steward s1 = new Steward();
        s1.setFirstName("Alice");
        s1.setLastName("Dunham");
        Steward s2 = new Steward();
        s2.setFirstName("Dylan");
        s2.setLastName("Bob");
        Steward alice = stewardDAO.addSteward(s1);
        stewardDAO.addSteward(s2);
        
        Steward aliceDB = stewardDAO.getSteward(alice.getId());
        
        assertEquals(alice, aliceDB);
        
        
    }

    /**
     * Test of getAllStewards method, of class StewardDAOImpl.
     */
    @Test
    public void testGetAllStewards() { System.out.println("testGetAllStewards()");
        // emty db
        assertEquals(0, stewardDAO.getAllStewards().size());
        //one
        Steward s1 = new Steward();
        s1.setFirstName("Alice");
        s1.setLastName("Dunham");
        stewardDAO.addSteward(s1);
        assertEquals(1, stewardDAO.getAllStewards().size());
        //two
        Steward s2 = new Steward();
        s2.setFirstName("Dylan");
        s2.setLastName("Bob");
        stewardDAO.addSteward(s2);
        assertEquals(2, stewardDAO.getAllStewards().size());

    }

    /**
     * Test of getStewardByName method, of class StewardDAOImpl.
     */
    @Test
    public void testGetStewardByName() { System.out.println("testGetStewardByName()");
    //setup
        Steward s1 = new Steward();
        s1.setFirstName("Alice");
        s1.setLastName("Dunham");
        Steward s2 = new Steward();
        s2.setFirstName("Dylan");
        s2.setLastName("Bob");
        Steward alice = stewardDAO.addSteward(s1);
        stewardDAO.addSteward(s2);
        
        List<Steward> results = stewardDAO.getStewardByName("Alice", "Dunham");
        assertEquals(1, results.size());
        assertEquals(alice, results.get(0));
  
    }
    
    /**
     * Test of getStewardByName method, of class StewardDAOImpl.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetStewardByName_firstNull() {
        //setup
        Steward s1 = new Steward();
        s1.setFirstName("Alice");
        s1.setLastName("Dunham");
        Steward s2 = new Steward();
        s2.setFirstName("Dylan");
        s2.setLastName("Bob");
        stewardDAO.addSteward(s1);
        stewardDAO.addSteward(s2);
        
        stewardDAO.getStewardByName(null, "Bob");
        
        
    }
    
    /**
     * Test of getStewardByName method, of class StewardDAOImpl.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetStewardByName_secondNull() {
        Steward s1 = new Steward();
        s1.setFirstName("Alice");
        s1.setLastName("Dunham");
        Steward s2 = new Steward();
        s2.setFirstName("Dylan");
        s2.setLastName("Bob");
        stewardDAO.addSteward(s1);
        stewardDAO.addSteward(s2);
        
        stewardDAO.getStewardByName("Alice", null);
    }
    
    /**
     * Test of getStewardByName method, of class StewardDAOImpl.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetStewardByName_bothNull() {
        stewardDAO.getStewardByName(null, null);
        
    }
    
    /**
     * Test of getStewardByName method, of class StewardDAOImpl.
     */
    @Test
    public void testGetStewardByName_nonexistent() {
        //setup
        Steward s1 = new Steward();
        s1.setFirstName("Alice");
        s1.setLastName("Dunham");
        Steward s2 = new Steward();
        s2.setFirstName("Dylan");
        s2.setLastName("Bob");
        stewardDAO.addSteward(s1);
        stewardDAO.addSteward(s2);
        
        List<Steward> gottenByName = stewardDAO.getStewardByName("Tom", "Hardy");
        assertTrue(gottenByName.isEmpty());
    }
    
}
