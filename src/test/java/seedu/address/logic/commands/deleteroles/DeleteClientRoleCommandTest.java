package seedu.address.logic.commands.deleteroles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalDevelopers.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.addroles.AddClientRoleCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.ClientRoles;
import seedu.address.testutil.Assert;
import seedu.address.testutil.ClientBuilder;

public class DeleteClientRoleCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullRole_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new DeleteClientRoleCommand(null));
    }

    @Test
    public void execute_roleAcceptedByModel_deleteSuccessful() throws CommandException {

        String validRole = "NewRole";
        AddClientRoleCommand addClientRoleCommand = new AddClientRoleCommand(validRole);
        addClientRoleCommand.execute(model);

        DeleteClientRoleCommand deleteClientRoleCommand = new DeleteClientRoleCommand(validRole);

        String expectedMessage = String.format(DeleteClientRoleCommand.MESSAGE_SUCCESS, validRole);
        assertEquals(deleteClientRoleCommand.execute(model).getFeedbackToUser(), expectedMessage);
    }

    @Test
    public void execute_roleSomeoneUsing_throwsCommandException() throws CommandException {
        // Assign a client with the specified role
        String roleWithClient = "NewRole";
        AddClientRoleCommand addClientRoleCommand = new AddClientRoleCommand(roleWithClient);
        addClientRoleCommand.execute(model);
        ClientBuilder clientBuilder = new ClientBuilder().withRole(roleWithClient);
        model.addClient(clientBuilder.build());

        // Attempt to delete the role
        DeleteClientRoleCommand deleteClientRoleCommand = new DeleteClientRoleCommand(roleWithClient);
        String expectedResult = String.format(DeleteClientRoleCommand.MESSAGE_CANNOT_DELETE_REPEAT,
                Messages.format(roleWithClient));

        // Assert that the command throws an exception
        assertCommandFailure(deleteClientRoleCommand, model, expectedResult);

        model.deleteClient(clientBuilder.build());
        deleteClientRoleCommand.execute(model);
    }


    @Test
    public void execute_roleNotInList_throwsCommandException() {
        String nonExistingRole = "NonExistingRole";
        DeleteClientRoleCommand deleteClientRoleCommand = new DeleteClientRoleCommand(nonExistingRole);

        assertCommandFailure(deleteClientRoleCommand, model,
                DeleteClientRoleCommand.MESSAGE_CANNOT_DELETE_NONEXISTING
                        + DeleteClientRoleCommand.MESSAGE_EXISTING_CLIENT_ROLES
                        + ClientRoles.printRoles());
    }

    @Test
    public void execute_roleIsPredefined_throwsCommandException() {
        String roleWithClient = "Client";
        DeleteClientRoleCommand deleteClientRoleCommand = new DeleteClientRoleCommand(roleWithClient);
        assertThrows(CommandException.class, () -> deleteClientRoleCommand.execute(model));
    }

    @Test
    public void equals() {
        String firstRole = "Tester";
        String secondRole = "Client";
        DeleteClientRoleCommand deleteFirstCommand = new DeleteClientRoleCommand(firstRole);
        DeleteClientRoleCommand deleteSecondCommand = new DeleteClientRoleCommand(secondRole);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // role will no longer exists-> returns false
        DeleteClientRoleCommand deleteFirstCommandCopy = new DeleteClientRoleCommand(firstRole);
        assertFalse(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different client role -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringTest() {
        String roleName = "Tester";
        DeleteClientRoleCommand deleteClientRoleCommand = new DeleteClientRoleCommand(roleName);
        assertEquals(deleteClientRoleCommand.toString(), new DeleteClientRoleCommand(roleName).toString());
    }
}
