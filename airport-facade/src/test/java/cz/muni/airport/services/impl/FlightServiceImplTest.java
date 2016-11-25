package cz.muni.airport.services.impl;

import cz.muni.airport.dao.FlightDAO;
import cz.muni.airport.model.Airplane;
import cz.muni.airport.model.Airport;
import cz.muni.airport.model.Flight;
import cz.muni.airport.model.Steward;
import java.util.Calendar;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Jiri Krejci, github name: xkrejci7
 */
@RunWith(MockitoJUnitRunner.class)
public class FlightServiceImplTest {
    
    @Mock
    private FlightDAO flightDAO;
    
    @Mock
    private AirplaneServiceImpl airplaneServiceImpl;
    
    @Mock
    private AirportServiceImpl airportServiceImpl;
    
    @InjectMocks
    private FlightServiceImpl flightService = new FlightServiceImpl();
    
    public FlightServiceImplTest() {
    }
    
    private Flight prepareFlight1() {
        Calendar arrival = Calendar.getInstance();
        arrival.set(2016, Calendar.DECEMBER, 20, 10, 22);

        Calendar departure = Calendar.getInstance();
        departure.set(2016, Calendar.DECEMBER, 20, 11, 22);

        Airplane airplane = new Airplane();
        airplane.setCapacity(100);
        airplane.setFlights(null);
        airplane.setName("Boeing 747");
        airplane.setType(cz.muni.airport.model.enums.PlaneType.BUSINESS_JET);
        airplane = airplaneServiceImpl.saveAirplane(airplane);

        Airport destination = new Airport();
        destination.setCity("Brno");
        destination.setIata("BRN");
        destination.setCountry("CZ");
        destination.setName("Letiste Turany");
        destination = airportServiceImpl.saveAirport(destination);

        Airport source = new Airport();
        source.setCity("Praha");
        source.setIata("PRG");
        source.setCountry("CZ");
        source.setName("Letiste Vaclava Havla");
        source = airportServiceImpl.saveAirport(source);

        Flight flight = new Flight();
        flight.setName("Flight1");
        flight.setArrival(arrival.getTime());
        flight.setDeparture(departure.getTime());
        flight.setPassagers(50);
        flight.setAirplane(airplane);
        flight.setDestinationPort(destination);
        flight.setSourcePort(source);
        flight.setFlightState(cz.muni.airport.model.enums.FlightState.OPEN);

        return flight;
    }
    
    @Test
    public void testAddFlight() {
        
        Flight flight1 = prepareFlight1();
        when(flightDAO.addFlight(flight1)).thenReturn(flight1);
        
        Flight added = flightService.saveFlight(flight1);
        
        verify(flightDAO).addFlight(flight1);
        assertEquals(flight1, added);
    
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void  testAddFlightNull(){
        when(flightDAO.addFlight(null)).thenThrow(new IllegalArgumentException("flight is null"));

        flightService.saveFlight(null);
    }
    
    @Test
    public void testRemoveSteward() {
        Flight flight1 = prepareFlight1();
        
        flightService.removeFlight(flight1);
        
        verify(flightDAO).removeFlight(flight1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveFlightNull() {
        doThrow(new IllegalArgumentException("flight is null")).when(flightDAO).removeFlight(null);
        flightService.removeFlight(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateFlightNull() {
        when(flightDAO.updateFlight(null)).thenThrow(new IllegalArgumentException("flight is null"));
        flightService.updateFlight(null);
    }
    
    @Test
    public void testGetSteward() {
        Flight flight1 = prepareFlight1();
        when(flightDAO.getFlightById(Long.MIN_VALUE)).thenReturn(flight1);
        
        Flight gotten = flightService.getFlight(Long.MIN_VALUE);
        
        verify(flightDAO).getFlightById(Long.MIN_VALUE);
        assertEquals(flight1, gotten);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetFlightNull() {
        when(flightDAO.getFlightById(null)).thenThrow(new IllegalArgumentException());
        Flight gotten = flightService.getFlight(null);
    }
}
