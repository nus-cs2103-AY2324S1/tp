package seedu.address.model.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
            new StatusPredicate(Status.MISSED, firstPredicateKeywordList);
        StatusPredicate secondPredicate =
            new StatusPredicate(Status.COMPLETED, secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StatusPredicate firstPredicateCopy =
            new StatusPredicate(Status.MISSED, firstPredicateKeywordList);
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
            new StatusPredicate(Status.PENDING, null);
        assertTrue(predicate.test(new ScheduleBuilder().build()));
    }

    @Test
    public void test_containsStatusAndPerson_returnsTrue() {
        StatusPredicate predicate =
            new StatusPredicate(Status.PENDING, TypicalPersons.ALICE);
        assertTrue(predicate.test(new ScheduleBuilder().build()));
    }

    @Test
    public void test_containsStatusAndPerson_returnsFalse() {
        StatusPredicate predicate =
            new StatusPredicate(Status.PENDING, TypicalPersons.BOB);
        assertFalse(predicate.test(new ScheduleBuilder().build()));
    }

    @Test
    public void toStringMethod() {
        Person tutor = TypicalPersons.ALICE;
        StatusPredicate predicate = new StatusPredicate(Status.MISSED, tutor);

        String expected = StatusPredicate.class.getCanonicalName() + "{status=" + Status.MISSED + "}";
        assertEquals(expected, predicate.toString());
    }
}
