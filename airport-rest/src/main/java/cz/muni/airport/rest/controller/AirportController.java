package cz.muni.airport.rest.controller;

import cz.muni.airport.dto.AirportCreateDTO;
import cz.muni.airport.dto.AirportDTO;
import cz.muni.airport.facadeApi.AirportFacade;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jiri Krejci, github name:xkrejci7
 */
@RestController
@RequestMapping("/airports")
@Transactional
public class AirportController {

    @Autowired
    AirportFacade airportFacade;

    /**
     * Get list of Airports curl -i -X GET
     * http://localhost:8082/pa165/rest/airports
     *
     * @return AirportDTO
     */
    @RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE)
    public Collection<AirportDTO> getAllAirports() {
        return airportFacade.getAllAirports();
    }

    /**
     * Get Airport by identifier curl -i -X GET
     * http://localhost:8082/pa165/rest/airports/id/1
     *
     * @param id identifier for airport
     * @return AirportDTO
     */
    @RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE, value = "/id/{id}")
    public AirportDTO getAirportById(@PathVariable long id) {
        return airportFacade.getAirportById(id);
    }

    /**
     * Get Airports by city curl -i -X GET
     * http://localhost:8082/pa165/rest/airports/city/Bratislava
     *
     * @param city city name
     * @return AirportDTO
     */
    @RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE, value = "/city/{city}")
    public Collection<AirportDTO> getAirportByCity(@PathVariable String city) {
        return airportFacade.getAirportsByCity(city);
    }

    /**
     * Get Airports by country curl -i -X GET
     * http://localhost:8082/pa165/rest/airports/country/Slovensko
     *
     * @param country country name
     * @return AirportDTO
     */
    @RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE, value = "/country/{country}")
    public Collection<AirportDTO> getAirportsByCountry(@PathVariable String country) {
        return airportFacade.getAirportsByCountry(country);
    }

    /**
     * Get Airports by iata curl -i -X GET
     * http://localhost:8082/pa165/rest/airports/iata/BRA
     *
     * @param iata iata code identifier
     * @return AirportDTO
     */
    @RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE, value = "/iata/{iata}")
    public Collection<AirportDTO> getAirportsByIata(@PathVariable String iata) {
        return airportFacade.getAirportsByIata(iata);
    }

    /**
     * Get Airports by name curl -i -X GET
     * http://localhost:8082/pa165/rest/airports/city/Bratislava
     *
     * @param name name of airport
     * @return AirportDTO
     */
    @RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE, value = "/name/{name}")
    public Collection<AirportDTO> getAirportsByName(@PathVariable String name) {
        return airportFacade.getAirportsByName(name);
    }

    /**
     * Delete Airport specified by identifier curl -i -X DELETE
     * http://localhost:8082/pa165/rest/airports/id/1
     *
     * @param id identifier for airport
     */
    @RequestMapping(method = DELETE, produces = APPLICATION_JSON_VALUE, value = "/id/{id}")
    public void removeAirport(@PathVariable long id) {
        airportFacade.removeAirport(id);
    }

    /**
     * Create a new airport by POST method curl -X POST -i -H "Content-Type:
     * application/json" --data '{"iata": "iata", "name": "name", "country":
     * "country", "city": "city" }'
     * http://localhost:8082/pa165/rest/airports/create
     *
     * @param airport AirportCreateDTO with required fields
     * @return the created airport AirportDTO
     */
    @RequestMapping(method = POST, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE, value = "/create")
    public AirportDTO createAirport(@RequestBody AirportCreateDTO airport) {
        return airportFacade.createAirport(airport);
    }

    /**
     *
     * Update airport by PUT method curl -X PUT -i -H "Content-Type:
     * application/json" --data '{ "iata": "iata2", "name": "name2", "country":
     * "country2", "city": "city2" } '
     * http://localhost:8082/pa165/rest/airports/id/1
     *
     * @param id identified of the airport to be updated
     * @param airport AirportDTO with required fields
     * @return the updated airport AirportDTO
     */
    @RequestMapping(method = PUT, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE, value = "/id/{id}")
    public AirportDTO update(@PathVariable("id") long id, @RequestBody AirportDTO airport) {
        airport.setId(id);
        airportFacade.updateAirport(airport);
        return airportFacade.getAirportById(id);
    }

}
