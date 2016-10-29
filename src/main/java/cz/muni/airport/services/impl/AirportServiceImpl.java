package cz.muni.airport.services.impl;

import cz.muni.airport.services.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import cz.muni.airport.dao.AirportDAO;
import cz.muni.airport.model.Airport;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jiøí Krejèí
 */
public class AirportServiceImpl implements AirportService {
    
    @Autowired
    private AirportDAO airportDAO;

    @Override
    @Transactional
    public Airport saveAirport(Airport airport) {
        airportDAO.addAirport(airport);
        return airport;
    }

    @Override
    @Transactional
    public Airport updateAirport(Airport airport) {
        airportDAO.updateAirport(airport);
        return airport;
    }

    @Override
    @Transactional
    public void removeAirport(Airport airport) {
        airportDAO.removeAirport(airport);
    }

    @Override
    public List<Airport> getAllAirports() {
        return airportDAO.getAllAirports();
    }

    @Override
    public Airport getAirportById(Long id) {
        return airportDAO.getAirportById(id);
    }

    @Override
    public List<Airport> getAirportsByCity(String city) {
        return airportDAO.getAirportsByCity(city);
    }

    @Override
    public List<Airport> getAirportsByName(String name) {
        return airportDAO.getAirportsByName(name);
    }

    @Override
    public List<Airport> getAirportsByCountry(String country) {
        return airportDAO.getAirportsByCountry(country);
    }

}
