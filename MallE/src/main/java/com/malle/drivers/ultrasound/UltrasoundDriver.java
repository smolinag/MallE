/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.malle.drivers.ultrasound;

import com.pi4j.io.gpio.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author smoli
 */
public class UltrasoundDriver {

    private final Integer NUM_SAMPLES_FOR_SINGLE_MEASUREMENT = 5;

    private final String sensorName;
    private final Pin triggerPin;
    private final Pin echoPin;
    private final GpioPinDigitalOutput triggerOut;
    private final GpioPinDigitalInput echoIn;

    private boolean isSensing = false;
    private Double distance;

    public UltrasoundDriver(String sensorName, Pin trigger, Pin echo) {
        GpioController gpio = GpioFactory.getInstance();

        this.sensorName = sensorName;
        triggerPin = trigger;
        echoPin = echo;

        triggerOut = gpio.provisionDigitalOutputPin(trigger);
        echoIn = gpio.provisionDigitalInputPin(echo);
    }

    public synchronized Double getSingleDistanceMeasurement() {
        if (!isSensing) {
            List<Double> measurements = new ArrayList<>();
            Thread ultrasoundThread = new Thread("ultrasound-" + sensorName) {
                @Override
                public void run() {
                    try {
                        //Discard first sample
                        for (int i = 0; i < NUM_SAMPLES_FOR_SINGLE_MEASUREMENT; i++) {
                            triggerOut.high();
                            Thread.sleep((long) 0.01);// Delay for 10 microseconds
                            triggerOut.low();

                            //Wait until the ECHO pin gets HIGH
                            while (echoIn.isLow()) {
                            }
                            // Store the surrent time to calculate ECHO pin HIGH time.
                            long startTime = System.nanoTime(); 

                            //Wait until the ECHO pin gets LOW
                            while (echoIn.isHigh()) { 
                            }
                            // Store the echo pin HIGH end time to calculate ECHO pin HIGH time.
                            long endTime = System.nanoTime(); 

                            distance = (((endTime - startTime) / 1e3) / 2) / 29.1;
                            measurements.add(distance);
                            Thread.sleep(20);
                        }                        
                        distance = getMedianValue(measurements);
                        isSensing = false;
                    } catch (InterruptedException ex) {
                        Logger.getLogger(UltrasoundDriver.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            ;
            };
            ultrasoundThread.start();
            isSensing = true;
        }
        
        while (isSensing) {
        }
        return distance;
    }
    
    private Double getMedianValue(List<Double> measurements){
        Collections.sort(measurements,(m1,m2) -> m1.compareTo(m2));
        return measurements.get((int)Math.floor(NUM_SAMPLES_FOR_SINGLE_MEASUREMENT / 2.));
    }
}
