/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.malle.drivers.hbridge;

import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.SoftPwm;

/**
 *
 * @author Santiago Noatec
 */
public class HBridgeDriver {

    private final int PWM_RANGE = 100;

    //MotorA
    private final GpioPinDigitalOutput motorAPin1;
    private final GpioPinDigitalOutput motorAPin2;
    private final int motorAEnable;

    //MotorB
    private final GpioPinDigitalOutput motorBPin1;
    private final GpioPinDigitalOutput motorBPin2;
    private final int motorBEnable;

    public HBridgeDriver(Pin motorAPin1, Pin motorAPin2, Pin motorAEnable, Pin motorBPin1, Pin motorBPin2, Pin motorBEnable) {

        //Configure MotorA
        GpioController gpio = GpioFactory.getInstance();
        this.motorAEnable = motorAPin1.getAddress();
        SoftPwm.softPwmCreate(this.motorAEnable, 0, PWM_RANGE);
        this.motorAPin1 = gpio.provisionDigitalOutputPin(motorAPin1);
        this.motorAPin2 = gpio.provisionDigitalOutputPin(motorAPin2);

        //Configure MotorB 
        this.motorBEnable = motorAPin1.getAddress();
        SoftPwm.softPwmCreate(this.motorBEnable, 0, PWM_RANGE);
        this.motorBPin1 = gpio.provisionDigitalOutputPin(motorBPin1);
        this.motorBPin2 = gpio.provisionDigitalOutputPin(motorBPin2);
    }

    public void stopThrotle() {
        SoftPwm.softPwmStop(motorAEnable);
        SoftPwm.softPwmStop(motorBEnable);
    }

    public void move(Speed speedA, Speed speedB, boolean aForward, boolean bForward) {
        
        //Set motor A speed and turn direction
        SoftPwm.softPwmWrite(motorAEnable, speedA.getPwmVal());
        if (aForward) {
            motorAPin1.low();
            motorAPin2.high();
        } else {
            motorAPin1.high();
            motorAPin2.low();
        }

        //Set motor B speed and turn direction
        SoftPwm.softPwmWrite(motorBEnable, speedB.getPwmVal());
        if (aForward) {
            motorBPin1.low();
            motorBPin2.high();
        } else {
            motorBPin1.high();
            motorBPin2.low();
        }
    }
}
