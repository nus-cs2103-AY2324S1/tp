package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalEvents.LAUNCH;
import static seedu.address.testutil.TypicalEvents.TRAINING;
import static seedu.address.testutil.TypicalEvents.getTypicalCalendar;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskManager;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;

public class DeleteContactEventCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalCalendar(),
            getTypicalTaskManager(), new UserPrefs());

    @Test
    public void execute_deleteEvent_success() {
        Person person = ALICE;
        Event event = LAUNCH;
        LocalDateTime eventTime = event.getStartDateTime();
        DeleteContactEventCommand deleteContactEventCommand = new DeleteContactEventCommand(INDEX_FIRST_PERSON,
                eventTime);

        String expectedMessage = String.format(DeleteContactEventCommand.MESSAGE_DELETE_EVENT_FROM_PERSON_SUCCESS,
                person.getName(), Messages.format(event));
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                model.getCalendar(), model.getTaskManager(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), person);

        assertCommandSuccess(deleteContactEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteContactEventCommand deleteContactEventCommand = new DeleteContactEventCommand(outOfBoundIndex,
                LAUNCH.getStartDateTime());

        assertCommandFailure(deleteContactEventCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Add event to filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DeleteContactEventCommand deleteContactEventCommand = new DeleteContactEventCommand(outOfBoundIndex,
                LAUNCH.getStartDateTime());

        assertCommandFailure(deleteContactEventCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final DeleteContactEventCommand standardCommand = new DeleteContactEventCommand(
                INDEX_FIRST_PERSON,
                LAUNCH.getStartDateTime());

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(
                new DeleteContactEventCommand(
                        INDEX_SECOND_PERSON,
                        LAUNCH.getStartDateTime())));

        // different event -> returns false
        assertFalse(standardCommand.equals(
                new DeleteContactEventCommand(
                        INDEX_FIRST_PERSON,
                        TRAINING.getStartDateTime())));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        LocalDateTime eventTime = TRAINING.getStartDateTime();
        DeleteContactEventCommand deleteContactEventCommand = new DeleteContactEventCommand(index, eventTime);
        String expected = DeleteContactEventCommand.class.getCanonicalName() + "{index=" + index + ", eventTime="
                + eventTime + "}";
        assertEquals(expected, deleteContactEventCommand.toString());
    }
}
