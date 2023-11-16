package seedu.address.logic.commands.addroles;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.TabIndex;
import seedu.address.logic.commands.add.AddDeveloperCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.ClientRoles;
/**
 * Represents a command to add a new role for clients to the address book.
 * This allows users to define custom roles for clients.
 */
public class AddClientRoleCommand extends Command {
    public static final String COMMAND_WORD = "add-client-role";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a role for clients to the address book. "
            + "Parameters: " + PREFIX_ROLE + "ROLE "
            + "Example: " + PREFIX_ROLE + "Developer ";

    public static final String MESSAGE_SUCCESS = "New role for client added: %1$s";
    public static final String MESSAGE_DUPLICATE_ROLE = "This client role already exists in the address book!";
    private final String toAdd;

    /**
     * Creates an AddClientRoleCommand to add a new client role with the specified name.
     *
     * @param role The name of the new client role to be added.
     */
    public AddClientRoleCommand(String role) {
        requireNonNull(role);
        toAdd = role;
    }

    /**
     * Executes the operation to add a new client role to the address book.
     *
     * @param model The model that contains the address book data.
     * @return A CommandResult indicating the success or failure of the operation.
     * @throws CommandException If an error occurs during the execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (ClientRoles.isValidRole(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ROLE);
        }

        String successMessage = String.format(MESSAGE_SUCCESS, Messages.format(toAdd));
        TabIndex index = TabIndex.Client;

        ClientRoles newRole = new ClientRoles(toAdd.toString());
        ClientRoles.addClientRole(newRole);
        model.commitAddressBook(model, successMessage, index);
        return new CommandResult(successMessage, index);
    }

    /**
     * Checks if this AddClientRoleCommand is equal to another object.
     *
     * @param other The object to compare with this AddClientRoleCommand.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddDeveloperCommand)) {
            return false;
        }

        AddClientRoleCommand otherAddClientRoleCommand = (AddClientRoleCommand) other;
        return toAdd.equals(otherAddClientRoleCommand.toAdd);
    }

    /**
     * Generates a string representation of this AddClientRoleCommand.
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
