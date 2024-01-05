package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalConsultations.getTypicalConsultationListBook;
import static seedu.address.testutil.TypicalGradedTest.getTypicalGradedTestList;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalSessions.getTypicalSessionList;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionNumber;
import seedu.address.testutil.TypicalSessions;

public class DeleteSessionCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList(),
            getTypicalSessionList(), getTypicalConsultationListBook(), getTypicalGradedTestList());

    @Test
    public void execute_validSessionNumber_success() {
        Session sessionToDelete = model.findSessionBySessionNumber(TypicalSessions.SESSION_TYPICAL1.getSessionNumber());
        DeleteSessionCommand deleteSessionCommand = new DeleteSessionCommand(
                TypicalSessions.SESSION_TYPICAL1.getSessionNumber());

        String expectedMessage = String.format(DeleteSessionCommand.MESSAGE_DELETE_SESSION_SUCCESS,
                Messages.format(sessionToDelete));

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList(),
                getTypicalSessionList(), getTypicalConsultationListBook(), getTypicalGradedTestList());

        expectedModel.deleteSession(sessionToDelete);

        assertCommandSuccess(deleteSessionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidSessionNumber_throwsSessionNotFoundException() {
        SessionNumber targetSessionNumber = new SessionNumber("12312");
        DeleteSessionCommand deleteSessionCommand = new DeleteSessionCommand(targetSessionNumber);

        assertCommandFailure(deleteSessionCommand, model, Messages.MESSAGE_SESSION_NOT_FOUND);
    }

    @Test
    public void getCommandType() {
        SessionNumber targetSessionNumber = new SessionNumber("12312");
        DeleteSessionCommand command = new DeleteSessionCommand(targetSessionNumber);

        assertEquals(command.getCommandType(), CommandType.DELETE_SESSION);
    }

    @Test
    public void equals() {
        DeleteSessionCommand deleteFirstCommand = new DeleteSessionCommand(
                TypicalSessions.SESSION_TYPICAL1.getSessionNumber());
        DeleteSessionCommand deleteSecondCommand = new DeleteSessionCommand(
                TypicalSessions.SESSION_TYPICAL2.getSessionNumber());

        // same object -> returns true
        assertEquals(deleteFirstCommand, deleteFirstCommand);

        // same values -> returns true
        DeleteSessionCommand deleteFirstCommandCopy = new DeleteSessionCommand(
                TypicalSessions.SESSION_TYPICAL1.getSessionNumber());
        assertEquals(deleteFirstCommand, deleteFirstCommandCopy);

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertNotEquals(null, deleteFirstCommand);

        // different person -> returns false
        assertNotEquals(deleteFirstCommand, deleteSecondCommand);
    }

    @Test
    public void toStringMethod() {
        SessionNumber targetSessionNumber = new SessionNumber("2");
        DeleteSessionCommand deleteSessionCommand = new DeleteSessionCommand(targetSessionNumber);
        String expected = DeleteSessionCommand.class.getCanonicalName()
                + "{targetSessionNumber=" + targetSessionNumber + "}";
        assertEquals(expected, deleteSessionCommand.toString());
    }
}
