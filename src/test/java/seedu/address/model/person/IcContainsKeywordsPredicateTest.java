package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class IcContainsKeywordsPredicateTest {

    @Test
    public void testIcContainsKeywordsPredicate() {
        IcContainsKeywordsPredicate icPredicate = new IcContainsKeywordsPredicate("T1234567G");

        // Test with a person having matching IC
        assertTrue(icPredicate.test(new PersonBuilder().withIc("T1234567G").build()));

        // Test with a person having a different IC
        assertFalse(icPredicate.test(new PersonBuilder().withIc("T1234567K").build()));
    }
}
