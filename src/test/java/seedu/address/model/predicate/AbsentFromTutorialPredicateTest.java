package seedu.address.model.predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.tag.Tag;

public class AbsentFromTutorialPredicateTest {
    @Test
    public void equals() {
        Tag firstTag = new Tag("first");
        Tag secondTag = new Tag("second");
        Index firstIndex = Index.fromZeroBased(1);
        Index secondIndex = Index.fromZeroBased(2);

        AbsentFromTutorialPredicate firstPredicate = new AbsentFromTutorialPredicate(firstIndex, firstTag);
        AbsentFromTutorialPredicate secondPredicate = new AbsentFromTutorialPredicate(secondIndex, secondTag);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AbsentFromTutorialPredicate firstPredicateCopy = new AbsentFromTutorialPredicate(firstIndex, firstTag);
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
        Index index = Index.fromZeroBased(0);
        AbsentFromTutorialPredicate predicate = AbsentFromTutorialPredicate.create(index, tag);

        AbsentFromTutorialPredicate expected = new AbsentFromTutorialPredicate(index, tag);
        assertEquals(expected, predicate);
    }

    @Test
    public void toStringMethod() {
        Tag tag = Tag.create("CS2103T T02");
        Index index = Index.fromZeroBased(0);
        AbsentFromTutorialPredicate predicate = AbsentFromTutorialPredicate.create(index, tag);

        assertEquals("Attendance Filter: " + index + " " + tag.getTagName(), predicate.toString());
    }
}
