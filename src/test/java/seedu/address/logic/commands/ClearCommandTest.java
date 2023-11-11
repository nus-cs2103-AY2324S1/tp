package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void constructor_nullConfirmation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ClearCommand(null));
    }

    @Test
    public void equals() {
        ClearCommand clearCommandConfirm = new ClearCommand("confirm");
        ClearCommand clearCommandReject = new ClearCommand("reject");

        // same object -> returns true
        assertTrue(clearCommandConfirm.equals(clearCommandConfirm));

        // same values -> returns true
        ClearCommand clearCommandConfirmCopy = new ClearCommand("confirm");
        assertTrue(clearCommandConfirm.equals(clearCommandConfirmCopy));

        // different types -> returns false
        assertFalse(clearCommandConfirm.equals(1));

        // null -> returns false
        assertFalse(clearCommandConfirm.equals(null));

        // different confirmation -> returns false
        assertFalse(clearCommandConfirm.equals(clearCommandReject));
    }

    @Test
    public void execute_confirmClear_success() throws CommandException {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());
        CommandResult expectedCommandResult = new CommandResult(ClearCommand.MESSAGE_SUCCESS);
        Command clearCommand = new ClearCommand("confirm");
        clearCommand.toString();
        clearCommand.execute(model);
        assertCommandSuccess(clearCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_rejectClear_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        CommandResult expectedCommandResult = new CommandResult(ClearCommand.MESSAGE_USAGE, CommandType.CLEAR);

        assertCommandSuccess(new ClearCommand("reject"), model, expectedCommandResult, model);
    }

    @Test
    public void execute_noConfirmation_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        CommandResult expectedCommandResult = new CommandResult(ClearCommand.MESSAGE_USAGE, CommandType.CLEAR);

        assertCommandSuccess(new ClearCommand(""), model, expectedCommandResult, model);
    }

    @Test
    public void execute_noConfirmation_confirm() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        CommandResult expectedCommandResult = new CommandResult(ClearCommand.MESSAGE_PROMPT);

        assertCommandSuccess(new ClearCommand("confirm"), model, expectedCommandResult, model);
    }

    @Test
    public void execute_nullConfirmation_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        CommandResult expectedCommandResult = new CommandResult(ClearCommand.MESSAGE_USAGE, CommandType.CLEAR);

        assertCommandSuccess(new ClearCommand("null"), model, expectedCommandResult, model);
    }
    @Test
    public void hashCode_sameConfirmation_sameHashCode() {
        ClearCommand clearCommand1 = new ClearCommand("confirm");
        ClearCommand clearCommand2 = new ClearCommand("confirm");

        assertEquals(clearCommand1.hashCode(), clearCommand2.hashCode());
    }

    @Test
    public void hashCode_differentConfirmation_differentHashCode() {
        ClearCommand clearCommand1 = new ClearCommand("confirm");
        ClearCommand clearCommand3 = new ClearCommand("different");

        // Ensure that hash codes are not equal
        assertNotEquals(clearCommand1.hashCode(), clearCommand3.hashCode());
    }


}
