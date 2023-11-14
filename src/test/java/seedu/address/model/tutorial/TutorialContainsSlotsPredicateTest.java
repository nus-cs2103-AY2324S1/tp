package seedu.address.model.tutorial;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class TutorialContainsSlotsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateSlotList = Collections.singletonList("01");
        List<String> secondPredicateSlotList = Arrays.asList("01", "02");

        TutorialContainsSlotsPredicate firstPredicate = new TutorialContainsSlotsPredicate(firstPredicateSlotList);
        TutorialContainsSlotsPredicate secondPredicate = new TutorialContainsSlotsPredicate(secondPredicateSlotList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TutorialContainsSlotsPredicate firstPredicateCopy = new TutorialContainsSlotsPredicate(firstPredicateSlotList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tutorialContainsSlots_returnsTrue() {
        // One slot
        TutorialContainsSlotsPredicate predicate = new TutorialContainsSlotsPredicate(Collections.singletonList("01"));
        assertTrue(predicate.test(new PersonBuilder().withTutorials("01", "02").build()));

        // Multiple slots
        predicate = new TutorialContainsSlotsPredicate(Arrays.asList("01", "02"));
        assertTrue(predicate.test(new PersonBuilder().withTutorials("01", "02").build()));

        // Only one matching slot
        predicate = new TutorialContainsSlotsPredicate(Arrays.asList("02", "03"));
        assertTrue(predicate.test(new PersonBuilder().withTutorials("01", "03").build()));
    }

    @Test
    public void test_tutorialDoesNotContainSlots_returnsFalse() {
        // Non-matching slot
        TutorialContainsSlotsPredicate predicate = new TutorialContainsSlotsPredicate(Arrays.asList("03"));
        assertFalse(predicate.test(new PersonBuilder().withTutorials("01", "02").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> slots = List.of("01", "02");
        TutorialContainsSlotsPredicate predicate = new TutorialContainsSlotsPredicate(slots);

        String expected = TutorialContainsSlotsPredicate.class.getCanonicalName() + "{slots=" + slots + "}";
        assertEquals(expected, predicate.toString());
    }
}
