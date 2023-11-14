package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Group;

public class UnmarkGroupAttendanceCommandTest {

    private Model model;
    private UnmarkGroupAttendanceCommand unmarkGroupAttendanceCommand;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
    }

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new UnmarkGroupAttendanceCommand(null, null));
    }

    @Test
    public void execute_validGroupAndTutorial_markAttendanceSuccessful() {
        // Group 1 is invalid because of empty model
        Group group = new Group("Group1");

        // Index 1 corresponds to week 1
        int week = 1;

        // Create a new MarkAttendanceCommand
        unmarkGroupAttendanceCommand = new UnmarkGroupAttendanceCommand(group, Index.fromOneBased(week));

        // Execute the command and expect a CommandException
        assertThrows(CommandException.class, () -> unmarkGroupAttendanceCommand.execute(model));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Group group = new Group("Group1");
        // Index 1 corresponds to week 1
        int week = 1;

        // Create a new MarkAttendanceCommand
        unmarkGroupAttendanceCommand = new UnmarkGroupAttendanceCommand(group, Index.fromOneBased(week));

        // Execute the command and expect a CommandException
        assertThrows(CommandException.class, () -> unmarkGroupAttendanceCommand.execute(model));
    }

    @Test
    public void equals_sameCommand_returnsTrue() {
        // Create a MarkAttendanceCommand with the same index and tutorial
        Group group = new Group("Group1");
        UnmarkGroupAttendanceCommand command1 = new UnmarkGroupAttendanceCommand(group,
                Index.fromOneBased(1));
        UnmarkGroupAttendanceCommand command2 = new UnmarkGroupAttendanceCommand(group,
                Index.fromOneBased(1));

        // They should be equal
        assertTrue(command1.equals(command2));
    }

    @Test
    public void equals_differentCommands_returnsFalse() {
        // Create two different MarkAttendanceCommands
        Group group = new Group("Group1");
        Group group2 = new Group("Group2");
        UnmarkGroupAttendanceCommand command1 = new UnmarkGroupAttendanceCommand(group,
                Index.fromOneBased(1));
        UnmarkGroupAttendanceCommand command2 = new UnmarkGroupAttendanceCommand(group2,
                Index.fromOneBased(1));
        // They should not be equal
        assertFalse(command1.equals(command2));
    }
}
