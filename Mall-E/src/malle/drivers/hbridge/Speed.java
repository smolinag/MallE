/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package malle.drivers.hbridge;


/**
 *
 * @author Santiago Noatec
 */
public enum Speed {
    STOP(0),
    SLOW(30),
    MEDIUM(50),
    FAST(70),
    MAX(85);
    
    private int pwmVal;
    
    Speed(int pwmVal){
        this.pwmVal = pwmVal;
    }
    
    public int getPwmVal(){
        return pwmVal;
    }
}
