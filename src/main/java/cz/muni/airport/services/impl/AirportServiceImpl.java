/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.airport.services.impl;

import cz.muni.airport.dao.IAirportDao;
import cz.muni.airport.services.IAirportService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Jirka
 */
public class AirportServiceImpl implements IAirportService {
    
    @Autowired
    private IAirportDao airportDao;

}
