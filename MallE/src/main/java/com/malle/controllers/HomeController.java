/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.malle.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Santiago Noatec
 */
@Controller
public class HomeController {

    @RequestMapping(value= "/home", method = RequestMethod.GET)
    public String getHolaMundo() {
        return "home";
    }
}
