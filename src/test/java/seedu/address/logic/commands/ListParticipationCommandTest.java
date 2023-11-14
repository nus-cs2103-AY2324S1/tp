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
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class ListParticipationCommandTest {
    private Model model;
    private ListParticipationCommand listParticipationCommand;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
    }

    @Test
    public void execute_validIndex_listParticipationSuccessful() throws CommandException {
        // Create a sample person with attendance and participation points
        Person person = new PersonBuilder()
                .withAttendance("P,A,P,A,P,A,P,A,P,A,P,A", "100,0,150,0,300,0,50,0,70,0,90,0")
                .build();

        // Add the sample person to the model
        model.addPerson(person);

        // Index 1 corresponds to the sample person
        int index = 1;

        // Create a new ListParticipationCommand
        listParticipationCommand = new ListParticipationCommand(Index.fromOneBased(index));

        // Execute the command
        String result = listParticipationCommand.execute(model).getFeedbackToUser();

        // Check if the result contains the person's name, attendance, and participation points
        assertTrue(result.contains("Tutorial 1: [P], Participation Points: [100]"));
        assertTrue(result.contains("Tutorial 2: [A], Participation Points: [0]"));
        assertTrue(result.contains("Tutorial 3: [P], Participation Points: [150]"));
        assertTrue(result.contains("Tutorial 4: [A], Participation Points: [0]"));
        assertTrue(result.contains("Tutorial 5: [P], Participation Points: [300]"));
        assertTrue(result.contains("Tutorial 6: [A], Participation Points: [0]"));
        assertTrue(result.contains("Tutorial 7: [P], Participation Points: [50]"));
        assertTrue(result.contains("Tutorial 8: [A], Participation Points: [0]"));
        assertTrue(result.contains("Tutorial 9: [P], Participation Points: [70]"));
        assertTrue(result.contains("Tutorial 10: [A], Participation Points: [0]"));
        assertTrue(result.contains("Tutorial 11: [P], Participation Points: [90]"));
        assertTrue(result.contains("Tutorial 12: [A], Participation Points: [0]"));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        // Index 1 is invalid in an empty model
        int index = 1;

        // Create a new ListParticipationCommand
        listParticipationCommand = new ListParticipationCommand(Index.fromOneBased(index));

        // Execute the command and expect a CommandException
        assertThrows(CommandException.class, () -> listParticipationCommand.execute(model));
    }

    @Test
    public void equals_sameCommand_returnsTrue() {
        // Create an InputParticipationCommand with the same index, week, and points
        ListParticipationCommand command1 = new ListParticipationCommand(Index.fromOneBased(1));
        ListParticipationCommand command2 = new ListParticipationCommand(Index.fromOneBased(1));

        // They should be equal
        assertTrue(command1.equals(command2));
        assertTrue(command1.equals(command1));
    }

    @Test
    public void equals_differentCommands_returnsFalse() {
        // Create two different InputParticipationCommands
        ListParticipationCommand command1 = new ListParticipationCommand(Index.fromOneBased(1));
        ListParticipationCommand command2 = new ListParticipationCommand(Index.fromOneBased(2));

        // They should not be equal
        assertFalse(command1.equals(command2));
        assertFalse(command1.equals(1));
    }
}
