package seedu.address.logic.commands.events;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.event.EventNameContainsKeywordsPredicate;



public class FilterEventNameCommandTest {
    @Test
    public void equals() {
        EventNameContainsKeywordsPredicate predicate1 =
                new EventNameContainsKeywordsPredicate(Arrays.asList("meeting"));
        EventNameContainsKeywordsPredicate predicate2 =
                new EventNameContainsKeywordsPredicate(Arrays.asList("conference"));

        FilterEventNameCommand command1 = new FilterEventNameCommand(predicate1);
        FilterEventNameCommand command2 = new FilterEventNameCommand(predicate1);
        FilterEventNameCommand command3 = new FilterEventNameCommand(predicate2);

        // same object -> returns true
        assertTrue(command1.equals(command1));

        // same values -> returns true
        assertTrue(command1.equals(command2));

        // different types -> returns false
        assertFalse(command1.equals(1));

        // null -> returns false
        assertFalse(command1.equals(null));

        // different predicate -> returns false
        assertFalse(command1.equals(command3));
    }

    @Test
    public void toStringMethod() {
        EventNameContainsKeywordsPredicate predicate = new EventNameContainsKeywordsPredicate(Arrays.asList("meeting"));
        FilterEventNameCommand command = new FilterEventNameCommand(predicate);
        String expected = FilterEventNameCommand.class.getCanonicalName() + "{namePredicate=" + predicate + "}";
        assertEquals(expected, command.toString());
    }
}
