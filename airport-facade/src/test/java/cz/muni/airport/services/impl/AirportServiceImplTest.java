package cz.muni.airport.services.impl;

import cz.muni.airport.dao.AirportDAO;
import cz.muni.airport.model.Airport;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import static org.springframework.http.ResponseEntity.created;

/**
 *
 * @author Andrea Navratilova, github name: andrea-n
 */
@RunWith(MockitoJUnitRunner.class)
public class AirportServiceImplTest {

	@Mock
	private AirportDAO airportDAO;

	@InjectMocks
	private AirportServiceImpl airportService = new AirportServiceImpl();

	
	public AirportServiceImplTest() {
	}

	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}

	private Airport createAirport(){
		Airport airport = new Airport();
		airport.setCity("Brno");
		airport.setIata("BRN");
		airport.setCountry("Czech republic");
		airport.setName("Brno letiste");
		return airport;
	}

	/**
	 * Test of saveAirport method, of class AirportServiceImpl.
	 */
	@Test
	public void testSaveAirport() {
		System.out.println("saveAirport");
		Airport airport = createAirport();

		when(airportDAO.addAirport(airport)).thenReturn(airport);
		Airport created = airportService.saveAirport(airport);

		verify(airportDAO).addAirport(airport);
		assertEquals(airport, created);
	}

	/**
	 * Test of updateAirport method, of class AirportServiceImpl.
	 */
	@Test
	public void testUpdateAirport() {
		System.out.println("updateAirport");
		Airport airport = createAirport();

		when(airportDAO.updateAirport(airport)).thenReturn(airport);
		Airport updated = airportService.updateAirport(airport);

		verify(airportDAO).updateAirport(airport);
		assertEquals(airport, updated);
	}

	/**
	 * Test of removeAirport method, of class AirportServiceImpl.
	 */
	@Test
	public void testRemoveAirport() {
		System.out.println("removeAirport");
		Airport airport = createAirport();

		airportService.removeAirport(airport);
		verify(airportDAO).removeAirport(airport);
	}

	/**
	 * Test of findAllAirports method, of class AirportServiceImpl.
	 */
	@Test
	public void testFindAllAirports() {
		System.out.println("findAllAirports");
		Airport airport = createAirport();
		List<Airport> airports = new ArrayList<>();
		airports.add(airport);

		when(airportDAO.getAllAirports()).thenReturn(airports);
		List<Airport> result = airportService.findAllAirports();

		verify(airportDAO).getAllAirports();
		assertEquals(airports, result);
	}

	/**
	 * Test of findAirportById method, of class AirportServiceImpl.
	 */
	@Test
	public void testFindAirportById() {
		System.out.println("findAirportById");
		Airport airport = createAirport();

		when(airportDAO.getAirportById(Long.MIN_VALUE)).thenReturn(airport);
		Airport result = airportService.findAirportById(Long.MIN_VALUE);

		verify(airportDAO).getAirportById(Long.MIN_VALUE);
		assertEquals(airport, result);
	}

	/**
	 * Test of findAirportsByCity method, of class AirportServiceImpl.
	 */
	@Test
	public void testFindAirportsByCity() {
		System.out.println("findAirportsByCity");
		Airport airport = createAirport();
		List<Airport> airports = new ArrayList<>();
		airports.add(airport);
		
		String city = "Brno";

		when(airportDAO.getAirportsByCity(city)).thenReturn(airports);
		List<Airport> result = airportService.findAirportsByCity(city);

		verify(airportDAO).getAirportsByCity(city);
		assertEquals(airports, result);
	}

	/**
	 * Test of findAirportsByName method, of class AirportServiceImpl.
	 */
	@Test
	public void testFindAirportsByName() {
		System.out.println("findAirportsByName");
		Airport airport = createAirport();
		List<Airport> airports = new ArrayList<>();
		airports.add(airport);
		
		String name = "Brno letiste";

		when(airportDAO.getAirportsByName(name)).thenReturn(airports);
		List<Airport> result = airportService.findAirportsByName(name);

		verify(airportDAO).getAirportsByName(name);
		assertEquals(airports, result);
	}

	/**
	 * Test of findAirportsByCountry method, of class AirportServiceImpl.
	 */
	@Test
	public void testFindAirportsByCountry() {
		System.out.println("findAirportsByCountry");
		Airport airport = createAirport();
		List<Airport> airports = new ArrayList<>();
		airports.add(airport);
		
		String country = "Czech republic";

		when(airportDAO.getAirportsByCountry(country)).thenReturn(airports);
		List<Airport> result = airportService.findAirportsByCountry(country);

		verify(airportDAO).getAirportsByCountry(country);
		assertEquals(airports, result);
	}

	/**
	 * Test of findAirportByIata method, of class AirportServiceImpl.
	 */
	@Test
	public void testFindAirportByIata() {
		System.out.println("findAirportByIata");
		Airport airport = createAirport();
		List<Airport> airports = new ArrayList<>();
		airports.add(airport);
		
		String itata = "BRN";

		when(airportDAO.getAirportsByIata(itata)).thenReturn(airports);
		List<Airport> result = airportService.findAirportByIata(itata);

		verify(airportDAO).getAirportsByIata(itata);
		assertEquals(airports, result);
	}
}
