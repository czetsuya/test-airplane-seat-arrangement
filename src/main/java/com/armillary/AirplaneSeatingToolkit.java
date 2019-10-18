package com.armillary;

import java.util.Arrays;

import com.armillary.domain.Airplane;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Edward P. Legaspi | czetsuya@gmail.com
 */
@Slf4j
public class AirplaneSeatingToolkit {

    private Integer seatCtr = 1;

    public AirplaneSeatingToolkit(Integer[][] seatSpecification, int noOfCustomers) {

        Airplane airplane = new Airplane();
        airplane.setSeatSpecification(seatSpecification);

        int row = findMaxRow(seatSpecification);
        int col = sumColLength(seatSpecification);

        airplane.initSeatArrangement(row, col);

        log.debug("row x col: {} x {}", row, col);

        for (int r = 0; r < row; r++) {
            for (int seatGroupIdx = 0; seatGroupIdx < seatSpecification.length; seatGroupIdx++) {
                if (seatGroupIdx != 0) {
                    populateAisle(r, airplane, seatGroupIdx, Airplane.NOT_FIRST);
                } else if (seatGroupIdx != seatSpecification.length - 1) {
                    populateAisle(r, airplane, seatGroupIdx, Airplane.NOT_LAST);
                }
//                populateWindow();
//                populateMiddle();
            }
        }

        print2D(airplane.getSeatArrangement());
    }

    private void populateAisle(int row, Airplane airplane, int seatGroupIdx, String position) {

        Integer[] seatGroupSpecification = airplane.getSeatSpecification(seatGroupIdx);
        for (int i = 0; i < seatGroupSpecification[0]; i++) {
            if (position.equals(Airplane.NOT_FIRST)) {
                airplane.setSeat(row, airplane.getSeatSpecificationArrangementFirstIndex(seatGroupIdx), seatCtr++);

            } else if (position.equals(Airplane.NOT_LAST)) {
                airplane.setSeat(row, airplane.getSeatSpecificationArrangementLastIndex(seatGroupIdx), seatCtr++);
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
