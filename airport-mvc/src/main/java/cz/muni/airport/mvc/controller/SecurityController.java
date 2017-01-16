/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.airport.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author karbo
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
