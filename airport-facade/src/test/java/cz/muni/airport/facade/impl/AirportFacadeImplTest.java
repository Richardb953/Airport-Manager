package cz.muni.airport.facade.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import cz.muni.airport.dto.AirportCreateDTO;
import cz.muni.airport.dto.AirportDTO;
import cz.muni.airport.facadeApi.AirportFacade;
import cz.muni.airport.model.Airport;
import cz.muni.airport.services.AirportService;
import cz.muni.airport.services.BeanMappingService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author Andrea Navratilova, github name: andrea-n
 */
@RunWith(MockitoJUnitRunner.class)
public class AirportFacadeImplTest {

    @Mock
    private AirportService airportService;

    @Mock
    private BeanMappingService beanMapperService;

    @InjectMocks
    private AirportFacade airportFacade = new AirportFacadeImpl();

    public AirportFacadeImplTest() {
    }

    private AirportDTO createAirportDTO1() {
        AirportDTO airportDTO = new AirportDTO();
        airportDTO.setCity("Brno");
        airportDTO.setIata("BRN");
        airportDTO.setCountry("Czech republic");
        airportDTO.setName("Brno letiste");
        return airportDTO;
    }

    private AirportCreateDTO createAirportCreateDTO1() {
        AirportCreateDTO airportDTO = new AirportCreateDTO();
        airportDTO.setCity("Brno");
        airportDTO.setIata("BRN");
        airportDTO.setCountry("Czech republic");
        airportDTO.setName("Brno letiste");
        return airportDTO;
    }

    private Airport createAirport1() {
        Airport airport = new Airport();
        airport.setCity("Brno");
        airport.setIata("BRN");
        airport.setCountry("Czech republic");
        airport.setName("Brno letiste");
        return airport;
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of createAirport method, of class AirportFacadeImpl.
     */
    @Test
    public void testCreateAirport() {
        System.out.println("createAirport");

        Airport airport = createAirport1();
        AirportCreateDTO airportCreateDTO = createAirportCreateDTO1();
        AirportDTO airportDTO = createAirportDTO1();

        when(beanMapperService.mapTo(airportCreateDTO, Airport.class)).thenReturn(airport);
        when(beanMapperService.mapTo(airport, AirportDTO.class)).thenReturn(airportDTO);
        when(airportService.saveAirport(airport)).thenReturn(airport);

        AirportDTO created = airportFacade.createAirport(airportCreateDTO);
        assertEquals(airportDTO, created);
    }

    /**
     * Test of getAllAirports method, of class AirportFacadeImpl.
     */
    @Test
    public void testGetAllAirports() {
        System.out.println("getAllAirports");
        Airport airport = createAirport1();
        AirportDTO airportDTO = createAirportDTO1();

        List<Airport> airports = new ArrayList<>();
        airports.add(airport);

        when(beanMapperService.mapTo(airportDTO, Airport.class)).thenReturn(airport);
        when(beanMapperService.mapTo(airport, AirportDTO.class)).thenReturn(airportDTO);
        when(airportService.findAllAirports()).thenReturn(airports);

        List<AirportDTO> result = airportFacade.getAllAirports();

        verify(beanMapperService).mapTo(airports, AirportDTO.class);
        verify(airportService).findAllAirports();
    }

    /**
     * Test of getAirportById method, of class AirportFacadeImpl.
     */
    @Test
    public void testGetAirportById() {
        System.out.println("getAirportById");
        Airport airport = createAirport1();
        AirportDTO airportDTO = createAirportDTO1();

        when(beanMapperService.mapTo(airportDTO, Airport.class)).thenReturn(airport);
        when(beanMapperService.mapTo(airport, AirportDTO.class)).thenReturn(airportDTO);
        when(airportService.findAirportById(anyLong())).thenReturn(airport);

        AirportDTO result = airportFacade.getAirportById(Long.MIN_VALUE);
        verify(beanMapperService).mapTo(airport, AirportDTO.class);
        verify(airportService).findAirportById(Long.MIN_VALUE);
    }

    /**
     * Test of getAirportsByIata method, of class AirportFacadeImpl.
     */
    @Test
    public void testGetAirportsByIata() {
        System.out.println("getAirportsByIata");
        Airport airport = createAirport1();
        AirportDTO airportDTO = createAirportDTO1();

        List<Airport> airports = new ArrayList<>();
        airports.add(airport);
        String iata = "BRN";

        when(beanMapperService.mapTo(airportDTO, Airport.class)).thenReturn(airport);
        when(beanMapperService.mapTo(airport, AirportDTO.class)).thenReturn(airportDTO);
        when(airportService.findAirportByIata(iata)).thenReturn(airports);

        List<AirportDTO> result = airportFacade.getAirportsByIata(iata);

        verify(beanMapperService).mapTo(airports, AirportDTO.class);
        verify(airportService).findAirportByIata(iata);
    }

    /**
     * Test of getAirportsByCity method, of class AirportFacadeImpl.
     */
    @Test
    public void testGetAirportsByCity() {
        System.out.println("getAirportsByCity");
        Airport airport = createAirport1();
        AirportDTO airportDTO = createAirportDTO1();

        List<Airport> airports = new ArrayList<>();
        airports.add(airport);

        String city = "Brno";

        when(beanMapperService.mapTo(airportDTO, Airport.class)).thenReturn(airport);
        when(beanMapperService.mapTo(airport, AirportDTO.class)).thenReturn(airportDTO);
        when(airportService.findAirportsByCity(city)).thenReturn(airports);

        List<AirportDTO> result = airportFacade.getAirportsByCity(city);

        verify(beanMapperService).mapTo(airports, AirportDTO.class);
        verify(airportService).findAirportsByCity(city);
    }

    /**
     * Test of getAirportsByName method, of class AirportFacadeImpl.
     */
    @Test
    public void testGetAirportsByName() {
        System.out.println("getAirportsByName");
        Airport airport = createAirport1();
        AirportDTO airportDTO = createAirportDTO1();

        List<Airport> airports = new ArrayList<>();
        airports.add(airport);

        String name = "Brno letiste";

        when(beanMapperService.mapTo(airportDTO, Airport.class)).thenReturn(airport);
        when(beanMapperService.mapTo(airport, AirportDTO.class)).thenReturn(airportDTO);
        when(airportService.findAirportsByName(name)).thenReturn(airports);

        List<AirportDTO> result = airportFacade.getAirportsByName(name);

        verify(beanMapperService).mapTo(airports, AirportDTO.class);
        verify(airportService).findAirportsByName(name);
    }

    /**
     * Test of getAirportsByCountry method, of class AirportFacadeImpl.
     */
    @Test
    public void testGetAirportsByCountry() {
        System.out.println("getAirportsByCountry");
        Airport airport = createAirport1();
        AirportDTO airportDTO = createAirportDTO1();

        List<Airport> airports = new ArrayList<>();
        airports.add(airport);

        String country = "Czech republic";

        when(beanMapperService.mapTo(airportDTO, Airport.class)).thenReturn(airport);
        when(beanMapperService.mapTo(airport, AirportDTO.class)).thenReturn(airportDTO);
        when(airportService.findAirportsByCountry(country)).thenReturn(airports);

        List<AirportDTO> result = airportFacade.getAirportsByCountry(country);

        verify(beanMapperService).mapTo(airports, AirportDTO.class);
        verify(airportService).findAirportsByCountry(country);
    }

    /**
     * Test of updateAirport method, of class AirportFacadeImpl.
     */
    @Test
    public void testUpdateAirport() {
        System.out.println("updateAirport");
        Airport airport = createAirport1();
        AirportDTO airportDTO = createAirportDTO1();

        when(beanMapperService.mapTo(airportDTO, Airport.class)).thenReturn(airport);
        when(beanMapperService.mapTo(airport, AirportDTO.class)).thenReturn(airportDTO);
        when(airportService.updateAirport(airport)).thenReturn(airport);

        AirportDTO created = airportFacade.updateAirport(airportDTO);
        assertEquals(airportDTO, created);
    }

}
