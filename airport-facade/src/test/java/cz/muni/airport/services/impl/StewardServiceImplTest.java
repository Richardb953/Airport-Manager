package cz.muni.airport.services.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cz.muni.airport.dao.StewardDAO;
import cz.muni.airport.model.Airport;
import cz.muni.airport.model.Flight;
import cz.muni.airport.model.Steward;
import cz.muni.airport.services.StewardService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author Karolína Božková, github name: Kayeeec
 */
@RunWith(MockitoJUnitRunner.class)
public class StewardServiceImplTest {
    
    @Mock
    private StewardDAO stewardDAO;

    @InjectMocks
    private StewardService stewardService = new StewardServiceImpl();
    
    public StewardServiceImplTest() {
    }
    
    private Steward prepareSteward1(){
        Steward steward = new Steward();
        steward.setFirstName("John");
        steward.setLastName("Malkovich");
        return steward;
    }
    private List<Airport> prepareAirports(){
        Airport turany = new Airport();
        turany.setCity("Brno");
        turany.setCountry("CZE");
        turany.setIata("BRQ");
        turany.setName("Tuřany");
        
        Airport blava = new Airport();
        blava.setCity("Bratislava");
        blava.setCountry("SLO");
        blava.setIata("BTS");
        blava.setName("Blava");
        
        Airport paris = new Airport();
        paris.setCity("Paris");
        paris.setCountry("FRA");
        paris.setIata("CDG");
        paris.setName("Charles de Gaull");
        
        Airport london = new Airport();
        london.setCity("London");
        london.setCountry("GBR");
        london.setIata("LHR");
        london.setName("Heathrow");
        
        List<Airport> airports = new ArrayList<>(4);
        airports.add(0, turany);
        airports.add(1, blava);
        airports.add(2, paris);
        airports.add(3, london);
        /*
        Airport brno = prepareAirports().get(0);
        Airport blava = prepareAirports().get(1);
        Airport paris = prepareAirports().get(2);
        Airport london = prepareAirports().get(3);
        */
        
        return airports;
    }
    
    private Date createDate(int year, int month, int day, int hour, int minute){
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day, hour, minute);
        return cal.getTime();
    }
    
    private Flight prepareFlight(){
        Airport brno = prepareAirports().get(0);
        Airport london = prepareAirports().get(3);
        
        Flight flight = new Flight();
        flight.setName("Wants airplane");
        flight.setDestinationPort(brno);
        flight.setSourcePort(london);
        flight.setDeparture(createDate(2016, Calendar.JANUARY, 1, 8, 0));
        flight.setArrival(createDate(2016, Calendar.JANUARY, 1, 11, 0));
        
        return flight;
        
    }
    
    private List<Flight> prepareUnavailableFlights(){
        Airport brno = prepareAirports().get(0);
        Airport blava = prepareAirports().get(1);
        Airport paris = prepareAirports().get(2);
        Airport london = prepareAirports().get(3);
        
        Flight interferesTimely = new Flight();
        interferesTimely.setName("the same time");
        interferesTimely.setDestinationPort(brno);
        interferesTimely.setSourcePort(london);
        interferesTimely.setArrival(createDate(2016, Calendar.JANUARY, 1, 11, 0));
        interferesTimely.setDeparture(createDate(2016, Calendar.JANUARY, 1, 9, 0));
        
        Flight interferesDestination = new Flight();
        interferesDestination.setName("the same time");
        interferesDestination.setDestinationPort(paris);
        interferesDestination.setSourcePort(london);
        interferesDestination.setArrival(createDate(2016, Calendar.JANUARY, 1, 7, 0));
        interferesDestination.setDeparture(createDate(2016, Calendar.JANUARY, 1, 2, 0));
        
        Flight interferesSource = new Flight();
        interferesSource.setName("the same time");
        interferesSource.setDestinationPort(paris);
        interferesSource.setSourcePort(london);
        interferesSource.setArrival(createDate(2016, Calendar.JANUARY, 1, 15, 0));
        interferesSource.setDeparture(createDate(2016, Calendar.JANUARY, 1, 12, 0));
        
        List<Flight> unavailable = new ArrayList<Flight>(3);
        unavailable.add(0,interferesTimely);
        unavailable.add(1,interferesDestination);
        unavailable.add(2,interferesSource);
        return unavailable;
        
        
    }
    
    private List<Flight> prepareAvailableFlights(){
        Airport brno = prepareAirports().get(0);
        Airport blava = prepareAirports().get(1);
        Airport paris = prepareAirports().get(2);
        Airport london = prepareAirports().get(3);
        
        Flight previous = new Flight();
        previous.setName("the same time");
        previous.setDestinationPort(london);
        previous.setSourcePort(brno);
        previous.setArrival(createDate(2016, Calendar.JANUARY, 1, 7, 0));
        previous.setDeparture(createDate(2016, Calendar.JANUARY, 1, 2, 0));
        
        Flight next = new Flight();
        next.setName("the same time");
        next.setDestinationPort(paris);
        next.setSourcePort(brno);
        next.setArrival(createDate(2016, Calendar.JANUARY, 1, 15, 0));
        next.setDeparture(createDate(2016, Calendar.JANUARY, 1, 12, 0));
        
         List<Flight> available = new ArrayList<Flight>(2);
        available.add(0,previous);
        available.add(1,next);
        return available;
        
        
    }
    
   
    @Test
    public void testIsAvailableEmpty() {
        List<Flight> empty = new ArrayList<>();
        Steward steward = prepareSteward1();
        steward.setFlights(empty);
        
        assertTrue(stewardService.isAvailable(steward, prepareFlight()));    
    }
    /*
    @Test
    public void testIsAvailable() {
        List<Flight> unavailable = prepareUnavailableFlights();
        List<Flight> available = prepareAvailableFlights();
               
        Steward steward = prepareSteward1();
        
        steward.setFlights(available);
        assertTrue(stewardService.isAvailable(steward, prepareFlight()));
        
        steward.setFlights(unavailable);
        assertTrue(!stewardService.isAvailable(steward, prepareFlight()));
    }
*/
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
