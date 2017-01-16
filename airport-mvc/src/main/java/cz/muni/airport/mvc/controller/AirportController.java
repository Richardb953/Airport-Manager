package cz.muni.airport.mvc.controller;

import cz.muni.airport.dto.AirportDTO;
import cz.muni.airport.facadeApi.AirportFacade;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Jiri Krejci, github name:xkrejci7
 */
@Controller
@RequestMapping("/airport")
@Transactional
public class AirportController {

    @Autowired
    private AirportFacade airportFacade;

//    private final static Logger log = LoggerFactory.getLogger(AirportController.class);

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAllAirports(Model model) {
        model.addAttribute("airports", airportFacade.getAllAirports());
        model.addAttribute("newAirport", new AirportDTO());
        return "airports";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String createAirport(@Valid @ModelAttribute("newAirport") AirportDTO newAirport, Model model,
            BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        airportFacade.createAirport(newAirport);
        return "redirect:/airport/all";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String removeAirport(@PathVariable long id, RedirectAttributes redirectAttributes) {
        airportFacade.removeAirport(id);
        return "redirect:/airport/all";
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable long id, Model model){
        model.addAttribute("airportToUpdate", airportFacade.getAirportById(id));
        return "airport_update";
    }
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updateAirport(@PathVariable long id, @Valid @ModelAttribute("airportToUpdate") AirportDTO airportToUpdate, Model model,
            BindingResult bindingResult) {
        if(!bindingResult.hasErrors()){
            airportToUpdate.setId(id);
            
            airportFacade.updateAirport(airportToUpdate);
            return "redirect:/airport/all"; 
        }
        return "airport_update";
    }
}
