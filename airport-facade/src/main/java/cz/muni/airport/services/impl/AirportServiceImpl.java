package cz.muni.airport.services.impl;

import cz.muni.airport.services.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import cz.muni.airport.dao.AirportDAO;
import cz.muni.airport.model.Airport;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of Airport Service
 * 
 * @author Jiri Krejci, github name: xkrejci7
 */
@Service("airportService")
public class AirportServiceImpl implements AirportService {

    @Autowired
    private AirportDAO airportDAO;

    public void setAirportDAO(AirportDAO airportDAO) {
        this.airportDAO = airportDAO;
    }

    @Override
    @Transactional
    public Airport addAirport(Airport airport) {
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

    @Override
    public List<Airport> getAirportByIata(String iata) {
        return airportDAO.getAirportsByIata(iata);
    }

}
