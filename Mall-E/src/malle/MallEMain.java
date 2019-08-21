/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package malle;

import com.pi4j.io.gpio.RaspiPin;
import java.util.Random;
import malle.drivers.servomotor.ServoMotorDriver;
import malle.drivers.ultrasound.UltrasoundDriver;

/**
 *
 * @author Santiago Noatec
 */
public class MallEMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {

        ServoMotorDriver servoUpDown = new ServoMotorDriver(RaspiPin.GPIO_26, -90, 90, 0);
        ServoMotorDriver servoRightLeft = new ServoMotorDriver(RaspiPin.GPIO_23, -90, 90, 0);

        UltrasoundDriver headUltrasound = new UltrasoundDriver("head", RaspiPin.GPIO_04, RaspiPin.GPIO_05);
        
        while (true) {
            Thread.sleep(2000);
            System.out.println("DISTANCE: " + headUltrasound.getSingleDistanceMeasurement() + "cm");
            Thread.sleep(1000);
        }

//        while(true){
//            //int randNum = new Random().nextInt(200) - 100;
//            servoUpDown.setAngle(0);
//            System.out.println("0°");
//            Thread.sleep(5000);
//            servoUpDown.setAngle(45);
//            System.out.println("45°");
//            Thread.sleep(5000);
//            servoUpDown.setAngle(90);
//            System.out.println("90°");
//            Thread.sleep(5000);
//             servoUpDown.setAngle(-45);
//            System.out.println("-45°");
//            Thread.sleep(5000);
//            servoUpDown.setAngle(-90);
//            System.out.println("-90°");
//            Thread.sleep(5000);
//        }
    }

}
