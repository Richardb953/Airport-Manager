package cz.muni.airport.services.impl;

import cz.muni.airport.services.IAirportService;
import org.springframework.beans.factory.annotation.Autowired;
import cz.muni.airport.dao.AirportDAO;

/**
 *
 * @author Jirka
 */
public class AirportServiceImpl implements IAirportService {
    
    @Autowired
    private AirportDAO airportDao;

}
