package cz.muni.airport.facadeApi;

import cz.muni.airport.dto.FlightDTO;

/**
 * Created by Richard Bariny on 9.11.2016.
 * Facade Interface of Flight entity
 * @author Richard Bariny, github name: Richardb953
 */
public interface FlightFacade {

    /**
     * Create new Flight
     */
    void createFlight(FlightDTO flightDTO);
}
