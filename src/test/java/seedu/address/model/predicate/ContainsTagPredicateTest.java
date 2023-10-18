package seedu.address.model.predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;

public class ContainsTagPredicateTest {
    @Test
    public void equals() {
        Tag firstTag = new Tag("first");
        Tag secondTag = new Tag("second");

        ContainsTagPredicate firstPredicate = new ContainsTagPredicate(firstTag);
        ContainsTagPredicate secondPredicate = new ContainsTagPredicate(secondTag);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ContainsTagPredicate firstPredicateCopy = new ContainsTagPredicate(firstTag);
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
        Tag tag = Tag.create("CS2103T T02");
        ContainsTagPredicate predicate = ContainsTagPredicate.create(tag);

        ContainsTagPredicate expected = new ContainsTagPredicate(tag);
        assertEquals(expected, predicate);
    }

    @Test
    public void toStringMethod() {
        Tag tag = Tag.create("CS2103T T02");
        ContainsTagPredicate predicate = new ContainsTagPredicate(tag);

        assertEquals("Tag Filter: " + tag.getTagName(), predicate.toString());
    }

}
