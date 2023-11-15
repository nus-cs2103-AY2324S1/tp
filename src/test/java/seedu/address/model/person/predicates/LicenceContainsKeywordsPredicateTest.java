package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class LicenceContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "first";
        String secondPredicateKeyword = "first second";

        LicenceContainsKeywordsPredicate firstPredicate = new LicenceContainsKeywordsPredicate(firstPredicateKeyword);
        LicenceContainsKeywordsPredicate secondPredicate = new LicenceContainsKeywordsPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        LicenceContainsKeywordsPredicate firstPredicateCopy =
                new LicenceContainsKeywordsPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_licenceContainsKeywords_returnsTrue() {
        // One keyword
        LicenceContainsKeywordsPredicate predicate = new LicenceContainsKeywordsPredicate("SLA1111A");
        assertTrue(predicate.test(new PersonBuilder().withLicencePlate("SLA1111A").build()));

        // Trailing and Leading white spaces
        predicate = new LicenceContainsKeywordsPredicate("   SLA1111A    ");
        assertTrue(predicate.test(new PersonBuilder().withLicencePlate("SLA1111A").build()));

        // Mixed-case keywords
        predicate = new LicenceContainsKeywordsPredicate("slA1111a");
        assertTrue(predicate.test(new PersonBuilder().withLicencePlate("SLA1111A").build()));

        // Partial keyword
        predicate = new LicenceContainsKeywordsPredicate("SLA");
        assertTrue(predicate.test(new PersonBuilder().withLicencePlate("SLA1111A").build()));
    }

    @Test
    public void test_licenceDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        LicenceContainsKeywordsPredicate predicate = new LicenceContainsKeywordsPredicate("2222");
        assertFalse(predicate.test(new PersonBuilder().withLicencePlate("SLA1111A").build()));

        // Keyword including white spaces
        predicate = new LicenceContainsKeywordsPredicate("SLA 1111A");
        assertFalse(predicate.test(new PersonBuilder().withLicencePlate("SLA1111A").build()));

        // Keyword do not match exact
        predicate = new LicenceContainsKeywordsPredicate("SLAB1111A");
        assertFalse(predicate.test(new PersonBuilder().withLicencePlate("SLA1111A").build()));
    }

    @Test
    public void toStringMethod() {
        String keyword = "keyword1";
        LicenceContainsKeywordsPredicate predicate = new LicenceContainsKeywordsPredicate(keyword);

        String expected = LicenceContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=[" + keyword + "]}";
        assertEquals(expected, predicate.toString());
    }
}
