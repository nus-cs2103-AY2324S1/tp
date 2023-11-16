package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.BOB;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.ClientRoles;
import seedu.address.model.developer.DeveloperRoles;
import seedu.address.testutil.ClientBuilder;
import seedu.address.testutil.DeveloperBuilder;

public class UndoCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model modelAfterChange = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private UndoCommand undoCommand = new UndoCommand();

    @Test
    public void execute_validUndo_success() throws CommandException {
        model.addClient(BOB);
        model.commitAddressBook(model, "Add Alice", TabIndex.Client);
        undoCommand.execute(model);
        assertEquals(model, modelAfterChange);
    }

    @Test
    public void execute_noUndoAvailable_throwsCommandException() {
        assertThrows(CommandException.class, () -> undoCommand.execute(model));
    }

    @Test
    public void execute_undoNonRoleCommand_success() throws CommandException {
        // Add a new role for a client
        ClientBuilder clientBuilder = new ClientBuilder();
        model.addClient(clientBuilder.build());
        model.commitAddressBook(model, "New client added", TabIndex.Client);

        // Undo the role addition
        undoCommand.execute(model);

        assertFalse(model.getFilteredClientList().contains(clientBuilder.build()));
    }

    @Test
    public void execute_undoClientRoleCommand_success() throws CommandException {
        // Add a new role for a client
        ClientRoles.addClientRole(new ClientRoles("NewRole"));
        model.commitAddressBook(model, "New role for client added: NewRole", TabIndex.Client);

        // Undo the role addition
        undoCommand.execute(model);

        assertFalse(ClientRoles.isValidRole("NewRole"));
    }

    @Test
    public void execute_undoNonDeveloperRoleCommand_success() throws CommandException {
        // Add a new role for a client
        DeveloperBuilder developerBuilder = new DeveloperBuilder();
        model.addDeveloper(developerBuilder.build());
        model.commitAddressBook(model, "New Developer Added", TabIndex.Developer);

        // Undo the role addition
        undoCommand.execute(model);

        assertFalse(model.getFilteredClientList().contains(developerBuilder.build()));
    }

    @Test
    public void execute_undoDeveloperRoleCommand_success() throws CommandException {
        // Add a new role for a client
        DeveloperRoles.addDeveloperRole(new DeveloperRoles("NewRole"));
        model.commitAddressBook(model, "New role for developer added: NewRole", TabIndex.Developer);

        // Undo the role addition
        undoCommand.execute(model);

        assertFalse(DeveloperRoles.isValidRole("NewRole"));
    }

    @Test
    public void execute_undoDeleteClientRoleCommand_success() throws CommandException {
        ClientRoles.addClientRole(new ClientRoles("NewRole"));
        model.commitAddressBook(model, "New role for client added: NewRole", TabIndex.Client);

        // Delete a role for a client
        ClientRoles.deleteClientRole(new ClientRoles("NewRole"));
        model.commitAddressBook(model, "Role for clients deleted: NewRole", TabIndex.Client);

        // Undo the role deletion
        undoCommand.execute(model);

        assertTrue(ClientRoles.isValidRole("NewRole"));
        ClientRoles.deleteClientRole(new ClientRoles("NewRole"));
    }

    @Test
    public void execute_undoDeleteDeveloperRoleCommand_success() throws CommandException {
        DeveloperRoles.addDeveloperRole(new DeveloperRoles("RoleToDelete"));
        model.commitAddressBook(model, "New role for client added: NewRole", TabIndex.Developer);

        // Delete a role for a developer
        DeveloperRoles.deleteDeveloperRole(new DeveloperRoles("RoleToDelete"));
        model.commitAddressBook(model, "Role for developers deleted: RoleToDelete", TabIndex.Developer);

        // Undo the role deletion
        undoCommand.execute(model);

        assertTrue(DeveloperRoles.isValidRole("RoleToDelete"));
        DeveloperRoles.deleteDeveloperRole(new DeveloperRoles("RoleToDelete"));
    }
}
