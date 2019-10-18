package com.armillary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * The Spring boot runner class.
 * 
 * @author Edward P. Legaspi | czetsuya@gmail.com
 */
@SpringBootApplication
@EnableCaching
public class AirplaneSeatingPlacementApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirplaneSeatingPlacementApplication.class, args);
    }

}
