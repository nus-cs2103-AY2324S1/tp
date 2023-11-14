package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.InternshipBuilder;

/**
 * Contains tests for {@code ApplicationStatusContainsKeywordsPredicate}.
 * This class is responsible for testing the behavior of {@link ApplicationStatusContainsKeywordsPredicate}
 * to ensure that it correctly identifies if an {@code Internship}'s {@code ApplicationStatus} matches any
 * of the given keywords.
 *
 * @see ApplicationStatusContainsKeywordsPredicate
 */
public class ApplicationStatusContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Arrays.asList("first", "second");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "third");

        ApplicationStatusContainsKeywordsPredicate firstPredicate =
                new ApplicationStatusContainsKeywordsPredicate(firstPredicateKeywordList);
        ApplicationStatusContainsKeywordsPredicate secondPredicate =
                new ApplicationStatusContainsKeywordsPredicate(secondPredicateKeywordList);

        // same string representation -> returns true
        assertEquals(
                firstPredicate.toString(),
                ApplicationStatusContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=[first, second]}"
        );

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ApplicationStatusContainsKeywordsPredicate firstPredicateCopy =
                new ApplicationStatusContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_applicationStatusContainsKeywords_returnsTrue() {
        // One keyword
        ApplicationStatusContainsKeywordsPredicate predicate =
                new ApplicationStatusContainsKeywordsPredicate(Arrays.asList("Accepted"));
        assertTrue(predicate.test(new InternshipBuilder().withApplicationStatus("Accepted").build()));

        // Mixed-case keyword
        predicate = new ApplicationStatusContainsKeywordsPredicate(Arrays.asList("aCcEpTeD"));
        assertTrue(predicate.test(new InternshipBuilder().withApplicationStatus("Accepted").build()));
    }

    @Test
    public void test_applicationStatusDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        ApplicationStatusContainsKeywordsPredicate predicate = new ApplicationStatusContainsKeywordsPredicate(
                Arrays.asList("Rejected"));
        assertFalse(predicate.test(new InternshipBuilder().withApplicationStatus("Accepted").build()));

        // One matching keyword
        predicate = new ApplicationStatusContainsKeywordsPredicate(Arrays.asList("Accepted", "Rejected"));
        assertTrue(predicate.test(new InternshipBuilder().withApplicationStatus("Rejected").build()));
    }

}
