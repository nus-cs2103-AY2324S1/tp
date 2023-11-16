package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.ClientRoles;
import seedu.address.model.developer.DeveloperRoles;
import seedu.address.testutil.ClientBuilder;

public class RedoCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model modelAfterChange = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private RedoCommand redoCommand = new RedoCommand();
    private UndoCommand undoCommand = new UndoCommand();

    @Test
    public void execute_validRedo_success() throws CommandException {
        ClientBuilder clientBuilder = new ClientBuilder();
        model.addClient(clientBuilder.build());
        model.commitAddressBook(model, "Add Benson", TabIndex.Client);
        model.undoAddressBook(model);

        modelAfterChange = model;

        redoCommand.execute(model);

        assertEquals(model, modelAfterChange); // Ensure that the redo was successful
    }

    @Test
    public void execute_noRedoAvailable_throwsCommandException() {
        assertThrows(CommandException.class, () -> redoCommand.execute(model));
    }

    @Test
    public void execute_redoRoleCommand_success() throws CommandException {
        // Undo a command that added a new role for a client
        ClientRoles.addClientRole(new ClientRoles("NewRole"));
        model.commitAddressBook(model, "New role for client added: NewRole", TabIndex.Client);
        undoCommand.execute(model);

        // Redo the role addition
        redoCommand.execute(model);

        assertTrue(ClientRoles.isValidRole("NewRole"));
        ClientRoles.deleteClientRole(new ClientRoles("NewRole"));
    }

    @Test
    public void execute_redoDeveloperRoleCommand_success() throws CommandException {
        // Undo a command that added a new role for a developer
        DeveloperRoles.addDeveloperRole(new DeveloperRoles("NewRole"));
        model.commitAddressBook(model, "New role for developer added: NewRole", TabIndex.Developer);
        undoCommand.execute(model);

        // Redo the role addition
        redoCommand.execute(model);
        assertTrue(DeveloperRoles.isValidRole("NewRole"));
        DeveloperRoles.deleteDeveloperRole(new DeveloperRoles("NewRole"));
    }

    @Test
    public void execute_redoDeleteClientRoleCommand_success() throws CommandException {
        ClientRoles.addClientRole(new ClientRoles("RoleToDelete"));
        model.commitAddressBook(model, "New role for client added: RoleToDelete", TabIndex.Client);

        // Undo a command that deleted a role for a client
        ClientRoles.deleteClientRole(new ClientRoles("RoleToDelete"));
        model.commitAddressBook(model, "Role for clients deleted: RoleToDelete", TabIndex.Client);
        undoCommand.execute(model);

        // Redo the role deletion
        redoCommand.execute(model);

        assertFalse(ClientRoles.isValidRole("RoleToDelete"));
    }

    @Test
    public void execute_redoDeleteDeveloperRoleCommand_success() throws CommandException {
        DeveloperRoles.addDeveloperRole(new DeveloperRoles("RoleToDelete"));
        model.commitAddressBook(model, "New role for developer added: NewRole", TabIndex.Developer);

        // Undo a command that deleted a role for a developer
        DeveloperRoles.deleteDeveloperRole(new DeveloperRoles("RoleToDelete"));
        model.commitAddressBook(model, "Role for developers deleted: RoleToDelete", TabIndex.Developer);
        undoCommand.execute(model);

        // Redo the role deletion
        redoCommand.execute(model);

        assertFalse(DeveloperRoles.isValidRole("RoleToDelete"));
    }
}
