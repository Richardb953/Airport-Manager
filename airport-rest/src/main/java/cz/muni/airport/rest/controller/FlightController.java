package cz.muni.airport.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.validation.Valid;

import cz.muni.airport.dto.AirportDTO;
import cz.muni.airport.dto.FlightDTO;
import cz.muni.airport.enums.FlightState;
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
    private static final String DATE_FORMAT = "YYYY-MM-DD'T'hh:mm";

    @Autowired
    private FlightFacade flightFacade;

    @Autowired
    private AirportFacade airportFacade;
    private final static Logger log = LoggerFactory.getLogger(FlightController.class);

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

    /***
     * UPDATE FLIGHT formular
     * @param flightId ID of flight entity
     * @param model formular
     * @return formular
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateFlight(
            @PathVariable(value = "id") Long flightId,
            Model model
    ) {
        model.addAttribute("formatter", new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH));
        model.addAttribute("flight", flightFacade.getFlightById(flightId));
        return "flight_update";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.POST)
    public String updateFlight(
            @Valid FlightDTO flight,
            BindingResult result,
            Model model
    ) {
        if ( !result.hasErrors() ) {
            FlightDTO flightDTO = flightFacade.getFlightById(flight.getId());
            //flightDTO.setName(flight.getName());
            //flightDTO.setDeparture(flight.getDeparture());
            //flightDTO.setArrival(flight.getArrival());

            flightFacade.updateFlight(flight);
            return "redirect:/flight/all";
        } else{
            for(ObjectError err : result.getAllErrors()) {
                log.warn("Template problem: "  + err.getDefaultMessage());
            }
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
    public String sourceAirport(
            @PathVariable(value = "id") Long flightId,
            Model model
    ){
        //todo vracia letiska
        model.addAttribute("aiports", airportFacade.getAllAirports());
        model.addAttribute("flight", flightFacade.getFlightById(flightId));
        return "flight_add_airport";

    }

    @RequestMapping(value = "/{id}/airport/source", method = RequestMethod.POST)
    public String sourceAirport( @ModelAttribute(value = "flight") FlightDTO flight,
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
    public String destinationAirport(
            @PathVariable(value = "id") Long flightId,
            Model model
    ){
        model.addAttribute("aiports", airportFacade.getAllAirports());
        model.addAttribute("flight", flightFacade.getFlightById(flightId));
        return "flight_add_airport";

    }

    @RequestMapping(value = "/{id}/airport/destination", method = RequestMethod.POST)
    public String destinationAirport(
            @ModelAttribute(value = "flight") FlightDTO flight,
            @ModelAttribute(value = "airport") AirportDTO airport,
            BindingResult result,
            Model model
    ){
        if ( !result.hasErrors() ) {
            if(airport != null){
                flight.setDestinationport(airport);
                flightFacade.updateFlight(flight);
                return "redirect:/flight/all";

            }
        }
        return "flight_add_airport";
    }

}
