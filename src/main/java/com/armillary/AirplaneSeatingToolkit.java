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

    public AirplaneSeatingToolkit(int[][] seatSpecification, int noOfCustomers) {

        Airplane airplane = new Airplane();
        airplane.setSeatSpecification(seatSpecification);

        int row = findMaxRow(seatSpecification);
        int col = findMaxCol(seatSpecification);

        airplane.initSeatArrangement(row, col);

        log.debug("row x col: {} x {}", row, col);

        for (int r = 0; r < row; r++) {
            for (int seatGroupIdx = 0; seatGroupIdx < seatSpecification.length; seatGroupIdx++) {
                if (seatGroupIdx != 0) {
                    populateAisle(row, airplane, seatGroupIdx, Airplane.NOT_FIRST);
                }
                if (seatGroupIdx != seatSpecification.length - 1) {
                    populateAisle(row, airplane, seatGroupIdx, Airplane.NOT_LAST);
                }
//                populateWindow();
//                populateMiddle();
            }
        }

//        int currentCtr = 1;
//        int currentRow = 0;
//
//        for (int i = 0; i < seatingPillars.length; i++) {
//            if (i != 0) {
//                SeatingPillar sp = new SeatingPillar(seatingPillars[i], currentCtr);
//                populateAisle(sp, SeatingPillar.NOT_FIRST, currentRow);
//                seatingPillars[i] = sp.getData();
//                currentCtr = sp.getCounter();
//            }
//        }

        print2D(airplane.getSeatArrangement());
    }

    private void populateAisle(int row, Airplane airplane, int seatGroupIdx, String position) {

        Integer[] seatGroupSpecification = airplane.getSeatSpecification(seatGroupIdx);
        for (int i = 0; i < seatGroupSpecification[0]; i++) {
            if (position.equals(Airplane.NOT_FIRST)) {
                airplane.setSeat(row, 0, seatCtr++);
            }
            if (position.equals(Airplane.NOT_LAST)) {
                airplane.setSeat(row, seatGroupSpecification[1], seatCtr++);
            }
        }
    }

    public static int findMaxRow(int[][] seatSpecification) {

        return Arrays.stream(seatSpecification).mapToInt(e -> e[1]).max().getAsInt();
    }

    public static int findMaxCol(int[][] seatSpecification) {

        return Arrays.stream(seatSpecification).mapToInt(e -> e[0]).max().getAsInt();
    }

    public static void print2D(Integer[][] seatArrangement) {

        for (Integer[] row : seatArrangement) {
            log.debug(Arrays.toString(row));
        }
    }
}
