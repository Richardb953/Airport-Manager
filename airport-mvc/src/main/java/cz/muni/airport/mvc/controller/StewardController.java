package cz.muni.airport.mvc.controller;

import cz.muni.airport.facadeApi.StewardFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
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
        return "stewards";
    }
}
