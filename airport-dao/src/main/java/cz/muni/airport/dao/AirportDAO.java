package cz.muni.airport.dao;

import cz.muni.airport.model.Airport;
import java.util.List;

/**
 * DAO interface for Airport entity
 * 
 * @author Jiri Krejci, github name: xkrejci7
 */
public interface AirportDAO {

    /**
     * Create airport
     * @param airport Airport object to be created
     * @return Airport object
     */
    Airport addAirport(Airport airport);

    /**
     * Update airport
     * @param airport Airport object to be updated
     * @return Airport object after update
     */
    Airport updateAirport(Airport airport);

    /**
     * Delete airport
     * @param airport Airport object to be deleted 
     */
    void removeAirport(Airport airport);

    /**
     * Get all airports
     * @return List<Airport> of Airport objects
     */
    List<Airport> getAllAirports();

    /**
     * Get Airport
     * @param id Long airport id
     * @return Airport object
     */
    Airport getAirportById(Long id);
    
    /**
     * Get Airport
     * @param iata String airport iata identifier
     * @return List<Airport> of Airport objects
     */
    List<Airport> getAirportsByIata(String iata);

    /**
     * Get all airports with given city
     * @param city String value of city
     * @return List<Airport> of Airport objects
     */
    List<Airport> getAirportsByCity(String city);

    /**
     * Get all airports with given name
     * @param name String value of name
     * @return List<Airport> of Airport objects
     */
    List<Airport> getAirportsByName(String name);

    /**
     * Get all airports with given country
     * @param country String value of country
     * @return List<Airport> of Airport objects
     */
    List<Airport> getAirportsByCountry(String country);

}
