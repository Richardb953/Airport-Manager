package cz.muni.airport.dao.impl;

import cz.muni.airport.dao.FlightDAO;
import cz.muni.airport.model.Airplane;
import cz.muni.airport.model.Airport;
import cz.muni.airport.model.Flight;
import cz.muni.airport.model.PlaneType;
import java.util.Calendar;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Tests all methods of FlightDAOImpl.
 *
 * @author Jiri Krejci
 */
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:WEB-INF/applicationContext.xml"})
public class FlightDAOImplTest {

    @Autowired(required = true)
    private FlightDAO flightDAO;

    public FlightDAOImplTest() {
    }

    @Test
    @DirtiesContext
    public void testAddFlight() {

        flightDAO.addFlight(createFlight1());
        assertEquals(1, flightDAO.getAllFlights().size());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddFlight_null() {
        Flight flight = null;
        flightDAO.addFlight(flight);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveFlight_null() {
        Flight flight = null;
        flightDAO.removeFlight(flight);
    }

    @Test
    @DirtiesContext
    public void testRemoveFlight() {

        Flight f1 = createFlight1();
        Flight f2 = createFlight2();
        flightDAO.addFlight(f1);
        flightDAO.addFlight(f2);

        assertEquals(2, flightDAO.getAllFlights().size());

        flightDAO.removeFlight(f1);

        assertEquals(1, flightDAO.getAllFlights().size());

        flightDAO.removeFlight(f2);

        assertEquals(0, flightDAO.getAllFlights().size());

    }

    @Test()
    @DirtiesContext
    public void testUpdateFlight() {

        Flight f1 = createFlight1();
        flightDAO.addFlight(f1);

        Flight chenged = f1;

        Calendar arrival = Calendar.getInstance();
        arrival.set(2015, Calendar.JANUARY, 21, 11, 12);
        Calendar departure = Calendar.getInstance();
        departure.set(2017, Calendar.FEBRUARY, 21, 10, 10);

        chenged.setName("FlightChanged");
        chenged.setArrival(arrival.getTime());
        chenged.setDeparture(departure.getTime());
        chenged.setPassagers(10);

        f1 = flightDAO.updateFlight(f1);
        assertEquals("FlightChanged", flightDAO.getFlight(f1.getId()).getName());
        assertEquals(arrival.getTime(), flightDAO.getFlight(f1.getId()).getArrival());
        assertEquals(departure.getTime(), flightDAO.getFlight(f1.getId()).getDeparture());
        assertEquals(new Integer(10), flightDAO.getFlight(f1.getId()).getPassagers());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateFlight_Null() {
        Flight flight = null;
        flightDAO.updateFlight(flight);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetFlight_Null() {
        Long id = null;
        flightDAO.getFlight(id);
    }

    @Test
    public void testGetFlight_nonexistent() {
        Flight flight = flightDAO.getFlight((long) 2);
        assertNull(flight);

    }

    @Test
    @DirtiesContext
    public void testGetSteward() {

        Flight f1 = createFlight1();
        Flight f2 = createFlight2();
        Flight added = flightDAO.addFlight(f1);
        flightDAO.addFlight(f2);

        Flight retrived = flightDAO.getFlight(added.getId());

        assertEquals(added, retrived);

    }

    @Test
    @DirtiesContext
    public void testGetAllFlight() {

        assertEquals(0, flightDAO.getAllFlights().size());

        flightDAO.addFlight(createFlight1());
        assertEquals(1, flightDAO.getAllFlights().size());

        flightDAO.addFlight(createFlight2());
        assertEquals(2, flightDAO.getAllFlights().size());
    }

    @Test
    @DirtiesContext
    public void testGetFlightByName() {

        Flight f = createFlight1();
        f.setName("Flight123");
        Flight addedToDB = flightDAO.addFlight(f);

        List<Flight> flights = flightDAO.getFlightsByName("Flight123");

        assertEquals(1, flights.size());
        assertEquals(addedToDB, flights.get(0));

    }
//    
//    /**
//     * Tests if getStewardByName method throws exception if one of the parameters is null.
//     */
//    @Test(expected = IllegalArgumentException.class)
//    public void testGetStewardByName_firstNull() {
//        //setup
//        Steward s1 = new Steward();
//        s1.setFirstName("Alice");
//        s1.setLastName("Dunham");
//        Steward s2 = new Steward();
//        s2.setFirstName("Dylan");
//        s2.setLastName("Bob");
//        stewardDAO.addSteward(s1);
//        stewardDAO.addSteward(s2);
//        
//        stewardDAO.getStewardByName(null, "Bob");
//        
//        
//    }
//    
//    /**
//     * Tests if getStewardByName method throws exception if one of the parameters is null.
//     */
//    @Test(expected = IllegalArgumentException.class)
//    public void testGetStewardByName_secondNull() {
//        Steward s1 = new Steward();
//        s1.setFirstName("Alice");
//        s1.setLastName("Dunham");
//        Steward s2 = new Steward();
//        s2.setFirstName("Dylan");
//        s2.setLastName("Bob");
//        stewardDAO.addSteward(s1);
//        stewardDAO.addSteward(s2);
//        
//        stewardDAO.getStewardByName("Alice", null);
//    }
//    
//    /**
//     * Tests if getStewardByName method throws exception if both parameters are null.
//     */
//    @Test(expected = IllegalArgumentException.class)
//    public void testGetStewardByName_bothNull() {
//        stewardDAO.getStewardByName(null, null);
//        
//    }
//    
//    /**
//     * Tests behaviour of getStewardByName method if given entry is not in the database.
//     * Expected result is empty list. 
//     */
//    @Test
//    public void testGetStewardByName_nonexistent() {
//        //setup
//        Steward s1 = new Steward();
//        s1.setFirstName("Alice");
//        s1.setLastName("Dunham");
//        Steward s2 = new Steward();
//        s2.setFirstName("Dylan");
//        s2.setLastName("Bob");
//        stewardDAO.addSteward(s1);
//        stewardDAO.addSteward(s2);
//        
//        List<Steward> gottenByName = stewardDAO.getStewardByName("Tom", "Hardy");
//        assertTrue(gottenByName.isEmpty());
//}

    private Flight createFlight1() {
        Calendar arrival = Calendar.getInstance();
        arrival.set(2016, Calendar.DECEMBER, 20, 10, 22);

        Calendar departure = Calendar.getInstance();
        departure.set(2016, Calendar.DECEMBER, 20, 11, 22);

        Airplane airplane = new Airplane();
        airplane.setCapacity(100);
        airplane.setFlights(null);
        airplane.setName("Boeing 747");
        airplane.setType(PlaneType.BUSINESS_JET);

        Airport destination = new Airport();
        destination.setCity("Brno");
        destination.setCountry("CZ");
        destination.setName("Letiste Turany");

        Airport source = new Airport();
        source.setCity("Praha");
        source.setCountry("CZ");
        source.setName("Letiste Vaclava Havla");

        Flight flight = new Flight();
        flight.setName("Flight1");
        flight.setArrival(arrival.getTime());
        flight.setDeparture(departure.getTime());
        flight.setPassagers(50);

        return flight;
    }

    private Flight createFlight2() {
        Calendar arrival = Calendar.getInstance();
        arrival.set(2016, Calendar.DECEMBER, 20, 10, 22);

        Calendar departure = Calendar.getInstance();
        departure.set(2016, Calendar.DECEMBER, 20, 11, 22);

        Airplane airplane = new Airplane();
        airplane.setCapacity(100);
        airplane.setFlights(null);
        airplane.setName("Boeing 757");
        airplane.setType(PlaneType.BUSINESS_JET);

        Airport destination = new Airport();
        destination.setCity("Bratislava");
        destination.setCountry("SK");
        destination.setName("Letiste Blava");

        Airport source = new Airport();
        source.setCity("Milano");
        source.setCountry("IT");
        source.setName("Milano airport");

        Flight flight = new Flight();
        flight.setName("Flight2");
        flight.setArrival(arrival.getTime());
        flight.setDeparture(departure.getTime());
        flight.setPassagers(90);

        return flight;
    }

}
