package cz.muni.airport.facade.impl;

import cz.muni.airport.dto.AirplaneDTO;
import cz.muni.airport.dto.AirportDTO;
import cz.muni.airport.dto.FlightDTO;
import cz.muni.airport.enums.FlightState;
import cz.muni.airport.facadeApi.FlightFacade;
import cz.muni.airport.model.Airplane;
import cz.muni.airport.model.Airport;
import cz.muni.airport.model.Flight;
import cz.muni.airport.services.BeanMappingService;
import cz.muni.airport.services.FlightService;
import cz.muni.airport.services.impl.AirplaneServiceImpl;
import cz.muni.airport.services.impl.AirportServiceImpl;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Jirka
 */
@RunWith(MockitoJUnitRunner.class)
public class FlightFacadeImplTest {

    @Mock
    private FlightService flightService;

    @Mock
    private AirportFacadeImpl airportFacadeImpl;

    @Mock
    private AirportServiceImpl airportServiceImpl;

    @Mock
    private AirplaneFacadeImpl airplaneFacadeImpl;

    @Mock
    private AirplaneServiceImpl airplaneServiceImpl;

    @Mock
    private BeanMappingService beanMapperService;

    @InjectMocks
    private FlightFacade flightFacade = new FlightFacadeImpl();

    List<Flight> data;

    @Before
    public void initialize() {
        data = new ArrayList<>();
    }

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
        airplane.setType(cz.muni.airport.model.enums.PlaneType.CARGO);
        airplaneFacadeImpl.createAirplane(airplane);

        AirportDTO destination = new AirportDTO();
        destination.setCity("Brno");
        destination.setIata("BRN");
        destination.setCountry("CZ");
        destination.setName("Letiste Turany");
        airportFacadeImpl.createAirport(destination);

        AirportDTO source = new AirportDTO();
        source.setCity("Praha");
        source.setIata("PRG");
        source.setCountry("CZ");
        source.setName("Letiste Vaclava Havla");
        airportFacadeImpl.createAirport(source);

        FlightDTO flight = new FlightDTO();
        flight.setName("Flight1");
        flight.setArrival(arrival.getTimeInMillis());
        flight.setDeparture(departure.getTimeInMillis());
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
