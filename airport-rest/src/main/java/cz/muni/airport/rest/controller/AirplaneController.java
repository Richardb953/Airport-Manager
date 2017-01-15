package cz.muni.airport.rest.controller;

import cz.muni.airport.dto.AirplaneDTO;
import cz.muni.airport.dto.StewardDTO;
import cz.muni.airport.facadeApi.AirplaneFacade;
import cz.muni.airport.facadeApi.StewardFacade;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by Richard Bariny on 7.1.2017.
 *
 * @author Richard Bariny, github name:Richardb953
 */
@RestController
@RequestMapping("/airplanes")
@Transactional
public class AirplaneController {

    @Autowired
    AirplaneFacade af;

    @RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE)
    public Collection<AirplaneDTO> airplanes(Model model) {
//        return af.getAllStewards();
        return af.getAllAirplanes();
    }

    @RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE, value = "/{id}")
    public AirplaneDTO getGhostById(@PathVariable long id) {
        return af.getAirplaneById(id);
    }
    
    @RequestMapping(method = DELETE, produces = APPLICATION_JSON_VALUE, value = "/{id}")
    public void  delete(@PathVariable long id) {
        AirplaneDTO deleted = af.getAirplaneById(id);
        af.deleteAirplane(deleted);
//        return deleted;
    }
}
