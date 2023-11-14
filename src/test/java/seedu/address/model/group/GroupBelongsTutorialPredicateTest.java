package seedu.address.model.group;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.GroupBuilder;

public class GroupBelongsTutorialPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "01";
        String secondPredicateKeyword = "02";

        GroupBelongsTutorialPredicate firstPredicate = new GroupBelongsTutorialPredicate(firstPredicateKeyword);
        GroupBelongsTutorialPredicate secondPredicate = new GroupBelongsTutorialPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        GroupBelongsTutorialPredicate firstPredicateCopy = new GroupBelongsTutorialPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different group -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_groupBelongsTutorial_returnsTrue() {
        // One keyword
        GroupBelongsTutorialPredicate predicate = new GroupBelongsTutorialPredicate("01");
        assertTrue(predicate.test(new GroupBuilder().withTutorial("01").build()));
    }

    @Test
    public void test_groupDoesNotBelongTutorial_returnsFalse() {
        // Non-matching tutorial
        GroupBelongsTutorialPredicate predicate = new GroupBelongsTutorialPredicate("02");
        assertFalse(predicate.test(new GroupBuilder().withTutorial("01").build()));
    }

    @Test
    public void toStringMethod() {
        String tutorial = "01";
        GroupBelongsTutorialPredicate predicate = new GroupBelongsTutorialPredicate(tutorial);

        String expected = GroupBelongsTutorialPredicate.class.getCanonicalName() + "{tutorial=" + tutorial + "}";
        assertEquals(expected, predicate.toString());
    }
}
