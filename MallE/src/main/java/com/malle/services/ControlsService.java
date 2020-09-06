/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.malle.services;

import com.malle.drivers.DriversMain;
import com.malle.drivers.hbridge.Speed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author Santiago Noatec
 */
@Service
public class ControlsService {

    public ResponseEntity<Integer> moveHeadServo(String direction) {
        Integer ans = 1;
        switch (direction) {
            case "U":
                DriversMain.getServoUpDown().startAngleIncreasing(2);
                DriversMain.getServoRightLeft().stopAngleIncreasing();
                break;
            case "D":
                DriversMain.getServoUpDown().startAngleIncreasing(-2);
                DriversMain.getServoRightLeft().stopAngleIncreasing();
                break;
            case "L":
                DriversMain.getServoRightLeft().startAngleIncreasing(2);
                DriversMain.getServoUpDown().stopAngleIncreasing();
                break;
            case "R":
                DriversMain.getServoRightLeft().startAngleIncreasing(-2);
                DriversMain.getServoUpDown().stopAngleIncreasing();
                break;
            case "UR":
                DriversMain.getServoUpDown().startAngleIncreasing(2);
                DriversMain.getServoRightLeft().startAngleIncreasing(-2);
                break;
            case "UL":
                DriversMain.getServoUpDown().startAngleIncreasing(2);
                DriversMain.getServoRightLeft().startAngleIncreasing(2);
                break;
            case "DR":
                DriversMain.getServoUpDown().startAngleIncreasing(-2);
                DriversMain.getServoRightLeft().startAngleIncreasing(-2);
                break;
            case "DL":
                DriversMain.getServoUpDown().startAngleIncreasing(-2);
                DriversMain.getServoRightLeft().startAngleIncreasing(2);
                break;
            case "S":
                DriversMain.getServoUpDown().stopAngleIncreasing();
                DriversMain.getServoRightLeft().stopAngleIncreasing();
            default:
                ans = 0;
                break;
        }
        return ResponseEntity.status(HttpStatus.OK).body(ans);
    }

    public ResponseEntity<Integer> move(String direction, String speedName) {

        //Get speed (PWM width)
        Speed speed = Speed.getSpeedLevelByName(speedName);
        
        System.out.println("Direction: " + direction + " Speed: " + speedName);

        Integer ans = 1;
        switch (direction) {
            case "F": //forward
                DriversMain.getHBridgeDriver().move(1,1);
                break;
            case "B": //backward
                DriversMain.getHBridgeDriver().move(-1,-1);
                break;
            case "L": //left
                DriversMain.getHBridgeDriver().move(1,0);
                break;
            case "R": //right
                DriversMain.getHBridgeDriver().move(0,1);
                break;           
            case "S": //stop
                DriversMain.getHBridgeDriver().move(0,0);
                break;
            default:
                ans = 0;
                break;
        }
        return ResponseEntity.status(HttpStatus.OK).body(ans);
    }
}
