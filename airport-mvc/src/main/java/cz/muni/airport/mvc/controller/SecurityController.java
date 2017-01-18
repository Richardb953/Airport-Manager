package cz.muni.airport.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Karolína Božková, github name: Kayeeec
 */
@Controller
@RequestMapping("/app")
public class SecurityController {
    
    @RequestMapping(value="/login")
    public String homePage() {
            return "loginform";
    }
    
    @RequestMapping(value="/accessDenied")
    public String accessDenied() {
            return "accessDeniedPage";
    }
    
}
