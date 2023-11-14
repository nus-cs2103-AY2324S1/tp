package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.InternshipBuilder;

/**
 * Contains tests for {@code StartDateWithinRangePredicate}.
 * This class is responsible for testing the behavior of {@link StartDateWithinRangePredicate}
 * to ensure that it correctly determines if an {@code Internship}'s {@code StartDate} lies within
 * the specified range.
 *
 * @see StartDateWithinRangePredicate
 */
public class StartDateWithinRangePredicateTest {
    @Test
    public void equals_sameObject_returnsTrue() {
        StartDateWithinRangePredicate predicateOne =
                new StartDateWithinRangePredicate(Arrays.asList(new StartDate("01/01/2021"),
                        new StartDate("01/01/2022")));

        assertEquals(predicateOne.toString(),
                StartDateWithinRangePredicate.class.getCanonicalName() + "{startDateRange=[01/01/2021, 01/01/2022]}");
        assertEquals(predicateOne, predicateOne);
    }

    @Test
    public void equals_differentType_returnsFalse() {
        StartDateWithinRangePredicate predicate =
                new StartDateWithinRangePredicate(Arrays.asList(new StartDate("01/01/2021"),
                        new StartDate("01/01/2022")));
        assertNotEquals(predicate, new Object());
    }

    @Test
    public void equals_differentPredicateWithSameDates_returnsTrue() {
        StartDateWithinRangePredicate predicateOne =
                new StartDateWithinRangePredicate(Arrays.asList(new StartDate("01/01/2021"),
                        new StartDate("01/01/2022")));
        StartDateWithinRangePredicate predicateTwo =
                new StartDateWithinRangePredicate(Arrays.asList(new StartDate("01/01/2021"),
                        new StartDate("01/01/2022")));
        assertEquals(predicateOne, predicateTwo);
    }

    @Test
    public void equals_differentPredicateWithDifferentDates_returnsFalse() {
        StartDateWithinRangePredicate predicateOne =
                new StartDateWithinRangePredicate(Arrays.asList(new StartDate("01/01/2021"),
                        new StartDate("01/01/2022")));
        StartDateWithinRangePredicate predicateTwo =
                new StartDateWithinRangePredicate(Arrays.asList(new StartDate("01/01/2020"),
                        new StartDate("01/01/2021")));
        assertNotEquals(predicateOne, predicateTwo);
    }

    @Test
    public void test_startDateWithinRange_returnsTrue() {
        StartDateWithinRangePredicate predicate = new StartDateWithinRangePredicate(Arrays.asList(
                new StartDate("01/01/2022"), new StartDate("31/12/2022")
        ));

        // Test with date 15/06/2022, which is within range
        Internship internship = new InternshipBuilder().withStartDate("15/06/2022").build();
        assertTrue(predicate.test(internship));
    }

    @Test
    public void test_startDateOutsideRange_returnsFalse() {
        StartDateWithinRangePredicate predicate = new StartDateWithinRangePredicate(Arrays.asList(
                new StartDate("01/01/2022"), new StartDate("31/12/2022")
        ));

        // Test with date 15/12/2021, which is before the range
        Internship internship = new InternshipBuilder().withStartDate("15/12/2021").build();
        assertFalse(predicate.test(internship));

        // Test with date 15/01/2023, which is after the range
        internship = new InternshipBuilder().withStartDate("15/01/2023").build();
        assertFalse(predicate.test(internship));
    }

    @Test
    public void test_equalStartDateToRangeBoundary_returnsTrue() {
        StartDateWithinRangePredicate predicate = new StartDateWithinRangePredicate(Arrays.asList(
                new StartDate("01/01/2022"), new StartDate("31/12/2022")
        ));

        // Test with date 01/01/2022, which is the start of the range
        Internship internship = new InternshipBuilder().withStartDate("01/01/2022").build();
        assertTrue(predicate.test(internship));

        // Test with date 31/12/2022, which is the end of the range
        internship = new InternshipBuilder().withStartDate("31/12/2022").build();
        assertTrue(predicate.test(internship));
    }
}
