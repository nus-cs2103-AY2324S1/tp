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

        IllnessContainsKeywordsPredicate firstPredicate =
                new IllnessContainsKeywordsPredicate(firstPredicateKeywordList);
        IllnessContainsKeywordsPredicate secondPredicate =
                new IllnessContainsKeywordsPredicate(secondPredicateKeywordList);

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

        // Mixed-case keywords
        predicate = new IllnessContainsKeywordsPredicate(Arrays.asList("fEvEr", "HEADache"));
        assertTrue(predicate.test(new PersonBuilder().withTags("fever headache").build()));
    }
}
