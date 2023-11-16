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
import seedu.address.model.developer.DeveloperRoles;

/**
 * Represents a command to add a new role for developers to the address book.
 * This allows users to define custom roles for developers.
 */
public class AddDeveloperRoleCommand extends Command {
    public static final String COMMAND_WORD = "add-developer-role";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a role for developers to the address book. "
            + "Parameters: " + PREFIX_ROLE + "ROLE "
            + "Example: " + PREFIX_ROLE + "Developer ";

    public static final String MESSAGE_SUCCESS = "New role for developer added: %1$s";
    public static final String MESSAGE_DUPLICATE_ROLE = "This developer role already exists in the address book!";
    private final String toAdd;

    /**
     * Creates an AddDeveloperRoleCommand to add a new developer role with the specified name.
     *
     * @param role The name of the new developer role to be added.
     */
    public AddDeveloperRoleCommand(String role) {
        requireNonNull(role);
        toAdd = role;
    }

    /**
     * Executes the operation to add a new developer role to the address book.
     *
     * @param model The model that contains the address book data.
     * @return A CommandResult indicating the success or failure of the operation.
     * @throws CommandException If an error occurs during the execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (DeveloperRoles.isValidRole(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ROLE);
        }

        String successMessage = String.format(MESSAGE_SUCCESS, Messages.format(toAdd));
        TabIndex index = TabIndex.Developer;

        DeveloperRoles newRole = new DeveloperRoles(toAdd.toString());
        DeveloperRoles.addDeveloperRole(newRole);
        model.commitAddressBook(model, successMessage, index);
        return new CommandResult(successMessage, index);
    }

    /**
     * Checks if this AddDeveloperRoleCommand is equal to another object.
     *
     * @param other The object to compare with this AddDeveloperRoleCommand.
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

        AddDeveloperRoleCommand otherAddDeveloperRoleCommand = (AddDeveloperRoleCommand) other;
        return toAdd.equals(otherAddDeveloperRoleCommand.toAdd);
    }

    /**
     * Generates a string representation of this AddDeveloperRoleCommand.
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
