package cz.muni.airport.mvc.controller;

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
@RequestMapping("/airport")
@Transactional
public class AirportController {


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String airports(Model model) {
        return "airports";
    }
}
