/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.malle.controllers;

import com.malle.services.ControlsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Santiago Noatec
 */
@RestController
@CrossOrigin
@RequestMapping("${datasource.app.uri}/controls")
public class ControlsController {
    
    @Autowired
    private ControlsService controlsService;
    
    @RequestMapping(value= "/movehead", params = {"direction"}, method = RequestMethod.GET)
    public ResponseEntity<Integer> moveHeadServo(@RequestParam("direction") String direction) {
        return controlsService.moveHeadServo(direction);
    }
    
    @RequestMapping(value= "/move", params = {"direction"}, method = RequestMethod.GET)
    public ResponseEntity<Integer> move(@RequestParam("direction") String direction) {
        return controlsService.move(direction);
    }
}
