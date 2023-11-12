package seedu.address.model.person;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.PersonBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StatusContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameContainsKeywordsPredicate firstPredicate = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        NameContainsKeywordsPredicate secondPredicate = new NameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeywordsPredicate firstPredicateCopy = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
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
        StatusContainsKeywordsPredicate predicate = new StatusContainsKeywordsPredicate(Collections.singletonList("interviewed"));
        assertTrue(predicate.test(new PersonBuilder().withStatus("interviewed").build()));

        // Only one matching keyword
        predicate = new StatusContainsKeywordsPredicate(Arrays.asList("interviewed", "rejected"));
        assertTrue(predicate.test(new PersonBuilder().withStatus("rejected").build()));

        // Mixed case keywords
        predicate = new StatusContainsKeywordsPredicate(Arrays.asList("inTerVieWed", "rejeCted"));
        assertTrue(predicate.test(new PersonBuilder().withStatus("rejected").build()));
    }

    @Test
    public void test_statusDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        StatusContainsKeywordsPredicate predicate = new StatusContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withStatus("interviewed").build()));

        // Non-matching keyword
        predicate = new StatusContainsKeywordsPredicate(Arrays.asList("Interviewed"));
        assertFalse(predicate.test(new PersonBuilder().withStatus("rejected").build()));

        // Keywords match name, phone, email, address and tag but does not match status
        predicate = new StatusContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street",
                "intern"));
        assertFalse(predicate.test(new PersonBuilder()
                .withName("Alice")
                .withPhone("12345")
                .withEmail("alice@email.com")
                .withAddress("Main Street")
                .withTags("intern").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        StatusContainsKeywordsPredicate predicate = new StatusContainsKeywordsPredicate(keywords);

        String expected = StatusContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }


}
