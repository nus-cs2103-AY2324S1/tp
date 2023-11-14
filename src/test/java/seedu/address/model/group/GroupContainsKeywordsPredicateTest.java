package seedu.address.model.group;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.GroupBuilder;

public class GroupContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("1");
        List<String> secondPredicateKeywordList = Arrays.asList("1", "2");

        GroupContainsKeywordsPredicate firstPredicate = new GroupContainsKeywordsPredicate(firstPredicateKeywordList);
        GroupContainsKeywordsPredicate secondPredicate = new GroupContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        GroupContainsKeywordsPredicate firstPredicateCopy =
                new GroupContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different group -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_groupContainsKeywords_returnsTrue() {
        // one keyword
        GroupContainsKeywordsPredicate predicate = new GroupContainsKeywordsPredicate(Collections.singletonList("1"));
        assertTrue(predicate.test(new GroupBuilder().withNumber("1").build()));

        // Only one matching keyword
        predicate = new GroupContainsKeywordsPredicate(Arrays.asList("1", "2"));
        assertTrue(predicate.test(new GroupBuilder().withNumber("1").build()));
    }

    @Test
    public void test_groupDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        GroupContainsKeywordsPredicate predicate = new GroupContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new GroupBuilder().withNumber("1").build()));

        // Non-matching keyword
        predicate = new GroupContainsKeywordsPredicate(Arrays.asList("2"));
        assertFalse(predicate.test(new GroupBuilder().withNumber("1").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("1", "2");
        GroupContainsKeywordsPredicate predicate = new GroupContainsKeywordsPredicate(keywords);

        String expected = GroupContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
