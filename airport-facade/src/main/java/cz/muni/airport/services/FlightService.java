package cz.muni.airport.services;

import cz.muni.airport.model.Flight;

import java.util.List;

/**
 * Created by Richard Bariny on 23.10.2016
 * @author github:Richardb953
 *
 * Service layer interface for Flight object
 *
 */

public interface FlightService {

    /**
     * CREATE FLIGHT - adding new entity
     * @param flight entity of flight object
     * @return Flight object
     */
    Flight saveFlight(Flight flight);

    /**
     * READ FLIGHT - read exiting entity
     * @param id Long value of flight id
     * @return Flight object
     */
    Flight getFlight(Long id);

    /**
     * UPDATE FLIGHT - update existing entity
     * @param flight entity of flight object
     * @return Flight object
     */
    Flight updateFlight(Flight flight);

    /**
     * DELETE FLIGHT - delete entity
     * @param flight Long value of flight id
     */
    void removeFlight(Flight flight);

    /**
     * FIND FLIGHT BY NAME - find all entities by parameter
     * @param name String value of flight name
     * @return List of Flight objects
     */
    List<Flight> findFlightByName(String name);

    /**
     * FIND ALL FLIGHTS - find all entities
     * @return List of Flight objects
     */
    List<Flight> findAllFlights();



}
