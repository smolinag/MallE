/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package malle.drivers.hbridge;

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
    
    public HBridgeDriver(Pin motorAPin1, Pin motorAPin2, Pin motorAEnable, Pin motorBPin1, Pin motorBPin2, Pin motorBEnable){
        
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

    public void stopThrotle(){
        SoftPwm.softPwmStop(motorAEnable);
        SoftPwm.softPwmStop(motorBEnable);
    }
    
    public void moveForward(Speed speed){
        SoftPwm.softPwmWrite(motorAEnable, speed.getPwmVal());
        motorAPin1.high();
        motorAPin2.low();
        SoftPwm.softPwmWrite(motorBEnable, speed.getPwmVal());
        motorBPin1.high();
        motorBPin2.low();
    }
    
    public void moveBackward(Speed speed){
        SoftPwm.softPwmWrite(motorAEnable, speed.getPwmVal());
        motorAPin1.low();
        motorAPin2.high();
        SoftPwm.softPwmWrite(motorBEnable, speed.getPwmVal());
        motorBPin1.low();
        motorBPin2.high();
    }
    
    public void turnLeft(Speed speedA, Speed speedB){
        SoftPwm.softPwmWrite(motorAEnable, speedA.getPwmVal());
        motorAPin1.high();
        motorAPin2.low();
        SoftPwm.softPwmWrite(motorBEnable, speedB.getPwmVal());
        motorBPin1.high();
        motorBPin2.low();
    }
}
