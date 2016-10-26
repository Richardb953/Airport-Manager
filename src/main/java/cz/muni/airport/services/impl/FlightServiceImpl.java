package cz.muni.airport.services.impl;

import cz.muni.airport.dao.FlightDAO;
import cz.muni.airport.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author rba on 23.10.2016.
 */

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired FlightDAO flightDao;



}
