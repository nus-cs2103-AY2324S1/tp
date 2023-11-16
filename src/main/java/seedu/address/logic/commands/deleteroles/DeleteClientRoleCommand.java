package seedu.address.logic.commands.deleteroles;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.TabIndex;
import seedu.address.logic.commands.delete.DeleteClientCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.ClientRoles;

/**
 * Represents a command to delete a role for clients from the address book.
 * This allows users to remove custom roles for clients.
 */
public class DeleteClientRoleCommand extends Command {
    public static final String COMMAND_WORD = "delete-client-role";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete a role for clients in the address book. "
            + "Parameters: " + PREFIX_ROLE + "ROLE "
            + "Example: " + PREFIX_ROLE + "Developer ";

    public static final String MESSAGE_SUCCESS = "Role for clients deleted: %1$s";
    public static final String MESSAGE_CANNOT_DELETE_REPEAT = "This client role cannot be deleted "
            + "as there are clients of this role";
    public static final String MESSAGE_CANNOT_DELETE_PREXISTS = "You are not allowed to delete this client role.";
    public static final String MESSAGE_CANNOT_DELETE_NONEXISTING = "This client role does not exist. ";
    public static final String MESSAGE_EXISTING_CLIENT_ROLES = "These are the existing client roles: \n";

    private final String toAdd;

    /**
     * Creates a DeleteClientRoleCommand to delete a specific client role by its name.
     *
     * @param role The name of the client role to be deleted.
     */
    public DeleteClientRoleCommand(String role) {
        requireNonNull(role);
        toAdd = role;
    }

    /**
     * Executes the operation to delete a client role from the address book.
     *
     * @param model The model that contains the address book data.
     * @return A CommandResult indicating the success or failure of the operation.
     * @throws CommandException If an error occurs during the execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (ClientRoles.isRemovableRole(model, toAdd)) { //if when you search for the role result != 1
            if (ClientRoles.isNotInList()) {
                throw new CommandException(MESSAGE_CANNOT_DELETE_NONEXISTING + MESSAGE_EXISTING_CLIENT_ROLES
                        + ClientRoles.printRoles());
            } else if (!ClientRoles.isNotDefault()) {
                throw new CommandException(MESSAGE_CANNOT_DELETE_PREXISTS);
            } else if (!ClientRoles.isNoRepeat()) {
                throw new CommandException(MESSAGE_CANNOT_DELETE_REPEAT);
            }
        }

        String successMessage = String.format(MESSAGE_SUCCESS, Messages.format(toAdd));
        TabIndex index = TabIndex.Client;

        ClientRoles newRole = new ClientRoles(toAdd.toString());
        ClientRoles.deleteClientRole(newRole);
        model.commitAddressBook(model, successMessage, index);
        return new CommandResult(successMessage, index);
    }

    /**
     * Checks if this DeleteClientRoleCommand is equal to another object.
     *
     * @param other The object to compare with this DeleteClientRoleCommand.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteClientCommand)) {
            return false;
        }

        DeleteClientRoleCommand otherDeleteClientRoleCommand = (DeleteClientRoleCommand) other;
        return toAdd.equals(otherDeleteClientRoleCommand.toAdd);
    }

    /**
     * Generates a string representation of this DeleteClientRoleCommand.
     *
     * @return A string representation of this object.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
