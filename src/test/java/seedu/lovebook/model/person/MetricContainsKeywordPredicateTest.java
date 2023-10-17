package seedu.lovebook.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_NAME;

import org.junit.jupiter.api.Test;

import seedu.lovebook.logic.parser.Prefix;
import seedu.lovebook.testutil.PersonBuilder;

public class MetricContainsKeywordPredicateTest {

    @Test
    public void equals() {
        String keyword1 = "first";
        String keyword2 = "second";

        MetricContainsKeywordPredicate firstPredicate = new MetricContainsKeywordPredicate(keyword1, PREFIX_NAME);
        MetricContainsKeywordPredicate secondPredicate = new MetricContainsKeywordPredicate(keyword2, PREFIX_NAME);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        MetricContainsKeywordPredicate firstPredicateCopy = new MetricContainsKeywordPredicate(keyword1, PREFIX_NAME);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different date -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        MetricContainsKeywordPredicate predicate = new MetricContainsKeywordPredicate("Alice", PREFIX_NAME);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").build()));


        // Mixed-case keywords
        predicate = new MetricContainsKeywordPredicate("aLIce", PREFIX_NAME);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        // MetricContainsKeywordPredicate predicate = new MetricContainsKeywordPredicate(" ", PREFIX_NAME);
        //   assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword
        MetricContainsKeywordPredicate predicate = new MetricContainsKeywordPredicate("Carol", PREFIX_NAME);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Keywords match age, gender and lovebook, but does not match name
        predicate = new MetricContainsKeywordPredicate("Alice", PREFIX_AGE);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withAge("33")
                .withGender("F").withHeight("12334").build()));
    }

    @Test
    public void toStringMethod() {
        String keyword = "Alice";
        Prefix metric = PREFIX_NAME;
        MetricContainsKeywordPredicate predicate = new MetricContainsKeywordPredicate(keyword, PREFIX_NAME);
        String expected = MetricContainsKeywordPredicate.class.getCanonicalName()
                + "{keyword=" + keyword + ", prefix=" + metric
                + "}";
        assertEquals(expected, predicate.toString());
    }
}
