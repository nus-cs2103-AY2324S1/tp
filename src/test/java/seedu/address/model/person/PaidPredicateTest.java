package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PaidPredicateTest {
    @Test
    public void equals() {

        PaidPredicate firstPredicate = new PaidPredicate(true);
        PaidPredicate secondPredicate = new PaidPredicate(true);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PaidPredicate firstPredicateCopy = new PaidPredicate(true);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));
    }
}
