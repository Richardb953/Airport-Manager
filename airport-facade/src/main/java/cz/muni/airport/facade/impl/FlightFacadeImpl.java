package cz.muni.airport.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import javax.inject.Inject;

import cz.muni.airport.dto.FlightDTO;
import cz.muni.airport.dto.StewardDTO;
import cz.muni.airport.facadeApi.FlightFacade;
import cz.muni.airport.model.Flight;
import cz.muni.airport.model.Steward;
import cz.muni.airport.services.BeanMappingService;
import cz.muni.airport.services.FlightService;
/**
 * Created by Richard Bariny on 9.11.2016.
 * Implementation of Facade interface
 *
 * @author Richard Bariny, github name: Richardb953
 */
public class FlightFacadeImpl implements FlightFacade {

    @Inject
    FlightService flightService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public Long createFlight(FlightDTO flightDTO) {
        return null;
    }

    @Override
    public List<FlightDTO> getAllFlights() {
        return beanMappingService.mapTo(flightService.findAllFlights(), FlightDTO.class);
    }

    @Override
    public FlightDTO getFlightById(Long id) {
        return beanMappingService.mapTo(flightService.getFlight(id), FlightDTO.class);
    }

    @Override
    public void removeFlight(Long id) {
        flightService.removeFlight(flightService.getFlight(id));
    }

    @Override
    public FlightDTO updateFlight(FlightDTO flightDTO) {
        Flight flight = beanMappingService.mapTo(flightDTO, Flight.class);
        return beanMappingService.mapTo(flightService.updateFlight(flight), FlightDTO.class);
    }

    @Override
    public boolean validateFlight(FlightDTO flightDTO) {
        return false;
    }

    @Override
    public FlightDTO addStewardToFlight(FlightDTO flightDTO, StewardDTO stewardDTO) {
        Flight flight = beanMappingService.mapTo(flightDTO, Flight.class);
        Steward steward = beanMappingService.mapTo(stewardDTO, Steward.class);

        return beanMappingService.mapTo(flightService.addStewardToFlight(flight, steward), FlightDTO.class);
    }
}
