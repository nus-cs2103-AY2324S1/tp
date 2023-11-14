package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.InternshipBuilder;

/**
 * Contains tests for {@code DurationWithinRangePredicate}.
 * This class is responsible for testing the behavior of {@link DurationWithinRangePredicate}
 * to ensure that it correctly determines if an {@code Internship}'s {@code Duration} lies within
 * the specified range.
 *
 * @see DurationWithinRangePredicate
 */
public class DurationWithinRangePredicateTest {

    @Test
    public void test_durationWithinRange_returnsTrue() {
        DurationWithinRangePredicate predicate = new DurationWithinRangePredicate(Arrays.asList(
                new Duration("2"), new Duration("4")
        ));

        // Test with duration 3, which is within range 2-4
        Internship internship = new InternshipBuilder().withDuration("3").build();
        assertTrue(predicate.test(internship));
    }

    @Test
    public void test_durationOutsideRange_returnsFalse() {
        DurationWithinRangePredicate predicate = new DurationWithinRangePredicate(Arrays.asList(
                new Duration("2"), new Duration("4")
        ));

        // Test with duration 1, which is outside range 2-4
        Internship internship = new InternshipBuilder().withDuration("1").build();
        assertFalse(predicate.test(internship));

        // Test with duration 5, which is outside range 2-4
        internship = new InternshipBuilder().withDuration("5").build();
        assertFalse(predicate.test(internship));
    }

    @Test
    public void test_equalDurationToRangeBoundary_returnsTrue() {
        DurationWithinRangePredicate predicate = new DurationWithinRangePredicate(Arrays.asList(
                new Duration("2"), new Duration("4")
        ));

        // Test with duration 2, which is equal to the lower boundary
        Internship internship = new InternshipBuilder().withDuration("2").build();
        assertTrue(predicate.test(internship));

        // Test with duration 4, which is equal to the upper boundary
        internship = new InternshipBuilder().withDuration("4").build();
        assertTrue(predicate.test(internship));
    }

    @Test
    public void test_equalDurationPredicateToNull_returnsFalse() {
        DurationWithinRangePredicate predicate = new DurationWithinRangePredicate(Arrays.asList(
                new Duration("2"), new Duration("4")
        ));
        assertFalse(predicate.equals(null));
    }
}
