package cz.muni.airport.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.muni.airport.facadeApi.FlightFacade;
import cz.muni.airport.mvc.conf.MvcConfig;

/**
 * Created by Richard Bariny on 10.12.2016.
 *
 * @author Richard Bariny, github name:Richardb953
 */

@Controller
@RequestMapping("/flight")
public class FlightController {

    private final FlightFacade flightFacade;

    @Autowired
    public FlightController(FlightFacade flightFacade) {
        this.flightFacade = flightFacade;
    }

    /**
     * Shows a list of products with the ability to add, delete or edit.
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("flights", flightFacade.getAllFlights());
        return "/templates/flight/list";
    }
}
