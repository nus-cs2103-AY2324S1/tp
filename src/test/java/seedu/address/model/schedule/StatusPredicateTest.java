package seedu.address.model.schedule;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import seedu.address.model.person.Person;
import seedu.address.testutil.ScheduleBuilder;
import seedu.address.testutil.TypicalPersons;

public class StatusPredicateTest {

    @Test
    public void equals() {
        Person firstPredicateKeywordList = TypicalPersons.ALICE;
        Person secondPredicateKeywordList = TypicalPersons.AMY;

        StatusPredicate firstPredicate =
            new StatusPredicate(Collections.singletonList(Status.MISSED.toString()), firstPredicateKeywordList);
        StatusPredicate secondPredicate =
            new StatusPredicate(Collections.singletonList(Status.COMPLETED.toString()), secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StatusPredicate firstPredicateCopy =
            new StatusPredicate(Collections.singletonList(Status.MISSED.toString()), firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different status -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_containsStatus_returnsTrue() {
        StatusPredicate predicate =
            new StatusPredicate(Collections.singletonList(Status.PENDING.toString()), null);
        assertTrue(predicate.test(new ScheduleBuilder().build()));
    }

    @Test
    public void toStringMethod() {
        Person tutor = TypicalPersons.ALICE;
        StatusPredicate predicate = new StatusPredicate(Collections.singletonList(Status.MISSED.toString()), tutor);

        String expected = StatusPredicate.class.getCanonicalName() + "{status=[" + Status.MISSED.toString() + "]}";
        assertEquals(expected, predicate.toString());
    }
}
