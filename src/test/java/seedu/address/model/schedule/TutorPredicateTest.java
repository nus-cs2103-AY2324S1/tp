package seedu.address.model.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.ScheduleBuilder;
import seedu.address.testutil.TypicalPersons;

public class TutorPredicateTest {

    @Test
    public void equals() {
        Person firstPredicateKeywordList = TypicalPersons.ALICE;
        Person secondPredicateKeywordList = TypicalPersons.AMY;

        TutorPredicate firstPredicate =
            new TutorPredicate(firstPredicateKeywordList);
        TutorPredicate secondPredicate =
            new TutorPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TutorPredicate firstPredicateCopy =
            new TutorPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_containsTutor_returnsTrue() {
        TutorPredicate predicate =
            new TutorPredicate(TypicalPersons.ALICE);
        assertTrue(predicate.test(new ScheduleBuilder().build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords returns true
        TutorPredicate predicate = new TutorPredicate(null);
        assertFalse(predicate.test(new ScheduleBuilder().build()));

        // Non-matching keyword
        predicate = new TutorPredicate(TypicalPersons.CARL);
        assertFalse(predicate.test(new ScheduleBuilder().build()));

    }

    @Test
    public void toStringMethod() {
        Person tutor = TypicalPersons.ALICE;
        TutorPredicate predicate = new TutorPredicate(tutor);

        String expected = TutorPredicate.class.getCanonicalName() + "{tutor=" + tutor + "}";
        assertEquals(expected, predicate.toString());
    }
}
