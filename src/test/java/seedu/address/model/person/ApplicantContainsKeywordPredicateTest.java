package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ApplicantBuilder;

public class ApplicantContainsKeywordPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ApplicantContainsKeywordsPredicate firstPredicate =
                new ApplicantContainsKeywordsPredicate(firstPredicateKeywordList);
        ApplicantContainsKeywordsPredicate secondPredicate =
                new ApplicantContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ApplicantContainsKeywordsPredicate firstPredicateCopy = new ApplicantContainsKeywordsPredicate(
                firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }
    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        ApplicantContainsKeywordsPredicate predicate = new ApplicantContainsKeywordsPredicate(
                Collections.singletonList("Alice"));
        assertTrue(predicate.test(new ApplicantBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new ApplicantContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new ApplicantBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new ApplicantContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new ApplicantBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new ApplicantContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new ApplicantBuilder().withName("Alice Bob").build()));

        // Keyword is phone number
        predicate = new ApplicantContainsKeywordsPredicate((Arrays.asList("91239123")));
        assertTrue(predicate.test(new ApplicantBuilder().withPhone("91239123").build()));
    }
    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ApplicantContainsKeywordsPredicate predicate = new ApplicantContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ApplicantBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new ApplicantContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new ApplicantBuilder().withName("Alice Bob").build()));

        // multiple keywords
        predicate = new ApplicantContainsKeywordsPredicate(Arrays.asList("December", "Alicia"));
        assertFalse(predicate.test(new ApplicantBuilder().withName("Alice Bob").build()));
    }
    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        ApplicantContainsKeywordsPredicate predicate = new ApplicantContainsKeywordsPredicate(keywords);

        String expected = ApplicantContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
