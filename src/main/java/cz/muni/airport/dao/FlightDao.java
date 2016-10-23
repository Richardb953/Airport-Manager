package java.cz.muni.airport.dao;

import java.cz.muni.airport.model.Flight;
import java.util.List;

/**
 * @author rba on 23.10.2016.
 */
public interface FlightDao {

    /**
     * CREATE FLIGHT
     */
    Flight addFlight(Flight flight);

    /**
     * READ FLIGHT
     */
    Flight getFlight(Long id);

    /**
     * UPDATE FLIGHT
     */
    Flight updateFlight(Flight flight);

    /**
     * DELETE FLIGHT
     */
    void removeFlight(Long id);

    /**
     * FIND FLIGHT
     */
    List<Flight> getFlightsByName(String name);

    /**
     * FIND ALL FLIGHTS
     */
    List<Flight> getAllFlights();
}
