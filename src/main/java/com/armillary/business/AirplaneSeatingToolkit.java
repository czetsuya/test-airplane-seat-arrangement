package com.armillary.business;

import java.util.Arrays;

import com.armillary.business.domain.AirplaneSeating;

import lombok.extern.slf4j.Slf4j;

/**
 * Toolkit for managing the arrangement of seats in an airplane.
 * 
 * @author Edward P. Legaspi | czetsuya@gmail.com
 */
@Slf4j
public class AirplaneSeatingToolkit {

    /**
     * Current no of customers being seated.
     */
    private Integer seatCtr = 1;

    /**
     * No of passengers waiting in queue.
     */
    private int noOfPassengers;

    /**
     * A 2D array that represents the rows and columns.
     */
    private Integer[][] seatSpecification;

    /**
     * Initialize this toolkit.
     * <p>
     * a 2D array that represents the rows and columns e.g: [[3,4], [4,5], [2,3],
     * [3,4]] The number of passengers waiting in the queue.
     * </p>
     * 
     * @param seatSpecification a 2D array that represents the rows and columns
     * @param noOfPassengers    no of passengers waiting in queue
     */
    public AirplaneSeatingToolkit(Integer[][] seatSpecification, int noOfPassengers) {

        this.seatSpecification = seatSpecification;
        this.noOfPassengers = noOfPassengers;
    }

    /**
     * Arrange the seats with the following rule.
     * <p>
     * Always seat passengers starting from the front row to back, starting from the
     * left to the right Fill aisle seats first followed by window seats followed by
     * center seats (any order in center seats)
     * </p>
     * 
     * @return
     */
    public Integer[][] arrangeSeats() {

        AirplaneSeating airplaneSeating = new AirplaneSeating();
        airplaneSeating.setSpecification(seatSpecification);

        int row = findMaxRow(seatSpecification);
        int col = sumColLength(seatSpecification);

        airplaneSeating.initArrangement(row, col);

        log.debug("row x col: {} x {}", row, col);

        for (int r = 0; r < row; r++) {
            for (int specificationIdx = 0; specificationIdx < seatSpecification.length && isValidCounter(); specificationIdx++) {
                int firstIndex = airplaneSeating.getSpecificationArrangementFirstIndex(specificationIdx);
                int lastIndex = airplaneSeating.getSpecificationArrangementLastIndex(specificationIdx);

                populateAisle(r, airplaneSeating, specificationIdx, firstIndex, lastIndex);
            }
        }

        for (int r = 0; r < row && isValidCounter(); r++) {
            populateWindow(r, airplaneSeating, true);
            populateWindow(r, airplaneSeating, false);
        }

        for (int r = 0; r < row; r++) {
            for (int specificationIdx = 0; specificationIdx < seatSpecification.length && isValidCounter(); specificationIdx++) {
                populateMiddle(r, airplaneSeating, specificationIdx);
            }
        }

        print2D(airplaneSeating.getArrangement());
        return airplaneSeating.getArrangement();
    }

    /**
     * Checks if the seat counter is still valid. Counter stops when we reach the
     * total no of passengers.
     * 
     * @return true if valid and we can still allot a seat for a passenger
     */
    public boolean isValidCounter() {
        return seatCtr <= noOfPassengers;
    }

    /**
     * Populate the isle
     * 
     * @param row              current row of arrangement array being process
     * @param airplaneSeating  holds the specification plus the arrangement array
     * @param specificationIdx the current specification index
     * @param firstIndex       the actual first index of the specification in the
     *                         arrangement array
     * @param lastIndex        the actual last index of the specification in the
     *                         arrangement array
     */
    private void populateAisle(int row, AirplaneSeating airplaneSeating, int specificationIdx, int firstIndex, int lastIndex) {

        if (!airplaneSeating.isValidSeat(specificationIdx, row)) {
            return;
        }

        if (specificationIdx == 0) {
            airplaneSeating.setValue(row, lastIndex, seatCtr++);

        } else if (specificationIdx == airplaneSeating.getSpecification().length - 1) {
            airplaneSeating.setValue(row, firstIndex, seatCtr++);

        } else {
            if (airplaneSeating.getSpecification()[specificationIdx][AirplaneSeating.COL_INDEX] == 2) {
                for (int i = firstIndex; i <= lastIndex; i++) {
                    airplaneSeating.setValue(row, i, seatCtr++);
                }

            } else {
                // greater than 2
                airplaneSeating.setValue(row, firstIndex, seatCtr++);
                airplaneSeating.setValue(row, lastIndex, seatCtr++);
            }
        }
    }

    /**
     * 
     * @param row             the current row of the arrangement array being process
     * @param airplaneSeating holds the specification plus the arrangement array
     * @param isLeft          are we processing the left window seats of an
     *                        airplane?
     */
    private void populateWindow(int row, AirplaneSeating airplaneSeating, boolean isLeft) {

        if (isLeft) {
            if (!airplaneSeating.isValidSeat(0, row)) {
                return;
            }

            airplaneSeating.setValue(row, 0, seatCtr++); // left window

        } else {
            if (!airplaneSeating.isValidSeat(airplaneSeating.getSpecification().length - 1, row)) {
                return;
            }

            int lastHorizontalSeatIndex = airplaneSeating.getArrangement()[AirplaneSeating.COL_INDEX].length - 1;
            airplaneSeating.setValue(row, lastHorizontalSeatIndex, seatCtr++); // right window
        }
    }

    /**
     * Populates the middle cells of a seat pillar in the given specification id.
     * 
     * @param row              the current row of the arrangement array being
     *                         process
     * @param airplaneSeating  holds the specification plus the arrangement array
     * @param specificationIdx current specification index
     */
    private void populateMiddle(int row, AirplaneSeating airplaneSeating, int specificationIdx) {

        if (!airplaneSeating.isValidSeat(specificationIdx, row)) {
            return;
        }

        if (airplaneSeating.getSpecification(specificationIdx)[AirplaneSeating.COL_INDEX] > 2) {
            for (int i = airplaneSeating.getSpecificationArrangementFirstIndex(specificationIdx) + 1; i < airplaneSeating.getSpecificationArrangementLastIndex(specificationIdx)
                    && isValidCounter(); i++) {
                airplaneSeating.setValue(row, i, seatCtr++);
            }
        }
    }

    /**
     * Finds the maximum row on all seat specifications.
     * 
     * @param seatSpecification 2d array of seat specifications
     * @return the max row
     */
    public static int findMaxRow(Integer[][] seatSpecification) {

        return Arrays.stream(seatSpecification).mapToInt(e -> e[1]).max().getAsInt();
    }

    /**
     * Computes the sum of all the columns.
     * 
     * @param seatSpecification specification of seats or groupings
     * @return sum of all columns
     */
    public static int sumColLength(Integer[][] seatSpecification) {

        return Arrays.stream(seatSpecification).mapToInt(e -> e[1]).sum();
    }

    /**
     * Prints the arrangement array.
     * 
     * @param seatArrangement the arranged seat array
     */
    public static void print2D(Integer[][] seatArrangement) {

        for (Integer[] row : seatArrangement) {
            log.debug(Arrays.toString(row));
        }
    }
}
