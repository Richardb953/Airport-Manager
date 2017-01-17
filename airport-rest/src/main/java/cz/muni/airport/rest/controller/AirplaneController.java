package cz.muni.airport.rest.controller;

import cz.muni.airport.dto.AirplaneDTO;
import cz.muni.airport.facadeApi.AirplaneFacade;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @author Jiri Krejci, github name:xkrejci7
 */
@RestController
@RequestMapping("/airplanes")
@Transactional
public class AirplaneController {

    @Autowired
    AirplaneFacade airplaneFacade;

    /**
     * Get list of Airplanes curl -i -X GET
     * http://localhost:8082/pa165/rest/airplanes
     *
     * @return list of AirplaneDTO
     */
    @RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE)
    public Collection<AirplaneDTO> airplanes(Model model) {
        Collection<AirplaneDTO> toReturn = new ArrayList<>();
        for (AirplaneDTO a : airplaneFacade.getAllAirplanes()) {
            a.setFlights(new ArrayList<>());
            toReturn.add(a);
        }
        return toReturn;
    }

}
