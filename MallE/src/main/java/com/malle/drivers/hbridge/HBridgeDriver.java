/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.malle.drivers.hbridge;

import com.pi4j.io.gpio.*;

/**
 *
 * @author Santiago Noatec
 */
public class HBridgeDriver {
    
    //MotorA
    private final GpioPinDigitalOutput motorAPin1;
    private final GpioPinDigitalOutput motorAPin2;

    //MotorB
    private final GpioPinDigitalOutput motorBPin1;
    private final GpioPinDigitalOutput motorBPin2;

    public HBridgeDriver(Pin motorAPin1, Pin motorAPin2, Pin motorBPin1, Pin motorBPin2) {

        GpioController gpio = GpioFactory.getInstance();
        
        //Configure MotorA        
        this.motorAPin1 = gpio.provisionDigitalOutputPin(motorAPin1);
        this.motorAPin2 = gpio.provisionDigitalOutputPin(motorAPin2);

        //Configure MotorB 
        this.motorBPin1 = gpio.provisionDigitalOutputPin(motorBPin1);
        this.motorBPin2 = gpio.provisionDigitalOutputPin(motorBPin2);
        
        //Stop motors
        move(0, 0);
    }

    public void move(Integer aStatus, Integer bStatus) {
        
        switch (aStatus) {
            case -1:
                motorAPin1.low();
                motorAPin2.high();
                break;
            case 0:
                motorAPin1.low();
                motorAPin2.low();
                break;
            case 1:
                motorAPin1.high();
                motorAPin2.low();
                break;
        }
        
        switch (bStatus) {
            case -1:
                motorBPin1.low();
                motorBPin2.high();
                break;
            case 0:
                motorBPin1.low();
                motorBPin2.low();
                break;
            case 1:
                motorBPin1.high();
                motorBPin2.low();
                break;
        }
    }
}
