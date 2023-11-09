package seedu.address.logic.commands.personcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


public class SortPatientCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_sortList_success() {
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        boolean isAscending = false;
        expectedModel.sortPatientList(isAscending, "name");

        assertCommandSuccess(new SortPatientCommand(isAscending, "name"),
                model, String.format(SortPatientCommand.MESSAGE_SUCCESS,
                        "descending", "name"), expectedModel);
    }
    @Test
    public void equals() {
        SortPatientCommand sortPatientFirstCommand = new SortPatientCommand(true, "name");
        SortPatientCommand sortPatientSecondCommand = new SortPatientCommand(false, "birthday");

        // same object -> returns true
        assertTrue(sortPatientFirstCommand.equals(sortPatientFirstCommand));

        // same values -> returns true
        SortPatientCommand sortPatientFirstCommandCopy = new SortPatientCommand(true, "name");
        assertTrue(sortPatientFirstCommand.equals(sortPatientFirstCommandCopy));

        // different types -> returns false
        assertFalse(sortPatientFirstCommand.equals(1));

        // null -> returns false
        assertFalse(sortPatientFirstCommand.equals(null));

        // different appointment -> returns false
        assertFalse(sortPatientFirstCommand.equals(sortPatientSecondCommand));
    }
}
