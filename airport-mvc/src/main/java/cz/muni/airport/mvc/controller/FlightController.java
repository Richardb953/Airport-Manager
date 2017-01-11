package cz.muni.airport.mvc.controller;

import cz.muni.airport.facadeApi.AirplaneFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.muni.airport.facadeApi.AirportFacade;
import cz.muni.airport.facadeApi.FlightFacade;
import cz.muni.airport.facadeApi.StewardFacade;

/**
 * Created by Richard Bariny on 10.12.2016.
 *
 * @author Richard Bariny, github name:Richardb953
 */
@Controller
@RequestMapping("/flight")
@Transactional
public class FlightController {

    @Autowired
    private FlightFacade flightFacade;

    @Autowired
    private AirportFacade airportFacade;

    @Autowired
    private AirplaneFacade airplaneFacade;
    
    @Autowired
    private StewardFacade stewardFacade;

    /**
     * Shows a list of products with the ability to add, delete or edit.
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("flights", airportFacade.getAllAirports());
        return "flight";
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String flights(Model model) {
        model.addAttribute("flights", flightFacade.getAllFlights());
        model.addAttribute("airplanes", airplaneFacade.getAllAirplanes());
        model.addAttribute("stewards", stewardFacade.getAllStewards());
        model.addAttribute("airports", airportFacade.getAllAirports());
        return "flights";
    }

}
