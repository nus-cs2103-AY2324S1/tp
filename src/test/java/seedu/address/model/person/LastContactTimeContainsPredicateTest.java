package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.DateTimeUtil.parse;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class LastContactTimeContainsPredicateTest {
    private LocalDateTime time1 = parse("10.09.2023 1000");
    private LocalDateTime time2 = parse("11.09.2023 1000");

    @Test
    public void equals() {
        LastContactTimeContainsPredicate firstPredicate = new LastContactTimeContainsPredicate(time1);
        LastContactTimeContainsPredicate secondPredicate = new LastContactTimeContainsPredicate(time2);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        LastContactTimeContainsPredicate firstPredicateCopy = new LastContactTimeContainsPredicate(time1);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_lastContactTimeMatches_returnsTrue() {
        LastContactTimeContainsPredicate predicate =
                new LastContactTimeContainsPredicate(time1);
        assertTrue(predicate.test(new PersonBuilder()
                .withLastContactedTime("10.09.2023 1000").build()));
    }

    @Test
    public void test_lastContactTimeDoesNotMatch_returnsFalse() {
        LastContactTimeContainsPredicate predicate =
                new LastContactTimeContainsPredicate(time2);
        assertFalse(predicate.test(new PersonBuilder()
                .withLastContactedTime("10.09.2023 1000").build()));
    }

    @Test
    public void toStringMethod() {
        LastContactTimeContainsPredicate predicate = new LastContactTimeContainsPredicate(time1);

        String expected = LastContactTimeContainsPredicate.class.getCanonicalName()
                + "{time=" + time1 + "}";
        assertEquals(expected, predicate.toString());
    }
}
