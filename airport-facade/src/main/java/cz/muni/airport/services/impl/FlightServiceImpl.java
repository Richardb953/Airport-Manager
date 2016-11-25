package cz.muni.airport.services.impl;

import cz.muni.airport.dao.FlightDAO;
import cz.muni.airport.dto.FlightCreateDTO;
import cz.muni.airport.model.Airplane;
import cz.muni.airport.model.Flight;
import cz.muni.airport.model.Steward;
import cz.muni.airport.services.AirplaneService;
import cz.muni.airport.services.FlightService;
import cz.muni.airport.services.StewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

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

    @Inject
    private AirplaneService airplaneService;
    
    @Inject
    private StewardService stewardService;

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
        return flightDao.getFlightById(id);
    }

    @Override
    @Transactional
    public List<Flight> findAllFlights() {
        return flightDao.getAllFlights();
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
    public List<Flight> findFlightByName(String name) {
        return flightDao.getFlightsByName(name);
    }
}
