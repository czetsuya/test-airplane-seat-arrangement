package com.armillary.business.service;

import javax.validation.Valid;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.armillary.AirplaneSeatingToolkit;
import com.armillary.business.domain.SeatingRequestDto;

/**
 * @author Edward P. Legaspi | czetsuya@gmail.com
 */
@Service
public class AirplaneSeatingService {

    /**
     * Calculates a seat arrangement for the passenger with the given parameter.
     * 
     * @param postData a 2d integer array that contains the seat specification
     * @return a computed arrangement of passenger seat
     */
    @Cacheable("seat-arrangement")
    public Integer[][] calculateSeats(@Valid SeatingRequestDto postData) {

        AirplaneSeatingToolkit airplaneSeatingToolkit = new AirplaneSeatingToolkit(postData.getSpecification(), postData.getNoOfCustomers());
        return airplaneSeatingToolkit.arrangeSeats();
    }

}
