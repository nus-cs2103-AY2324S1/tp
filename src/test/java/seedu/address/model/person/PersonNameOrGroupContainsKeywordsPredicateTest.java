package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonNameOrGroupContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        PersonNameOrGroupContainsKeywordsPredicate firstPredicate =
                new PersonNameOrGroupContainsKeywordsPredicate(firstPredicateKeywordList);
        PersonNameOrGroupContainsKeywordsPredicate secondPredicate =
                new PersonNameOrGroupContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonNameOrGroupContainsKeywordsPredicate firstPredicateCopy =
                new PersonNameOrGroupContainsKeywordsPredicate(firstPredicateKeywordList);
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
        PersonNameOrGroupContainsKeywordsPredicate predicate =
                new PersonNameOrGroupContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new PersonNameOrGroupContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new PersonNameOrGroupContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new PersonNameOrGroupContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_groupContainsKeywords_returnsTrue() {
        // One keyword
        PersonNameOrGroupContainsKeywordsPredicate predicate =
                new PersonNameOrGroupContainsKeywordsPredicate(Collections.singletonList("friends"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").withGroups("friends").build()));

        // Multiple keywords
        predicate = new PersonNameOrGroupContainsKeywordsPredicate(Arrays.asList("friends", "classmates"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob")
                .withGroups("friends", "classmates").build()));

        // Only one matching keyword
        predicate = new PersonNameOrGroupContainsKeywordsPredicate(Arrays.asList("friends", "family"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").withGroups("friends").build()));

        // Mixed-case keywords
        predicate = new PersonNameOrGroupContainsKeywordsPredicate(Arrays.asList("fRiENds", "cLAsSmaTes"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob")
                .withGroups("friends", "classmates").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PersonNameOrGroupContainsKeywordsPredicate predicate =
                new PersonNameOrGroupContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new PersonNameOrGroupContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new PersonNameOrGroupContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }

    @Test
    public void test_groupDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PersonNameOrGroupContainsKeywordsPredicate predicate =
                new PersonNameOrGroupContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new PersonNameOrGroupContainsKeywordsPredicate(Arrays.asList("family"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").withGroups("friends").build()));

        // Keywords match phone, email and address, but does not match group
        predicate = new PersonNameOrGroupContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withGroups("friends").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        PersonNameOrGroupContainsKeywordsPredicate predicate = new PersonNameOrGroupContainsKeywordsPredicate(keywords);

        String expected = PersonNameOrGroupContainsKeywordsPredicate.class.getCanonicalName()
                + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
