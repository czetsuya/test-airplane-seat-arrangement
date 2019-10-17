package com.armillary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents the group of seats in an airline.
 * 
 * @author Edward P. Legaspi | czetsuya@gmail.com
 */
@Getter
@AllArgsConstructor
public class SeatingPillar {

    public static final String NOT_FIRST = "NOT_FIRST";
    public static final String NOT_LAST = "NOT_LAST";

    private int[] data;
    private int counter;

    public void populateAisle(String position, int currentRow) {

        if (position.equals(NOT_FIRST)) {
            data[0] = counter++;

        } else {
            data[data.length] = counter++;

        }
    }

}
