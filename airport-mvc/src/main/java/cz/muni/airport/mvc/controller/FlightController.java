package cz.muni.airport.mvc.controller;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.validation.Valid;

import cz.muni.airport.dao.StewardDAO;
import cz.muni.airport.dto.AirportDTO;
import cz.muni.airport.dto.FlightDTO;
import cz.muni.airport.dto.StewardDTO;
import cz.muni.airport.enums.FlightState;
import cz.muni.airport.facadeApi.AirportFacade;
import cz.muni.airport.facadeApi.FlightFacade;
import cz.muni.airport.facadeApi.StewardFacade;
import cz.muni.airport.model.Airport;
import cz.muni.airport.model.Flight;
import cz.muni.airport.model.Steward;

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

    @Autowired
    private StewardFacade stewardFacade;

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

    /**
     * **********************************************************************************************************
     * Create new Flight formular
     * @param model of formular
     * @return formular
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addFlight(Model model) {

        FlightDTO flightDTO  = new FlightDTO();
        flightDTO.setArrival(new Date());
        flightDTO.setDeparture(new Date());

        model.addAttribute("formatter", new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH));
        model.addAttribute("flight", flightDTO);
        return "flight_add";
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addFlight(
            @Valid @ModelAttribute(value = "flight") FlightDTO flight,
            BindingResult result,
            Model model
    ) {
        if ( !result.hasErrors() ) {
            flight.setFlightState(FlightState.OPEN);
            flightFacade.createNewFlight(flight);
            return "redirect:/flight/all";
        } else{
            for(ObjectError err : result.getAllErrors()) {
                log.warn("Template problem: "  + err.getDefaultMessage());
            }
        }

        return "flight_add";
    }

    /***
     * ****************************************************************************************************************************
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

    @RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
    public String removeFlight(
            @PathVariable(value = "id") Long flightId,
            Model model
    ) {
        if( flightId != null ){
            flightFacade.removeFlight(flightId);
        }

        return "redirect:/flight/all";
    }

    @RequestMapping(value = "remove/{id}", method = RequestMethod.POST)
    public String removeFlight(
            @PathVariable(value = "id") Long flightId,
            BindingResult result,
            Model model
    ) {
       if( flightId != null ){
           flightFacade.removeFlight(flightId);
       }

        return "redirect:/flight/all";
    }

    /**
     * *********************************************************************************************************************************
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
        FlightDTO flightDTO = flightFacade.getFlightById(flightId);
        Long checkedItem = 0L;
        if(flightDTO.getSourceport() != null )
            checkedItem = flightDTO.getSourceport().getId();

        model.addAttribute("airports", airportFacade.getAllAirports());
        model.addAttribute("flight", flightDTO);
        model.addAttribute("checkedItem", checkedItem );

        return "flight_add_airport";

    }

    @RequestMapping(value = "/{id}/airport/source", method = RequestMethod.POST)
    public String sourceAirport(
            @PathVariable(value = "id") Long flightId,
            BindingResult result,
            Long checkedItem,
            Model model
    ){
        if ( !result.hasErrors() ) {
            FlightDTO flightDTO = flightFacade.getFlightById(flightId);
            AirportDTO airportDTO = airportFacade.getAirportById(checkedItem);
            flightDTO.setSourceport(airportDTO);
            flightFacade.updateFlight(flightDTO);
            return "redirect:/flight/all";
        }
        return "flight_add_airport";
    }

    /**
     * **************************************************************************************************************************
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
        Long  checkedItem = 0L;
        FlightDTO flightDTO = flightFacade.getFlightById(flightId);
        if(flightDTO.getDestinationport() != null)
            checkedItem = flightDTO.getDestinationport().getId();

        model.addAttribute("airports", airportFacade.getAllAirports());
        model.addAttribute("flight", flightDTO);
        model.addAttribute("checkedItem", checkedItem );

        return "flight_add_airport";

    }

    @RequestMapping(value = "/{id}/airport/destination", method = RequestMethod.POST)
    public String destinationAirport(
            @PathVariable(value = "id") Long flightId,
            Long checkedItem,
            BindingResult result,
            Model model
    ){
        if ( !result.hasErrors() ) {
                FlightDTO flightDTO = flightFacade.getFlightById(flightId);
                flightDTO.setDestinationport(airportFacade.getAirportById(checkedItem));
                flightFacade.updateFlight(flightDTO);
                return "redirect:/flight/all";
        }
        return "flight_add_airport";
    }

    /**
     * Add steward to flight
     * @param flightId flight id
     * @param model formular model
     * @return formular
     */
    @RequestMapping(value = "/{id}/steward", method = RequestMethod.GET)
    public String stewardFlight(
            @PathVariable(value = "id") Long flightId,
            Model model
    ){
        FlightDTO flightDTO = flightFacade.getFlightById(flightId);
        List<Long> checkedItem = flightDTO.getStewards().stream().map(StewardDTO::getId).collect(Collectors.toList());

        //todo len available stewards
        model.addAttribute("stewards", stewardFacade.getAllStewards());
        model.addAttribute("flight", flightDTO);
        model.addAttribute("checkedItem", checkedItem);
        return "steward_flight";

    }

    @RequestMapping(value = "/{id}/steward", method = RequestMethod.POST)
    public String stewardFlight(
            @PathVariable(value = "id") Long flightId,
            List<String> checkedItem,
            BindingResult result,
            Model model
    ){
        if ( !result.hasErrors() ) {
            if(checkedItem != null){
                //load basic
                FlightDTO flightDTO = flightFacade.getFlightById(flightId);
                //update just stewards nor other fields
                List<StewardDTO> stewardDTOs = new ArrayList<>();
                for(String item : checkedItem){
                    long l = Long.parseLong(item);
                    stewardDTOs.add(stewardFacade.getSteward(l));
                }
                flightDTO.setStewards(stewardDTOs);
                flightFacade.updateFlight(flightDTO);
                return "redirect:/flight/all";

            }
        }
        return "steward_flight";
    }

}
