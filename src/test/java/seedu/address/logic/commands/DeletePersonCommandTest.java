package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalLessons.getTypicalScheduleList;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeletePersonCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            getTypicalScheduleList());

    @Test
    public void test_valid_index() throws ParseException, CommandException {
        int size = model.getFilteredPersonList().size();
        while (size > 0) {
            DeletePersonCommand deletePersonCommand = new DeletePersonCommand(size);
            deletePersonCommand.execute(model);
            size--;
        }
        assertEquals(0, model.getFilteredPersonList().size());
    }
    @Test
    public void test_invalid_index() {
        int size = model.getFilteredPersonList().size();
        DeletePersonCommand deletePersonCommand = new DeletePersonCommand(size + 1);
        assertThrows(CommandException.class, () -> deletePersonCommand.execute(model));
        DeletePersonCommand deletePersonCommand2 = new DeletePersonCommand(0);
        assertThrows(CommandException.class, () -> deletePersonCommand2.execute(model));
        DeletePersonCommand deletePersonCommand3 = new DeletePersonCommand(-1);
        assertThrows(CommandException.class, () -> deletePersonCommand3.execute(model));
    }
    @Test
    public void equals() {
        DeletePersonCommand deleteFirstCommand = new DeletePersonCommand(1);
        DeletePersonCommand deleteSecondCommand = new DeletePersonCommand(2);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeletePersonCommand deleteFirstCommandCopy = new DeletePersonCommand(1);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        DeletePersonCommand deletePersonCommand = new DeletePersonCommand(1);
        String expected = DeletePersonCommand.class.getCanonicalName() + "{targetIndex=1}";
        assertEquals(expected, deletePersonCommand.toString());
    }
}
