package cz.muni.airport.services;

import cz.muni.airport.model.Flight;

import java.util.List;

/**
 * @author rba on 23.10.2016.
 */

public interface FlightService {

    Flight saveFlight(Flight flight);
    Flight updateFlight(Flight flight);
    void removeFlight(Flight flight);
    Flight getFlight(Long id);
    List<Flight> findAllFlights();
    List<Flight> findFlightByName(String name);



}
