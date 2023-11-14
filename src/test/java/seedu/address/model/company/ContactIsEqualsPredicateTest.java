package seedu.address.model.company;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalCompanies;

public class ContactIsEqualsPredicateTest {

    @Test
    public void equals() {
        Company firstPredicateCompany = TypicalCompanies.APPLE;
        Company secondPredicateCompany = TypicalCompanies.MICROSOFT;

        ContactIsEqualsPredicate firstPredicate = new ContactIsEqualsPredicate(firstPredicateCompany);
        ContactIsEqualsPredicate secondPredicate = new ContactIsEqualsPredicate(secondPredicateCompany);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        ContactIsEqualsPredicate firstPredicateCopy = new ContactIsEqualsPredicate(firstPredicateCompany);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertNotEquals(1, firstPredicate);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different company -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }
}

