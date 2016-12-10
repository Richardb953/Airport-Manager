package cz.muni.airport.dao;

import java.util.List;

import cz.muni.airport.model.Flight;

/**
 * DAO Flight interface
 * created by Richard Bariny on 25.10.2016
 * @author Richard Bariny, github name: Richardb953
 */

public interface FlightDAO {

    /**
     * CREATE FLIGHT
     * @param flight entity of flight object
     * @return Flight object
     */
    Flight addFlight(Flight flight);

    /**
     * READ FLIGHT
     * @param id Long value of flight id
     * @return Flight object
     * @throws IllegalArgumentException if id is null
     */
    Flight getFlightById(Long id) ;

    /**
     * UPDATE FLIGHT
     * @param flight entity of flight object
     * @return Flight object
     */
    Flight updateFlight(Flight flight);

    /**
     * DELETE FLIGHT
     * @param flight flight entity of flight object
     */
    void removeFlight(Flight flight);

    /**
     * FIND FLIGHT BY NAME
     * @param name String value of flight name
     * @return List of Flight objects
     * @throws IllegalArgumentException if name is null
     */
    List<Flight> getFlightsByName(String name);

    /**
     * FIND ALL FLIGHTS
     * @return List of Flight objects
     */
    List<Flight> getAllFlights();

}
