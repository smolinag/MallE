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

        Integer ans = 1;
        switch (direction) {
            case "F": //forward
                DriversMain.getHBridgeDriver().move(speed, speed, true, true);
                break;
            case "B": //backward
                DriversMain.getHBridgeDriver().move(speed, speed, false, false);
                break;
            case "L": //left
                DriversMain.getHBridgeDriver().move(speed, Speed.STOP, true, false);
                break;
            case "R": //right
                DriversMain.getHBridgeDriver().move(Speed.STOP, speed, false, true);
                break;
            case "FL": //forward-left
                DriversMain.getHBridgeDriver().move(speed, Speed.SLOW, true, true);
                break;
            case "FR": //forward-right
                DriversMain.getHBridgeDriver().move(Speed.SLOW, speed, true, true);
                break;
            case "BL": //backward-left
                DriversMain.getHBridgeDriver().move(speed, Speed.SLOW, false, false);
                break;
            case "BR": //backward-right
                DriversMain.getHBridgeDriver().move(Speed.SLOW, speed, false, false);
                break;
            case "S": //stop
                DriversMain.getHBridgeDriver().stopThrotle();
                break;
            default:
                ans = 0;
                break;
        }
        return ResponseEntity.status(HttpStatus.OK).body(ans);
    }
}
