package cz.muni.airport.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

import cz.muni.airport.dto.AirportDTO;
import cz.muni.airport.dto.FlightDTO;
import cz.muni.airport.facadeApi.AirportFacade;
import cz.muni.airport.facadeApi.FlightFacade;
import cz.muni.airport.model.Flight;

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

    /**
     * Shows a list of products with the ability to add, delete or edit.
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("flights", flightFacade.getAllFlights());
        return "flights";
    }

    /**
     * Create new Flight formular
     * @param model of formular
     * @return formular
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addFlightGet(Model model) {
        FlightDTO flightDTO  = new FlightDTO();
        model.addAttribute( "flight", flightDTO);
        return "flight_add";
    }
    @RequestMapping(value = "/add/", method = RequestMethod.POST)
    public String addFlightPost(
            @Valid @ModelAttribute(value = "flight") FlightDTO flight,
            BindingResult result,
            Model model
    ) {
        if ( !result.hasErrors() ) {
            flightFacade.createFlight(flight);
            return "redirect:/flight/list";
        }

        return "flight_add";
    }

    /***
     * UPDATE FLIGHT formular
     * @param flightId ID of flight entity
     * @param model formular
     * @return formular
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateFlightGet(
            @PathVariable(value = "id") Long flightId,
            Model model
    ) {

        model.addAttribute("flight", flightFacade.getFlightById(flightId));
        return "flight_update";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.POST)
    public String updateFlightPost(
            @Valid @ModelAttribute(value = "flight") FlightDTO flight,
            BindingResult result,
            Model model
    ) {
        if ( !result.hasErrors() ) {
            flightFacade.updateFlight(flight);
            return "redirect:/flight/list/";
        }

        return "flight_update";
    }

    /**
     * Set source port for flight
     * @param flightId flight
     * @param model formular model
     * @return formular
     */
    @RequestMapping(value = "/{id}/airport/source", method = RequestMethod.GET)
    public String sourceAirportGet(
            @PathVariable(value = "id") Long flightId,
            Model model
    ){
        //todo vracia letiska
        model.addAttribute("aiports", airportFacade.getAllAirports());
        model.addAttribute("flight", flightFacade.getFlightById(flightId));
        return "flight_add_airport";

    }

    @RequestMapping(value = "/{id}/airport/source", method = RequestMethod.POST)
    public String sourceAirportPost( @ModelAttribute(value = "flight") FlightDTO flight,
                                 @ModelAttribute(value = "airport") AirportDTO airport,
                                 BindingResult result,
                                 Model model
    ){
        if ( !result.hasErrors() ) {
            if(airport != null){
                flight.setSourceport(airport);
                flightFacade.updateFlight(flight);
            }
        }
        return "flight_add_airport";
    }

    /**
     * Add destination airport to flight
     * @param flightId flight id
     * @param model formular model
     * @return formular
     */
    @RequestMapping(value = "/{id}/airport/destination", method = RequestMethod.GET)
    public String destinationAirportGet(
            @PathVariable(value = "id") Long flightId,
            Model model
    ){
        model.addAttribute("aiports", airportFacade.getAllAirports());
        model.addAttribute("flight", flightFacade.getFlightById(flightId));
        return "flight_add_airport";

    }

    @RequestMapping(value = "/{id}/airport/destination", method = RequestMethod.POST)
    public String destinationAirportPost(
            @ModelAttribute(value = "flight") FlightDTO flight,
            @ModelAttribute(value = "airport") AirportDTO airport,
            BindingResult result,
            Model model
    ){
        if ( !result.hasErrors() ) {
            if(airport != null){
                flight.setDestinationport(airport);
                flightFacade.updateFlight(flight);
                return "redirect:/flight/list";

            }
        }
        return "flight_add_airport";
    }




}
