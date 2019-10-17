package com.armillary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Edward P. Legaspi | czetsuya@gmail.com
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Airplane {

    public static final String NOT_FIRST = "NOT_FIRST";
    public static final String NOT_LAST = "NOT_LAST";
    private int[][] seatSpecification;
    private Integer[][] seatArrangement;

    public void initSeatArrangement(int row, int col) {

        seatArrangement = new Integer[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                seatArrangement[i][j] = null;
            }
        }
    }

    public void setSeat(int row, int col, int val) {

        seatArrangement[row][col] = val;
    }

    public Integer[] getSeatGroup(int seatGroupIdx) {

        return seatArrangement[seatGroupIdx];
    }

    public Integer[] getSeatSpecification(int seatGroupIdx) {

        return seatArrangement[seatGroupIdx];
    }
}
