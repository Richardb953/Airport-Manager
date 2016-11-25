package cz.muni.airport.services.impl;

import cz.muni.airport.dao.StewardDAO;
import cz.muni.airport.model.Steward;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Karolína Božková, github name: Kayeeec
 */
@RunWith(MockitoJUnitRunner.class)
public class StewardServiceImplTest {
    
    @Mock
    private StewardDAO stewardDAO;
    @InjectMocks
    private StewardServiceImpl stewardService = new StewardServiceImpl();
    
    public StewardServiceImplTest() {
    }
    
    private Steward prepareSteward1(){
        Steward steward = new Steward();
        steward.setFirstName("John");
        steward.setLastName("Malkovich");
        return steward;
    }

    /**
     * Test of addSteward method, of class StewardServiceImpl.
     */
    @Test
    public void testAddSteward() {
        System.out.println("addSteward");
        
        Steward s1 = prepareSteward1();
        when(stewardDAO.addSteward(s1)).thenReturn(s1);
        
        Steward added = stewardService.addSteward(s1);
        
        verify(stewardDAO).addSteward(s1);
        assertEquals(s1, added);
    
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void  testAddStewardNull(){
        when(stewardDAO.addSteward(null)).thenThrow(new IllegalArgumentException("steward is null"));

        stewardService.addSteward(null);
    }

    /**
     * Test of removeSteward method, of class StewardServiceImpl.
     */
    @Test
    public void testRemoveSteward() {
        System.out.println("removeSteward");
        
        Steward s1 = prepareSteward1();
        
        stewardService.removeSteward(s1);
        
        verify(stewardDAO).removeSteward(s1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveStewardNull() {
        doThrow(new IllegalArgumentException("steward is null")).when(stewardDAO).removeSteward(null);
        stewardService.removeSteward(null);
    }
    
    

    /**
     * Test of updateSteward method, of class StewardServiceImpl.
     */
    @Test
    public void testUpdateSteward() {
        System.out.println("updateSteward");
        
        Steward s1 = prepareSteward1();
        when(stewardDAO.updateSteward(s1)).thenReturn(s1);
        
        Steward updated = stewardService.updateSteward(s1);
        
        verify(stewardDAO).updateSteward(s1);
        assertEquals(s1, updated);
        
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateStewardNull() {
        when(stewardDAO.updateSteward(null)).thenThrow(new IllegalArgumentException("steward is null"));
        stewardService.updateSteward(null);
    }

    /**
     * Test of getSteward method, of class StewardServiceImpl.
     */
    @Test
    public void testGetSteward() {
        System.out.println("getSteward");
        
        Steward s1 = prepareSteward1();
        when(stewardDAO.getStewardById(Long.MIN_VALUE)).thenReturn(s1);
        
        Steward gotten = stewardService.getSteward(Long.MIN_VALUE);
        
        verify(stewardDAO).getStewardById(Long.MIN_VALUE);
        assertEquals(s1, gotten);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetStewardNull() {
        when(stewardDAO.getStewardById(null)).thenThrow(new IllegalArgumentException());
        Steward gotten = stewardService.getSteward(null);
    }

    /**
     * Test of getAllStewards method, of class StewardServiceImpl.
     */
    @Test
    public void testGetAllStewards() {
        System.out.println("getAllStewards");
        
        Steward s1 = prepareSteward1();
        List<Steward> stewards = new ArrayList<>();
        stewards.add(s1);
        when(stewardDAO.getAllStewards()).thenReturn(stewards);
        
        List<Steward> allStewards = stewardService.getAllStewards();
        
        verify(stewardDAO).getAllStewards();
        assertEquals(stewards, allStewards);
        
    }

    /**
     * Test of getStewardByName method, of class StewardServiceImpl.
     */
    @Test
    public void testGetStewardByName() {
        System.out.println("getStewardByName");
        
        String first = "John", last = "Malkovich";
        Steward s1 = prepareSteward1();
        List<Steward> stewards = new ArrayList<>();
        stewards.add(s1);
        when(stewardDAO.getStewardByName(first, last)).thenReturn(stewards);
        
        List<Steward> gotten = stewardService.getStewardByName(first, last);
        
        verify(stewardDAO).getStewardByName(first, last);
        assertEquals(stewards, gotten);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetStewardByNameFirstNull() {
        System.out.println("getStewardByNameFirstNull");
        String last = "Malkovich";
        when(stewardDAO.getStewardByName(null, last)).thenThrow(new IllegalArgumentException());
        
        List<Steward> gotten = stewardService.getStewardByName(null, last);
        
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetStewardByNameLastNull() {
        System.out.println("getStewardByNameFirstNull");
        String first = "John";
        when(stewardDAO.getStewardByName(first, null)).thenThrow(new IllegalArgumentException());
        
        List<Steward> gotten = stewardService.getStewardByName(first, null);
        
    }
    
}
