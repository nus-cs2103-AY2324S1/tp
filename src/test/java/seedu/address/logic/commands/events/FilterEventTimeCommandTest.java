package seedu.address.logic.commands.events;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.parser.DateTimeParser.parseDateTimeInstance;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventTimeBeforePredicate;

public class FilterEventTimeCommandTest {
    @Test
    public void equals() {
        EventTimeBeforePredicate predicate1 = null;
        EventTimeBeforePredicate predicate2 = null;
        try {
            predicate1 = new EventTimeBeforePredicate(parseDateTimeInstance("23-01-2023"));
            predicate2 = new EventTimeBeforePredicate(parseDateTimeInstance("24-01-2023"));
        } catch (ParseException e) {
            fail();
        }

        FilterEventTimeCommand command1 = new FilterEventTimeCommand(predicate1);
        FilterEventTimeCommand command2 = new FilterEventTimeCommand(predicate1);
        FilterEventTimeCommand command3 = new FilterEventTimeCommand(predicate2);

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
        EventTimeBeforePredicate predicate = null;
        try {
            predicate = new EventTimeBeforePredicate(parseDateTimeInstance("23-01-2023"));
        } catch (ParseException e) {
            fail();
        }
        FilterEventTimeCommand command = new FilterEventTimeCommand(predicate);
        String expected = FilterEventTimeCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, command.toString());
    }
}

