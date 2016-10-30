package cz.muni.airport.dao.impl;

import cz.muni.airport.dao.AirportDAO;
import cz.muni.airport.model.Airport;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Tests for AirportDAOImpl class
 * @author Andrea Navratilova
 */
public class AirportDAOImplTest {
	
	//@Autowired(required = true)
	private AirportDAO airportDAO;
	
	public AirportDAOImplTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:WEB-INF/applicationContext.xml");
        airportDAO = ctx.getBean(AirportDAO.class);
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
		
	}
	
	@After
	public void tearDown() {
	}

	/**
	 * Test of addAirport method, of class AirportDAOImpl.
	 */
	@Test
	public void testAddAirport() {
		System.out.println("add Airport");
		
		Airport airport = new Airport();
		airport.setName("Brno letištì");
		airport.setCountry("Èeská republika");
		airport.setCity("Brno");
		airportDAO.addAirport(airport);
		assertEquals(1, airportDAO.getAllAirports().size());
		assertEquals(airport, airportDAO.getAirportsByName("Brno letištì").get(0));
	}

	/**
	 * Test of updateAirport method, of class AirportDAOImpl.
	 */
	@Test
	public void testUpdateAirport() {
		System.out.println("update Airport");
		
		Airport airport = new Airport();
		airport.setName("Brno letištì");
		airport.setCountry("Èeská republika");
		airport.setCity("Brno");
		airportDAO.addAirport(airport);
		
		Airport airportUpdate = airportDAO.getAirportsByName("Brno letištì").get(0);
		airportUpdate.setName("Paris Aéroport");
		airportUpdate.setCountry("France");
		airportUpdate.setCity("Paris");
		airportDAO.updateAirport(airportUpdate);
		
		assertEquals("Paris Aéroport", airportDAO.getAirportById(airportUpdate.getId()).getName());
		assertEquals("France", airportDAO.getAirportById(airportUpdate.getId()).getCountry());
		assertEquals("Paris Aéroport", airportDAO.getAirportById(airportUpdate.getId()).getCity());
	}

	/**
	 * Test of removeAirport method, of class AirportDAOImpl.
	 */
	@Test
	public void testRemoveAirport() {
		System.out.println("remove Airport");
		
		Airport airport1 = new Airport();
		airport1.setName("Brno letištì");
		airport1.setCountry("Èeská republika");
		airport1.setCity("Brno");
		airportDAO.addAirport(airport1);
		
		Airport airport2 = new Airport();
		airport2.setName("Paris Aéroport");
		airport2.setCountry("France");
		airport2.setCity("Paris");
		airportDAO.addAirport(airport2);
		
		airportDAO.removeAirport(airport1);
		assertEquals(1, airportDAO.getAllAirports().size());
		assertEquals("Paris Aéroport", airportDAO.getAllAirports().get(0));
	}

	/**
	 * Test of getAllAirports method, of class AirportDAOImpl.
	 */
	@Test
	public void testGetAllAirports() {
		System.out.println("get all Airports");
		
		Airport airport1 = new Airport();
		airport1.setName("Brno letištì");
		airport1.setCountry("Èeská republika");
		airport1.setCity("Brno");
		airportDAO.addAirport(airport1);
		
		Airport airport2 = new Airport();
		airport2.setName("Paris Aéroport");
		airport2.setCountry("France");
		airport2.setCity("Paris");
		airportDAO.addAirport(airport2);
		
		assertEquals(2, airportDAO.getAllAirports().size());
	}

	/**
	 * Test of getAirportById method, of class AirportDAOImpl.
	 */
	@Test
	public void testGetAirportById() {
		System.out.println("get Airport by id");
		
		Airport airport = new Airport();
		airport.setName("Brno letištì");
		airport.setCountry("Èeská republika");
		airport.setCity("Brno");
		Airport result = airportDAO.addAirport(airport);
		
		assertEquals(result, airportDAO.getAirportById(result.getId()));
	}

	/**
	 * Test of getAirportsByCity method, of class AirportDAOImpl.
	 */
	@Test
	public void testGetAirportsByCity() {
		System.out.println("get Airports by city");
		
		Airport airport1 = new Airport();
		airport1.setName("Brno letištì");
		airport1.setCountry("Èeská republika");
		airport1.setCity("Brno");
		airportDAO.addAirport(airport1);
		
		Airport airport2 = new Airport();
		airport2.setName("Paris Aéroport");
		airport2.setCountry("France");
		airport2.setCity("Paris");
		airportDAO.addAirport(airport2);
		
		assertEquals(airport1, airportDAO.getAirportsByCity("Brno").get(0));
		assertEquals(1, airportDAO.getAirportsByCity("Brno").size());
	}

	/**
	 * Test of getAirportsByName method, of class AirportDAOImpl.
	 */
	@Test
	public void testGetAirportsByName() {
		System.out.println("get Airports by name");
		
		Airport airport1 = new Airport();
		airport1.setName("Brno letištì");
		airport1.setCountry("Èeská republika");
		airport1.setCity("Brno");
		airportDAO.addAirport(airport1);
		
		Airport airport2 = new Airport();
		airport2.setName("Paris Aéroport");
		airport2.setCountry("France");
		airport2.setCity("Paris");
		airportDAO.addAirport(airport2);
		
		assertEquals(airport1, airportDAO.getAirportsByName("Brno letištì").get(0));
		assertEquals(1, airportDAO.getAirportsByName("Brno letištì").size());
	}

	/**
	 * Test of getAirportsByCountry method, of class AirportDAOImpl.
	 */
	@Test
	public void testGetAirportsByCountry() {
		System.out.println("get Airports by country");
		
		Airport airport1 = new Airport();
		airport1.setName("Brno letištì");
		airport1.setCountry("Èeská republika");
		airport1.setCity("Brno");
		airportDAO.addAirport(airport1);
		
		Airport airport2 = new Airport();
		airport2.setName("Paris Aéroport");
		airport2.setCountry("France");
		airport2.setCity("Paris");
		airportDAO.addAirport(airport2);
		
		assertEquals(airport1, airportDAO.getAirportsByCountry("Èeská republika").get(0));
		assertEquals(1, airportDAO.getAirportsByCountry("Èeská republika").size());
	}
	
}
