package seedu.ccacommander.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ccacommander.testutil.TypicalEvents.AURORA_BOREALIS;
import static seedu.ccacommander.testutil.TypicalEvents.BOXING_DAY;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.ccacommander.model.shared.Name;
public class EventInNameCollectionPredicateTest {
    @Test
    public void test_eventInCollection_returnsTrue() {
        EventInNameCollectionPredicate predicate = new EventInNameCollectionPredicate(
                Arrays.asList(AURORA_BOREALIS.getName(), BOXING_DAY.getName()));

        assertTrue(predicate.test(AURORA_BOREALIS));
        assertTrue(predicate.test(BOXING_DAY));
    }

    @Test
    public void test_eventNotInCollection_returnsFalse() {

        EventInNameCollectionPredicate firstPredicate =
                new EventInNameCollectionPredicate(Collections.emptyList());
        assertFalse(firstPredicate.test(AURORA_BOREALIS));


        EventInNameCollectionPredicate secondPredicate =
                new EventInNameCollectionPredicate(Collections.singleton(BOXING_DAY.getName()));
        assertFalse(secondPredicate.test(AURORA_BOREALIS));
    }

    @Test
    public void equals() {
        Set<Name> firstPredicateEventNameSet = Collections.singleton(BOXING_DAY.getName());
        List<Name> secondPredicateEventNameList = Collections.singletonList(BOXING_DAY.getName());
        List<Name> thirdPredicateEventNameList = Collections.singletonList(AURORA_BOREALIS.getName());

        EventInNameCollectionPredicate firstPredicate =
                new EventInNameCollectionPredicate(firstPredicateEventNameSet);
        EventInNameCollectionPredicate secondPredicate =
                new EventInNameCollectionPredicate(secondPredicateEventNameList);
        EventInNameCollectionPredicate thirdPredicate =
                new EventInNameCollectionPredicate(thirdPredicateEventNameList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same events -> returns true
        EventInNameCollectionPredicate firstPredicateCopy =
                new EventInNameCollectionPredicate(firstPredicateEventNameSet);
        assertTrue(firstPredicate.equals(firstPredicateCopy));


        // same events different collection type -> returns true
        assertTrue(firstPredicate.equals(secondPredicate));


        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different events -> returns false
        assertFalse(secondPredicate.equals(thirdPredicate));
    }
}
