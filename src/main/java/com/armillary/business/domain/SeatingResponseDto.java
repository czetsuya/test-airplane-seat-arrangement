package com.armillary.business.domain;

import lombok.Data;

/**
 * The response object that contains the passengers' seat arrangement on a
 * plane.
 * 
 * @author Edward P. Legaspi | czetsuya@gmail.com
 */
@Data
public class SeatingResponseDto {

    private Integer[][] seatArrangements;
}
