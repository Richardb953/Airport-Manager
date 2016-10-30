package cz.muni.airport.dao;

import cz.muni.airport.model.Airplane;
import cz.muni.airport.model.Flight;
import cz.muni.airport.model.PlaneType;
import java.util.Calendar;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 *
 *
 * @author Jiri Krejci
 */
@ContextConfiguration(locations = {"WEB-INF/applicationContext.xml"})
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class FlightDAOTest {

    @Autowired
    private FlightDAO flightDAO;
    
    @Test
    public void firstTete() {
        
        Flight flight = new Flight();
        flight.setName("LetCislo42");

        Calendar arrival = Calendar.getInstance();
        arrival.set(2016, Calendar.DECEMBER, 20, 10, 22);

        Calendar departure = Calendar.getInstance();
        departure.set(2016, Calendar.DECEMBER, 20, 11, 22);

        flight.setArrival(arrival.getTime());
        flight.setDeparture(departure.getTime());
        flight.setPassagers(50);
        flight.setAirplane(null);
        flight.setDestinationPort(null);
        flight.setSourcePort(null);
        
        Flight saved = flightDAO.addFlight(flight);
        
        System.out.println(saved);
        
        Assert.assertEquals(1, flightDAO.getAllFlights().size());

        
    }

}
