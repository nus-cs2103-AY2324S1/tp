package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class RemoveMeetingContactCommandTest {

        private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        @Test
        public void execute_validIndex_success() {
                RemoveMeetingContactCommand rmmcCommand = new RemoveMeetingContactCommand(INDEX_FIRST_MEETING,
                                INDEX_FIRST_MEETING);

                String expectedMessage = String.format(
                                RemoveMeetingContactCommand.MESSAGE_REMOVE_MEETING_CONTACT_SUCCESS,
                                "wan", "too");

                String message = assertDoesNotThrow(() -> rmmcCommand.execute(model)).getFeedbackToUser();

                assertEquals(expectedMessage, message);
        }

        @Test
        public void execute_invalidIndex_throwsCommandException() {
                RemoveMeetingContactCommand rmmcCommand = new RemoveMeetingContactCommand(INDEX_SECOND_MEETING,
                                INDEX_FIRST_MEETING);

                assertThrows(CommandException.class, () -> rmmcCommand.execute(model));
        }

        @Test
        public void equals() {
                RemoveMeetingContactCommand rmmcFirstCommand = new RemoveMeetingContactCommand(
                                INDEX_FIRST_MEETING, INDEX_FIRST_PERSON);
                RemoveMeetingContactCommand rmmcSecondCommand = new RemoveMeetingContactCommand(INDEX_SECOND_MEETING,
                                INDEX_SECOND_PERSON);

                // same object -> returns true
                assertTrue(rmmcFirstCommand.equals(rmmcFirstCommand));

                // same values -> returns true
                RemoveMeetingContactCommand rmmcFirstCommandCopy = new RemoveMeetingContactCommand(INDEX_FIRST_MEETING,
                                INDEX_FIRST_PERSON);
                assertTrue(rmmcFirstCommand.equals(rmmcFirstCommandCopy));

                // different types -> returns false
                assertFalse(rmmcFirstCommand.equals(1));

                // null -> returns false
                assertFalse(rmmcFirstCommand.equals(null));

                // different person -> returns false
                assertFalse(rmmcFirstCommand.equals(rmmcSecondCommand));
        }

        @Test
        public void toStringMethod() {
                RemoveMeetingContactCommand rmmcCommand = new RemoveMeetingContactCommand(INDEX_FIRST_MEETING,
                                INDEX_FIRST_PERSON);
                String expected = RemoveMeetingContactCommand.class.getCanonicalName() + "{meetingIndex="
                                + INDEX_FIRST_MEETING
                                + ", " + "attendeeIndex=" + INDEX_FIRST_PERSON + "}";
                assertEquals(expected, rmmcCommand.toString());
        }
}
