package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.MemberBuilder;

public class MemberContainsKeywordPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");
        MemberContainsKeywordsPredicate firstPredicate =
                new MemberContainsKeywordsPredicate(firstPredicateKeywordList);
        MemberContainsKeywordsPredicate secondPredicate =
                new MemberContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        MemberContainsKeywordsPredicate firstPredicateCopy = new MemberContainsKeywordsPredicate(
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
        MemberContainsKeywordsPredicate predicate = new MemberContainsKeywordsPredicate(
                Collections.singletonList("Alicia"));
        assertTrue(predicate.test(new MemberBuilder().withName("Alicia Teng").build()));

        // Multiple keywords
        predicate = new MemberContainsKeywordsPredicate(Arrays.asList("Alicia", "Teng"));
        assertTrue(predicate.test(new MemberBuilder().withName("Alicia Teng").build()));

        // Only one matching keyword
        predicate = new MemberContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new MemberBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new MemberContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new MemberBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        MemberContainsKeywordsPredicate predicate = new MemberContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new MemberBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new MemberContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new MemberBuilder().withName("Alice Bob").build()));

        // Multiple non-matching keyword
        predicate = new MemberContainsKeywordsPredicate(Arrays.asList("Carol", "Seven"));
        assertFalse(predicate.test(new MemberBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        ApplicantContainsKeywordsPredicate predicate = new ApplicantContainsKeywordsPredicate(keywords);

        String expected = ApplicantContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
