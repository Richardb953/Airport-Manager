package cz.muni.airport.dao.impl;

import cz.muni.airport.dao.AirportDAO;
import cz.muni.airport.dao.FlightDAO;
import cz.muni.airport.model.Airplane;
import cz.muni.airport.model.Airport;
import cz.muni.airport.model.Flight;
import cz.muni.airport.model.PlaneType;
import java.util.Calendar;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 *
 *
 * @author Jiri Krejci
 */
public class FlightDAOImplTest {

    private final FlightDAO flightDAO;

    private AirportDAO airportDAO;

    public FlightDAOImplTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:WEB-INF/applicationContext.xml");
        flightDAO = ctx.getBean(FlightDAO.class);
    }

    @Test
    public void testAddFlight() {

        Calendar arrival = Calendar.getInstance();
        arrival.set(2016, Calendar.DECEMBER, 20, 10, 22);

        Calendar departure = Calendar.getInstance();
        departure.set(2016, Calendar.DECEMBER, 20, 11, 22);

        Airplane airplane = new Airplane();
        airplane.setCapacity(100);
        airplane.setFlights(null);
        airplane.setName("Boeing 747");
        airplane.setType(PlaneType.BUSINESS_JET);
//        airplaneDAO.addAirplane(airplane);

        Airport destination = new Airport();
        destination.setCity("Brno");
        destination.setCountry("CZ");
        destination.setName("Letiste Turany");
//        airportDAO.addAirport(destination);

        Airport source = new Airport();
        source.setCity("Praha");
        source.setCountry("CZ");
        source.setName("Letiste Vaclava Havla");
        
        airportDAO.addAirport(source);

        Flight flight = new Flight();
        flight.setName("Flight1");
        flight.setArrival(arrival.getTime());
        flight.setDeparture(departure.getTime());
        flight.setPassagers(50);
        flight.setAirplane(null);
        flight.setDestinationPort(null);
        flight.setSourcePort(source);

        flightDAO.addFlight(flight);

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

}
