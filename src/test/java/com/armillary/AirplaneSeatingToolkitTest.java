package com.armillary;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

/**
 * The output values have been manually computed by hand.
 * 
 * @author Edward P. Legaspi | czetsuya@gmail.com
 */
public class AirplaneSeatingToolkitTest {

    private static final Integer[][] INPUT1 = { { 2, 3 }, { 3, 4 }, { 3, 2 }, { 4, 3 } };
    private static final Integer[][] INPUT2 = { { 3, 4 }, { 4, 5 }, { 2, 3 }, { 3, 4 } };
    private static final Integer[][] INPUT1_OUTPUT = { { 19, 25, 1, 2, 26, 27, 3, 4, 5, 6, 28, 20 }, { 21, 29, 7, 8, 30, null, 9, 10, 11, 12, null, 22 },
            { null, null, null, 13, null, null, 14, 15, 16, 17, null, 23 }, { null, null, null, null, null, null, null, null, null, 18, null, 24 } };
    private static final Integer[][] INPUT2_OUTPUT = { { 19, 25, 26, 1, 2, 27, 28, 29, 3, 4, 30, 5, 6, 31, 32, 20 },
            { 21, 33, 34, 7, 8, 35, 36, 37, 9, 10, 38, 11, 12, 39, 40, 22 }, { 23, 41, 42, 13, 14, 43, 44, 45, 15, null, null, null, 16, 46, 47, 24 },
            { null, null, null, null, 17, 48, 49, 50, 18, null, null, null, null, null, null, null },
            { null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null } };

    private static final int NO_OF_CUSTOMERS = 30;
    private static final int NO_OF_CUSTOMERS2 = 100;

    @Test
    public void specification_whenFindMaxRow_ok() {

        assertThat(AirplaneSeatingToolkit.findMaxRow(INPUT1), equalTo(4));
    }

    @Test
    public void specification_whenSumColLength_ok() {

        assertThat(AirplaneSeatingToolkit.sumColLength(INPUT1), equalTo(12));
    }

    @Test
    public void seat_whenPopulateAisle_ok() {

        AirplaneSeatingToolkit tk = new AirplaneSeatingToolkit(INPUT1, NO_OF_CUSTOMERS);
        assertThat(tk.arrangeSeats(), equalTo(INPUT1_OUTPUT));
    }

    @Test
    public void seat_whenPopulateAisle2_ok() {

        AirplaneSeatingToolkit tk = new AirplaneSeatingToolkit(INPUT2, NO_OF_CUSTOMERS2);
        assertThat(tk.arrangeSeats(), equalTo(INPUT2_OUTPUT));
    }
}
