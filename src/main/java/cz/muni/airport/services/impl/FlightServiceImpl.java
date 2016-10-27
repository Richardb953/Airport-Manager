package cz.muni.airport.services.impl;

import cz.muni.airport.dao.FlightDAO;
import cz.muni.airport.model.Flight;
import cz.muni.airport.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author rba on 23.10.2016.
 */

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    FlightDAO flightDao;

    @Override public Flight saveFlight(Flight flight) {
       return flightDao.addFlight(flight);
    }

    @Override public Flight updateFlight(Flight flight) {
        return flightDao.updateFlight(flight);
    }

    @Override public void removeFlight(Flight flight) {
        flightDao.removeFlight(flight.getId());
    }

    @Override public Flight getFlight(Long id) {
        return flightDao.getFlight(id);
    }

    @Override public List<Flight> findAllFlights() {
        return flightDao.getAllFlights();
    }

    @Override public List<Flight> findFlightByName(String name) {
        return flightDao.getFlightsByName(name);
    }
}
