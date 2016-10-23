/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.airport.dao;

import cz.muni.airport.model.Airport;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Jiri Krejci
 */
public interface IAirportDao {
    
    	public void create(Airport airport) throws IllegalArgumentException;
        public void update(Airport airport) throws IllegalArgumentException;
	public void remove(Airport airport) throws IllegalArgumentException;
        
        public Airport getAirport(Long id) throws IllegalArgumentException;
	public List<Airport> getAllAirports();
	public List<Airport> getAirportsByCity(String city) throws IllegalArgumentException;
	public List<Airport> getAirportsByCountry(String country) throws IllegalArgumentException;
	public List<Airport> getAirportsByCountry(Locale locale) throws IllegalArgumentException;
        
    	
    
}
