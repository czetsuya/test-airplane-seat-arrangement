package com.armillary.business.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents an airplane setting with specification and actual arrangement.
 * 
 * @author Edward P. Legaspi | czetsuya@gmail.com
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AirplaneSeating {

    public static final int ROW_INDEX = 0;
    public static final int COL_INDEX = 1;
    private Integer[][] specification;
    private Integer[][] arrangement;

    /**
     * Initialize the size of the computed seat arrangement.
     * 
     * @param row the number of rows
     * @param col the number of columns
     */
    public void initArrangement(int row, int col) {

        arrangement = new Integer[row][col];
    }

    /**
     * Sets the value of a given cell.
     * 
     * @param row the row
     * @param col the column
     * @param val integer value
     */
    public void setValue(int row, int col, int val) {

        arrangement[row][col] = val;
    }

    /**
     * Returns the specification on a given seating group. For example we have a 2d
     * integer array [[2, 3], [3, 4]]. A specificationIdx with value = 1 will return
     * the array [3, 4].
     * 
     * @param specificationIdx the group index
     * @return an array of integer that contains the row x col of a group
     */
    public Integer[] getSpecification(int specificationIdx) {

        return specification[specificationIdx];
    }

    /**
     * Computes the first index of a given specification id. For example we have a
     * 2d integer array [[2, 3], [3, 4]]. A specificationIdx with value = 1 will
     * return 3. It adds the columns of each specification until it reach the index.
     * 
     * @param specificationIdx the index of the specification
     * @return the first index of the specification in the arrangement array
     */
    public int getSpecificationArrangementFirstIndex(int specificationIdx) {

        int firstIndex = 0;
        if (specificationIdx > 0) {
            for (int i = 0; i < specificationIdx; i++) {
                firstIndex += specification[i][1];
            }
        }
        return firstIndex;
    }

    /**
     * Computes the last index of the specification with the given index in the
     * arrangement array.
     * 
     * @param specificationIdx the index of the specification
     * @return the last index of the specification in the arrangement array
     * @return
     */
    public int getSpecificationArrangementLastIndex(int specificationIdx) {

        return getSpecificationArrangementFirstIndex(specificationIdx) + specification[specificationIdx][1] - 1;
    }

    /**
     * Computes the number of columns of an specification index.
     * 
     * @param specificationIdx the index of the specification
     * @return value of the column
     */
    public int getSpecificationLength(int specificationIdx) {

        return specification[specificationIdx][COL_INDEX];
    }

    /**
     * Evaluates whether a given specification row is less than the current row
     * being process.
     * 
     * @param specificationIdx the index of the specification
     * @param row              current row of arrangement array being process
     * @return true means we can still process this row
     */
    public boolean isValidSeat(int specificationIdx, int row) {

        return specification[specificationIdx][ROW_INDEX] > row;
    }
}
