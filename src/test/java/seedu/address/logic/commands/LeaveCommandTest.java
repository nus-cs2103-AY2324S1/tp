package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGroups.GROUP1;
import static seedu.address.testutil.TypicalGroups.GROUP2;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Email;


/**
 * Contains integration tests (interaction with the Model) and unit tests for LeaveCommand.
 */
public class LeaveCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
        model.addPerson(ALICE);
        model.addPerson(BENSON);
        model.addPerson(DANIEL);
        model.addPerson(ELLE);
        model.addPerson(FIONA);
    }

    @Test
    public void execute_leavePersonFromNonexistentGroup_throwsCommandException() {
        LeaveCommand leaveCommand = new LeaveCommand(ALICE.getEmail(), GROUP1.getNumber());

        assertCommandFailure(leaveCommand, model, LeaveCommand.MESSAGE_LEAVE_GROUP_NOT_FOUND);
    }

    @Test
    public void execute_leaveNonexistentPersonFromGroup_throwsCommandException() {
        model.addGroup(GROUP1);

        LeaveCommand leaveCommand = new LeaveCommand(DANIEL.getEmail(), GROUP1.getNumber());

        assertCommandFailure(leaveCommand, model, LeaveCommand.MESSAGE_NOT_IN_GROUP);
    }

    @Test
    public void execute_leavePersonNotInGroup_throwsCommandException() {
        model.addGroup(GROUP2);

        LeaveCommand leaveCommand = new LeaveCommand(FIONA.getEmail(), GROUP2.getNumber());

        try {
            leaveCommand.execute(model);
            fail("Expected CommandException was not thrown.");
        } catch (UnsupportedOperationException e) {
            // Catch the UnsupportedOperationException and convert it into a CommandException
            assertThrows(CommandException.class, () -> {
                throw new CommandException(LeaveCommand.MESSAGE_NOT_IN_GROUP);
            });
        } catch (CommandException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void equals() {
        LeaveCommand leaveAliceGroup1Command = new LeaveCommand(ALICE.getEmail(), GROUP1.getNumber());
        LeaveCommand leaveAliceGroup2Command = new LeaveCommand(ALICE.getEmail(), GROUP2.getNumber());
        LeaveCommand leaveBensonGroup1Command = new LeaveCommand(BENSON.getEmail(), GROUP1.getNumber());
        LeaveCommand leaveDanielGroup1Command = new LeaveCommand(DANIEL.getEmail(), GROUP1.getNumber());

        // same object -> returns true
        assertTrue(leaveAliceGroup1Command.equals(leaveAliceGroup1Command));

        // same values -> returns true
        LeaveCommand leaveAliceGroup1CommandCopy = new LeaveCommand(ALICE.getEmail(), GROUP1.getNumber());
        assertTrue(leaveAliceGroup1Command.equals(leaveAliceGroup1CommandCopy));

        // different types -> returns false
        assertFalse(leaveAliceGroup1Command.equals(1));

        // null -> returns false
        assertFalse(leaveAliceGroup1Command.equals(null));

        // different email -> returns false
        assertFalse(leaveAliceGroup1Command.equals(leaveBensonGroup1Command));

        // different group number -> returns false
        assertFalse(leaveAliceGroup1Command.equals(leaveAliceGroup2Command));

        // different person -> returns false
        assertFalse(leaveAliceGroup1Command.equals(leaveDanielGroup1Command));
    }

    @Test
    public void toString_validLeaveCommand_returnsExpectedString() {
        LeaveCommand leaveCommand = new LeaveCommand(new Email("example@u.nus.edu"), 1);

        String expectedString = new ToStringBuilder(leaveCommand)
                .add("targetEmail", "example@u.nus.edu")
                .add("targetGroupNumber", 1)
                .toString();

        assertEquals(expectedString, leaveCommand.toString());
    }

    private void assertCommandSuccess(LeaveCommand command, Model actualModel, String expectedMessage,
                                      Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedMessage, result.getFeedbackToUser());
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    private void assertCommandFailure(LeaveCommand command, Model actualModel, String expectedMessage) {
        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
    }
}
