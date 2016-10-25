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
     * @param id Long value of flight id
     */
    void removeFlight(Long id);

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

    /**
     * UPDATE CAPACITY BY CONST
     * @param flight entity of flight object
     * @param passengerConst const for update passengers can be possitive or negative
     * @return Flight update flight object
     */
    Flight updatePassengersByConst(Flight flight, Integer passengerConst);
}
