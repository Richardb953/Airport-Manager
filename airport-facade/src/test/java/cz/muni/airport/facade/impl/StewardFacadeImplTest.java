package cz.muni.airport.facade.impl;

import cz.muni.airport.dto.StewardDTO;
import cz.muni.airport.facadeApi.StewardFacade;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Karolína Božková, github name: Kayeeec
 */
public class StewardFacadeImplTest {
    
    @Autowired
    private StewardFacade stewardFacade;
    
    public StewardFacadeImplTest() {
    }
    
    @Test
    public void shouldAutowireDependencies() {
        assertNotNull(stewardFacade);
    }

    /**
     * Test of createSteward method, of class StewardFacadeImpl.
     */
    @Test
    public void testCreateSteward() {
        System.out.println("createSteward");
        StewardDTO stewardDTO = null;
        StewardFacadeImpl instance = new StewardFacadeImpl();
        StewardDTO expResult = null;
        StewardDTO result = instance.createSteward(stewardDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateSteward method, of class StewardFacadeImpl.
     */
    @Test
    public void testUpdateSteward() {
        System.out.println("updateSteward");
        StewardDTO stewardDTO = null;
        StewardFacadeImpl instance = new StewardFacadeImpl();
        StewardDTO expResult = null;
        StewardDTO result = instance.updateSteward(stewardDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteSteward method, of class StewardFacadeImpl.
     */
    @Test
    public void testDeleteSteward() {
        System.out.println("deleteSteward");
        StewardDTO stewardDTO = null;
        StewardFacadeImpl instance = new StewardFacadeImpl();
        instance.deleteSteward(stewardDTO);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSteward method, of class StewardFacadeImpl.
     */
    @Test
    public void testGetSteward() {
        System.out.println("getSteward");
        Long id = null;
        StewardFacadeImpl instance = new StewardFacadeImpl();
        StewardDTO expResult = null;
        StewardDTO result = instance.getSteward(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStewardByName method, of class StewardFacadeImpl.
     */
    @Test
    public void testGetStewardByName() {
        System.out.println("getStewardByName");
        String firstName = "";
        String lastName = "";
        StewardFacadeImpl instance = new StewardFacadeImpl();
        List<StewardDTO> expResult = null;
        List<StewardDTO> result = instance.getStewardByName(firstName, lastName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllStewards method, of class StewardFacadeImpl.
     */
    @Test
    public void testGetAllStewards() {
        System.out.println("getAllStewards");
        StewardFacadeImpl instance = new StewardFacadeImpl();
        List<StewardDTO> expResult = null;
        List<StewardDTO> result = instance.getAllStewards();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
