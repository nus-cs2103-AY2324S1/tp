package seedu.address.model.interview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.testutil.InterviewBuilder;

public class JobContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        JobContainsKeywordsPredicate firstPredicate = new JobContainsKeywordsPredicate(firstPredicateKeywordList);
        JobContainsKeywordsPredicate secondPredicate = new JobContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        JobContainsKeywordsPredicate firstPredicateCopy = new JobContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different applicant -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_jobContainsKeywords_returnsTrue() throws ParseException {
        // One keyword
        JobContainsKeywordsPredicate predicate = new JobContainsKeywordsPredicate(Collections.singletonList("Engineer"));
        assertTrue(predicate.test(new InterviewBuilder().withJobRole("Engineer").build()));

        // Multiple keywords
        predicate = new JobContainsKeywordsPredicate(Arrays.asList("Engineer", "Analyst"));
        assertTrue(predicate.test(new InterviewBuilder().withJobRole("Engineer Analyst").build()));

        // Only one matching keyword
        predicate = new JobContainsKeywordsPredicate(Arrays.asList("Engineer", "Developer"));
        assertTrue(predicate.test(new InterviewBuilder().withJobRole("Engineer Analyst").build()));

        // Mixed-case keywords
        predicate = new JobContainsKeywordsPredicate(Arrays.asList("enGinEer", "aNalySt"));
        assertTrue(predicate.test(new InterviewBuilder().withJobRole("Engineer Analyst").build()));
    }

    @Test
    public void test_jobDoesNotContainKeywords_returnsFalse() throws ParseException {
        // Zero keywords
        JobContainsKeywordsPredicate predicate = new JobContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new InterviewBuilder().withJobRole("Engineer").build()));

        // Non-matching keyword
        predicate = new JobContainsKeywordsPredicate(Arrays.asList("Analyst"));
        assertFalse(predicate.test(new InterviewBuilder().withJobRole("Engineer Developer").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        JobContainsKeywordsPredicate predicate = new JobContainsKeywordsPredicate(keywords);

        String expected = JobContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
