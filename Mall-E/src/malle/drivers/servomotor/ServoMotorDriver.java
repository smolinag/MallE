/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package malle.drivers.servomotor;

import com.pi4j.io.gpio.*;

/**
 *
 * @author Santiago Noatec
 */
public class ServoMotorDriver {
    
    private final int MIN_ANGLE_LIMIT = -90;
    private final int MAX_ANGLE_LIMIT = 90;
    private final int START_ANGLE = 0;
    private final int PWM_CLCK = 1920;
    private final int PWM_RANGE = 200;
    
    private Integer minAngle;
    private Integer maxAngle;
    
    private RaspiPin pwmPin;
    
    public ServoMotorDriver(RaspiPin pwmPin, Integer minAngle, Integer maxAngle){
        this.pwmPin = pwmPin;
        
        //Set angle limits
        this.minAngle = minAngle != null && minAngle >= MIN_ANGLE_LIMIT && minAngle < MAX_ANGLE_LIMIT ? minAngle : MIN_ANGLE_LIMIT;
        this.maxAngle = maxAngle != null && maxAngle <= MAX_ANGLE_LIMIT && maxAngle > this.minAngle ? maxAngle : MAX_ANGLE_LIMIT;
        
        //Set initial position
        
    }
    
    public void increaseAngle(int angleStep){
        
    }
    
    
}
