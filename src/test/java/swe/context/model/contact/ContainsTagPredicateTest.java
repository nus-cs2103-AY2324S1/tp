package swe.context.model.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import swe.context.testutil.ContactBuilder;


public class ContainsTagPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "friend";
        String secondPredicateKeyword = "colleague";

        ContainsTagPredicate firstPredicate = new ContainsTagPredicate(firstPredicateKeyword);
        ContainsTagPredicate secondPredicate = new ContainsTagPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ContainsTagPredicate firstPredicateCopy = new ContainsTagPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different contact -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One tag
        ContainsTagPredicate predicate = new ContainsTagPredicate("friend");
        assertTrue(predicate.test(new ContactBuilder().withTags("friend").build()));

        // Mixed-case tag
        predicate = new ContainsTagPredicate("Friend");
        assertTrue(predicate.test(new ContactBuilder().withTags("friend").build()));

        // Multiple tags
        predicate = new ContainsTagPredicate("friend");
        assertTrue(predicate.test(new ContactBuilder().withTags("friend", "colleague").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Non-matching tag
        ContainsTagPredicate predicate = new ContainsTagPredicate("colleague");
        assertFalse(predicate.test(new ContactBuilder().withTags("friend").build()));

        // Multiple non-matching tags
        predicate = new ContainsTagPredicate("colleague");
        assertFalse(predicate.test(new ContactBuilder().withTags("friend", "student").build()));
    }

    @Test
    public void toStringMethod() {
        String keyword = "keyword1";
        ContainsTagPredicate predicate = new ContainsTagPredicate(keyword);

        String expected = ContainsTagPredicate.class.getCanonicalName() + "{keyword=" + keyword + "}";
        assertEquals(expected, predicate.toString());
    }
}
