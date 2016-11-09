package cz.muni.airport.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;

import cz.muni.airport.dto.FlightDTO;
import cz.muni.airport.facadeApi.FlightFacade;
import cz.muni.airport.model.Flight;
import cz.muni.airport.services.FlightService;
/**
 * Created by richard Bariny on 9.11.2016.
 * Implementation of Facade interface
 */
public class FlightFacadeImpl implements FlightFacade {

    @Autowired
    FlightService flightService;

    @Override
    public void createFlight(FlightDTO flightDTO) {
        Flight flight = new Flight();
        flightService.saveFlight(flight);
    }
}
