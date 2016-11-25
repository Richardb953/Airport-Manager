package cz.muni.airport.facade;

import org.hibernate.service.spi.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cz.muni.airport.dto.AirplaneDTO;
import cz.muni.airport.dto.FlightDTO;
import cz.muni.airport.facade.impl.AirplaneFacadeImpl;
import cz.muni.airport.model.Airplane;
import cz.muni.airport.model.Flight;
import cz.muni.airport.model.enums.PlaneType;
import cz.muni.airport.services.AirplaneService;
import cz.muni.airport.services.BeanMappingService;
import cz.muni.airport.services.FlightService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by Richard Bariny on 25.11.2016.
 *
 * @author Richard Bariny, github name:Richardb953
 */
@RunWith(MockitoJUnitRunner.class)
public class AirplaneFacadeTestImpl {
    @Mock
    private AirplaneService airplaneService;

    @Mock
    private FlightService flightService;

    @Mock
    private BeanMappingService beanMapperService;

    @InjectMocks
    private AirplaneFacadeImpl airplaneFacade = new AirplaneFacadeImpl();

    public AirplaneFacadeTestImpl() {
    }

    private Airplane airplane;
    private AirplaneDTO airplaneDTO;

    private Flight flight;
    private FlightDTO flightDTO;
    private List<Airplane> allAirplanes = new ArrayList<>();
    private List<AirplaneDTO> allAirplaneDTOs = new ArrayList<>();
    Calendar arrival;
    Calendar departure;


    @BeforeMethod
    public void prepareObjects() {

        allAirplanes = new ArrayList<>(3);
        allAirplaneDTOs = new ArrayList<>(3);

        airplane = new Airplane();
        airplane.setId(1L);
        airplane.setName("Boening-477");
        airplane.setCapacity(50);
        airplane.setType(PlaneType.AIRLINER);

        airplaneDTO = new AirplaneDTO();
        airplaneDTO.setId(1L);
        airplaneDTO.setName("Boening-477");
        airplaneDTO.setCapacity(50);
        airplaneDTO.setType(PlaneType.AIRLINER);


        allAirplanes.add(airplane);
        allAirplaneDTOs.add(airplaneDTO);

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

        airplane.addFlight(flight);

        flightDTO = new FlightDTO();
        flightDTO.setName("LetCislo42");
        flightDTO.setAirplane(airplaneDTO);
        flightDTO.setArrival(arrival.getTime().getTime());
        flightDTO.setDeparture(departure.getTime().getTime());
        flightDTO.setPassagers(50);

        airplaneDTO.addFlight(flightDTO);

        when(beanMapperService.mapTo(airplaneDTO, Airplane.class)).thenReturn(airplane);
        when(beanMapperService.mapTo(airplane, AirplaneDTO.class)).thenReturn(airplaneDTO);
    }


    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldAutowireDependencies() {
        assertNotNull(airplaneFacade);
    }

    @Test
    public void createAirplane(){
        AirplaneDTO airplaneDTO = airplaneFacade.createAirplane(this.airplaneDTO);
        assertEquals(airplaneDTO, this.airplaneDTO);

    }

    @Test
    public void updateAirplane(){
        AirplaneDTO airplaneDTO = airplaneFacade.updateAirplane(this.airplaneDTO);
        assertEquals(airplaneDTO, this.airplaneDTO);
    }

    @Test
    public void getAllAirplanes(){
        when(airplaneFacade.getAllAirplanes()).thenReturn(this.allAirplaneDTOs);
        List<AirplaneDTO> airplaneDTOList = airplaneFacade.getAllAirplanes();
        assertEquals(airplaneFacade.getAllAirplanes(), allAirplaneDTOs);
    }

    @Test
    public void getAirplaneById(){
        when(airplaneFacade.getAirplaneById(any(Long.class))).thenReturn(this.airplaneDTO);
        AirplaneDTO airplaneDTO = airplaneFacade.getAirplaneById(1L);
        assertEquals(airplaneDTO, this.airplaneDTO);
    }

    @Test
    public void getAirplaneByName(){
        when(airplaneFacade.getAirplaneByName(any(String.class))).thenReturn(this.allAirplaneDTOs);
        List<AirplaneDTO> airplaneDTO = airplaneFacade.getAirplaneByName("Boening-477");
        assertEquals(airplaneDTO, this.allAirplaneDTOs);
    }

    @Test
    public void isAvailable(){
        when(airplaneFacade.isAvailable(any(AirplaneDTO.class), any(FlightDTO.class))).thenReturn(true);
        assertEquals(airplaneFacade.isAvailable(this.airplaneDTO, this.flightDTO), true);
    }

}
