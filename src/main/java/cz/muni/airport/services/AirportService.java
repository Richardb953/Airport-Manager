package cz.muni.airport.services;

import cz.muni.airport.model.Airport;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jiri Krejci
 */

@Service
public interface AirportService {
    
    Airport saveAirport(Airport airport) throws IllegalArgumentException;
    Airport updateAirport(Airport airport) throws IllegalArgumentException;
    void removeAirport(Airport airport) throws IllegalArgumentException;

    List<Airport> getAllAirports();
    
    Airport getAirportById(Long id) throws IllegalArgumentException;
    List<Airport> getAirportsByCity(String city) throws IllegalArgumentException;
    List<Airport> getAirportsByName(String name) throws IllegalArgumentException;
    List<Airport> getAirportsByCountry(String country) throws IllegalArgumentException;   
    
    
}
