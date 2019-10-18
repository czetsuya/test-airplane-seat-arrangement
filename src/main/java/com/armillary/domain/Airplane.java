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
    private Integer[][] seatSpecification;
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

        return seatSpecification[seatGroupIdx];
    }

    public int getSeatSpecificationArrangementFirstIndex(int group) {
        int firstIndex = 0;
        for (int i = 1; i < group; i++) {
            firstIndex += seatSpecification[i - 1][0];
        }
        return firstIndex;
    }

    public int getSeatSpecificationArrangementLastIndex(int group) {
        return getSeatSpecificationArrangementFirstIndex(group) + seatSpecification[group][0] - 1;
    }

    public int getSeatSpecificationLength(int group) {

        return seatSpecification[group][0];
    }
}
