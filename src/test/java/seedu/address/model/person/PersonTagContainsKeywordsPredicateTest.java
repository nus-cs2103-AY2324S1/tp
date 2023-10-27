package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonTagContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        PersonTagContainsKeywordsPredicate firstPredicate =
                new PersonTagContainsKeywordsPredicate(firstPredicateKeywordList);
        PersonTagContainsKeywordsPredicate secondPredicate =
                new PersonTagContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonTagContainsKeywordsPredicate firstPredicateCopy =
                new PersonTagContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_personTagContainsKeywords_returnsTrue() {
        // One keyword
        PersonTagContainsKeywordsPredicate predicate =
                new PersonTagContainsKeywordsPredicate(Collections.singletonList("VIP"));
        assertTrue(predicate.test(new PersonBuilder().withTags("VIP").build()));

        // Multiple keywords
        predicate = new PersonTagContainsKeywordsPredicate(Arrays.asList("VIP", "Family"));
        assertTrue(predicate.test(new PersonBuilder().withTags("VIP", "Family").build()));

        // Only one matching keyword
        predicate = new PersonTagContainsKeywordsPredicate(Arrays.asList("VIP", "Family"));
        assertTrue(predicate.test(new PersonBuilder().withTags("Friend", "VIP").build()));

        // Mixed-case keywords
        predicate = new PersonTagContainsKeywordsPredicate(Arrays.asList("vip", "Family"));
        assertTrue(predicate.test(new PersonBuilder().withTags("VIP", "family").build()));
    }

    @Test
    public void test_personTagDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PersonTagContainsKeywordsPredicate predicate = new PersonTagContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withTags("VIP").build()));

        // Non-matching keyword
        predicate = new PersonTagContainsKeywordsPredicate(Arrays.asList("Friend"));
        assertFalse(predicate.test(new PersonBuilder().withTags("VIP", "Family").build()));

        // Keywords match others
        predicate = new PersonTagContainsKeywordsPredicate(
                Arrays.asList("Alice", "12345", "alice@email.com", "Renewal", "Friend"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withStatus("Renewal").withTags("Family").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        PersonTagContainsKeywordsPredicate predicate = new PersonTagContainsKeywordsPredicate(keywords);

        String expected = PersonTagContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
