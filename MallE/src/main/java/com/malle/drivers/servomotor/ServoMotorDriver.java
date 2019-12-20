/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.malle.drivers.servomotor;

import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Santiago Noatec
 */
public class ServoMotorDriver {

    private final int MIN_ANGLE_LIMIT = -90;
    private final int MAX_ANGLE_LIMIT = 90;
    private final int PWM_CLCK = 192;
    private final int PWM_RANGE = 2000;

    private final Integer minAngle;
    private final Integer maxAngle;
    private Integer currentAngle;

    private Pin pwmPin;
    private final GpioPinPwmOutput pwmPinOut;

    private boolean isAngleIncreasing = false;
    private boolean angleIncreasingShouldStop = false;    
    private Integer angleIncreaseTimeInterval = 50; //ms

    public ServoMotorDriver(Pin pwmPin, Integer minAngle, Integer maxAngle, Integer startAngle) {
        this.pwmPin = pwmPin;

        //Configure PWM
        GpioController gpio = GpioFactory.getInstance();
        pwmPinOut = gpio.provisionPwmOutputPin(pwmPin);
        Gpio.pwmSetMode(Gpio.PWM_MODE_MS);
        Gpio.pwmSetClock(PWM_CLCK);
        Gpio.pwmSetRange(PWM_RANGE);

        //Set angle limits
        this.minAngle = minAngle != null && minAngle >= MIN_ANGLE_LIMIT && minAngle < MAX_ANGLE_LIMIT ? minAngle : MIN_ANGLE_LIMIT;
        this.maxAngle = maxAngle != null && maxAngle <= MAX_ANGLE_LIMIT && maxAngle > this.minAngle ? maxAngle : MAX_ANGLE_LIMIT;

        //Set initial position
        setAngle(startAngle);
    }

    public void startAngleIncreasing(int angleStep) {
        if (!isAngleIncreasing) {
            Thread servoIncreasingThread = new Thread() {
                @Override
                public void run() {
                    while (!angleIncreasingShouldStop) {
                        pwmPinOut.setPwm(angleToDutyCycle(currentAngle + angleStep));
                        try {
                            Thread.sleep(angleIncreaseTimeInterval);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(ServoMotorDriver.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            };
            servoIncreasingThread.start();
            isAngleIncreasing = true;
        }
    }
    
    public void stopAngleIncreasing(){
        if(isAngleIncreasing){
            angleIncreasingShouldStop = true;
            isAngleIncreasing = false;
        }
    }

    public void setAngle(int angleToSet) {
        pwmPinOut.setPwm(angleToDutyCycle(angleToSet));
    }

    private Integer angleToDutyCycle(int angle) {
        //Limit angle
        if (angle < minAngle) {
            angle = minAngle;
        }
        if (angle > maxAngle) {
            angle = maxAngle;
        }
        currentAngle = angle;

        //Get dutycycle
        double dutyCycleD = (50. / 90.) * angle + 150;
        return (int) Math.round(dutyCycleD);
    }
}
