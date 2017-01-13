package cz.muni.airport.mvc.controller;

import cz.muni.airport.dto.AirportDTO;
import cz.muni.airport.facadeApi.AirportFacade;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by Richard Bariny on 7.1.2017.
 *
 * @author Richard Bariny, github name:Richardb953
 */
@Controller
@RequestMapping("/airport")
@Transactional
public class AirportController {

    @Autowired
    private AirportFacade airportFacade;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String airports(Model model) {
        model.addAttribute("airports", airportFacade.getAllAirports());
        return "airports";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("airportCreate") AirportDTO formBean, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        AirportDTO airportDTO = airportFacade.createAirport(formBean);
        redirectAttributes.addFlashAttribute("alert_success", "Airport " + airportDTO.getName() + " was created.");
        return "redirect:" + uriBuilder.path("/airport/all").toUriString();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, RedirectAttributes redirectAttributes, Model model, UriComponentsBuilder uriBuilder) {
        airportFacade.removeAirport(id);
        model.addAttribute("airports", airportFacade.getAllAirports());
        redirectAttributes.addFlashAttribute("alert_warning", "Airport " + id + " was deleted");
        return "redirect:" + uriBuilder.path("/airport/all").buildAndExpand().encode().toUriString();
    }
}
