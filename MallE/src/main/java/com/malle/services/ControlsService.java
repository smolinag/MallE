/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.malle.services;

import com.malle.drivers.DriversMain;
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
        System.out.println("MoveHeadServo " + direction);
        Integer ans = 1;
        switch (direction) {
            case "U":
                DriversMain.getVerticalServo().startAngleIncreasing(2);
                DriversMain.getHorizontalServo().stopAngleIncreasing();
                break;
            case "D":
                DriversMain.getVerticalServo().startAngleIncreasing(-2);
                DriversMain.getHorizontalServo().stopAngleIncreasing();
                break;
            case "L":
                DriversMain.getHorizontalServo().startAngleIncreasing(2);
                DriversMain.getVerticalServo().stopAngleIncreasing();
                break;
            case "R":
                DriversMain.getHorizontalServo().startAngleIncreasing(-2);
                DriversMain.getVerticalServo().stopAngleIncreasing();
                break;
            case "UR":
                DriversMain.getVerticalServo().startAngleIncreasing(2);
                DriversMain.getHorizontalServo().startAngleIncreasing(-2);
                break;
            case "UL":
                DriversMain.getVerticalServo().startAngleIncreasing(2);
                DriversMain.getHorizontalServo().startAngleIncreasing(2);
                break;
            case "DR":
                DriversMain.getVerticalServo().startAngleIncreasing(-2);
                DriversMain.getHorizontalServo().startAngleIncreasing(-2);
                break;
            case "DL":
                DriversMain.getVerticalServo().startAngleIncreasing(-2);
                DriversMain.getHorizontalServo().startAngleIncreasing(2);
                break;
            case "S":
                DriversMain.getVerticalServo().stopAngleIncreasing();
                DriversMain.getHorizontalServo().stopAngleIncreasing();
            default:
                ans = 0;
                break;
        }
        return ResponseEntity.status(HttpStatus.OK).body(ans);
    }

    public ResponseEntity<Integer> move(String direction) {
        
        System.out.println("Direction: " + direction);

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
