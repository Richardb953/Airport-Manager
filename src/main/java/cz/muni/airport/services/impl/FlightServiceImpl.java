package cz.muni.airport.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.muni.airport.dao.FlightDao;
import cz.muni.airport.services.FlightService;

/**
 * @author rba on 23.10.2016.
 */

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    FlightDao flightDao;



}
