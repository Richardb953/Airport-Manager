package cz.muni.airport.mvc.controller;

import cz.muni.airport.dto.AirplaneDTO;
import cz.muni.airport.enums.PlaneType;
import cz.muni.airport.facadeApi.AirplaneFacade;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by Richard Bariny on 7.1.2017.
 *
 * @author Richard Bariny, github name:Richardb953
 */


@Controller
@RequestMapping("/airplane")
@Transactional
public class AirplaneController {
    
    @Autowired
    private AirplaneFacade airplaneFacade;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) { 
        model.addAttribute("airplanes", airplaneFacade.getAllAirplanes());
        model.addAttribute("newAirplane", new AirplaneDTO());
        model.addAttribute("toUpdate", new AirplaneDTO());
        return "airplanes";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addAirplane(@ModelAttribute("newAirplane") AirplaneDTO newAirplane, Model model, 
            BindingResult bindingResult, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        AirplaneDTO airplaneDTO = airplaneFacade.createAirplane(newAirplane);
        return "redirect:/airplane/list";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteAirplane(@PathVariable long id, RedirectAttributes redirectAttributes) {
        //AirplaneDTO toDelete = airplaneFacade.getAirplaneById(id);
        airplaneFacade.deleteAirplane(id);
        return "redirect:/airplane/list";
    }
    
    @RequestMapping(value = "/update/{updateid}", method = RequestMethod.POST)
    public String updateAirplane(@PathVariable long updateid, @ModelAttribute("toUpdate") AirplaneDTO toUpdate, Model model) {
        System.out.print("updateid>>>>........................................>>>>>>  ");
                System.out.println(updateid);        
        System.out.print("toUpdate>>>>>........................................>>>>>  ");
                System.out.println(toUpdate.toString());
        toUpdate.setId(updateid);
        airplaneFacade.updateAirplane(toUpdate);
        return "redirect:/airplane/list";
    }
    
   
    
    
}
