package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionNumber;
import seedu.address.model.session.SessionRemark;
import seedu.address.testutil.SessionBuilder;



public class UpdateSessionRemarkCommandTest {

    @Test
    public void constructor_nullSessionNumber_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new UpdateSessionRemarkCommand(
                null, new SessionRemark("Remarks")));
    }

    @Test
    public void constructor_nullSessionRemark_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new UpdateSessionRemarkCommand(
                new SessionNumber("1"), null));
    }

    @Test
    public void execute_validSessionNumberAndSessionRemark_success() throws CommandException {
        Session validSession = new SessionBuilder().build();
        Model model = new ModelManager();
        model.addSession(validSession);

        UpdateSessionRemarkCommand updateSessionRemarkCommand = new UpdateSessionRemarkCommand(
                validSession.getSessionNumber(),
                new SessionRemark("New Remarks"));

        updateSessionRemarkCommand.execute(model);

        Session updatedSession = model.findSessionBySessionNumber(validSession.getSessionNumber());

        Session expectedSession = new SessionBuilder(validSession)
                .withSessionRemark("New Remarks")
                .build();

        // Verify that the session has been updated with the new remark.
        assertEquals(expectedSession, updatedSession);
    }

    @Test
    public void execute_invalidSessionNumber_throwsSessionNotFoundException() {
        Session validSession = new SessionBuilder().withSessionNumber("1").build();
        Model model = new ModelManager();
        model.addSession(validSession);

        UpdateSessionRemarkCommand updateSessionRemarkCommand = new UpdateSessionRemarkCommand(
                new SessionNumber("3"),
                new SessionRemark("New Remarks"));
        assertCommandFailure(updateSessionRemarkCommand, model, Messages.MESSAGE_SESSION_NOT_FOUND);
    }

    @Test
    public void getCommandType() {
        Session validSession = new SessionBuilder().build();
        UpdateSessionRemarkCommand command = new UpdateSessionRemarkCommand(
                validSession.getSessionNumber(),
                new SessionRemark("New Remarks"));

        assertEquals(command.getCommandType(), CommandType.UPDATE_SESSION_REMARK);

    }

    @Test
    public void equals() {
        final UpdateSessionRemarkCommand standardCommand = new UpdateSessionRemarkCommand(
                new SessionNumber("1"),
                new SessionRemark("New Remarks"));

        // Same values -> returns true
        UpdateSessionRemarkCommand commandWithSameValues = new UpdateSessionRemarkCommand(
                new SessionNumber("1"),
                new SessionRemark("New Remarks"));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // Same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // Different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // Different session number -> returns false
        assertFalse(standardCommand.equals(
                new UpdateSessionRemarkCommand(new SessionNumber("2"), new SessionRemark("New Remarks"))));

        // Different session remark -> returns false
        assertFalse(standardCommand.equals(
                new UpdateSessionRemarkCommand(new SessionNumber("1"), new SessionRemark("Old Remarks"))));
    }

    @Test
    public void toStringMethod() throws CommandException {
        Model model = new ModelManager();
        SessionNumber sessionNumber = new SessionNumber("1");
        Session session = new Session(sessionNumber);
        model.addSession(session);

        SessionRemark sessionRemark = new SessionRemark("Looks fun");
        UpdateSessionRemarkCommand command =
                new UpdateSessionRemarkCommand(sessionNumber, sessionRemark);
        command.execute(model);


        String expected = UpdateSessionRemarkCommand.class.getCanonicalName()
                + "{toUpdate=" + sessionNumber + ", remark=" + sessionRemark + "}";
        assertEquals(expected, command.toString());
    }
}
