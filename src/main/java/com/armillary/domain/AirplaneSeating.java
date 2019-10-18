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
public class AirplaneSeating {

    public static final String NOT_FIRST = "NOT_FIRST";
    public static final String NOT_LAST = "NOT_LAST";
    private Integer[][] specification;
    private Integer[][] arrangement;

    public void initArrangement(int row, int col) {

        arrangement = new Integer[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                arrangement[i][j] = null;
            }
        }
    }

    public void setSeatValue(int row, int col, int val) {

        arrangement[row][col] = val;
    }

    public Integer[] getSeatGroup(int seatGroupIdx) {

        return arrangement[seatGroupIdx];
    }

    public Integer[] getSeatSpecification(int seatGroupIdx) {

        return specification[seatGroupIdx];
    }

    public int getSeatSpecificationArrangementFirstIndex(int group) {
        int firstIndex = 0;
        for (int i = 1; i < group; i++) {
            firstIndex += specification[i - 1][0];
        }
        return firstIndex;
    }

    public int getSeatSpecificationArrangementLastIndex(int group) {
        return getSeatSpecificationArrangementFirstIndex(group) + specification[group][0] - 1;
    }

    public int getSeatSpecificationLength(int group) {

        return specification[group][0];
    }
}
