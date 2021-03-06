package cz.muni.airport.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

import cz.muni.airport.model.Flight;

/**
 * DAO Flight interface
 * created by Richard Bariny on 25.10.2016
 * @author Richard Bariny, github name: Richardb953
 */
@Repository
public interface FlightDAO {

    /**
     * CREATE FLIGHT
     * @param flight entity of flight object
     * @return Flight object
     * @throws IllegalArgumentException if flight is null
     */
    Flight addFlight(Flight flight) throws IllegalArgumentException;

    /**
     * READ FLIGHT
     * @param id Long value of flight id
     * @return Flight object
     * @throws IllegalArgumentException if id is null
     */
    Flight getFlightById(Long id) throws IllegalArgumentException ;

    /**
     * UPDATE FLIGHT
     * @param flight entity of flight object
     * @return Flight object
     * @throws IllegalArgumentException if flight is null
     */
    Flight updateFlight(Flight flight) throws IllegalArgumentException;

    /**
     * DELETE FLIGHT
     * @param flight flight entity of flight object
     * @throws IllegalArgumentException if flight is null
     */
    void removeFlight(Flight flight) throws IllegalArgumentException;;

    /**
     * FIND FLIGHT BY NAME
     * @param name String value of flight name
     * @return List of Flight objects
     * @throws IllegalArgumentException if name is null
     */
    List<Flight> getFlightsByName(String name) throws IllegalArgumentException;

    /**
     * FIND ALL FLIGHTS
     * @return List of Flight objects
     */
    List<Flight> getAllFlights();

}
