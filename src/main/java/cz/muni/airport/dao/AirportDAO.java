package cz.muni.airport.dao;

import cz.muni.airport.model.Airport;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Jiri Krejci
 */
public interface AirportDAO {

    void create(Airport airport) throws IllegalArgumentException;

    void update(Airport airport) throws IllegalArgumentException;

    void remove(Airport airport) throws IllegalArgumentException;

    Airport getAirport(Long id) throws IllegalArgumentException;

    List<Airport> getAllAirports();

    List<Airport> getAirportsByCity(String city) throws IllegalArgumentException;

    List<Airport> getAirportsByCountry(String country) throws IllegalArgumentException;

    List<Airport> getAirportsByCountry(Locale locale) throws IllegalArgumentException;

}
