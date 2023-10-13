package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class IcContainsKeywordsPredicateTest {

    @Test
    public void testIcContainsKeywordsPredicate() {
        IcContainsKeywordsPredicate icPredicate = new IcContainsKeywordsPredicate("A1234567G");

        // Test with a person having matching IC
        assertTrue(icPredicate.test(new PersonBuilder().withIc("A1234567G").build()));

        // Test with a person having a different IC
        assertFalse(icPredicate.test(new PersonBuilder().withIc("T1234567G").build()));

        // Test with a person having null IC
        assertFalse(icPredicate.test(new PersonBuilder().withIc(null).build()));

        // Test with a person having empty IC
        assertFalse(icPredicate.test(new PersonBuilder().withIc("").build()));

        // Test with a person having IC with different case
        assertTrue(icPredicate.test(new PersonBuilder().withIc("a1234567g").build()));
    }
}
