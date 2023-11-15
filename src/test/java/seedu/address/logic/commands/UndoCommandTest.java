package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class UndoCommandTest {
    private Model model;
    private Model successModel;


    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        successModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }
    @Test
    public void execute_noCommandExecuted_failure() {
        assertCommandFailure(new UndoCommand(), model, Messages.MESSAGE_CANNOT_UNDO);
    }

    @Test
    public void execute_oneModifyDataCommandExecuted_success() {
        // Delete person modifies tutee data
        CommandTestUtil.simulateDeleteCommand(model, ALICE);

        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS, successModel);
    }
}
