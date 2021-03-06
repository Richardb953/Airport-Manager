package cz.muni.airport.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.List;

import cz.muni.airport.dao.AirplaneDAO;
import cz.muni.airport.dao.AirportDAO;
import cz.muni.airport.dao.FlightDAO;
import cz.muni.airport.database.testConfig;
import cz.muni.airport.model.Airplane;
import cz.muni.airport.model.Airport;
import cz.muni.airport.model.Flight;
import cz.muni.airport.model.enums.FlightState;
import cz.muni.airport.model.enums.PlaneType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests all methods of FlightDAOImpl.
 *
 * @author Jiri Krejci, github name: xkrejci7
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringApplicationConfiguration(testConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
@PropertySource("/application.properties")
// Enable JMX so we can test the MBeans (you can't do this in a properties file)
@TestPropertySource(properties = { "spring.jmx.enabled:true",
        "spring.datasource.jmx-enabled:true" })

public class FlightDAOImplTest {

    @Autowired(required = true)
    private FlightDAO flightDAO;

    @Autowired(required = true)
    private AirplaneDAO airplaneDAO;

    @Autowired(required = true)
    private AirportDAO airportDAO;

    @Test
    public void testAddFlight() throws Exception {

        flightDAO.addFlight(createFlight1());
        assertEquals(1, flightDAO.getAllFlights().size());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddFlight_null() throws Exception {
        Flight flight = null;
        flightDAO.addFlight(flight);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveFlight_null() throws Exception {
        Flight flight = null;
        flightDAO.removeFlight(flight);
    }

    @Test
    public void testRemoveFlight() throws Exception {

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
    public void testUpdateFlight() throws Exception {

        Flight flight = flightDAO.addFlight(createFlight1());

        Calendar newArrival = Calendar.getInstance();
        newArrival.set(2015, Calendar.JANUARY, 21, 11, 12);
        Calendar newDeparture = Calendar.getInstance();
        newDeparture.set(2017, Calendar.FEBRUARY, 21, 10, 10);

        Airport newDestination = new Airport();
        newDestination.setCity("Vienna");
        newDestination.setIata("VIE");
        newDestination.setCountry("D");
        newDestination.setName("Vienna airport");
        newDestination = airportDAO.addAirport(newDestination);

        Airport newSource = new Airport();
        newSource.setCity("Kosice");
        newSource.setIata("LKO");
        newSource.setCountry("SK");
        newSource.setName("Letisko Kosice");
        newSource = airportDAO.addAirport(newSource);

        Airplane newAirplane = new Airplane();
        newAirplane.setCapacity(30);
        newAirplane.setName("Kane");
        newAirplane.setType(PlaneType.HELICOPTER);
        newAirplane = airplaneDAO.addAirplane(newAirplane);

        flight.setName("FlightChanged");
        flight.setArrival(newArrival.getTime());
        flight.setDeparture(newDeparture.getTime());
        flight.setPassagers(10);
        flight.setDestinationPort(newDestination);
        flight.setSourcePort(newSource);
        flight.setAirplane(newAirplane);
        flight.setFlightState(FlightState.ACCEPTED);

        flight = flightDAO.updateFlight(flight);

        assertEquals("FlightChanged", flightDAO.getFlightById(flight.getId()).getName());
        assertEquals(newArrival.getTime(), flightDAO.getFlightById(flight.getId()).getArrival());
        assertEquals(newDeparture.getTime(), flightDAO.getFlightById(flight.getId()).getDeparture());
        assertEquals(new Integer(10), flightDAO.getFlightById(flight.getId()).getPassagers());
        assertEquals(newAirplane, flightDAO.getFlightById(flight.getId()).getAirplane());
        assertEquals(newSource, flightDAO.getFlightById(flight.getId()).getSourcePort());
        assertEquals(newDestination, flightDAO.getFlightById(flight.getId()).getDestinationPort());
        assertEquals(flight, flightDAO.getFlightById(flight.getId()));
        assertEquals(FlightState.ACCEPTED, flightDAO.getFlightById(flight.getId()).getFlightState());
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateFlight_null() throws Exception {
        Flight flight = null;
        flightDAO.updateFlight(flight);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetFlightById_null() throws Exception {
        Long id = null;
        flightDAO.getFlightById(id);
    }

    @Test
    public void testGetFlightById_nonexistent() throws Exception {
        Flight flight = flightDAO.getFlightById((long) 2);
        assertNull(flight);

    }

    @Test
    public void testGetFlightById() throws Exception {

        Flight f1 = createFlight1();
        Flight f2 = createFlight2();
        Flight added = flightDAO.addFlight(f1);
        flightDAO.addFlight(f2);

        Flight retrived = flightDAO.getFlightById(added.getId());

        assertEquals(added, retrived);

    }

    @Test
    public void testGetAllFlights() throws Exception {

        assertEquals(0, flightDAO.getAllFlights().size());

        flightDAO.addFlight(createFlight1());
        assertEquals(1, flightDAO.getAllFlights().size());

        flightDAO.addFlight(createFlight2());
        assertEquals(2, flightDAO.getAllFlights().size());
    }

    @Test
    public void testGetFlightByName() throws Exception {

        Flight f = createFlight1();
        f.setName("Flight123");
        Flight addedToDB = flightDAO.addFlight(f);

        List<Flight> flights = flightDAO.getFlightsByName("Flight123");

        assertEquals(1, flights.size());
        assertEquals(addedToDB, flights.get(0));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetFlightByName_null() throws Exception {

        Flight f1 = createFlight1();
        flightDAO.addFlight(f1);

        flightDAO.getFlightsByName(null);

    }

    @Test
    public void testGetStewardByName_nonexistent() throws Exception {
        flightDAO.addFlight(createFlight1());

        List<Flight> flights = flightDAO.getFlightsByName("FlightX");
        assertTrue(flights.isEmpty());
    }

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
        airplane = airplaneDAO.addAirplane(airplane);

        Airport destination = new Airport();
        destination.setCity("Brno");
        destination.setIata("BRN");
        destination.setCountry("CZ");
        destination.setName("Letiste Turany");
        destination = airportDAO.addAirport(destination);

        Airport source = new Airport();
        source.setCity("Praha");
        source.setIata("PRG");
        source.setCountry("CZ");
        source.setName("Letiste Vaclava Havla");
        source = airportDAO.addAirport(source);

        Flight flight = new Flight();
        flight.setName("Flight1");
        flight.setArrival(arrival.getTime());
        flight.setDeparture(departure.getTime());
        flight.setPassagers(50);
        flight.setAirplane(airplane);
        flight.setDestinationPort(destination);
        flight.setSourcePort(source);
        flight.setFlightState(FlightState.OPEN);

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
        airplaneDAO.addAirplane(airplane);

        Airport destination = new Airport();
        destination.setCity("Bratislava");
        destination.setCountry("SK");
        destination.setName("Letiste Blava");
        destination.setIata("LBR");
        airportDAO.addAirport(destination);

        Airport source = new Airport();
        source.setCity("Milano");
        source.setCountry("IT");
        source.setName("Milano airport");
        source.setIata("MLO");
        airportDAO.addAirport(source);

        Flight flight = new Flight();
        flight.setName("Flight2");
        flight.setArrival(arrival.getTime());
        flight.setDeparture(departure.getTime());
        flight.setPassagers(90);
        flight.setAirplane(airplane);
        flight.setDestinationPort(destination);
        flight.setSourcePort(source);
        flight.setFlightState(FlightState.OPEN);

        return flight;
    }

}
