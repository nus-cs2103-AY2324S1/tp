package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.InputParticipationCommand.ATTENDANCE_NOT_MARKED;
import static seedu.address.logic.commands.InputParticipationCommand.MAXIMUM_PARTICIPATION_POINTS;
import static seedu.address.logic.commands.InputParticipationCommand.PARTICIPATION_POINTS_OUT_OF_RANGE;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class InputParticipationCommandTest {
    private Model model;
    private InputParticipationCommand inputParticipationCommand;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
    }

    @Test
    public void execute_validIndexAndTutorial_inputParticipationSuccessful() {
        // Create a sample person with attendance
        Person person = new PersonBuilder().withAttendance("P,U,U,U,U,U,U,U,U,U,U,U",
                "0,0,0,0,0,0,0,0,0,0,0,0").build();

        // Add the sample person to the model
        model.addPerson(person);

        // Index 1 corresponds to the sample person
        int index = 1;

        // Index 1 corresponds to tut 1
        int tut = 1;

        // Points to add
        int points = 50;

        // Create a new InputParticipationCommand
        inputParticipationCommand = new InputParticipationCommand(Index.fromOneBased(index),
                Index.fromOneBased(tut), points);

        // Execute the command
        try {
            inputParticipationCommand.execute(model);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        // Check if the person's participation points have been updated for week 1
        assertEquals(points, person.getAttendance().getParticipationPoints(0));
    }

    @Test
    public void execute_validZeroPoints_inputParticipationSuccessful() {
        // Create a sample person with attendance
        Person person = new PersonBuilder().withAttendance("1,0,0,0,0,0,0,0,0,0,0,0",
                "0,0,0,0,0,0,0,0,0,0,0,0").build();

        // Add the sample person to the model
        model.addPerson(person);

        // Index 1 corresponds to the sample person
        int index = 1;

        // Index 1 corresponds to tut 1
        int tut = 1;

        // Points to add
        int points = 0;

        // Create a new InputParticipationCommand
        inputParticipationCommand = new InputParticipationCommand(Index.fromOneBased(index),
                Index.fromOneBased(tut), points);

        // Execute the command
        try {
            inputParticipationCommand.execute(model);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        // Check if the person's participation points have been updated for week 1
        assertEquals(points, person.getAttendance().getParticipationPoints(0));
    }

    @Test
    public void execute_validMaxPoints_inputParticipationSuccessful() {
        // Create a sample person with attendance
        Person person = new PersonBuilder().withAttendance("P,U,U,U,U,U,U,U,U,U,U,U",
                "0,0,0,0,0,0,0,0,0,0,0,0").build();

        // Add the sample person to the model
        model.addPerson(person);

        // Index 1 corresponds to the sample person
        int index = 1;

        // Index 1 corresponds to tut 1
        int tut = 1;

        // Points to add
        int points = MAXIMUM_PARTICIPATION_POINTS;

        // Create a new InputParticipationCommand
        inputParticipationCommand = new InputParticipationCommand(Index.fromOneBased(index),
                Index.fromOneBased(tut), points);

        // Execute the command
        try {
            inputParticipationCommand.execute(model);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        // Check if the person's participation points have been updated for week 1
        assertEquals(points, person.getAttendance().getParticipationPoints(0));
    }

    @Test
    public void execute_unmarkedAttendance_inputParticipationUnsuccessful() {
        // Create a sample person with attendance
        Person person = new PersonBuilder().withAttendance("U,U,U,U,U,U,U,U,U,U,U,U",
                "0,0,0,0,0,0,0,0,0,0,0,0").build();

        // Add the sample person to the model
        model.addPerson(person);
        // Index 1 corresponds to the sample person
        int index = 1;
        // Index 1 corresponds to tut 1
        int tut = 1;
        // Points to add
        int points = 50;

        // Create a new InputParticipationCommand
        inputParticipationCommand = new InputParticipationCommand(Index.fromOneBased(index),
                Index.fromOneBased(tut), points);

        // Execute the command
        try {
            String result = inputParticipationCommand.execute(model).getFeedbackToUser();
            assertEquals(result, ATTENDANCE_NOT_MARKED);
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        // Index 1 is invalid in an empty model
        int index = 1;

        // Index 1 corresponds to tut 1
        int tut = 1;

        // Points to add
        int points = 50;

        // Create a new InputParticipationCommand
        inputParticipationCommand = new InputParticipationCommand(Index.fromOneBased(index),
                Index.fromOneBased(tut), points);

        // Execute the command and expect a CommandException
        assertThrows(CommandException.class, () -> inputParticipationCommand.execute(model));
    }

    @Test
    public void execute_negativePoints_throwsCommandException() {
        // Create a sample person with attendance
        Person person = new PersonBuilder().withAttendance("P,U,U,U,U,U,U,U,U,U,U,U",
                "0,0,0,0,0,0,0,0,0,0,0,0").build();

        // Add the sample person to the model
        model.addPerson(person);

        int personIndex = 1;
        int tutIndex = 1;
        int points = -1;

        // Create a new InputParticipationCommand
        inputParticipationCommand = new InputParticipationCommand(Index.fromOneBased(personIndex),
                Index.fromOneBased(tutIndex), points);

        // Execute the command and expect a error message
        try {
            String result = inputParticipationCommand.execute(model).getFeedbackToUser();
            assertEquals(result, PARTICIPATION_POINTS_OUT_OF_RANGE);
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void execute_largePoints_throwsCommandException() {
        // Create a sample person with attendance
        Person person = new PersonBuilder().withAttendance("P,U,U,U,U,U,U,U,U,U,U,U",
                "0,0,0,0,0,0,0,0,0,0,0,0").build();

        // Add the sample person to the model
        model.addPerson(person);

        int personIndex = 1;
        int tutIndex = 1;
        int points = MAXIMUM_PARTICIPATION_POINTS + 1;

        // Create a new InputParticipationCommand
        inputParticipationCommand = new InputParticipationCommand(Index.fromOneBased(personIndex),
                Index.fromOneBased(tutIndex), points);

        // Execute the command and expect a error message
        try {
            String result = inputParticipationCommand.execute(model).getFeedbackToUser();
            assertEquals(result, PARTICIPATION_POINTS_OUT_OF_RANGE);
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void equals_sameCommand_returnsTrue() {
        // Create an InputParticipationCommand with the same index, week, and points
        InputParticipationCommand command1 = new InputParticipationCommand(Index.fromOneBased(1),
                Index.fromOneBased(1), 50);
        InputParticipationCommand command2 = new InputParticipationCommand(Index.fromOneBased(1),
                Index.fromOneBased(1), 50);

        // They should be equal
        assertTrue(command1.equals(command2));
        assertTrue(command1.equals(command1));
    }

    @Test
    public void equals_differentCommands_returnsFalse() {
        // Create two different InputParticipationCommands
        InputParticipationCommand command1 = new InputParticipationCommand(Index.fromOneBased(1),
                Index.fromOneBased(1), 50);
        InputParticipationCommand command2 = new InputParticipationCommand(Index.fromOneBased(2),
                Index.fromOneBased(2), 100);

        // They should not be equal
        assertFalse(command1.equals(command2));
        assertFalse(command1.equals(1));
    }
}
