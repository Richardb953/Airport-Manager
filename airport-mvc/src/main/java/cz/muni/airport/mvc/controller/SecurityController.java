/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.airport.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
