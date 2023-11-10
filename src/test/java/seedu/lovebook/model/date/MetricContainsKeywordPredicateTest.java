package seedu.lovebook.model.date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_INCOME;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_NAME;

import org.junit.jupiter.api.Test;

import seedu.lovebook.logic.parser.Prefix;
import seedu.lovebook.testutil.PersonBuilder;

public class MetricContainsKeywordPredicateTest {

    @Test
    public void equals_name() {
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
    public void equals_income() {
        String keyword1 = "1000";
        String keyword2 = "2000";

        MetricContainsKeywordPredicate firstPredicate = new MetricContainsKeywordPredicate(keyword1, PREFIX_INCOME);
        MetricContainsKeywordPredicate secondPredicate = new MetricContainsKeywordPredicate(keyword2, PREFIX_INCOME);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        MetricContainsKeywordPredicate firstPredicateCopy = new MetricContainsKeywordPredicate(keyword1, PREFIX_INCOME);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(5000.1f));

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
        // MetricContainsKeywordPredicate predicate = new MetricContainsKeywordPredicate(" ", PREFIX_NAME)
        // assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword
        MetricContainsKeywordPredicate predicate = new MetricContainsKeywordPredicate("Carol", PREFIX_NAME);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Keywords match age, gender and height, but does not match name
        predicate = new MetricContainsKeywordPredicate("Alice", PREFIX_NAME);
        assertFalse(predicate.test(new PersonBuilder().withName("Barry").withAge("33")
                .withGender("F").withHeight("124").build()));
    }

    @Test
    public void test_genderContainsKeywords_returnsTrue() {
        // One keyword
        MetricContainsKeywordPredicate predicate = new MetricContainsKeywordPredicate("F", PREFIX_GENDER);
        assertTrue(predicate.test(new PersonBuilder().withGender("F").build()));

        // Mixed-case keywords
        predicate = new MetricContainsKeywordPredicate("f", PREFIX_GENDER);
        assertTrue(predicate.test(new PersonBuilder().withGender("F").build()));
    }

    @Test
    public void test_genderDoesNotContainKeyword_returnsFalse() {
        // Non-matching keyword
        MetricContainsKeywordPredicate predicate = new MetricContainsKeywordPredicate("M", PREFIX_GENDER);
        assertFalse(predicate.test(new PersonBuilder().withGender("F").build()));
    }

    @Test
    public void test_heightContainsKeywords_returnsTrue() {
        // One keyword
        MetricContainsKeywordPredicate predicate = new MetricContainsKeywordPredicate("123", PREFIX_HEIGHT);
        assertTrue(predicate.test(new PersonBuilder().withHeight("123").build()));

        // Mixed-case keywords
        predicate = new MetricContainsKeywordPredicate("123", PREFIX_HEIGHT);
        assertTrue(predicate.test(new PersonBuilder().withHeight("123").build()));
    }

    @Test
    public void test_heightDoesNotContainKeyword_returnsFalse() {
        // Non-matching keyword
        MetricContainsKeywordPredicate predicate = new MetricContainsKeywordPredicate("123", PREFIX_HEIGHT);
        assertFalse(predicate.test(new PersonBuilder().withHeight("124").build()));
    }

    @Test
    public void test_ageContainsKeywords_returnsTrue() {
        MetricContainsKeywordPredicate predicate = new MetricContainsKeywordPredicate("20", PREFIX_AGE);
        assertTrue(predicate.test(new PersonBuilder().withAge("20").build()));
    }

    @Test
    public void test_returnsFalse() {
        MetricContainsKeywordPredicate predicate = new MetricContainsKeywordPredicate("1000",
                new Prefix("random"));
        assertFalse(predicate.test(new PersonBuilder().withName("Ryann").build()));
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
