package cz.muni.airport.mvc.controller;

import cz.muni.airport.dto.StewardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.muni.airport.facadeApi.StewardFacade;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Steward Controller for steward templates
 * @author Andrea Navratilova, github name: andrea-n
 */

@Controller
@RequestMapping("/steward")
@Transactional
public class StewardController {
	
	@Autowired
    private StewardFacade stewardFacade;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String stewards(Model model) {
		model.addAttribute("stewards", stewardFacade.getAllStewards());
		StewardDTO stewardDTO  = new StewardDTO();

        model.addAttribute("steward", stewardDTO);
        return "stewards";
    }
	
	@RequestMapping(value = "/all", method = RequestMethod.POST)
     public String addSteward(@Valid @ModelAttribute(value = "steward") StewardDTO steward, BindingResult result, Model model) {
        if ( !result.hasErrors() ) {
            stewardFacade.createSteward(steward);
            return "redirect:/steward/all";
        }

        return "stewards";
    }
	 
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateSteward(@PathVariable(value = "id") Long stewardId, Model model) {
        model.addAttribute("steward", stewardFacade.getSteward(stewardId));
        return "steward_update";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.POST)
    public String updateSteward(@Valid StewardDTO steward, BindingResult result, Model model) {
        if ( !result.hasErrors() ) {
            StewardDTO stewardDTO = stewardFacade.getSteward(steward.getId());
            stewardDTO.setFirstName(steward.getFirstName());
            stewardDTO.setLastName(steward.getLastName());

            stewardFacade.updateSteward(stewardDTO);
            return "redirect:/steward/all";
        }
        return "flight_update";
    }

	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String removeSteward(@PathVariable(value = "id") Long stewardId, Model model) {
		StewardDTO stewardDTO = stewardFacade.getSteward(stewardId);
		stewardFacade.deleteSteward(stewardDTO);
		return "redirect:/steward/all";
    }
}
