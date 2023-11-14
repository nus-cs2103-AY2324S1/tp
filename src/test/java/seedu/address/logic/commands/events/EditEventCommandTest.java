package seedu.address.logic.commands.events;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BIRTHDAY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EVENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.contacts.ClearContactsCommand;
import seedu.address.logic.commands.events.EditEventCommand.EditEventDescriptor;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditEventCommand.
 */
public class EditEventCommandTest {
    @Test
    public void equals() {
        final EditEventCommand standardCommand = new EditEventCommand(INDEX_FIRST_EVENT, DESC_BIRTHDAY);

        // same values -> returns true
        EditEventDescriptor copyDescriptor = new EditEventDescriptor(DESC_BIRTHDAY);
        EditEventCommand commandWithSameValues = new EditEventCommand(INDEX_FIRST_EVENT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearContactsCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditEventCommand(INDEX_SECOND_EVENT, DESC_BIRTHDAY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditEventCommand(INDEX_FIRST_EVENT, DESC_MEETING)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditEventDescriptor editEventDescriptor = new EditEventDescriptor();
        EditEventCommand editEventCommand = new EditEventCommand(index, editEventDescriptor);
        String expected = EditEventCommand.class.getCanonicalName() + "{index=" + index + ", editEventDescriptor="
                + editEventDescriptor + "}";
        assertEquals(expected, editEventCommand.toString());
    }
}
