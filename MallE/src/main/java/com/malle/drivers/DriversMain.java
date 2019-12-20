/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.malle.drivers;

import com.malle.drivers.hbridge.HBridgeDriver;
import com.malle.drivers.servomotor.ServoMotorDriver;
import com.malle.drivers.ultrasound.UltrasoundDriver;
import com.pi4j.io.gpio.RaspiPin;

/**
 *
 * @author Santiago Noatec
 */
public class DriversMain {

    private static ServoMotorDriver servoUpDown = null;
    private static ServoMotorDriver servoRightLeft = null;
    private static UltrasoundDriver headUltrasound = null;
    private static HBridgeDriver hBridgeDriver = null;
    
    public static void initializeDrivers() {
        //Servos
        servoUpDown = new ServoMotorDriver(RaspiPin.GPIO_26, -90, 90, 0);
        servoRightLeft = new ServoMotorDriver(RaspiPin.GPIO_23, -90, 90, 0);
        
        //Ultrasound
        headUltrasound = new UltrasoundDriver("head", RaspiPin.GPIO_04, RaspiPin.GPIO_05);
        
        //Hbridge
        hBridgeDriver = new HBridgeDriver(RaspiPin.GPIO_04, RaspiPin.GPIO_04, RaspiPin.GPIO_04, RaspiPin.GPIO_04, RaspiPin.GPIO_04, RaspiPin.GPIO_04);
    }

    public static ServoMotorDriver getServoUpDown() {
        return servoUpDown;
    }

    public static ServoMotorDriver getServoRightLeft() {
        return servoRightLeft;
    }

    public static UltrasoundDriver getHeadUltrasound() {
        return headUltrasound;
    }
    
    public static HBridgeDriver getHBridgeDriver() {
        return hBridgeDriver;
    }
}
