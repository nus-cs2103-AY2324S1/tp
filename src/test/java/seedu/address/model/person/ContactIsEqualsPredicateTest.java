package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalPersons;

public class ContactIsEqualsPredicateTest {

    @Test
    public void equals() {
        Person firstPredicatePerson = TypicalPersons.ALICE;
        Person secondPredicatePerson = TypicalPersons.BENSON;

        ContactIsEqualsPredicate firstPredicate = new ContactIsEqualsPredicate(firstPredicatePerson);
        ContactIsEqualsPredicate secondPredicate = new ContactIsEqualsPredicate(secondPredicatePerson);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ContactIsEqualsPredicate firstPredicateCopy = new ContactIsEqualsPredicate(firstPredicatePerson);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }
}
