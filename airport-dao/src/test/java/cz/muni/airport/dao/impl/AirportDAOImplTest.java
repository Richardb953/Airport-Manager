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
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.springframework.test.annotation.DirtiesContext;

/**
 * Tests for AirportDAOImpl class
 * @author Andrea Navratilova
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AirportDAOImplTest {
	
	//@Autowired(required = true)
	private AirportDAO airportDAO;
	
	public AirportDAOImplTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:WEB-INF/applicationContextDao.xml");
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
		airport.setName("Brno letiste");
		airport.setCountry("Ceska republika");
		airport.setCity("Brno");
		airportDAO.addAirport(airport);
		assertEquals(1, airportDAO.getAllAirports().size());
		assertEquals(airport, airportDAO.getAirportsByName("Brno letiste").get(0));
	}
	
	/**
	 * Test of addAirport method, of class AirportDAOImpl, with null given.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAddAirportWithNull() {
		airportDAO.addAirport(null);
	}
	
	/**
	 * Test of updateAirport method, of class AirportDAOImpl.
	 */
	@Test
	public void testUpdateAirport() {
		System.out.println("update Airport");
		
		Airport airport = new Airport();
		airport.setName("Brno letiste");
		airport.setCountry("Ceska republika");
		airport.setCity("Brno");
		airportDAO.addAirport(airport);
		
		Airport airportUpdate = airportDAO.getAirportsByName("Brno letiste").get(0);
		airportUpdate.setName("Paris Aeroport");
		airportUpdate.setCountry("France");
		airportUpdate.setCity("Paris");
		airportDAO.updateAirport(airportUpdate);
		
		assertEquals("Paris Aeroport", airportDAO.getAirportById(airportUpdate.getId()).getName());
		assertEquals("France", airportDAO.getAirportById(airportUpdate.getId()).getCountry());
		assertEquals("Paris", airportDAO.getAirportById(airportUpdate.getId()).getCity());
	}
	
	/**
	 * Test of updateAirport method, of class AirportDAOImpl, with null given.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testUpdateAirportWithNull() {
		airportDAO.updateAirport(null);
	}
	
	/**
	 * Test of removeAirport method, of class AirportDAOImpl.
	 */
	@Test(expected = HibernateOptimisticLockingFailureException.class)
	public void testUpdateAirportId() {
		Airport airport = new Airport();
		airport.setName("Brno letiste");
		airport.setCountry("Ceska republika");
		airport.setCity("Brno");
		airportDAO.addAirport(airport);
		
		Airport airportUpdate = airportDAO.getAirportsByName("Brno letiste").get(0);
		airportUpdate.setId(new Long(10));
		airportDAO.updateAirport(airportUpdate);
	}

	/**
	 * Test of removeAirport method, of class AirportDAOImpl.
	 */
	@Test
	public void testRemoveAirport() {
		System.out.println("remove Airport");
		
		Airport airport1 = new Airport();
		airport1.setName("Brno letiste");
		airport1.setCountry("Ceska republika");
		airport1.setCity("Brno");
		airportDAO.addAirport(airport1);
		
		Airport airport2 = new Airport();
		airport2.setName("Paris Aeroport");
		airport2.setCountry("France");
		airport2.setCity("Paris");
		airportDAO.addAirport(airport2);
		
		airportDAO.removeAirport(airport1);
		assertEquals(1, airportDAO.getAllAirports().size());
		assertEquals("Paris Aeroport", airportDAO.getAllAirports().get(0).getName());
	}
	
	/**
	 * Test of removeAirport method, of class AirportDAOImpl, with null given.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveAirportWithNull() {
		airportDAO.removeAirport(null);
	}

	/**
	 * Test of getAllAirports method, of class AirportDAOImpl.
	 */
	@Test
	public void testGetAllAirports() {
		System.out.println("get all Airports");
		
		Airport airport1 = new Airport();
		airport1.setName("Brno letiste");
		airport1.setCountry("Ceska republika");
		airport1.setCity("Brno");
		airportDAO.addAirport(airport1);
		
		Airport airport2 = new Airport();
		airport2.setName("Paris Aeroport");
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
		airport.setName("Brno letiste");
		airport.setCountry("Ceska republika");
		airport.setCity("Brno");
		Airport result = airportDAO.addAirport(airport);
		
		assertEquals(result, airportDAO.getAirportById(result.getId()));
	}
	
	/**
	 * Test of getAirportById method, of class AirportDAOImpl, with null given.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetAirportByIdWithNull() {
		airportDAO.getAirportById(null);
	}
	
	/**
	 * Test of getAirportById method, of class AirportDAOImpl, with nonexisting id given.
	 */
	@Test
	public void testGetAirportByIdWithNonexistingId() {
		Airport airport = new Airport();
		airport.setName("Brno letiste");
		airport.setCountry("Ceska republika");
		airport.setCity("Brno");
		airportDAO.addAirport(airport);
		
		assertNull(airportDAO.getAirportById(new Long(10)));
	}

	/**
	 * Test of getAirportsByCity method, of class AirportDAOImpl.
	 */
	@Test
	public void testGetAirportsByCity() {
		System.out.println("get Airports by city");
		
		Airport airport1 = new Airport();
		airport1.setName("Brno letiste");
		airport1.setCountry("Ceska republika");
		airport1.setCity("Brno");
		airportDAO.addAirport(airport1);
		
		Airport airport2 = new Airport();
		airport2.setName("Paris Aeroport");
		airport2.setCountry("France");
		airport2.setCity("Paris");
		airportDAO.addAirport(airport2);
		
		assertEquals(airport1, airportDAO.getAirportsByCity("Brno").get(0));
		assertEquals(1, airportDAO.getAirportsByCity("Brno").size());
	}
	
	/**
	 * Test of getAirportsByCity method, of class AirportDAOImpl, with null given.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetAirportsByCityWithNull() {
		airportDAO.getAirportsByCity(null).size();
	}
	
	/**
	 * Test of getAirportsByCity method, of class AirportDAOImpl, with nonexisting city given.
	 */
	@Test
	public void testGetAirportsByCityWithNonexisting() {
		Airport airport = new Airport();
		airport.setName("Brno letiste");
		airport.setCountry("Ceska republika");
		airport.setCity("Brno");
		airportDAO.addAirport(airport);
		
		assertEquals(0, airportDAO.getAirportsByCity("Praha").size());
	}

	/**
	 * Test of getAirportsByName method, of class AirportDAOImpl.
	 */
	@Test
	public void testGetAirportsByName() {
		System.out.println("get Airports by name");
		
		Airport airport1 = new Airport();
		airport1.setName("Brno letiste");
		airport1.setCountry("Ceska republika");
		airport1.setCity("Brno");
		airportDAO.addAirport(airport1);
		
		Airport airport2 = new Airport();
		airport2.setName("Paris Aeroport");
		airport2.setCountry("France");
		airport2.setCity("Paris");
		airportDAO.addAirport(airport2);
		
		assertEquals(airport1, airportDAO.getAirportsByName("Brno letiste").get(0));
		assertEquals(1, airportDAO.getAirportsByName("Brno letiste").size());
	}
	
	/**
	 * Test of getAirportsByName method, of class AirportDAOImpl, with null given.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetAirportsByNameWithNull() {
		airportDAO.getAirportsByName(null).size();
	}
	
	/**
	 * Test of getAirportsByName method, of class AirportDAOImpl, with nonexisting name given.
	 */
	@Test
	public void testGetAirportsByNameWithNonexisting() {
		Airport airport = new Airport();
		airport.setName("Brno letiste");
		airport.setCountry("Ceska republika");
		airport.setCity("Brno");
		airportDAO.addAirport(airport);
		
		assertEquals(0, airportDAO.getAirportsByName("Praha letiště").size());
	}

	/**
	 * Test of getAirportsByCountry method, of class AirportDAOImpl.
	 */
	@Test
	public void testGetAirportsByCountry() {
		System.out.println("get Airports by country");
		
		Airport airport1 = new Airport();
		airport1.setName("Brno letiste");
		airport1.setCountry("Ceska republika");
		airport1.setCity("Brno");
		airportDAO.addAirport(airport1);
		
		Airport airport2 = new Airport();
		airport2.setName("Paris Aeroport");
		airport2.setCountry("France");
		airport2.setCity("Paris");
		airportDAO.addAirport(airport2);
		
		assertEquals(airport1, airportDAO.getAirportsByCountry("Ceska republika").get(0));
		assertEquals(1, airportDAO.getAirportsByCountry("Ceska republika").size());
	}
	
	/**
	 * Test of getAirportsByCountry method, of class AirportDAOImpl, with null given.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetAirportsByCountryWithNull() {
		airportDAO.getAirportsByCountry(null).size();
	}
	
	/**
	 * Test of getAirportsByCountry method, of class AirportDAOImpl, with nonexisting country given.
	 */
	@Test
	public void testGetAirportsByCountryWithNonexisting() {
		Airport airport = new Airport();
		airport.setName("Brno letiste");
		airport.setCountry("Ceska republika");
		airport.setCity("Brno");
		airportDAO.addAirport(airport);
		
		assertEquals(0, airportDAO.getAirportsByCountry("Germany").size());
	}
}
