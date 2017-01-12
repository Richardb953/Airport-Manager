package cz.muni.airport.mvc.controller;

import cz.muni.airport.facadeApi.AirplaneFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        return "airplane/list";
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String airplanes(Model model) {
        return "airplanes";
    }
}
