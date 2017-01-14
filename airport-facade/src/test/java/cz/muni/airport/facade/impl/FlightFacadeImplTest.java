package cz.muni.airport.facade.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cz.muni.airport.dto.AirplaneDTO;
import cz.muni.airport.dto.AirportDTO;
import cz.muni.airport.dto.FlightDTO;
import cz.muni.airport.enums.FlightState;
import cz.muni.airport.facadeApi.AirplaneFacade;
import cz.muni.airport.facadeApi.AirportFacade;
import cz.muni.airport.facadeApi.FlightFacade;
import cz.muni.airport.model.Airplane;
import cz.muni.airport.model.Airport;
import cz.muni.airport.model.Flight;
import cz.muni.airport.services.AirplaneService;
import cz.muni.airport.services.AirportService;
import cz.muni.airport.services.BeanMappingService;
import cz.muni.airport.services.FlightService;
import cz.muni.airport.services.StewardService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author Jirka
 */
@RunWith(MockitoJUnitRunner.class)
public class FlightFacadeImplTest {

    @Mock
    private FlightService flightService;

    @Mock
    private AirportFacade airportFacade;

    @Mock
    private AirportService airportService;

    @Mock
    private AirplaneFacade airplaneFacade;

    @Mock
    private AirplaneService airplaneService;

    @Mock
    private BeanMappingService beanMapperService;

    @Mock
    private StewardService stewardService;

    @InjectMocks
    private FlightFacade flightFacade = new FlightFacadeImpl();

    @Test
    public void shouldAutowireDependencies() {
        assertNotNull(flightFacade);
    }

    private FlightDTO prepareFlightDTO1() {
        Calendar arrival = Calendar.getInstance();
        arrival.set(2016, Calendar.DECEMBER, 20, 10, 22);

        Calendar departure = Calendar.getInstance();
        departure.set(2016, Calendar.DECEMBER, 20, 11, 22);

        AirplaneDTO airplane = new AirplaneDTO();
        airplane.setCapacity(100);
        airplane.setFlights(null);
        airplane.setName("Boeing 747");
        airplane.setType(cz.muni.airport.enums.PlaneType.CARGO);
        airplaneFacade.createAirplane(airplane);

        AirportDTO destination = new AirportDTO();
        destination.setCity("Brno");
        destination.setIata("BRN");
        destination.setCountry("CZ");
        destination.setName("Letiste Turany");
        airportFacade.createAirport(destination);

        AirportDTO source = new AirportDTO();
        source.setCity("Praha");
        source.setIata("PRG");
        source.setCountry("CZ");
        source.setName("Letiste Vaclava Havla");
        airportFacade.createAirport(source);

        FlightDTO flight = new FlightDTO();
        flight.setName("Flight1");
        flight.setArrival(arrival.getTime());
        flight.setDeparture(departure.getTime());
        flight.setPassagers(50);
        flight.setAirplane(airplane);
        flight.setDestinationport(destination);
        flight.setSourceport(source);
        flight.setFlightState(FlightState.OPEN);

        return flight;
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
        airplane = airplaneService.saveAirplane(airplane);

        Airport destination = new Airport();
        destination.setCity("Brno");
        destination.setIata("BRN");
        destination.setCountry("CZ");
        destination.setName("Letiste Turany");
        destination = airportService.saveAirport(destination);

        Airport source = new Airport();
        source.setCity("Praha");
        source.setIata("PRG");
        source.setCountry("CZ");
        source.setName("Letiste Vaclava Havla");
        source = airportService.saveAirport(source);

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
    public void testCreateFlight() {

        FlightDTO flightDTO1 = prepareFlightDTO1();
        Flight flight1 = prepareFlight1();

        when(beanMapperService.mapTo(flightDTO1, Flight.class)).thenReturn(flight1);
        when(beanMapperService.mapTo(flight1, FlightDTO.class)).thenReturn(flightDTO1);
        when(beanMapperService.mapTo(flight1.getId(), FlightDTO.class)).thenReturn(flightDTO1);
        when(flightService.saveFlight(flight1)).thenReturn(flight1);

        FlightDTO created = flightFacade.getFlightById(flightFacade.createFlight(flightDTO1));

        assertEquals(flightDTO1, created);
    }

    @Test
    public void testUpdateFlight() {

        FlightDTO flightDTO1 = prepareFlightDTO1();
        Flight flight1 = prepareFlight1();

        when(beanMapperService.mapTo(flightDTO1, Flight.class)).thenReturn(flight1);
        when(beanMapperService.mapTo(flight1, FlightDTO.class)).thenReturn(flightDTO1);
        when(beanMapperService.mapTo(flight1.getId(), FlightDTO.class)).thenReturn(flightDTO1);
        when(flightService.updateFlight(flight1)).thenReturn(flight1);

        FlightDTO updated = flightFacade.updateFlight(flightDTO1);

        assertEquals(flightDTO1, updated);
        verify(flightService).updateFlight(flight1);

    }

    @Test
    public void testDeleteSteward() {

        FlightDTO flightDTO1 = prepareFlightDTO1();
        Flight flight1 = prepareFlight1();

        when(beanMapperService.mapTo(flightDTO1, Flight.class)).thenReturn(flight1);
        when(beanMapperService.mapTo(flight1, FlightDTO.class)).thenReturn(flightDTO1);
        when(beanMapperService.mapTo(flight1.getId(), FlightDTO.class)).thenReturn(flightDTO1);
        when(flightService.saveFlight(flight1)).thenReturn(flight1);

        flightFacade.removeFlight(flightFacade.createFlight(flightDTO1));

        verify(beanMapperService).mapTo(flightDTO1, Flight.class);
    }

    @Test
    public void testGetAllStewards() {

        FlightDTO flightDTO1 = prepareFlightDTO1();
        Flight flight1 = prepareFlight1();
        List<Flight> res = new ArrayList<>();
        res.add(flight1);

        when(beanMapperService.mapTo(flightDTO1, Flight.class)).thenReturn(flight1);
        when(beanMapperService.mapTo(flight1, FlightDTO.class)).thenReturn(flightDTO1);
        when(beanMapperService.mapTo(flight1.getId(), FlightDTO.class)).thenReturn(flightDTO1);
        when(flightService.findAllFlights()).thenReturn(res);
        
        List<FlightDTO> result = flightFacade.getAllFlights();
        
        assertEquals(result.size(), 0);

        verify(beanMapperService).mapTo(res, FlightDTO.class);
        verify(flightService).findAllFlights();

    }

}
