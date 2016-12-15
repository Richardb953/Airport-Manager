package cz.muni.airport.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import cz.muni.airport.dao.FlightDAO;
import cz.muni.airport.model.Flight;
import cz.muni.airport.model.Steward;
import cz.muni.airport.services.AirplaneService;
import cz.muni.airport.services.FlightService;
import cz.muni.airport.services.StewardService;

/**
 *
 * Service layer from Flight object
 * Created by Richard Bariny on 21.10.2016
 * @author Richard Bariny, github name: Richardb953
 */

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightDAO flightDao;

    @Autowired
    private AirplaneService airplaneService;

    @Autowired
    private StewardService stewardService;

    @Override
    @Transactional
    public Flight saveFlight(Flight flight) {
        try {
            return flightDao.addFlight(flight);
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage(), e) {
            };
        }
    }

    @Override
    @Transactional
    public Flight updateFlight(Flight flight) {
        try {
            return flightDao.updateFlight(flight);
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage(), e) {
            };
        }
    }

    @Override
    @Transactional
    public void removeFlight(Flight flight) {
        try {
            flightDao.removeFlight(flight);
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage(), e) {
            };
        }
    }

    @Override
    @Transactional
    public Flight getFlight(Long id) throws DataAccessException{
        try {
            return flightDao.getFlightById(id);
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage(), e) {
            };
        }
    }

    @Override
    @Transactional
    public List<Flight> findAllFlights() {
        try {
            return flightDao.getAllFlights();
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage(), e) {
            };
        }
    }

    @Override
    public  Flight addStewardToFlight(Flight flight, Steward steward) {
        flight.addSteward(steward);
        return updateFlight(flight);
    }

    @Override
    public boolean validateFlight(Flight flight) {
        if(flight.getDeparture().before(flight.getArrival())){
            return false;
        }

        if(flight.getAirplane() != null){
            boolean airplateValidation = airplaneService.isAvailable(flight.getAirplane(), flight);
            if(!airplateValidation){
                return false;
            }
        }

        for(Steward steward : flight.getStewards()){
            boolean stewardValidation = stewardService.isAvailable(steward, flight);
            if(!stewardValidation){
                return false;
            }
        }

        return true;
    }


    @Override
    @Transactional
    public List<Flight> findFlightByName(String name) throws DataAccessException {
        try {
            return flightDao.getFlightsByName(name);
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage(), e) {
            };
        }
    }
}
