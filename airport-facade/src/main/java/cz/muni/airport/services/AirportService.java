package cz.muni.airport.services;

import cz.muni.airport.model.Airplane;
import cz.muni.airport.model.Airport;
import cz.muni.airport.model.Flight;
import cz.muni.airport.model.Steward;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Interface for Airport service
 *
 * @author Jiri Krejci, github name: xkrejci7
 */
@Service
public interface AirportService {

    /**
     * Add airport
     *
     * @param airport Airport object to be added
     * @return Airport object
     */
    Airport saveAirport(Airport airport);

    /**
     * Update airport
     *
     * @param airport Airport object to be updated
     * @return Airport object after update
     */
    Airport updateAirport(Airport airport);

    /**
     * Delete airport
     *
     * @param airport Airport object to be deleted
     */
    void removeAirport(Airport airport);

    /**
     * Get all airports
     *
     * @return List<Airport> of Airport objects
     */
    List<Airport> findAllAirports();

    /**
     * Get Airport
     *
     * @param id Long airport id
     * @return Airport object
     */
    Airport findAirportById(Long id);

    /**
     * Get Airport
     *
     * @param iata String airport iata identifier
     * @return Airport object
     */
    List<Airport> findAirportByIata(String iata);

    /**
     * Get all airports with given city
     *
     * @param city String value of city
     * @return List<Airport> of Airport objects
     */
    List<Airport> findAirportsByCity(String city);

    /**
     * Get all airports with given name
     *
     * @param name String value of name
     * @return List<Airport> of Airport objects
     */
    List<Airport> findAirportsByName(String name);

    /**
     * Get all airports with given country
     *
     * @param country String value of country
     * @return List<Airport> of Airport objects
     */
    List<Airport> findAirportsByCountry(String country);

//    /**
//     * Get all available stewards for given flight
//     *
//     * @param flight Flight to search for
//     * @return List<Steward> of Steward objects
//     */
//    List<Steward> getAvailableStewards(Flight flight);
//
//    /**
//     * Get all available stewards for given flight
//     *
//     * @param flight Flight to search for
//     * @return List<Airplane> of Airplane objects
//     */
//    List<Airplane> getAvailableAirplanes(Flight flight);
}
