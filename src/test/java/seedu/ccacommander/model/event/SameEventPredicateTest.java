package seedu.ccacommander.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ccacommander.testutil.TypicalEvents.AURORA_BOREALIS;
import static seedu.ccacommander.testutil.TypicalEvents.BOXING_DAY;

import org.junit.jupiter.api.Test;

public class SameEventPredicateTest {
    @Test
    public void testConstructor() {
        SameEventPredicate predicate = new SameEventPredicate(AURORA_BOREALIS);
        assertNotNull(predicate);
    }

    @Test
    public void test_sameEvent_returnsTrue() {
        // same event
        SameEventPredicate predicate = new SameEventPredicate(AURORA_BOREALIS);
        assertTrue(predicate.test(AURORA_BOREALIS));
    }

    @Test
    public void test_differentEvent_returnsFalse() {
        // different events
        SameEventPredicate predicate = new SameEventPredicate(AURORA_BOREALIS);
        assertFalse(predicate.test(BOXING_DAY));


        predicate = new SameEventPredicate(null);

        assertFalse(predicate.test(AURORA_BOREALIS));
    }

    @Test
    public void equals() {
        SameEventPredicate firstPredicate = new SameEventPredicate(AURORA_BOREALIS);
        SameEventPredicate secondPredicate = new SameEventPredicate(BOXING_DAY);

        assertTrue(firstPredicate.equals(firstPredicate));
        assertFalse(firstPredicate.equals(null));
        assertFalse(firstPredicate.equals(secondPredicate));
    }

}
