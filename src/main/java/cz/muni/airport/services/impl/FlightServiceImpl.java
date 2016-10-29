package cz.muni.airport.services.impl;

import cz.muni.airport.dao.FlightDAO;
import cz.muni.airport.model.Flight;
import cz.muni.airport.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author rba on 23.10.2016.
 */

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightDAO flightDao;

    @Override
    @Transactional
    public Flight saveFlight(Flight flight) {
       return flightDao.addFlight(flight);
    }

    @Override
    @Transactional
    public Flight updateFlight(Flight flight) {
        return flightDao.updateFlight(flight);
    }

    @Override
    @Transactional
    public void removeFlight(Flight flight) {
        flightDao.removeFlight(flight);
    }

    @Override
    @Transactional
    public Flight getFlight(Long id) {
        return flightDao.getFlight(id);
    }

    @Override
    @Transactional
    public List<Flight> findAllFlights() {
        return flightDao.getAllFlights();
    }

    @Override
    @Transactional
    public List<Flight> findFlightByName(String name) {
        return flightDao.getFlightsByName(name);
    }
}
