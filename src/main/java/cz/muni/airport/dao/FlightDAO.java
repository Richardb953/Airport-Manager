package cz.muni.airport.dao;

import cz.muni.airport.model.Flight;
import java.util.List;

/**
 * @author rba on 23.10.2016.
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
    Flight getFlight(Long id);

    /**
     * UPDATE FLIGHT
     * @param flight entity of flight object
     * @return Flight object
     */
    Flight updateFlight(Flight flight);

    /**
     * DELETE FLIGHT
     * @param flight Long value of flight id
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
