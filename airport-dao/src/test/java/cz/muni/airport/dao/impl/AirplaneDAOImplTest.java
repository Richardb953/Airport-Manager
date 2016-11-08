package cz.muni.airport.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cz.muni.airport.dao.AirplaneDAO;
import cz.muni.airport.model.Airplane;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Richard Bariny on 30.10.2016.
 *
 */

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:WEB-INF/applicationContext.xml"})
public class AirplaneDAOImplTest {

    @Autowired(required = true)
    private AirplaneDAO airplaneDAO;

    /**
     * Adding to empty DB should contain just this one object with id 1
     */
    @Test
    public void testAddAirplane(){
        System.out.println("Testing add Airplane");

        Airplane airplane = new Airplane();
        airplane.setName("Letadlo a prasek");
        airplaneDAO.addAirplane(airplane);
        assertEquals(1, airplaneDAO.getAllAirplanes().size());
        assertEquals(airplane.getName(), airplaneDAO.getAirplaneById(1L).getName());
    }

    /**
     * Add null object is not valid
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddNullAirplane(){
        System.out.println("Testing add null Airplane");

        airplaneDAO.addAirplane(null);
    }

    /**
     * Add and delete object on empty database should be empty database
     */
    @Test
    public void testRemoveAirplane(){
        System.out.println("Testing remove Airplane");

        Airplane airplane = new Airplane();
        airplane.setName("Letadlo a prasek");
        airplaneDAO.addAirplane(airplane);

        airplaneDAO.removeAirplane(airplane);
        assertEquals(0, airplaneDAO.getAllAirplanes().size());
    }

    /**
     * Remove null object is not valid
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNullAirplane(){
        System.out.println("Testing remove null Airplane");

        airplaneDAO.removeAirplane(null);
    }

    /**
     * Testing update airplane, after update names should be equals
     */

    @Test
    public void testUpdateAirplane(){
        System.out.println("Testing update Airplane");

        Airplane airplane = new Airplane();
        airplane.setName("Letadlo a prasek");
        airplaneDAO.addAirplane(airplane);
        airplane.setName("JustOne");
        airplaneDAO.updateAirplane(airplane);

        assertEquals(1, airplaneDAO.getAllAirplanes().size());
        assertEquals(airplane.getName(), airplaneDAO.getAirplaneById(1L).getName());
    }

    /**
     * Update  null object is not valid
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateNullAirplane(){
        System.out.println("Testing update null Airplane");

        airplaneDAO.updateAirplane(null);
    }

    /**
     * Test if the object is the same and has id 1 on empty DB
     * Test EQUALS method on object Airplane
     */
    @Test
    public void testGetAirplane(){
        System.out.println("Testing get Airplane");

        Airplane airplane = new Airplane();
        airplane.setName("Letadlo a prasek");
        airplaneDAO.addAirplane(airplane);

        assertEquals(airplane, airplaneDAO.getAirplaneById(1L));
    }

    /**
     * Get null id is not valid
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetAirplaneByNullId(){
        System.out.println("Testing get null Airplane");

        airplaneDAO.getAirplaneById(null);
    }

    /**
     * Get unexist id should return null?
     */
    @Test
    public void testGetUnexistAirplaneById(){
        System.out.println("Testing get unexist Airplane");

        Airplane airplane = new Airplane();
        airplane.setName("Letadlo a prasek");
        airplaneDAO.addAirplane(airplane);

        assertNull(airplaneDAO.getAirplaneById(2L));
    }

    /**
     * Find by name should return list with 1 size
     */
    @Test
    public void testFindAirplanesByName(){
        System.out.println("Testing find Airplane by name");

        Airplane airplane = new Airplane();
        airplane.setName("JustOne");
        airplaneDAO.addAirplane(airplane);
        assertEquals(1, airplaneDAO.getAirplaneByName("JustOne").size());
    }

    /**
     * Airplanes with null name should not exist and method is throwin exp
     */

    @Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
    public void testAddAirplaneWithNullName(){
        System.out.println("Testing find Airplane by null name");

        Airplane airplane = new Airplane();
        airplane.setName(null);
        airplaneDAO.addAirplane(airplane);
    }

    /**
     * Name is nullable false parameter adding should throw exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFindAirplaneByNullName() {
        airplaneDAO.getAirplaneByName(null).size();
    }



}
