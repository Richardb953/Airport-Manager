package cz.muni.airport.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

import cz.muni.airport.model.Airport;

/**
 * DAO interface for Airport entity
 * 
 * @author Jiri Krejci, github name: xkrejci7
 */
@Repository
public interface AirportDAO {

    /**
     * Create airport
     * @param airport Airport object to be created
     * @return Airport object
     */
    Airport addAirport(Airport airport) throws IllegalArgumentException;

    /**
     * Update airport
     * @param airport Airport object to be updated
     * @return Airport object after update
     */
    Airport updateAirport(Airport airport) throws IllegalArgumentException;

    /**
     * Delete airport
     * @param airport Airport object to be deleted 
     */
    void removeAirport(Airport airport) throws IllegalArgumentException;;

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
    Airport getAirportById(Long id) throws IllegalArgumentException;
    
    /**
     * Get Airport
     * @param iata String airport iata identifier
     * @return List<Airport> of Airport objects
     */
    List<Airport> getAirportsByIata(String iata) throws IllegalArgumentException;;

    /**
     * Get all airports with given city
     * @param city String value of city
     * @return List<Airport> of Airport objects
     */
    List<Airport> getAirportsByCity(String city) throws IllegalArgumentException;

    /**
     * Get all airports with given name
     * @param name String value of name
     * @return List<Airport> of Airport objects
     */
    List<Airport> getAirportsByName(String name) throws IllegalArgumentException;;

    /**
     * Get all airports with given country
     * @param country String value of country
     * @return List<Airport> of Airport objects
     */
    List<Airport> getAirportsByCountry(String country) throws IllegalArgumentException;;

}
