package cz.muni.airport.services.impl;

import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cz.muni.airport.dao.AirplaneDAO;
import cz.muni.airport.dao.FlightDAO;
import cz.muni.airport.dao.StewardDAO;
import cz.muni.airport.model.Airplane;
import cz.muni.airport.model.Airport;
import cz.muni.airport.model.Flight;
import cz.muni.airport.model.enums.PlaneType;
import cz.muni.airport.services.AirplaneService;
import cz.muni.airport.services.FlightService;
import cz.muni.airport.services.StewardService;

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

    @Mock
    private StewardDAO stewardDAO;

    @InjectMocks
    private AirplaneService airplaneService = new AirplaneServiceImpl();

    @InjectMocks
    private StewardService stewardService = new StewardServiceImpl();

    @InjectMocks
    private FlightService flightService = new FlightServiceImpl();

    public AirplaneServiceImplTest(){}

    private Airplane airplane2;
    private Flight flight2;
    private List<Flight> allFlights = new ArrayList<>(2);

    @BeforeMethod
    public void prepareObjects() {

        Airport bratiska = new Airport();
        Airport kosice = new Airport();
        bratiska.setName("Bratislava");
        kosice.setName("Kosice");

        List<Airplane> allAirplanes = new ArrayList<>(3);
        Airplane airplane = new Airplane();
        airplane.setName("Boening-477");
        airplane.setCapacity(50);
        airplane.setType(PlaneType.AIRLINER);

        Airplane airplane1 = new Airplane();
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

        Flight flight = new Flight();
        flight.setName("LetCislo42");
        flight.setAirplane(airplane);

        Calendar arrival = Calendar.getInstance();
        arrival.set(2016, Calendar.DECEMBER, 20, 10, 22);

        Calendar departure = Calendar.getInstance();
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


}
