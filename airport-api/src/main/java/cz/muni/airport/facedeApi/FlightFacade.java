package cz.muni.airport.facedeApi;

import cz.muni.airport.dto.FlightDTO;

/**
 * Created by richard Bariny on 9.11.2016.
 * Facade Interface of Flight entity
 */
public interface FlightFacade {

    /**
     * Create new Flight
     */
    void createFlight(FlightDTO flightDTO);
}
