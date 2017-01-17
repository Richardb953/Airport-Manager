package cz.muni.airport.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import cz.muni.airport.dto.AirplaneDTO;
import cz.muni.airport.facadeApi.AirplaneFacade;

/**
 * Created by Karolina Bozkova on 7.1.2017.
 *
 * Airplane Controller for Airplane Templates *
 * @author Karolina Bozkova, githubname: Kayeeec
 */


@Controller
@RequestMapping("/airplane")
@Transactional
public class AirplaneController {
    
    @Autowired
    private AirplaneFacade airplaneFacade;
    private final static Logger log = LoggerFactory.getLogger(AirplaneController.class);

    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_CASHIER')")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String list(Model model) { 
        model.addAttribute("airplanes", airplaneFacade.getAllAirplanes());
        model.addAttribute("newAirplane", new AirplaneDTO());
        return "airplanes";
    }
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addAirplane(@Valid @ModelAttribute("newAirplane") AirplaneDTO newAirplane, Model model, 
            BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        
        AirplaneDTO airplaneDTO = airplaneFacade.createAirplane(newAirplane);
            return "redirect:/airplane/all"; 
    }
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteAirplane(@PathVariable long id, RedirectAttributes redirectAttributes) {
        airplaneFacade.deleteAirplane(id);
        return "redirect:/airplane/all";
    }
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(value = "/semiupdate/{id}", method = RequestMethod.GET)
    public String update(@PathVariable long id, Model model){
        model.addAttribute("airplaneToUpdate", airplaneFacade.getAirplaneById(id));
        return "update_airplane";
    }
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updateAirplane(@PathVariable long id, @Valid @ModelAttribute("airplaneToUpdate") AirplaneDTO airplaneToUpdate, Model model,
            BindingResult bindingResult) {
        if(!bindingResult.hasErrors()){
            AirplaneDTO original = airplaneFacade.getAirplaneById(id);
            original.setCapacity(airplaneToUpdate.getCapacity());
            original.setName(airplaneToUpdate.getName());
            original.setType(airplaneToUpdate.getType());
            
            airplaneFacade.updateAirplane(original);
            return "redirect:/airplane/all"; 
        }
        return "update_airplane";
    }
    
    
    
    
}
