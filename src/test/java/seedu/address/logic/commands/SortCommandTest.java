package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SortCommand.
 */
public class SortCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_sortByGpa_success() throws CommandException {
        int indexOfLastPerson = model.getFilteredPersonList().size() - 1;
        Person personWithHighestGpa = new PersonBuilder(model.getFilteredPersonList().get(indexOfLastPerson))
                .withGpa(5.0).build();
        Person personWithLowestGpa = new PersonBuilder(model.getFilteredPersonList().get(0))
                .withGpa(0.0).build();

        model.setPerson(model.getFilteredPersonList().get(0), personWithLowestGpa);
        model.setPerson(model.getFilteredPersonList().get(indexOfLastPerson), personWithHighestGpa);

        CommandResult commandResult = new SortCommand("gpa").execute(model);
        String expectedMessage = SortCommand.MESSAGE_SUCCESS;

        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertTrue(model.getFilteredPersonList().get(0).equals(personWithHighestGpa)
                && model.getFilteredPersonList().get(indexOfLastPerson).equals(personWithLowestGpa));
    }
    @Test
    public void execute_sortEmptyList_throwsCommandException() {
        Model emptyModel = new ModelManager();
        SortCommand sortCommand = new SortCommand("gpa");
        assertCommandFailure(sortCommand, emptyModel, SortCommand.MESSAGE_EMPTY_LIST);
    }

    @Test
    public void equals() {
        SortCommand sortFirstCommand = new SortCommand("gpa");
        SortCommand sortSecondCommand = new SortCommand("name");

        assertTrue(sortFirstCommand.equals(sortFirstCommand));

        SortCommand sortFirstCommandCopy = new SortCommand("gpa");
        assertTrue(sortFirstCommand.equals(sortFirstCommandCopy));

        assertFalse(sortFirstCommand.equals(1));

        assertFalse(sortFirstCommand.equals(null));

        assertFalse(sortFirstCommand.equals(sortSecondCommand));
    }

    @Test
    public void toStringMethod() {
        String fieldName = "gpa";
        SortCommand sortCommand = new SortCommand(fieldName);
        String expected = SortCommand.class.getCanonicalName() + "{fieldName=" + fieldName + "}";
        assertEquals(expected, sortCommand.toString());
    }
}
