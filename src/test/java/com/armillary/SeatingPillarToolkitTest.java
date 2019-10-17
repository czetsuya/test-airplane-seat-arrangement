package com.armillary;

import org.junit.jupiter.api.Test;

/**
 * @author Edward P. Legaspi | czetsuya@gmail.com
 */
public class SeatingPillarToolkitTest {

    private static final int[][] INPUT = { { 2, 3 }, { 3, 4 }, { 3, 2 }, { 4, 3 } };
    private static final int NO_OF_CUSTOMERS = 30;

    @Test
    public void populateAisle() {
        AirplaneSeatingToolkit toolkit = new AirplaneSeatingToolkit(INPUT, NO_OF_CUSTOMERS);
        
    }

}
