package cz.muni.airport.dao;

import cz.muni.airport.model.Flight;
import java.util.List;

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
     */
    Flight getFlightById(Long id);

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
     */
    List<Flight> getFlightsByName(String name);

    /**
     * FIND ALL FLIGHTS
     * @return List of Flight objects
     */
    List<Flight> getAllFlights();

}
