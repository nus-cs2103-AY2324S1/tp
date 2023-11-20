package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_TIME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FOURTH_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SIXTH_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.MeetingTime;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class AddMeetingCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final MeetingTime validMeetingTime = new MeetingTime(VALID_MEETING_TIME_AMY);
    @Test
    public void constructor_nullArguments_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddMeetingCommand(null, null));

        // null MeetingTime
        assertThrows(NullPointerException.class, () -> new AddMeetingCommand(
                Index.fromZeroBased(2), null));

        // null Index
        assertThrows(NullPointerException.class, () -> new AddMeetingCommand(null, validMeetingTime));

    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(
                model.getFilteredPersonList().size() + 1); // Last person in typical address book
        AddMeetingCommand addMeetingCommand = new AddMeetingCommand(outOfBoundIndex, validMeetingTime);

        assertCommandFailure(addMeetingCommand, model,
                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndex_success() {
        Person personToAddMeeting = model.getFilteredPersonList().get(INDEX_FOURTH_PERSON.getZeroBased());
        Person personWithMeetingAdded = new PersonBuilder(personToAddMeeting)
                .withMeetingTime(validMeetingTime.toString()).buildClient();

        AddMeetingCommand addMeetingCommand = new AddMeetingCommand(INDEX_FOURTH_PERSON, validMeetingTime);

        String expectedMessage = String.format(AddMeetingCommand.MESSAGE_ADD_MEETING_SUCCESS,
                personWithMeetingAdded.getMeetingTimeString(), INDEX_FOURTH_PERSON.getOneBased());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToAddMeeting, personWithMeetingAdded);

        assertCommandSuccess(addMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_meetingTimeAlreadyPresent_throwsCommandException() {
        // First person already has meeting
        AddMeetingCommand addMeetingCommand = new AddMeetingCommand(INDEX_FIRST_PERSON, validMeetingTime);

        assertCommandFailure(addMeetingCommand, model, AddMeetingCommand.MESSAGE_MEETING_ALREADY_EXISTS);
    }

    @Test
    public void addMeeting_personIsClientWithoutMeeting_success() {
        Person clientToAddMeeting = model.getFilteredPersonList().get(INDEX_FOURTH_PERSON.getZeroBased());
        Person clientWithMeetingAdded = new PersonBuilder(clientToAddMeeting)
                .withMeetingTime(validMeetingTime.toString()).buildClient();

        AddMeetingCommand addMeetingCommand = new AddMeetingCommand(INDEX_FOURTH_PERSON, validMeetingTime);

        try {
            addMeetingCommand.execute(model);
        } catch (Exception e) {
            fail();
        }

        Person expectedClient = model.getFilteredPersonList().get(INDEX_FOURTH_PERSON.getZeroBased());

        assertTrue(expectedClient.isClient());
        assertEquals(expectedClient, clientWithMeetingAdded);
    }

    @Test
    public void addMeeting_personIsLeadWithoutMeeting_success() {
        Person leadToAddMeeting = model.getFilteredPersonList().get(INDEX_SIXTH_PERSON.getZeroBased());
        Person leadWithMeetingAdded = new PersonBuilder(leadToAddMeeting)
                .withMeetingTime(validMeetingTime.toString()).buildLead();

        AddMeetingCommand addMeetingCommand = new AddMeetingCommand(INDEX_SIXTH_PERSON, validMeetingTime);

        try {
            addMeetingCommand.execute(model);
        } catch (Exception e) {
            fail();
        }

        Person expectedLead = model.getFilteredPersonList().get(INDEX_SIXTH_PERSON.getZeroBased());

        assertTrue(expectedLead.isLead());
        assertEquals(expectedLead, leadWithMeetingAdded);
    }

    @Test
    public void equals() {
        AddMeetingCommand addMeetingFirstCommand = new AddMeetingCommand(INDEX_FOURTH_PERSON, validMeetingTime);
        AddMeetingCommand deleteMeetingSecondCommand = new AddMeetingCommand(INDEX_SIXTH_PERSON, validMeetingTime);

        // same object -> returns true
        assertTrue(addMeetingFirstCommand.equals(addMeetingFirstCommand));

        // same values -> returns true
        AddMeetingCommand addMeetingFirstCommandCopy = new AddMeetingCommand(INDEX_FOURTH_PERSON, validMeetingTime);
        assertTrue(addMeetingFirstCommand.equals(addMeetingFirstCommandCopy));

        // different types -> returns false
        assertFalse(addMeetingFirstCommand.equals(1));

        // null -> returns false
        assertFalse(addMeetingFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(addMeetingFirstCommand.equals(deleteMeetingSecondCommand));
    }

    @Test
    public void toStringMethod() {
        AddMeetingCommand addMeetingCommand = new AddMeetingCommand(INDEX_FOURTH_PERSON, validMeetingTime);
        String expectedString = AddMeetingCommand.class.getCanonicalName()
                + "{targetIndex=" + INDEX_FOURTH_PERSON + "}";
        assertTrue(addMeetingCommand.toString().equals(expectedString));
    }
}
