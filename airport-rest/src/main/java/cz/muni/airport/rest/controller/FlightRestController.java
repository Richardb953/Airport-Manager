package cz.muni.airport.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cz.muni.airport.dto.AirplaneDTO;
import cz.muni.airport.dto.FlightDTO;
import cz.muni.airport.dto.StewardDTO;
import cz.muni.airport.facadeApi.FlightFacade;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jiri Krejci, github name:xkrejci7
 */
@RestController
@RequestMapping("/rest/flights")
@Transactional
public class FlightRestController {

    @Autowired
    FlightFacade flightFacade;

    /**
     * Get list of Flights curl -i -X GET
     * http://localhost:8082/pa165/rest/flights
     *
     * @return list of FlightsDTO
     */
    @RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE)
    public Collection<FlightDTO> airplanes(Model model) {
        Collection<FlightDTO> toReturn = new ArrayList<>();
        for (FlightDTO f : flightFacade.getAllFlights()) {
            AirplaneDTO plane = f.getAirplane();
            plane.setFlights(new ArrayList<>());
            f.setAirplane(plane);

            List<StewardDTO> stewardToReturn = new ArrayList<>();
            for (StewardDTO s : f.getStewards()) {
                s.setFlights(new ArrayList<>());
                stewardToReturn.add(s);
            }
            
            f.setStewards(stewardToReturn);

            toReturn.add(f);
        }
        return toReturn;
    }
}
