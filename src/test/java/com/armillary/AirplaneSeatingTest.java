package com.armillary;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import com.armillary.business.domain.AirplaneSeating;

/**
 * @author Edward P. Legaspi | czetsuya@gmail.com
 */
public class AirplaneSeatingTest {

    private static final Integer[][] INPUT = { { 2, 3 }, { 3, 4 }, { 3, 2 }, { 4, 3 } };
    private AirplaneSeating ap = new AirplaneSeating();

    @Test
    public void onInit_whenCheckSize_ok() {

        ap.initArrangement(2, 2);

        assertThat(ap.getArrangement(), Matchers.arrayWithSize(2));
        assertThat(ap.getArrangement()[0], Matchers.arrayWithSize(2));
    }

    @Test
    public void whenSetValue_ok() {

        ap.initArrangement(2, 2);
        ap.setValue(1, 1, 100);
        assertThat(100, equalTo(ap.getArrangement()[1][1]));
    }

    @Test
    public void givenSpecification_whenGet_ok() {

        AirplaneSeating ap = new AirplaneSeating();
        ap.setSpecification(INPUT);
        assertThat(ap.getSpecification(1), equalTo(new int[] { 3, 4 }));
    }

    @Test
    public void specification_whenGetFirstIndex_ok() {

        AirplaneSeating ap = new AirplaneSeating();
        ap.setSpecification(INPUT);
        assertThat(7, equalTo(ap.getSpecificationArrangementFirstIndex(2)));
    }

    @Test
    public void specification_whenGetLastIndex_ok() {

        AirplaneSeating ap = new AirplaneSeating();
        ap.setSpecification(INPUT);
        assertThat(8, equalTo(ap.getSpecificationArrangementLastIndex(2)));
    }

    @Test
    public void specification_whenGetLength_ok() {

        AirplaneSeating ap = new AirplaneSeating();
        ap.setSpecification(INPUT);
        assertThat(2, equalTo(ap.getSpecificationLength(2)));
    }

    @Test
    public void seat_whenValid_ok() {

        AirplaneSeating ap = new AirplaneSeating();
        ap.setSpecification(INPUT);
        assertThat(true, equalTo(ap.isValidSeat(2, 0)));
    }

}
