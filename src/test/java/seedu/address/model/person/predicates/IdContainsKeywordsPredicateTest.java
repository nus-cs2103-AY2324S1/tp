package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class IdContainsKeywordsPredicateTest {
    private static final String QUERY_NRIC_1 = "T0123456I";
    private static final String QUERY_NRIC_2 = "T2468101Z";
    private static final List<String> FIRST_KEYWORD_LIST = Collections.singletonList(QUERY_NRIC_1);
    private static final List<String> SECOND_KEYWORD_LIST = Arrays.asList(QUERY_NRIC_1, QUERY_NRIC_2);

    @Test
    public void equals() {
        IdContainsKeywordsPredicate firstPredicate = new IdContainsKeywordsPredicate(FIRST_KEYWORD_LIST);
        IdContainsKeywordsPredicate secondPredicate = new IdContainsKeywordsPredicate(SECOND_KEYWORD_LIST);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        IdContainsKeywordsPredicate firstPredicateCopy = new IdContainsKeywordsPredicate(FIRST_KEYWORD_LIST);
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
        assertEquals(Objects.hash(PREFIX_NRIC, FIRST_KEYWORD_LIST),
                new IdContainsKeywordsPredicate(FIRST_KEYWORD_LIST).hashCode());
        assertEquals(Objects.hash(PREFIX_NRIC, SECOND_KEYWORD_LIST),
                new IdContainsKeywordsPredicate(SECOND_KEYWORD_LIST).hashCode());
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // Exact Match
        IdContainsKeywordsPredicate predicate = new IdContainsKeywordsPredicate(FIRST_KEYWORD_LIST);
        assertTrue(predicate.test(new PersonBuilder().withNric(QUERY_NRIC_1).build()));

        // Lower-case keywords
        predicate = new IdContainsKeywordsPredicate(Collections.singletonList(QUERY_NRIC_1.toLowerCase()));
        assertTrue(predicate.test(new PersonBuilder().withNric(QUERY_NRIC_1).build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        IdContainsKeywordsPredicate predicate = new IdContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withNric(QUERY_NRIC_1).build()));

        // Non-matching keyword
        predicate = new IdContainsKeywordsPredicate(FIRST_KEYWORD_LIST);
        assertFalse(predicate.test(new PersonBuilder().withName(QUERY_NRIC_2).build()));

        // Keywords match phone, email and address, but does not match nric
        predicate = new IdContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withNric(QUERY_NRIC_1).withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        IdContainsKeywordsPredicate predicate = new IdContainsKeywordsPredicate(keywords);

        String expected = IdContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
