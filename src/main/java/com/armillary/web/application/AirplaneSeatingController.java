package com.armillary.web.application;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.armillary.business.domain.SeatingRequestDto;
import com.armillary.business.domain.SeatingResponseDto;
import com.armillary.business.service.AirplaneSeatingService;

/**
 * API to manage the seating arrangement of passengers of a plane.
 * 
 * @author Edward P. Legaspi | czetsuya@gmail.com
 */
@RestController
@RequestMapping(path = "/airplanes/seats", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class AirplaneSeatingController {

    private AirplaneSeatingService airplaneSeatingService;

    @Autowired
    public AirplaneSeatingController(AirplaneSeatingService airplaneSeatingService) {

        this.airplaneSeatingService = airplaneSeatingService;
    }

    /**
     * Calculates a seating arrangement base on the information given.
     * 
     * @param postData the seat specification for the passengers
     */
    @PostMapping(path = "/calculate")
    public ResponseEntity<SeatingResponseDto> calculateSeats(@Valid @RequestBody SeatingRequestDto postData) {

        SeatingResponseDto result = new SeatingResponseDto();
        result.setSeatArrangements(airplaneSeatingService.calculateSeats(postData));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
