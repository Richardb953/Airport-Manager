package cz.muni.airport.service;

import org.hibernate.service.spi.ServiceException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.IllegalFormatCodePointException;
import java.util.List;

import cz.muni.airport.config.ServiceConfiguration;
import cz.muni.airport.dao.AirplaneDAO;
import cz.muni.airport.dao.FlightDAO;
import cz.muni.airport.model.Airplane;
import cz.muni.airport.model.Airport;
import cz.muni.airport.model.Flight;
import cz.muni.airport.model.enums.PlaneType;
import cz.muni.airport.services.AirplaneService;
import cz.muni.airport.services.FlightService;
import cz.muni.airport.services.impl.AirplaneServiceImpl;
import cz.muni.airport.services.impl.FlightServiceImpl;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by Richard Bariny on 25.11.2016.
 *
 * @author Richard Bariny, github name:Richardb953
 */

public class AirplaneServiceImplTest {
    @Mock
    private AirplaneDAO airplaneDAO;

    @Mock
    private FlightDAO flightDAO;

    @InjectMocks
    private AirplaneServiceImpl airplaneService = new AirplaneServiceImpl();

    @InjectMocks
    private FlightServiceImpl flightService = new FlightServiceImpl();

    public AirplaneServiceImplTest(){}

    private Airplane airplane, airplane1, airplane2;
    private Flight flight, flight2;
    private Airport bratiska, kosice;
    private List<Airplane> allAirplanes = new ArrayList<>(3);
    private List<Flight> allFlights = new ArrayList<>(2);
    Calendar arrival;
    Calendar departure;

    @BeforeMethod
    public void prepareObjects() {

        bratiska = new Airport();
        kosice = new Airport();
        bratiska.setName("Bratislava");
        kosice.setName("Kosice");

        allAirplanes = new ArrayList<>(3);
        airplane = new Airplane();
        airplane.setName("Boening-477");
        airplane.setCapacity(50);
        airplane.setType(PlaneType.AIRLINER);

        airplane1 = new Airplane();
        airplane.setName("Boening-478");
        airplane.setCapacity(50);
        airplane.setType(PlaneType.AIRLINER);

        airplane2 = new Airplane();
        airplane.setName("Boening-479");
        airplane.setCapacity(50);
        airplane.setType(PlaneType.AIRLINER);

        allAirplanes.add(airplane);
        allAirplanes.add(airplane1);
        allAirplanes.add(airplane2);

        flight = new Flight();
        flight.setName("LetCislo42");
        flight.setAirplane(airplane);

        arrival = Calendar.getInstance();
        arrival.set(2016, Calendar.DECEMBER, 20, 10, 22);

        departure = Calendar.getInstance();
        departure.set(2016, Calendar.DECEMBER, 20, 11, 22);

        flight.setArrival(arrival.getTime());
        flight.setDeparture(departure.getTime());
        flight.setPassagers(50);

        flight2 = new Flight();
        flight.setAirplane(airplane1);
        arrival.add(Calendar.HOUR, 2);
        flight2.setArrival(arrival.getTime());
        departure.add(Calendar.HOUR, 5);
        flight2.setDeparture(departure.getTime());
        flight2.setName("letoCislo439797");
        flight2.setDestinationPort(kosice);
        flight2.setSourcePort(bratiska);

        allFlights.add(flight);
        allFlights.add(flight2);

        airplane.addFlight(flight);
        airplane1.addFlight(flight2);

        airplaneService.saveAirplane(airplane);
        airplaneService.saveAirplane(airplane1);
        airplaneService.saveAirplane(airplane2);

        flightService.saveFlight(flight2);
        flightService.saveFlight(flight);

        //airplane 2 is free
    }

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void isAvailableCheckNullAirport(){
        flight2.setSourcePort(null);
        airplaneService.isAvailable(airplane2, flight2);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void isAvailableCheckNullAirport2(){
        flight2.setDestinationPort(null);
        airplaneService.isAvailable(airplane2, flight2);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void isAvailableCheckNulTime(){
        flight2.setArrival(null);
        airplaneService.isAvailable(airplane2, flight2);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void isAvailableCheckNulTime2(){
        flight2.setDeparture(null);
        airplaneService.isAvailable(airplane2, flight2);
    }

    @Test
    public void isAvailableCheckEmpty(){
        Assert.assertEquals(airplaneService.isAvailable(airplane2, flight2), true);
    }

    @Test
    public void isAvailableCheck(){
        Assert.assertEquals(airplaneService.isAvailable(airplane2, flight2), true);
    }

    @Test
    public void getAvailableAirplanes(){
        Flight flight = new Flight();
        flight.setName("letCisloAvailable");
        departure.add(Calendar.HOUR, 20);
        flight.setDeparture(departure.getTime());
        arrival.add(Calendar.HOUR, 20);
        flight.setArrival(arrival.getTime());
        flight.setSourcePort(bratiska);
        flight.setDestinationPort(kosice);

        when(airplaneService.getAllAirplanes()).thenReturn(allAirplanes);
        Assert.assertEquals(airplaneService.getAvailableAirplanes(flight).size(), 1);

    }

}
