package com.armillary;

import java.util.Arrays;

import com.armillary.domain.AirplaneSeating;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Edward P. Legaspi | czetsuya@gmail.com
 */
@Slf4j
public class AirplaneSeatingToolkit {

    private Integer seatCtr = 1;

    public AirplaneSeatingToolkit(Integer[][] seatSpecification, int noOfCustomers) {

        AirplaneSeating airplaneSeating = new AirplaneSeating();
        airplaneSeating.setSpecification(seatSpecification);

        int row = findMaxRow(seatSpecification);
        int col = sumColLength(seatSpecification);

        airplaneSeating.initArrangement(row, col);

        log.debug("row x col: {} x {}", row, col);

        for (int r = 0; r < row; r++) {
            for (int specificationIdx = 0; specificationIdx < seatSpecification.length; specificationIdx++) {
                if (specificationIdx != 0) {
                    populateAisle(r, airplaneSeating, specificationIdx, AirplaneSeating.NOT_FIRST);

                } else if (specificationIdx != seatSpecification.length - 1) {
                    populateAisle(r, airplaneSeating, specificationIdx, AirplaneSeating.NOT_LAST);
                }
//                populateWindow();
//                populateMiddle();
            }
        }

        print2D(airplaneSeating.getArrangement());
    }

    private void populateAisle(int row, AirplaneSeating airplane, int seatGroupIdx, String position) {

        Integer[] seatGroupSpecification = airplane.getSeatSpecification(seatGroupIdx);
        for (int i = 0; i < seatGroupSpecification[0]; i++) {
            if (position.equals(AirplaneSeating.NOT_FIRST)) {
                airplane.setSeatValue(row, airplane.getSeatSpecificationArrangementFirstIndex(seatGroupIdx), seatCtr++);

            } else if (position.equals(AirplaneSeating.NOT_LAST)) {
                airplane.setSeatValue(row, airplane.getSeatSpecificationArrangementLastIndex(seatGroupIdx), seatCtr++);
            }
        }
    }

    public static int findMaxRow(Integer[][] seatSpecification) {

        return Arrays.stream(seatSpecification).mapToInt(e -> e[1]).max().getAsInt();
    }

    public static int sumColLength(Integer[][] seatSpecification) {

        return Arrays.stream(seatSpecification).mapToInt(e -> e[0]).sum();
    }

    public static void print2D(Integer[][] seatArrangement) {

        for (Integer[] row : seatArrangement) {
            log.debug(Arrays.toString(row));
        }
    }
}
