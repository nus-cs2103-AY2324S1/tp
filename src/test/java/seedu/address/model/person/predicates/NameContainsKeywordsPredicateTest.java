package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class NameContainsKeywordsPredicateTest {
    private static final String QUERY_NAME_1 = "Alice";
    private static final String QUERY_NAME_2 = "Bob";
    private static final String QUERY_NAME_3 = "Carol";
    private static final List<String> FIRST_KEYWORD_LIST = Collections.singletonList(QUERY_NAME_1);
    private static final List<String> SECOND_KEYWORD_LIST = Arrays.asList(QUERY_NAME_1, QUERY_NAME_2);

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate = new NameContainsKeywordsPredicate(FIRST_KEYWORD_LIST);
        NameContainsKeywordsPredicate secondPredicate = new NameContainsKeywordsPredicate(SECOND_KEYWORD_LIST);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeywordsPredicate firstPredicateCopy = new NameContainsKeywordsPredicate(FIRST_KEYWORD_LIST);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void hashCodeTest() {
        assertEquals(Objects.hash(PREFIX_NAME, FIRST_KEYWORD_LIST),
                new NameContainsKeywordsPredicate(FIRST_KEYWORD_LIST).hashCode());
        assertEquals(Objects.hash(PREFIX_NAME, SECOND_KEYWORD_LIST),
                new NameContainsKeywordsPredicate(SECOND_KEYWORD_LIST).hashCode());
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(FIRST_KEYWORD_LIST);
        assertTrue(predicate.test(new PersonBuilder().withName(QUERY_NAME_1 + " " + QUERY_NAME_2).build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate(SECOND_KEYWORD_LIST);
        assertTrue(predicate.test(new PersonBuilder().withName(QUERY_NAME_1 + " " + QUERY_NAME_2).build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList(QUERY_NAME_2, QUERY_NAME_3));
        assertTrue(predicate.test(new PersonBuilder().withName(QUERY_NAME_1 + " " + QUERY_NAME_3).build()));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate(
                Arrays.asList(QUERY_NAME_1.toUpperCase(), QUERY_NAME_2.toLowerCase()));
        assertTrue(predicate.test(new PersonBuilder().withName(QUERY_NAME_1 + " " + QUERY_NAME_2).build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withName(QUERY_NAME_1).build()));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate(Collections.singletonList(QUERY_NAME_3));
        assertFalse(predicate.test(new PersonBuilder().withName(QUERY_NAME_1 + " " + QUERY_NAME_2).build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName(QUERY_NAME_1).withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(keywords);

        String expected = NameContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
