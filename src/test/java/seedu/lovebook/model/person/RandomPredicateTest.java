package seedu.lovebook.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.lovebook.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

public class RandomPredicateTest {

    @Test
    public void equals() {
        RandomPredicate randomPredicate = new RandomPredicate(ALICE);
        RandomPredicate randomPredicateCopy = new RandomPredicate(ALICE);
        // same object -> returns true
        assertTrue(randomPredicate.equals(randomPredicate));
        // same values -> true
        assertTrue(randomPredicate.equals(randomPredicateCopy));
        // different types -> false
        assertFalse(randomPredicate.equals(1));
        // null -> false
        assertFalse(randomPredicate.equals(null));
    }
}
