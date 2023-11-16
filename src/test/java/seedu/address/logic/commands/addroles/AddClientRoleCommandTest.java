package seedu.address.logic.commands.addroles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.deleteroles.DeleteClientRoleCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.Assert;

public class AddClientRoleCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullRole_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new AddClientRoleCommand(null));
    }

    @Test
    public void execute_roleAcceptedByModel_addSuccessful() throws CommandException {
        String validRole1 = "Tester";
        AddClientRoleCommand addClientRoleCommand = new AddClientRoleCommand(validRole1);
        String expectedMessage = String.format(AddClientRoleCommand.MESSAGE_SUCCESS, validRole1);

        assertEquals(addClientRoleCommand.execute(model).getFeedbackToUser(), expectedMessage);

        DeleteClientRoleCommand deleteClientRoleCommand = new DeleteClientRoleCommand(validRole1);
        deleteClientRoleCommand.execute(model);
    }

    @Test
    public void execute_duplicateRole_throwsCommandException() {
        String duplicateRole = "Developer";
        AddClientRoleCommand addClientRoleCommand = new AddClientRoleCommand(duplicateRole);

        assertCommandFailure(addClientRoleCommand, model, AddClientRoleCommand.MESSAGE_DUPLICATE_ROLE);
    }

    @Test
    public void equals() {
        String firstRole = "Tester";
        String secondRole = "Developer";
        AddClientRoleCommand addFirstCommand = new AddClientRoleCommand(firstRole);
        AddClientRoleCommand addSecondCommand = new AddClientRoleCommand(secondRole);

        // same object -> returns true
        assertTrue(addFirstCommand.equals(addFirstCommand));

        // same values -> returns False because cannot add twice
        AddClientRoleCommand addFirstCommandCopy = new AddClientRoleCommand(firstRole);
        assertFalse(addFirstCommand.equals(addFirstCommandCopy));

        // different types -> returns false
        assertFalse(addFirstCommand.equals(1));

        // null -> returns false
        assertFalse(addFirstCommand.equals(null));

        // different developer role -> returns false
        assertFalse(addFirstCommand.equals(addSecondCommand));
    }

    @Test
    public void toStringTest() {
        String roleName = "Tester";
        AddClientRoleCommand addClientRoleCommand = new AddClientRoleCommand(roleName);
        assertEquals(addClientRoleCommand.toString(), new AddClientRoleCommand(roleName).toString());
    }
}
