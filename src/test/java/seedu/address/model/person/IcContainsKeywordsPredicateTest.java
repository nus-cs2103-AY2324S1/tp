package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class IcContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        IcContainsKeywordsPredicate firstPredicate = new IcContainsKeywordsPredicate(firstPredicateKeywordList);
        IcContainsKeywordsPredicate secondPredicate = new IcContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        IcContainsKeywordsPredicate firstPredicateCopy = new IcContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_IcContainsKeywords_returnsTrue() {
        // One keyword
        IcContainsKeywordsPredicate predicate = new IcContainsKeywordsPredicate(Collections.singletonList("T1234567Q"));
        assertTrue(predicate.test(new PersonBuilder().withIc("T1234567Q").build()));

        // Small case
        predicate = new IcContainsKeywordsPredicate(Collections.singletonList("t4567123q"));
        assertTrue(predicate.test(new PersonBuilder().withIc("T4567123Q").build()));

        // Mixed case
        predicate = new IcContainsKeywordsPredicate(Collections.singletonList("T1723456q"));
        assertTrue(predicate.test(new PersonBuilder().withIc("T1723456Q").build()));
    }

    @Test
    public void test_IcDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        IcContainsKeywordsPredicate predicate = new IcContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withIc("Alice").build()));

        // Non-matching keyword
        predicate = new IcContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PersonBuilder().withIc("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new IcContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withIc("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        IcContainsKeywordsPredicate predicate = new IcContainsKeywordsPredicate(keywords);

        String expected = IcContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
