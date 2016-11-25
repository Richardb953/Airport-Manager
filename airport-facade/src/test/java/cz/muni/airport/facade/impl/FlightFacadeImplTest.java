package cz.muni.airport.facade.impl;

import cz.muni.airport.dto.FlightDTO;
import cz.muni.airport.services.BeanMappingService;
import cz.muni.airport.services.FlightService;
import java.util.List;
import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
    private BeanMappingService beanMapperService;

    @InjectMocks
    private FlightFacadeImpl flightFacade = new FlightFacadeImpl();

    @Test
    public void getAllFlightsTest() {

        when(flightService.findAllFlights()).thenReturn(null);

        List<FlightDTO> result = flightFacade.getAllFlights();

        verify(flightService).findAllFlights();
        Assert.assertEquals(result.size(), 0);

    }
}
