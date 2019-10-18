package com.armillary.business.domain;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author Edward P. Legaspi | czetsuya@gmail.com
 */
@Data
public class SeatingRequestDto {

    @NotNull
    private Integer[][] specification;

    @NotNull
    private int noOfCustomers;
}
