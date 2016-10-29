package cz.muni.airport.services.impl;

import cz.muni.airport.services.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import cz.muni.airport.dao.AirportDAO;

/**
 *
 * @author Jirka
 */
public class AirportServiceImpl implements AirportService {
    
    @Autowired
    private AirportDAO airportDao;

}
