package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.InputGroupParticipationCommand.MAXIMUM_PARTICIPATION_POINTS;
import static seedu.address.logic.commands.InputGroupParticipationCommand.PARTICIPATION_POINTS_OUT_OF_RANGE;
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

public class InputGroupParticipationCommandTest {
    private Model model;
    private InputGroupParticipationCommand inputGroupParticipationCommand;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
    }

    @Test
    public void execute_validIndexAndTutorial_inputParticipationSuccessful() {
        // Create a sample person with attendance
        // Group 1 corresponds to the group name
        Group group = new Group("Group1");
        // create a sample person with group
        Person person = new PersonBuilder().withGroup("Group1")
                .withAttendance("P,U,U,U,U,U,U,U,U,U,U,U", "0,0,0,0,0,0,0,0,0,0,0,0").build();
        // Add the sample person to the model
        model.addPerson(person);

        // Index 1 corresponds to tut 1
        int tut = 1;

        // Points to add
        int points = 50;

        // Create a new InputParticipationCommand
        inputGroupParticipationCommand = new InputGroupParticipationCommand(group,
                Index.fromOneBased(tut), points);

        // Execute the command
        try {
            inputGroupParticipationCommand.execute(model);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        // Check if the person's participation points have been updated for week 1
        assertEquals(points, person.getAttendance().getParticipationPoints(0));
    }

    @Test
    public void execute_validZeroPoints_inputParticipationSuccessful() {
        // Create a sample person with attendance
        // Group 1 corresponds to the group name
        Group group = new Group("Group1");
        // create a sample person with group
        Person person = new PersonBuilder().withGroup("Group1")
                .withAttendance("P,U,U,U,U,U,U,U,U,U,U,U,U", "0,0,0,0,0,0,0,0,0,0,0,0").build();
        // Add the sample person to the model
        model.addPerson(person);

        // Index 1 corresponds to tut 1
        int tut = 1;

        // Points to add
        int points = 0;

        // Create a new InputParticipationCommand
        inputGroupParticipationCommand = new InputGroupParticipationCommand(group,
                Index.fromOneBased(tut), points);

        // Execute the command
        try {
            inputGroupParticipationCommand.execute(model);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        // Check if the person's participation points have been updated for week 1
        assertEquals(points, person.getAttendance().getParticipationPoints(0));
    }

    @Test
    public void execute_validMaximumPoints_inputParticipationSuccessful() {
        // Create a sample person with attendance
        // Group 1 corresponds to the group name
        Group group = new Group("Group1");
        // create a sample person with group
        Person person = new PersonBuilder().withGroup("Group1")
                .withAttendance("P,U,U,U,U,U,U,U,U,U,U,U", "0,0,0,0,0,0,0,0,0,0,0,0").build();
        // Add the sample person to the model
        model.addPerson(person);

        // Index 1 corresponds to tut 1
        int tut = 1;

        // Points to add
        int points = MAXIMUM_PARTICIPATION_POINTS;

        // Create a new InputParticipationCommand
        inputGroupParticipationCommand = new InputGroupParticipationCommand(group,
                Index.fromOneBased(tut), points);

        // Execute the command
        try {
            inputGroupParticipationCommand.execute(model);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        // Check if the person's participation points have been updated for week 1
        assertEquals(points, person.getAttendance().getParticipationPoints(0));
    }

    @Test
    public void execute_invalidNegativePoints_inputParticipationSuccessful() {
        // Create a sample person with attendance
        // Group 1 corresponds to the group name
        Group group = new Group("Group1");
        // create a sample person with group
        Person person = new PersonBuilder().withGroup("Group1")
                .withAttendance("P,U,U,U,U,U,U,U,U,U,U,U,U", "0,0,0,0,0,0,0,0,0,0,0,0").build();
        // Add the sample person to the model
        model.addPerson(person);

        // Index 1 corresponds to tut 1
        int tut = 1;

        // Points to add
        int points = -1;

        // Create a new InputParticipationCommand
        inputGroupParticipationCommand = new InputGroupParticipationCommand(group,
                Index.fromOneBased(tut), points);

        // Execute the command and expect a error message
        try {
            String result = inputGroupParticipationCommand.execute(model).getFeedbackToUser();
            assertEquals(result, PARTICIPATION_POINTS_OUT_OF_RANGE);
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void execute_invalidLargePoints_inputParticipationSuccessful() {
        // Create a sample person with attendance
        // Group 1 corresponds to the group name
        Group group = new Group("Group1");
        // create a sample person with group
        Person person = new PersonBuilder().withGroup("Group1")
                .withAttendance("P,U,U,U,U,U,U,U,U,U,U,U,U", "0,0,0,0,0,0,0,0,0,0,0,0").build();
        // Add the sample person to the model
        model.addPerson(person);

        // Index 1 corresponds to tut 1
        int tut = 1;

        // Points to add
        int points = MAXIMUM_PARTICIPATION_POINTS + 1;

        // Create a new InputParticipationCommand
        inputGroupParticipationCommand = new InputGroupParticipationCommand(group,
                Index.fromOneBased(tut), points);

        // Execute the command and expect a error message
        try {
            String result = inputGroupParticipationCommand.execute(model).getFeedbackToUser();
            assertEquals(result, PARTICIPATION_POINTS_OUT_OF_RANGE);
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void execute_invalidGroup_throwsCommandException() {
        // Index 1 corresponds to tut 1
        int tut = 1;

        // Points to add
        int points = 50;

        // Create a new InputParticipationCommand
        inputGroupParticipationCommand = new InputGroupParticipationCommand(new Group("invalid"),
                Index.fromOneBased(tut), points);

        // Execute the command and expect a CommandException
        assertThrows(CommandException.class, () -> inputGroupParticipationCommand.execute(model));
    }

    @Test
    public void equals_sameCommand_returnsTrue() {
        // Create an InputParticipationCommand with the same index, week, and points
        InputGroupParticipationCommand command1 = new InputGroupParticipationCommand(new Group("same"),
                Index.fromOneBased(1), 50);
        InputGroupParticipationCommand command2 = new InputGroupParticipationCommand(new Group("same"),
                Index.fromOneBased(1), 50);

        // They should be equal
        assertTrue(command1.equals(command2));
        assertTrue(command1.equals(command1));
    }

    @Test
    public void equals_differentCommands_returnsFalse() {
        // Create two different InputParticipationCommands
        InputGroupParticipationCommand command1 = new InputGroupParticipationCommand(new Group("diff"),
                Index.fromOneBased(1), 50);
        InputGroupParticipationCommand command2 = new InputGroupParticipationCommand(new Group("diff"),
                Index.fromOneBased(2), 100);

        // They should not be equal
        assertFalse(command1.equals(command2));
        assertFalse(command1.equals(1));
    }
}
