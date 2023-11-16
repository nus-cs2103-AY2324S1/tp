package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.ClientRoles;
import seedu.address.model.developer.DeveloperRoles;

/**
 * Represents a command to redo the most recent undone command in the address book.
 * It restores the state of the address book to the state before the last undo operation.
 */
public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_SUCCESS = "Redo successful! The change below has been redone:";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.redoAddressBook(model);
        String previousCommand = model.getPreviousCommandForRedo();
        TabIndex index = model.getPreviousTabIndexForRedo();
        handleRoleRedo(previousCommand);
        return new CommandResult(MESSAGE_SUCCESS + "\n" + previousCommand, index);
    }

    private void handleRoleRedo(String previousCommand) {
        if (previousCommand.contains("New role for client added: ")) {
            ClientRoles.addClientRole(new ClientRoles(previousCommand.substring(27)));
        } else if (previousCommand.contains("New role for developer added: ")) {
            DeveloperRoles.addDeveloperRole(new DeveloperRoles(previousCommand.substring(30)));
        } else if (previousCommand.contains("Role for clients deleted: ")) {
            ClientRoles.deleteClientRole(new ClientRoles(previousCommand.substring(26)));
        } else if (previousCommand.contains("Role for developers deleted: ")) {
            DeveloperRoles.deleteDeveloperRole(new DeveloperRoles(previousCommand.substring(29)));
        }
    }
}
