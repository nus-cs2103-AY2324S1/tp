package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class StatusContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        StatusContainsKeywordsPredicate firstPredicate =
                new StatusContainsKeywordsPredicate(firstPredicateKeywordList);
        StatusContainsKeywordsPredicate secondPredicate =
                new StatusContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StatusContainsKeywordsPredicate firstPredicateCopy =
                new StatusContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_statusContainsKeywords_returnsTrue() {
        // One keyword
        StatusContainsKeywordsPredicate predicate =
                new StatusContainsKeywordsPredicate(Collections.singletonList("Active"));
        assertTrue(predicate.test(new PersonBuilder().withStatus("Active").build()));

        // Multiple keywords
        predicate = new StatusContainsKeywordsPredicate(Arrays.asList("Active", "Prospective"));
        assertTrue(predicate.test(new PersonBuilder().withStatus("Active").build()));

        // Mixed-case keywords
        predicate = new StatusContainsKeywordsPredicate(Arrays.asList("inAcTive"));
        assertTrue(predicate.test(new PersonBuilder().withStatus("Inactive").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        StatusContainsKeywordsPredicate predicate = new StatusContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withStatus("").build()));

        // Non-matching keyword
        predicate = new StatusContainsKeywordsPredicate(Arrays.asList("Actve"));
        assertFalse(predicate.test(new PersonBuilder().withStatus("Active").build()));

        // Keywords match name, phone and email, but does not match status
        predicate = new StatusContainsKeywordsPredicate(Arrays.asList("Alice", "12345", "alice@email.com", "Claimant"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withStatus("Renewal").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        StatusContainsKeywordsPredicate predicate = new StatusContainsKeywordsPredicate(keywords);

        String expected = StatusContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
