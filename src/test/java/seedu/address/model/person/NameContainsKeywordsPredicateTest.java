package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.developer.NameDeveloperContainsKeywordsPredicate;
import seedu.address.testutil.DeveloperBuilder;

public class NameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameDeveloperContainsKeywordsPredicate firstPredicate =
                new NameDeveloperContainsKeywordsPredicate(firstPredicateKeywordList);
        NameDeveloperContainsKeywordsPredicate secondPredicate =
                new NameDeveloperContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        NameDeveloperContainsKeywordsPredicate firstPredicateCopy =
                new NameDeveloperContainsKeywordsPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertNotEquals(1, firstPredicate);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different developer -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }


    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameDeveloperContainsKeywordsPredicate predicate =
                new NameDeveloperContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new DeveloperBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new NameDeveloperContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new DeveloperBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new NameDeveloperContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new DeveloperBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new NameDeveloperContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new DeveloperBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameDeveloperContainsKeywordsPredicate predicate =
                new NameDeveloperContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new DeveloperBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new NameDeveloperContainsKeywordsPredicate(List.of("Carol"));
        assertFalse(predicate.test(new DeveloperBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new NameDeveloperContainsKeywordsPredicate(Arrays.asList("12345454", "alice@email.com",
                "Main", "Street"));
        assertFalse(predicate.test(new DeveloperBuilder().withName("Alice").withPhone("12345454")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        NameDeveloperContainsKeywordsPredicate predicate = new NameDeveloperContainsKeywordsPredicate(keywords);

        String expected = NameDeveloperContainsKeywordsPredicate.class.getCanonicalName() + "{keywords="
                + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
