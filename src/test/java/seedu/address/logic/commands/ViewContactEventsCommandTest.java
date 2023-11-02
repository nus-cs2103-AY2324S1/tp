package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ViewContactEventsCommand.MESSAGE_VIEW_EVENTS_SUCCESS;
import static seedu.address.testutil.TypicalEvents.getTypicalCalendar;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskManager;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

class ViewContactEventsCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalCalendar(), getTypicalTaskManager(),
            new UserPrefs());

    @Test
    public void execute_validViewContactEvents_success() {
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getCalendar(),
                model.getTaskManager(), new UserPrefs());
        Index validIndex = Index.fromOneBased(1);
        String expectedMessage = MESSAGE_VIEW_EVENTS_SUCCESS;
        ViewContactEventsCommand viewContactEventsCommand = new ViewContactEventsCommand(validIndex);

        assertCommandSuccess(viewContactEventsCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_inValidIndex_throwsCommandException() {
        Index invalidIndex = Index.fromOneBased(999);
        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
        ViewContactEventsCommand viewContactEventsCommand = new ViewContactEventsCommand(invalidIndex);

        assertCommandFailure(viewContactEventsCommand, model, expectedMessage);
    }

    @Test
    public void equalsMethod() {
        ViewContactEventsCommand viewFirstCommand = new ViewContactEventsCommand(Index.fromOneBased(1));
        ViewContactEventsCommand viewSecondCommand = new ViewContactEventsCommand(Index.fromOneBased(2));

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewContactEventsCommand viewFirstCommandCopy = new ViewContactEventsCommand(Index.fromOneBased(1));
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }
}
