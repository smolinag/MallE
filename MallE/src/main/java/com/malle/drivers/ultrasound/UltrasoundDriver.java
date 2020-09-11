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
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author smoli
 */
public class UltrasoundDriver {

    private final String sensorName;
    private final Pin trigger;
    private final Pin echo;
    private GpioPinDigitalOutput triggerOut;
    private GpioPinDigitalInput echoIn;
    
    private int filterType;

    private boolean isStarted = false;

    private final int MEASUREMENT_WINDOW_SIZE = 5;
    private List<Double> measurements = new ArrayList<>();

    private Timer pollingTimer;
    private Timer measureTimer;

    private long wdTimestamp;

    public UltrasoundDriver(String sensorName, Pin trigger, Pin echo, int filterType) {

        this.sensorName = sensorName;
        this.trigger = trigger;
        this.echo = echo;
        
        this.filterType = filterType;

        provisionPins();
    }

    private void provisionPins() {
        GpioController gpio = GpioFactory.getInstance();
        triggerOut = gpio.provisionDigitalOutputPin(trigger);
        echoIn = gpio.provisionDigitalInputPin(echo);
    }

    private void unprovisionPins() {
        GpioController gpio = GpioFactory.getInstance();
        gpio.unprovisionPin(triggerOut);
        gpio.unprovisionPin(echoIn);
    }

    public void start() {
        startPolling();
        startMeasuring();
    }

    public void startPolling() {
        pollingTimer = new Timer("ultrasound-" + sensorName + "-polling");
        TimerTask pollingTimerTask = new TimerTask() {
            @Override
            public void run() {
                if ((System.currentTimeMillis() - wdTimestamp) > 200) {
                    stopMeasuring();
                    unprovisionPins();
                    provisionPins();
                    startMeasuring();
                }
            }
        };
        pollingTimer.schedule(pollingTimerTask, 20, 200);
    }

    public void startMeasuring() {
        if (!isStarted) {
            measureTimer = new Timer("ultrasound-" + sensorName + "-measurement");
            TimerTask measureTimerTask = new TimerTask() {
                @Override
                public void run() {
                    getSingleDistanceMeasurement();
                }
            };
            measureTimer.schedule(measureTimerTask, 0, 100);
            isStarted = true;
        }
    }

    public void stopMeasuring() {
        measureTimer.cancel();
        measureTimer.purge();
        isStarted = false;
    }

    public void getSingleDistanceMeasurement() {
        try {
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

            Double distance = (((endTime - startTime) / 1e3) / 2) / 29.1;

            addNewMeasurement(distance);
            wdTimestamp = System.currentTimeMillis();
        } catch (InterruptedException ex) {
            Logger.getLogger(UltrasoundDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private synchronized void addNewMeasurement(Double newMeasurement) {
        measurements.add(newMeasurement);
        if (measurements.size() > MEASUREMENT_WINDOW_SIZE) {
            measurements.remove(0);
        }
    }

    public Double getLatestMeasurement() {
        switch(filterType){
            default:
            case 0: //Latest measurement no filter
                return measurements.get(measurements.size() - 1);
            case 1: //Median filter
                return getMedianValue(measurements);
        }        
    }

    private Double getMedianValue(List<Double> measurements) {
        if (!measurements.isEmpty()) {
            Collections.sort(measurements, (m1, m2) -> m1.compareTo(m2));
            return measurements.get((int) Math.floor(measurements.size() / 2.));
        } else {
            return 0.;
        }
    }

    public String getName() {
        return sensorName;
    }
}
