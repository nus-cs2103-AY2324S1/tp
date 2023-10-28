package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void undo_nonEmptyAddressBook() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model emptyModel = new ModelManager();
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        ClearCommand clearCommand = new ClearCommand();

        // Execute the clear command
        clearCommand.execute(model);
        assertEquals(emptyModel, model);

        // Undo the command
        CommandResult undoResult = clearCommand.undo(model);
        assertEquals(expectedModel, model);
        assertEquals(ClearCommand.MESSAGE_UNDO_SUCCESS, undoResult.getFeedbackToUser());
    }

    @Test
    public void undo_emptyAddressBook() {
        Model model = new ModelManager();
        Model emptyModel = new ModelManager();
        Model expectedModel = new ModelManager();
        ClearCommand clearCommand = new ClearCommand();

        // Execute the clear command
        clearCommand.execute(model);
        assertEquals(model, emptyModel);

        // Undo the command
        CommandResult undoResult = clearCommand.undo(model);
        assertEquals(model, expectedModel);
        assertEquals(ClearCommand.MESSAGE_UNDO_SUCCESS, undoResult.getFeedbackToUser());
    }

}
