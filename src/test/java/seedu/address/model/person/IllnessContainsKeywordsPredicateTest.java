package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class IllnessContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");
        List<String> firstPredicateKeywordListUpperCase = Collections.singletonList("FIRST");
        List<String> firstPredicateKeywordListMixedCase = Collections.singletonList("FiRsT");

        IllnessContainsKeywordsPredicate firstPredicate =
                new IllnessContainsKeywordsPredicate(firstPredicateKeywordList);
        IllnessContainsKeywordsPredicate secondPredicate =
                new IllnessContainsKeywordsPredicate(secondPredicateKeywordList);
        IllnessContainsKeywordsPredicate firstPredicateUpperCase =
                new IllnessContainsKeywordsPredicate(firstPredicateKeywordListUpperCase);
        IllnessContainsKeywordsPredicate firstPredicateMixedCase =
                new IllnessContainsKeywordsPredicate(firstPredicateKeywordListMixedCase);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        IllnessContainsKeywordsPredicate firstPredicateCopy =
                new IllnessContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different illness -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));

        // same values but Upper Case - Same predicate since keywords are meant to be case-insensitive
        assertTrue(firstPredicate.equals(firstPredicateUpperCase));

        // same values but Mixed Case - Same predicate since keywords are meant to be case-insensitive
        assertTrue(firstPredicate.equals(firstPredicateMixedCase));
    }

    @Test
    public void test_illnessContainsKeywords_returnsTrue() {
        // One keyword
        IllnessContainsKeywordsPredicate predicate = new IllnessContainsKeywordsPredicate(Collections
                .singletonList("fever"));
        assertTrue(predicate.test(new PersonBuilder().withTags("fever").build()));

        // Multiple keywords
        predicate = new IllnessContainsKeywordsPredicate(Arrays.asList("fever", "headache"));
        assertTrue(predicate.test(new PersonBuilder().withTags("fever headache").build()));

        // Only one matching keyword
        predicate = new IllnessContainsKeywordsPredicate(Arrays.asList("fever", "headache"));
        assertTrue(predicate.test(new PersonBuilder().withTags("fever appendicitis").build()));

        // Keywords that make part of the illness (Keyword 'flu' should return patients with 'influenza' too)
        predicate = new IllnessContainsKeywordsPredicate(Arrays.asList("flu"));
        assertTrue(predicate.test(new PersonBuilder().withTags("influenza").build()));

        // Keywords with Cases Mixed - Test for Case Insensitivity
        predicate = new IllnessContainsKeywordsPredicate(Arrays.asList("fEvEr", "HEADache"));
        assertTrue(predicate.test(new PersonBuilder().withTags("fever headache").build()));
    }

    @Test
    public void test_illnessDoesNotContainKeywords_returnsFalse() {
        // Keywords do not match any illness
        IllnessContainsKeywordsPredicate predicate =
                new IllnessContainsKeywordsPredicate(Arrays.asList("fever", "headache"));
        assertFalse(predicate.test(new PersonBuilder().withTags("flu appendicitis").build()));

        // Typo in keyword leads to false result
        predicate = new IllnessContainsKeywordsPredicate(Collections
                .singletonList("fevar"));
        assertFalse(predicate.test(new PersonBuilder().withTags("fever").build()));
    }
}
