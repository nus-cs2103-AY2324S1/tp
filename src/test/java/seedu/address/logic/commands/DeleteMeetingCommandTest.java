package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteMeetingCommand}.
 */
public class DeleteMeetingCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDeleteMeeting = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person personWithMeetingDeleted = new PersonBuilder(personToDeleteMeeting)
                .withMeetingTime(null).buildClient(); // First person in typical address book without meeting time

        DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteMeetingCommand.MESSAGE_DELETE_MEETING_SUCCESS,
                personToDeleteMeeting.getMeetingTimeString(), INDEX_FIRST_PERSON.getOneBased());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToDeleteMeeting, personWithMeetingDeleted);

        assertCommandSuccess(deleteMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(
                model.getFilteredPersonList().size() + 1); // Last person in typical address book
        DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(deleteMeetingCommand, model,
                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDeleteMeeting = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person personWithMeetingDeleted = new PersonBuilder(personToDeleteMeeting)
                .withMeetingTime(null).buildClient(); // First person in typical address book without meeting time

        DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteMeetingCommand.MESSAGE_DELETE_MEETING_SUCCESS,
                personToDeleteMeeting.getMeetingTimeString(), INDEX_FIRST_PERSON.getOneBased());

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToDeleteMeeting, personWithMeetingDeleted);

        assertCommandSuccess(deleteMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON; // Second person in typical address book
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(deleteMeetingCommand, model,
                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void deleteMeeting_personIsLead() {
        Index indexFirstLead = Index.fromOneBased(5);
        Person leadToDeleteMeeting = model.getFilteredPersonList().get(indexFirstLead.getZeroBased());
        assertTrue(leadToDeleteMeeting.isLead());

        Person leadWithMeetingDeleted = new PersonBuilder(leadToDeleteMeeting)
                .withMeetingTime(null).buildLead(); // First lead in typical address book without meeting time

        DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(indexFirstLead);

        try {
            deleteMeetingCommand.execute(model);
        } catch (Exception e) {
            fail();
        }

        Person expectedLead = model.getFilteredPersonList().get(indexFirstLead.getZeroBased());

        assertTrue(expectedLead.isLead());
        assertEquals(expectedLead, leadWithMeetingDeleted);
    }

    @Test
    public void deleteMeeting_personNoMeetingTime_returnsPerson() {
        Index indexPersonWithNoMeeting = Index.fromOneBased(4); // Third person in typical address book
        Person personToDeleteMeeting = model.getFilteredPersonList()
                .get(indexPersonWithNoMeeting.getZeroBased());

        assertTrue(personToDeleteMeeting.getMeetingTime().isEmpty());

        Person personWithMeetingDeleted = new PersonBuilder(personToDeleteMeeting)
                .withMeetingTime(null).buildClient();
        assertEquals(personToDeleteMeeting, personWithMeetingDeleted);

        DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(indexPersonWithNoMeeting);

        try {
            deleteMeetingCommand.execute(model);
        } catch (Exception e) {
            fail();
        }

        Person expectedPerson = model.getFilteredPersonList().get(indexPersonWithNoMeeting.getZeroBased());
        assertEquals(expectedPerson, personWithMeetingDeleted);
    }

    @Test
    public void equals() {
        DeleteMeetingCommand deleteMeetingFirstCommand = new DeleteMeetingCommand(INDEX_FIRST_PERSON);
        DeleteMeetingCommand deleteMeetingSecondCommand = new DeleteMeetingCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteMeetingFirstCommand.equals(deleteMeetingFirstCommand));

        // same values -> returns true
        DeleteMeetingCommand deleteMeetingFirstCommandCopy = new DeleteMeetingCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteMeetingFirstCommand.equals(deleteMeetingFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteMeetingFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteMeetingFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteMeetingFirstCommand.equals(deleteMeetingSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(targetIndex);
        String expectedString = DeleteMeetingCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertTrue(deleteMeetingCommand.toString().equals(expectedString));
    }
}
