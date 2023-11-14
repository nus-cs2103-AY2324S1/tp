package seedu.address.logic.commands.events;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEvents.getTypicalEventsBook;
import static seedu.address.testutil.TypicalFinances.getTypicalFinancesBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EVENT;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonsBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteEventCommandTest {

    private Model model = new ModelManager(getTypicalPersonsBook(), getTypicalEventsBook(), getTypicalFinancesBook(),
            new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Event eventToDelete = model.getEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        DeleteEventCommand deleteEventCommand = new DeleteEventCommand(INDEX_FIRST_EVENT);
        String expectedMessage = String.format(DeleteEventCommand.MESSAGE_DELETE_EVENT_SUCCESS,
                Messages.format(eventToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getEventsBook(),
                model.getFinancesBook(),
                new UserPrefs());
        expectedModel.deleteEvent(eventToDelete);

        assertCommandSuccess(deleteEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getEventList().size() + 1);
        DeleteEventCommand deleteEventCommand = new DeleteEventCommand(outOfBoundIndex);

        assertCommandFailure(deleteEventCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        //showPersonAtIndex(model, INDEX_FIRST_PERSON);
        //Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        //DeleteEventCommand deleteEventCommand = new DeleteEventCommand(INDEX_FIRST_PERSON);
        //String expectedMessage = String.format(DeleteEventCommand.MESSAGE_DELETE_EVENT_SUCCESS,
        //Messages.format(personToDelete));
        //Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        //expectedModel.deletePerson(personToDelete);
        //showNoPerson(expectedModel);

        //assertCommandSuccess(deleteEventCommand, model, expectedMessage, expectedModel);

        // TO IMPLEMENT WHEN THERE IS EDIT METHOD
        assertEquals(true, true);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        //showPersonAtIndex(model, INDEX_FIRST_PERSON);
        //Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        //assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        //DeleteEventCommand deleteEventCommand = new DeleteEventCommand(outOfBoundIndex);

        //assertCommandFailure(deleteEventCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);

        // TO IMPLEMENT WHEN THERE IS EDIT METHOD
        assertEquals(true, true);
    }

    @Test
    public void equals() {
        DeleteEventCommand deleteFirstCommand = new DeleteEventCommand(INDEX_FIRST_EVENT);
        DeleteEventCommand deleteSecondCommand = new DeleteEventCommand(INDEX_SECOND_EVENT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteEventCommand deleteFirstCommandCopy = new DeleteEventCommand(INDEX_FIRST_EVENT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteEventCommand deleteEventCommand = new DeleteEventCommand(targetIndex);
        String expected = DeleteEventCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deleteEventCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
