package seedu.ccacommander.model.member;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.ccacommander.testutil.MemberBuilder;

public class MemberNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        MemberNameContainsKeywordsPredicate firstPredicate =
                new MemberNameContainsKeywordsPredicate(firstPredicateKeywordList);
        MemberNameContainsKeywordsPredicate secondPredicate =
                new MemberNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        MemberNameContainsKeywordsPredicate firstPredicateCopy =
                new MemberNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different member -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        MemberNameContainsKeywordsPredicate predicate =
                new MemberNameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new MemberBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new MemberNameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new MemberBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new MemberNameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new MemberBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new MemberNameContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new MemberBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        MemberNameContainsKeywordsPredicate predicate =
                new MemberNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new MemberBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new MemberNameContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new MemberBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new MemberNameContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new MemberBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        MemberNameContainsKeywordsPredicate predicate = new MemberNameContainsKeywordsPredicate(keywords);

        String expected = MemberNameContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
