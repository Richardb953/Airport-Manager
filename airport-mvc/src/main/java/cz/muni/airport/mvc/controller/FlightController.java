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
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import cz.muni.airport.dto.AirportDTO;
import cz.muni.airport.dto.FlightDTO;
import cz.muni.airport.dto.StewardDTO;
import cz.muni.airport.enums.FlightState;
import cz.muni.airport.facadeApi.AirportFacade;
import cz.muni.airport.facadeApi.FlightFacade;
import cz.muni.airport.facadeApi.StewardFacade;
import cz.muni.airport.mvc.data.AirportSelection;
import cz.muni.airport.mvc.data.ClientWithSelectionListWrapper;
import cz.muni.airport.mvc.data.ClientWithSelectionLongWrapper;
import cz.muni.airport.mvc.data.StewardSelection;

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
        List<AirportDTO> airportDTOs = airportFacade.getAllAirports();
        AirportDTO choosed = null;
        if(flightDTO.getSourceport() != null ){
            choosed = flightDTO.getSourceport();
        }

        ClientWithSelectionLongWrapper wrapper = new ClientWithSelectionLongWrapper();
        ArrayList<AirportSelection> allClientsWithSelection = new ArrayList<>();

        for(AirportDTO airportDTO : airportDTOs){
            AirportSelection airportSelection = new AirportSelection();
            airportSelection.setSelected((choosed != null) && airportDTO.getId().equals(choosed.getId()));
            airportSelection.setAirportID(airportDTO.getId().toString());
            airportSelection.setCity(airportDTO.getCity());
            airportSelection.setName(airportDTO.getName());
            airportSelection.setCountry(airportDTO.getCountry());
            allClientsWithSelection.add(airportSelection);
        }

        wrapper.setClientList(allClientsWithSelection);

        model.addAttribute("airports", airportDTOs);
        model.addAttribute("flight", flightDTO);
        model.addAttribute("wrapper", wrapper);

        return "flight_add_airport";

    }

    @RequestMapping(value = "/{id}/airport/source", method = RequestMethod.POST)
    public String sourceAirport(
            @PathVariable(value = "id") Long flightId,
            @ModelAttribute ClientWithSelectionLongWrapper wrapper,
            BindingResult result,
            Model model
            )
    {
        List<AirportDTO> choosedAirports = new ArrayList<>();
        if(wrapper != null ) {
            for (AirportSelection airportSelection : wrapper.getClientList()) {
                if(airportSelection.getSelected()){
                    choosedAirports.add(airportFacade.getAirportById(Long.parseLong(airportSelection.getAirportID())));
                }
            }
        }

        if ( !result.hasErrors() ) {
            //load basic
            FlightDTO flightDTO = flightFacade.getFlightById(flightId);
            //update just stewards nor other fields
            flightDTO.setSourceport(choosedAirports.get(0));
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

        FlightDTO flightDTO = flightFacade.getFlightById(flightId);
        List<AirportDTO> airportDTOs = airportFacade.getAllAirports();
        AirportDTO choosed = null;
        if(flightDTO.getDestinationport() != null ){
            choosed = flightDTO.getDestinationport();
        }

        ClientWithSelectionLongWrapper wrapper = new ClientWithSelectionLongWrapper();
        ArrayList<AirportSelection> allClientsWithSelection = new ArrayList<>();

        for(AirportDTO airportDTO : airportDTOs){
            AirportSelection airportSelection = new AirportSelection();
            airportSelection.setSelected((choosed != null) && airportDTO.getId().equals(choosed.getId()));
            airportSelection.setAirportID(airportDTO.getId().toString());
            airportSelection.setCity(airportDTO.getCity());
            airportSelection.setName(airportDTO.getName());
            airportSelection.setCountry(airportDTO.getCountry());
            allClientsWithSelection.add(airportSelection);
        }

        wrapper.setClientList(allClientsWithSelection);

        model.addAttribute("airports", airportDTOs);
        model.addAttribute("flight", flightDTO);
        model.addAttribute("wrapper", wrapper);

        return "flight_add_airport";

    }

    @RequestMapping(value = "/{id}/airport/destination", method = RequestMethod.POST)
    public String destinationAirport(
            @PathVariable(value = "id") Long flightId,
            @ModelAttribute ClientWithSelectionLongWrapper wrapper,
            BindingResult result,
            Model model
    )
    {
        List<AirportDTO> choosedAirports = new ArrayList<>();
        if(wrapper != null ) {
            for (AirportSelection airportSelection : wrapper.getClientList()) {
                if(airportSelection.getSelected()){
                    choosedAirports.add(airportFacade.getAirportById(Long.parseLong(airportSelection.getAirportID())));
                }
            }
        }

        if ( !result.hasErrors() ) {
            //load basic
            FlightDTO flightDTO = flightFacade.getFlightById(flightId);
            //update just stewards nor other fields
            flightDTO.setDestinationport(choosedAirports.get(0));
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
        List<StewardDTO> flightStewardDTOs = flightDTO.getStewards();
        List<StewardDTO> allStewardsDTOs = stewardFacade.getAllStewards();
        ClientWithSelectionListWrapper wrapper = new ClientWithSelectionListWrapper();
        ArrayList<StewardSelection> allClientsWithSelection = new ArrayList<>();

        for(StewardDTO stewardDTO : allStewardsDTOs){
            StewardSelection clientWithSelection = new StewardSelection();
            clientWithSelection.setSelected(flightStewardDTOs.contains(stewardDTO));
            clientWithSelection.setStewardID(stewardDTO.getId().toString());
            clientWithSelection.setFirstName(stewardDTO.getFirstName());
            clientWithSelection.setSecondName(stewardDTO.getLastName());
            allClientsWithSelection.add(clientWithSelection);
        }

        wrapper.setClientList(allClientsWithSelection);
        //todo len available stewards
        model.addAttribute("stewards", allStewardsDTOs);
        model.addAttribute("flight", flightDTO);
        model.addAttribute("wrapper", wrapper);
        return "steward_flight";

    }

    @RequestMapping(value = "/{id}/steward", method = RequestMethod.POST)
    public String stewardFlight(
            @PathVariable(value = "id") Long flightId,
            @ModelAttribute ClientWithSelectionListWrapper wrapper,
            BindingResult result,
            Model model
    ){
        List<StewardDTO> choosedStewards = new ArrayList<>();
        if(wrapper != null ) {
            for (StewardSelection stewardSelection : wrapper.getClientList()) {
                if(stewardSelection.getSelected()){
                    choosedStewards.add(stewardFacade.getSteward(Long.parseLong(stewardSelection.getStewardID())));
                }
            }
        }

        if ( !result.hasErrors() ) {
                //load basic
                FlightDTO flightDTO = flightFacade.getFlightById(flightId);
                //update just stewards nor other fields
                flightDTO.setStewards(choosedStewards);
                flightFacade.updateFlight(flightDTO);
                return "redirect:/flight/all";

        }
        return "steward_flight";
    }

}
