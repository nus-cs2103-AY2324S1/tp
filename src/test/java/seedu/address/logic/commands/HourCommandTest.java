package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Hour;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;



public class HourCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_hourCommand_success() {
        // Create a test person
        Person testPerson = new PersonBuilder().build();

        // Add the test person to the model
        model.addPerson(testPerson);

        // Create an HourCommand with a positive hourToAdd
        HourCommand command = new HourCommand(10);

        try {
            // Execute the command
            command.execute(model);

            // Check if the person's hour has been updated correctly
            ObservableList<Person> personList = model.getFilteredPersonList();
            Person updatedPerson = personList.get(personList.size() - 1);
            assertEquals(testPerson.updateHour(10), updatedPerson);
        } catch (CommandException e) {
            // Command execution should not throw an exception here
            assert false : "Command execution should not throw a CommandException";
        }
    }

    @Test
    public void execute_hourCommand_failure() {
        // Create an HourCommand with an hourToAdd that would result in an IllegalArgumentException
        HourCommand command = new HourCommand(10000);

        // Execute the command and expect a CommandException to be thrown
        assertThrows(CommandException.class, () -> command.execute(model));
    }

    @Test
    public void equals() {
        HourCommand command1 = new HourCommand(10);
        HourCommand command2 = new HourCommand(10);
        HourCommand command3 = new HourCommand(20);

        // Test same object
        assertEquals(command1, command1);

        // Test equality
        assertEquals(command1, command2);

        // Test inequality
        assertNotEquals(command1, command3);

        // Test not instance
        assertNotEquals(command1, new Hour(10));
    }
}
