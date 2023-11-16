package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.ClientRoles;
import seedu.address.model.developer.DeveloperRoles;

/**
 * Represents a command to undo the most recent change in the application.
 * This allows users to revert the effects of the previous command.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_SUCCESS = "Undo successful! The change below has been undone: ";

    /**
     * Executes the undo operation by restoring the previous state of the model and
     * retrieving information about the undone command.
     *
     * @param model The model from which to undo the most recent change.
     * @return A CommandResult indicating the success of the undo operation and the
     *         details of the undone command.
     * @throws CommandException If an error occurs during the undo operation.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.undoAddressBook(model);
        String previousCommand = model.getPreviousCommandForUndo();
        TabIndex index = model.getPreviousTabIndex();
        // check if it is any of the role commands
        handleRoleUndo(previousCommand);
        return new CommandResult(MESSAGE_SUCCESS + "\n" + previousCommand, index);
    }

    private void handleRoleUndo(String previousCommand) {
        if (previousCommand.contains("New role for client added: ")) {
            ClientRoles.deleteClientRole(new ClientRoles(previousCommand.substring(27)));
        } else if (previousCommand.contains("New role for developer added: ")) {
            DeveloperRoles.deleteDeveloperRole(new DeveloperRoles(previousCommand.substring(30)));
        } else if (previousCommand.contains("Role for clients deleted: ")) {
            ClientRoles.addClientRole(new ClientRoles(previousCommand.substring(26)));
        } else if (previousCommand.contains("Role for developers deleted: ")) {
            DeveloperRoles.addDeveloperRole(new DeveloperRoles(previousCommand.substring(29)));
        }
    }
}
