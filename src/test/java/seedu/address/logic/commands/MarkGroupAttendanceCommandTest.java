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
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class MarkGroupAttendanceCommandTest {
    private Model model;
    private MarkGroupAttendanceCommand markGroupAttendanceCommand;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
    }

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MarkGroupAttendanceCommand(null, null, null));
    }

    @Test
    public void execute_validGroupAndTutorial_markAttendanceSuccessful() {

        // Group 1 corresponds to the group name
        Group group = new Group("Group1");
        // create a sample person with group
        Person person = new PersonBuilder().withGroup("Group1").build();
        // Add the sample person to the model
        model.addPerson(person);

        // Index 1 corresponds to week 1
        int week = 1;

        // Create a new MarkAttendanceCommand
        markGroupAttendanceCommand = new MarkGroupAttendanceCommand(group, Index.fromOneBased(week), "P");

        // Execute the command
        try {
            markGroupAttendanceCommand.execute(model);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        // Check if the person's attendance has been marked for week 1
        assertTrue(person.getAttendance().isMarkedWeek(0));
    }

    @Test
    public void execute_invalidGroup_throwsCommandException() {
        // Group 1 is invalid because of empty model
        Group group = new Group("Group1");

        // Index 1 corresponds to week 1
        int week = 1;

        // Create a new MarkAttendanceCommand
        markGroupAttendanceCommand = new MarkGroupAttendanceCommand(group, Index.fromOneBased(week), "P");

        // Execute the command and expect a CommandException
        assertThrows(CommandException.class, () -> markGroupAttendanceCommand.execute(model));
    }

    @Test
    public void equals_sameCommand_returnsTrue() {
        // Create a MarkAttendanceCommand with the same group and tutorial
        Group group = new Group("Group1");
        MarkGroupAttendanceCommand command1 = new MarkGroupAttendanceCommand(group,
                Index.fromOneBased(1), "P");
        MarkGroupAttendanceCommand command2 = new MarkGroupAttendanceCommand(group,
                Index.fromOneBased(1), "P");
        // They should be equal
        assertTrue(command1.equals(command2));
    }

    @Test
    public void equals_differentCommands_returnsFalse() {
        Group group1 = new Group("Group1");
        Group group2 = new Group("Group2");
        // Create two different MarkAttendanceCommands
        MarkGroupAttendanceCommand command1 = new MarkGroupAttendanceCommand(group1,
                Index.fromOneBased(1), "P");
        MarkGroupAttendanceCommand command2 = new MarkGroupAttendanceCommand(group2,
                Index.fromOneBased(2), "P");

        // They should not be equal
        assertFalse(command1.equals(command2));
    }
}
