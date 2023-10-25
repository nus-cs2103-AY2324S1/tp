package seedu.address.model.predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class ContainsTagPredicateTest {

    @Test
    public void test_idContainsKeywords_returnsTrue() {
        Tag tag = new Tag("first");

        ContainsTagPredicate predicate = new ContainsTagPredicate(Optional.of(tag));
        assertTrue(predicate.test(new PersonBuilder().withTags("first", "second").build()));
    }

    @Test
    public void test_idDoesNotContainKeywords_returnsFalse() {
        Tag tag = new Tag("first");

        // Non-matching tag
        ContainsTagPredicate predicate = new ContainsTagPredicate(Optional.of(tag));
        assertFalse(predicate.test(new PersonBuilder().withTags("second", "third").build()));
    }

    @Test
    public void equals() {
        Tag firstTag = new Tag("first");
        Tag secondTag = new Tag("second");

        ContainsTagPredicate firstPredicate = new ContainsTagPredicate(Optional.of(firstTag));
        ContainsTagPredicate secondPredicate = new ContainsTagPredicate(Optional.of(secondTag));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ContainsTagPredicate firstPredicateCopy = new ContainsTagPredicate(Optional.of(firstTag));
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void create() {
        Tag tag = Tag.create("T02");
        ContainsTagPredicate predicate = ContainsTagPredicate.create(Optional.of(tag));

        ContainsTagPredicate expected = new ContainsTagPredicate(Optional.of(tag));
        assertEquals(expected, predicate);
    }

    @Test
    public void toStringMethod() {
        Tag tag = Tag.create("T02");
        ContainsTagPredicate predicate = new ContainsTagPredicate(Optional.of(tag));

        assertEquals("Tag Filter: " + tag.getTagName(), predicate.toString());
    }
}
