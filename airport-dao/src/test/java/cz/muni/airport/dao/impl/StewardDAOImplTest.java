package cz.muni.airport.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import cz.muni.airport.dao.StewardDAO;
import cz.muni.airport.model.Steward;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * This class tests all methods of StewardDAOImpl.
 *
 * @author Karolína Božková, github name: Kayeeec 
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:WEB-INF/applicationContextDao.xml"})
public class StewardDAOImplTest {
    
    @Autowired(required = true)
    private StewardDAO stewardDAO;
    
    public StewardDAOImplTest() {
    }

    /**
     * Tests wether addSteward() adds steward into the database. 
     * Database is empty, after adding tests if it has one entry.
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
     * Tests if addSteward() throws IllegalArgumentException when given null parameter. 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddSteward_null() {
        Steward steward = null;
        stewardDAO.addSteward(steward);
        
    }
    
    /**
     * Tests if removeSteward() throws IllegalArgumentException when given null parameter. 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveSteward_null(){
        Steward steward = null;
        stewardDAO.removeSteward(steward);
    }
      
    /**
     * Tests if removeSteward() removes entry from database. 
     * Database is set up with two entities, after remove operation only one entry is expected.
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
     * Tests if updateSteward() allows to change ID. Exception is expected. Id must not be changed.
     */
    @Test(expected = HibernateOptimisticLockingFailureException.class)
    public void testUpdateSteward_ID() {   System.out.println("testUpdateStewardID()");   
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
     * Tests if updateSteward() works properly on all attributes except from id and flights and if it 
     * does not change unwanted entries.
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
        assertEquals("Don", stewardDAO.getStewardById(toChangeID).getFirstName());
        
        //lastName
        toChange.setLastName("Qichote");
        stewardDAO.updateSteward(toChange);
        assertEquals("Qichote", stewardDAO.getStewardById(toChangeID).getLastName());
        
        //others unchanged
        assertEquals(unchanged, stewardDAO.getStewardById(unchangedID));
                
    }
    
    /**
     * Tests if updateSteward() throws IllegalArgumentException if given null parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateSteward_Null(){ System.out.println("testUpdateStewardNull()");
        Steward s = null;
        stewardDAO.updateSteward(s);
    }

    /**
     * Tests if getStewardById() throws IllegalArgumentException if given null parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetStewardById_Null() { System.out.println("testGetStewardNull()");
        Long id = null;
        Steward steward = stewardDAO.getStewardById(id);
    }
    
    /**
     * Tests behavior of getStewardById() when searching for entry that is not in the database.
     * Expexted result is null object.
     */
    @Test
    public void testGetStewardById_nonexistent(){ System.out.println("testGetStewardNonexistent()");
        //empty db
        Steward s = stewardDAO.getStewardById((long) 3 );
        assertNull(s);
        
    }
    
    /**
     * Tests proper behaviour of getStewardById() method. 
     */
    @Test
    public void testGetStewardById(){ System.out.println("testGetSteward()");
        //setup
        Steward s1 = new Steward();
        s1.setFirstName("Alice");
        s1.setLastName("Dunham");
        Steward s2 = new Steward();
        s2.setFirstName("Dylan");
        s2.setLastName("Bob");
        Steward alice = stewardDAO.addSteward(s1);
        stewardDAO.addSteward(s2);
        
        //test
        Steward aliceDB = stewardDAO.getStewardById(alice.getId());
        
        assertEquals(alice, aliceDB);
        
        
    }

    /**
     * Test of getAllStewards method, of class StewardDAOImpl.
     * On empty database empty list of results expected.
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
        s2.setFirstName("Alice");
        s2.setLastName("Bob");
        Steward alice = stewardDAO.addSteward(s1);
        stewardDAO.addSteward(s2);
        
        List<Steward> results = stewardDAO.getStewardByName("Alice", "Dunham");
        assertEquals(1, results.size());
        assertEquals(alice, results.get(0));
  
    }
    
    /**
     * Tests if getStewardByName method throws exception if one of the parameters is null.
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
     * Tests if getStewardByName method throws exception if one of the parameters is null.
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
     * Tests if getStewardByName method throws exception if both parameters are null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetStewardByName_bothNull() {
        stewardDAO.getStewardByName(null, null);
        
    }
    
    /**
     * Tests behaviour of getStewardByName method if given entry is not in the database.
     * Expected result is empty list. 
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
