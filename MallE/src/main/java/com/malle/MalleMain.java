/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.malle;

import com.malle.drivers.DriversMain;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author Santiago Noatec
 */
@SpringBootApplication
public class MalleMain {
    
    public static void main(String[] args) {
        
        //Start drivers
        DriversMain.initializeDrivers();
        
        SpringApplication.run(MalleMain.class, args);
    }
}
