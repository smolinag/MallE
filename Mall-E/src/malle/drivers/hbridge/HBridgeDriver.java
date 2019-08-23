/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package malle.drivers.hbridge;

import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.Gpio;

/**
 *
 * @author Santiago Noatec
 */
public class HBridgeDriver {
    
    private final int PWM_CLCK = 20;
    private final int PWM_RANGE = 100;
    
    //MotorA
    private final GpioPinDigitalOutput motorAPin1;
    private final GpioPinDigitalOutput motorAPin2;
    private final GpioPinPwmOutput motorAEnable;
    
    private final GpioPinDigitalOutput motorBPin1;
    private final GpioPinDigitalOutput motorBPin2;
    private final GpioPinPwmOutput motorBEnable;
    
    public HBridgeDriver(Pin motorAPin1, Pin motorAPin2, Pin motorAEnable, Pin motorBPin1, Pin motorBPin2, Pin motorBEnable){
        
        //Configure MotorA
        GpioController gpio = GpioFactory.getInstance();        
        this.motorAEnable = gpio.provisionPwmOutputPin(motorAEnable);
        Gpio.pwmSetMode(Gpio.PWM_MODE_MS);
        Gpio.pwmSetClock(PWM_CLCK);
        Gpio.pwmSetRange(PWM_RANGE);
    }
    
    
}
