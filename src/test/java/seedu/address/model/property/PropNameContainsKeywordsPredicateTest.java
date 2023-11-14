package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PropertyBuilder;

public class PropNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        PropNameContainsKeywordsPredicate firstPredicate =
                new PropNameContainsKeywordsPredicate(firstPredicateKeywordList);
        PropNameContainsKeywordsPredicate secondPredicate =
                new PropNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PropNameContainsKeywordsPredicate firstPredicateCopy =
                new PropNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different property -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        PropNameContainsKeywordsPredicate predicate =
                new PropNameContainsKeywordsPredicate(Collections.singletonList("Aquavista"));
        assertTrue(predicate.test(new PropertyBuilder().withName("Aquavista Skyview").build()));

        // Multiple keywords
        predicate = new PropNameContainsKeywordsPredicate(Arrays.asList("Aquavista", "Skyview"));
        assertTrue(predicate.test(new PropertyBuilder().withName("Aquavista Skyview").build()));

        // Only one matching keyword
        predicate = new PropNameContainsKeywordsPredicate(Arrays.asList("Skyview", "Horizonview"));
        assertTrue(predicate.test(new PropertyBuilder().withName("Aquavista Horizonview").build()));

        // Mixed-case keywords
        predicate = new PropNameContainsKeywordsPredicate(Arrays.asList("AqUaVisTa", "SkyViEw"));
        assertTrue(predicate.test(new PropertyBuilder().withName("Aquavista Skyview").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PropNameContainsKeywordsPredicate predicate = new PropNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PropertyBuilder().withName("Aquavista").build()));

        // Non-matching keyword
        predicate = new PropNameContainsKeywordsPredicate(Arrays.asList("Horizonview"));
        assertFalse(predicate.test(new PropertyBuilder().withName("Aquavista Skyview").build()));

        // Keywords match phone, address and price, but does not match name
        predicate = new PropNameContainsKeywordsPredicate(Arrays.asList("Horizonview", "Main", "Street"));
        assertFalse(predicate.test(new PropertyBuilder().withName("Aquavista").withPhone("87612345")
                .withAddress("123 Orchid Lane, Singapore 456789").withPrice("123456").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        PropNameContainsKeywordsPredicate predicate = new PropNameContainsKeywordsPredicate(keywords);

        String expected = PropNameContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
