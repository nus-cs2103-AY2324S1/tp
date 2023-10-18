package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ListStudentsCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListStudentsCommand.
 */
public class ListStudentsCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void equals() {
        ListStudentsCommand listStudentsFirstCommand = new ListStudentsCommand();
        ListStudentsCommand listStudentsSecondCommand = new ListStudentsCommand();

        // same object -> returns true
        assertTrue(listStudentsFirstCommand.equals(listStudentsFirstCommand));

        // both instanceof ListStudentsCommand -> returns true
        assertTrue(listStudentsFirstCommand.equals(listStudentsSecondCommand));
    }

    @Test
    public void execute_listStudents_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS);
        assertCommandSuccess(new ListStudentsCommand(), model, expectedCommandResult, expectedModel);
    }
}
