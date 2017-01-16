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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import cz.muni.airport.dto.AirplaneDTO;
import cz.muni.airport.dto.AirportDTO;
import cz.muni.airport.dto.FlightDTO;
import cz.muni.airport.dto.StewardDTO;
import cz.muni.airport.enums.FlightState;
import cz.muni.airport.facadeApi.AirplaneFacade;
import cz.muni.airport.facadeApi.AirportFacade;
import cz.muni.airport.facadeApi.FlightFacade;
import cz.muni.airport.facadeApi.StewardFacade;
import cz.muni.airport.mvc.data.AirplaneSelection;
import cz.muni.airport.mvc.data.AirplaneWrapper;
import cz.muni.airport.mvc.data.AirportSelection;
import cz.muni.airport.mvc.data.StewardWrapper;
import cz.muni.airport.mvc.data.AirportWrapper;
import cz.muni.airport.mvc.data.StewardSelection;

/**
 * Created by Richard Bariny on 10.12.2016.
 * Flight object controller for templates about Flight
 * @author Richard Bariny, github name:Richardb953
 */
@Controller
@RequestMapping("/flight")
@Transactional
public class FlightController {

    private static final String DATE_FORMAT = "YYYY-MM-DD'T'hh:mm";

    private final FlightFacade flightFacade;

    private final AirportFacade airportFacade;

    private final StewardFacade stewardFacade;

    private final AirplaneFacade airplaneFacade;

    private final static Logger log = LoggerFactory.getLogger(FlightController.class);

    @Autowired
    public FlightController(AirportFacade airportFacade, StewardFacade stewardFacade, AirplaneFacade airplaneFacade, FlightFacade flightFacade) {
        this.airportFacade = airportFacade;
        this.stewardFacade = stewardFacade;
        this.airplaneFacade = airplaneFacade;
        this.flightFacade = flightFacade;
    }

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
     *
     * @param model of formular
     * @return formular
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addFlight(Model model) {

        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setArrival(new Date());
        flightDTO.setDeparture(new Date());

        //todo odstranit formatter
        model.addAttribute("formatter", new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH));
        model.addAttribute("flight", flightDTO);
        return "flight_add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addFlight(
            @Valid @ModelAttribute(value = "flight") FlightDTO flight,
            BindingResult result
    ) {
        if (!result.hasErrors()) {
            flight.setFlightState(FlightState.OPEN);
            flightFacade.createNewFlight(flight);
            return "redirect:/flight/all";
        } else {
            for (ObjectError err : result.getAllErrors()) {
                log.warn("Template problem: " + err.getDefaultMessage());
            }
        }

        return "flight_add";
    }

    /**
     * *
     * ****************************************************************************************************************************
     * UPDATE FLIGHT formular
     *
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
        if (!result.hasErrors()) {
            FlightDTO flightDTO = flightFacade.getFlightById(flight.getId());
            flightDTO.setName(flight.getName());
            flightDTO.setPassagers(flight.getPassagers());
            flightDTO.setDeparture(flight.getDeparture());
            flightDTO.setArrival(flight.getArrival());

            flightFacade.updateFlight(flightDTO);
            return "redirect:/flight/all";
        } else {
            for (ObjectError err : result.getAllErrors()) {
                log.warn("Template problem: " + err.getDefaultMessage());
            }
        }

        return "flight_update";
    }

    /**
     * ****************************************************************************************************************************
     * Remove Flight there is no difference between POST and GET because of logic of template call
     * @param flightId ID of removed flight
     * @return template
     */
    @RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
    public String removeFlight(
            @PathVariable(value = "id") Long flightId
    ) {
        if (flightId != null) {
            flightFacade.removeFlight(flightId);
        }

        return "redirect:/flight/all";
    }

    @RequestMapping(value = "remove/{id}", method = RequestMethod.POST)
    public String removeFlight(
            @PathVariable(value = "id") Long flightId,
            Model model
    ) {
        if (flightId != null) {
            flightFacade.removeFlight(flightId);
        }

        return "redirect:/flight/all";
    }

    /**
     * *********************************************************************************************************************************
     * Set source port for flight
     *
     * @param flightId flight
     * @param model formular model
     * @return formular
     */
    @RequestMapping(value = "/{id}/airport/source", method = RequestMethod.GET)
    public String sourceAirport(
            @PathVariable(value = "id") Long flightId,
            Model model
    ) {

        FlightDTO flightDTO = flightFacade.getFlightById(flightId);
        List<AirportDTO> airportDTOs = airportFacade.getAllAirports();
        AirportDTO choosed = null;
        if (flightDTO.getSourceport() != null) {
            choosed = flightDTO.getSourceport();
        }

        AirportWrapper wrapper = new AirportWrapper();
        //prepare data for view
        ArrayList<AirportSelection> allClientsWithSelection = processData(airportDTOs,choosed);
        wrapper.setClientList(allClientsWithSelection);

        model.addAttribute("airports", airportDTOs);
        model.addAttribute("flight", flightDTO);
        model.addAttribute("wrapper", wrapper);

        return "flight_add_airport";

    }

    @RequestMapping(value = "/{id}/airport/source", method = RequestMethod.POST)
    public String sourceAirport(
            @PathVariable(value = "id") Long flightId,
            @ModelAttribute AirportWrapper wrapper,
            BindingResult result
    )
    {
       if ( !result.hasErrors() ) {
           List<AirportDTO> choosedAirports = new ArrayList<>();
           if(wrapper != null ) {
               for (AirportSelection airportSelection : wrapper.getClientList()) {
                   if(airportSelection.getSelected()){
                       choosedAirports.add(airportFacade.getAirportById(Long.parseLong(airportSelection.getAirportID())));
                   }
               }
           }
            FlightDTO flightDTO = flightFacade.getFlightById(flightId);
           //there should be at least one airport, if there is more it is frontend bug
            flightDTO.setSourceport(choosedAirports.get(0));
            flightFacade.updateFlight(flightDTO);
            return "redirect:/flight/all";

        }
        return "flight_add_airport";
    }

    /**
     * **************************************************************************************************************************
     * Add destination airport to flight
     *
     * @param flightId flight id
     * @param model formular model
     * @return formular
     */
    @RequestMapping(value = "/{id}/airport/destination", method = RequestMethod.GET)
    public String destinationAirport(
            @PathVariable(value = "id") Long flightId,
            Model model
    ) {

        FlightDTO flightDTO = flightFacade.getFlightById(flightId);
        List<AirportDTO> airportDTOs = airportFacade.getAllAirports();
        AirportDTO choosed = null;
        if (flightDTO.getDestinationport() != null) {
            choosed = flightDTO.getDestinationport();
        }

        AirportWrapper wrapper = new AirportWrapper();
        ArrayList<AirportSelection> allClientsWithSelection = processData(airportDTOs, choosed);

        wrapper.setClientList(allClientsWithSelection);

        model.addAttribute("airports", airportDTOs);
        model.addAttribute("flight", flightDTO);
        model.addAttribute("wrapper", wrapper);

        return "flight_add_airport";

    }

    @RequestMapping(value = "/{id}/airport/destination", method = RequestMethod.POST)
    public String destinationAirport(
            @PathVariable(value = "id") Long flightId,
            @ModelAttribute AirportWrapper wrapper,
            BindingResult result
    )
    {
        if ( !result.hasErrors() ) {
            List<AirportDTO> choosedAirports = new ArrayList<>();
            if (wrapper != null) {
                for (AirportSelection airportSelection : wrapper.getClientList()) {
                    if (airportSelection.getSelected()) {
                        choosedAirports.add(airportFacade.getAirportById(Long.parseLong(airportSelection.getAirportID())));
                    }
                }
            }

            if (!result.hasErrors()) {
                //load basic
                FlightDTO flightDTO = flightFacade.getFlightById(flightId);
                flightDTO.setDestinationport(choosedAirports.get(0));
                flightFacade.updateFlight(flightDTO);
                return "redirect:/flight/all";
            }
        }
        return "flight_add_airport";
    }

    /**
     * Add steward to flight
     *
     * @param flightId flight id
     * @param model formular model
     * @return formular
     */
    @RequestMapping(value = "/{id}/steward", method = RequestMethod.GET)
    public String stewardFlight(
            @PathVariable(value = "id") Long flightId,
            Model model
    ) {
        FlightDTO flightDTO = flightFacade.getFlightById(flightId);
        List<StewardDTO> flightStewardDTOs = flightDTO.getStewards();
        List<StewardDTO> allStewardsDTOs = stewardFacade.getAllStewards();
        StewardWrapper wrapper = new StewardWrapper();
        ArrayList<StewardSelection> allClientsWithSelection = new ArrayList<>();

        for (StewardDTO stewardDTO : allStewardsDTOs) {
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
            @ModelAttribute StewardWrapper wrapper,
            BindingResult result
        ){
        if ( !result.hasErrors() ) {
            FlightDTO flightDTO = flightFacade.getFlightById(flightId);
            if(wrapper != null ) {
                flightDTO = flightFacade.updateFlight(flightDTO);
                for (StewardSelection stewardSelection : wrapper.getClientList()) {
                    //todo logiku presunut do facade
                    if(stewardSelection.getSelected()){
                        if(!flightDTO.getStewards().contains(stewardFacade.getSteward(Long.parseLong(stewardSelection.getStewardID())))){
                            flightDTO = flightFacade.addStewardToFlight(flightDTO, (stewardFacade.getSteward(Long.parseLong(stewardSelection.getStewardID()))));
                        }
                        System.out.print("ADDING STEWARD " + stewardFacade.getSteward(Long.parseLong(stewardSelection.getStewardID())));

                    }
                     else {
                        if(flightDTO.getStewards().contains(stewardFacade.getSteward(Long.parseLong(stewardSelection.getStewardID())))) {
                            flightDTO = flightFacade.removeStewardToFlight(flightDTO, (stewardFacade.getSteward(Long.parseLong(stewardSelection.getStewardID()))));
                        }
                        System.out.print("REMOVING STEWARD " + stewardFacade.getSteward(Long.parseLong(stewardSelection.getStewardID())));

                        }
                    }
                }
            return "redirect:/flight/all";
        }

        return "steward_flight";
    }

    /**
     * *********************************************************************************************************************************
     * Set airplane for flight
     *
     * @param flightId flight
     * @param model formular model
     * @return formular
     */
    @RequestMapping(value = "/{id}/airplane", method = RequestMethod.GET)
    public String airplane(
            @PathVariable(value = "id") Long flightId,
            Model model
    ) {

        FlightDTO flightDTO = flightFacade.getFlightById(flightId);
        //todo just available
        List<AirplaneDTO> airplanes = airplaneFacade.getAllAirplanes();
        AirplaneDTO choosed = null;
        if (flightDTO.getAirplane() != null) {
            choosed = flightDTO.getAirplane();
        }

        AirplaneWrapper wrapper = new AirplaneWrapper();
        ArrayList<AirplaneSelection> allClientsWithSelection = new ArrayList<>();

        for (AirplaneDTO airplaneDTO : airplanes) {
            AirplaneSelection airplaneSelection = new AirplaneSelection();
            airplaneSelection.setSelected((choosed != null) && airplaneDTO.getId().equals(choosed.getId()));
            airplaneSelection.setAirplaneID(airplaneDTO.getId().toString());
            airplaneSelection.setName(airplaneDTO.getName());
            airplaneSelection.setCapacity(String.valueOf(airplaneDTO.getCapacity()));
            airplaneSelection.setType(airplaneDTO.getType().toString());
            allClientsWithSelection.add(airplaneSelection);
        }

        wrapper.setClientList(allClientsWithSelection);

        model.addAttribute("airports", airplanes);
        model.addAttribute("flight", flightDTO);
        model.addAttribute("wrapper", wrapper);

        return "flight_add_airplane";

    }

    @RequestMapping(value = "/{id}/airplane", method = RequestMethod.POST)
    public String airplane(
            @PathVariable(value = "id") Long flightId,
            @ModelAttribute AirplaneWrapper wrapper,
            BindingResult result
    )
    {
       if ( !result.hasErrors() ) {
            List<AirplaneDTO> chooseAirplane = new ArrayList<>();
            if(wrapper != null ) {
                for (AirplaneSelection airplaneSelection : wrapper.getClientList()) {
                    if(airplaneSelection.getSelected()){
                        chooseAirplane.add(airplaneFacade.getAirplaneById(Long.parseLong(airplaneSelection.getAirplaneID())));
                    }
                }
            }
            FlightDTO flightDTO = flightFacade.getFlightById(flightId);
            flightDTO.setAirplane(chooseAirplane.get(0));
            flightFacade.updateFlight(flightDTO);
            return "redirect:/flight/all";

        }
        return "flight_add_airplane";
    }


    /**
     * Prepare Template Airport view data - necessary because of select boxes
     * @param airportDTOs aiports
     * @param choosed picked Airports
     * @return DataView
     */
    private static ArrayList<AirportSelection> processData(List<AirportDTO> airportDTOs, AirportDTO choosed){
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
        return allClientsWithSelection;
    }

}
