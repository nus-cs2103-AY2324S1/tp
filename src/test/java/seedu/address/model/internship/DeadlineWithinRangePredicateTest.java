package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.InternshipBuilder;

/**
 * Contains tests for {@code DeadlineWithinRangePredicate}.
 * This class is responsible for testing the behavior of {@link DeadlineWithinRangePredicate}
 * to ensure that it correctly determines if an {@code Internship}'s {@code Deadline} lies within
 * the specified range.
 *
 * @see DeadlineWithinRangePredicate
 */
public class DeadlineWithinRangePredicateTest {
    @Test
    public void equals_sameObject_returnsTrue() {
        Deadline firstDeadline = new Deadline("10/01/2023", "11/01/2023");
        Deadline secondDeadline = new Deadline("20/01/2023", "21/01/2023");
        DeadlineWithinRangePredicate predicate = new DeadlineWithinRangePredicate(Arrays.asList(firstDeadline,
                secondDeadline));
        assertTrue(predicate.equals(predicate));

        // same string representation -> returns True
        assertTrue(predicate.toString().equals(
                DeadlineWithinRangePredicate.class.getCanonicalName() + "{deadlineRange=[10/01/2023, 20/01/2023]}")
        );
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        Deadline firstDeadline = new Deadline("10/01/2023", "11/01/2023");
        Deadline secondDeadline = new Deadline("20/01/2023", "21/01/2023");

        DeadlineWithinRangePredicate firstPredicate = new DeadlineWithinRangePredicate(Arrays.asList(firstDeadline,
                secondDeadline));
        DeadlineWithinRangePredicate secondPredicate = new DeadlineWithinRangePredicate(Arrays.asList(firstDeadline,
                secondDeadline));

        assertTrue(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void equals_differentTypes_returnsFalse() {
        Deadline firstDeadline = new Deadline("10/01/2023", "11/01/2023");
        Deadline secondDeadline = new Deadline("20/01/2023", "21/01/2023");
        DeadlineWithinRangePredicate predicate = new DeadlineWithinRangePredicate(Arrays.asList(firstDeadline,
                secondDeadline));

        assertFalse(predicate.equals(1));
    }

    @Test
    public void equals_null_returnsFalse() {
        Deadline firstDeadline = new Deadline("10/01/2023", "11/01/2023");
        Deadline secondDeadline = new Deadline("20/01/2023", "21/01/2023");
        DeadlineWithinRangePredicate predicate = new DeadlineWithinRangePredicate(Arrays.asList(firstDeadline,
                secondDeadline));

        assertFalse(predicate.equals(null));
    }

    @Test
    public void equals_differentValues_returnsFalse() {
        Deadline firstDeadline = new Deadline("10/01/2023", "11/01/2023");
        Deadline secondDeadline = new Deadline("20/01/2023", "21/01/2023");
        Deadline thirdDeadline = new Deadline("25/01/2023", "26/01/2023");

        DeadlineWithinRangePredicate firstPredicate = new DeadlineWithinRangePredicate(Arrays.asList(firstDeadline,
                secondDeadline));
        DeadlineWithinRangePredicate secondPredicate = new DeadlineWithinRangePredicate(Arrays.asList(firstDeadline,
                thirdDeadline));

        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_deadlineIsWithinRange_returnsTrue() {
        Deadline lowerLimit = new Deadline("01/01/2023", "02/01/2023");
        Deadline upperLimit = new Deadline("31/12/2023", "31/12/2024");
        DeadlineWithinRangePredicate predicate =
                new DeadlineWithinRangePredicate(Arrays.asList(lowerLimit, upperLimit));

        // Deadline is in the middle of the range
        Internship internship1 = new InternshipBuilder()
                .withDeadline("15/06/2023", "16/06/2023").build();
        assertTrue(predicate.test(internship1));

        // Deadline is at the start of the range
        Internship internship2 = new InternshipBuilder().withDeadline("01/01/2023", "02/01/2023")
                .build();
        assertTrue(predicate.test(internship2));

        // Deadline is at the end of the range
        Internship internship3 = new InternshipBuilder().withDeadline("31/12/2023", "31/12/2024")
                .build();
        assertTrue(predicate.test(internship3));
    }

    @Test
    public void test_deadlineIsNotWithinRange_returnsFalse() {
        Deadline lowerLimit = new Deadline("01/01/2023", "02/01/2023");
        Deadline upperLimit = new Deadline("31/12/2023", "31/12/2024");
        DeadlineWithinRangePredicate predicate =
                new DeadlineWithinRangePredicate(Arrays.asList(lowerLimit, upperLimit));

        // Deadline is before the range
        Internship internship1 = new InternshipBuilder().withDeadline("31/12/2022", "31/12/2023")
                .build();
        assertFalse(predicate.test(internship1));

        // Deadline is after the range
        Internship internship2 = new InternshipBuilder().withDeadline("01/01/2024", "02/01/2024")
                .build();
        assertFalse(predicate.test(internship2));
    }

    // Additional test cases can be added to check for boundary cases and other behaviors
}
