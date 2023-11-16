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
import seedu.address.model.developer.DeveloperRoles;

/**
 * Represents a command to delete a role for developers from the address book.
 * This allows users to remove custom roles for developers.
 */
public class DeleteDeveloperRoleCommand extends Command {
    public static final String COMMAND_WORD = "delete-developer-role";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete a role for developers in the address book. "
            + "Parameters: " + PREFIX_ROLE + "ROLE "
            + "Example: " + PREFIX_ROLE + "Developer ";

    public static final String MESSAGE_SUCCESS = "Role for developers deleted: %1$s";
    public static final String MESSAGE_CANNOT_DELETE_REPEAT = "This developer role cannot be deleted "
            + "as there are developers of this role";
    public static final String MESSAGE_CANNOT_DELETE_PREXISTS = "You are not allowed to delete this developer role.";
    public static final String MESSAGE_CANNOT_DELETE_NONEXISTING = "This developer role does not exist. ";
    public static final String MESSAGE_EXISTING_DEVELOPERS_ROLES = "These are the existing developer roles: \n";

    private final String toAdd;

    /**
     * Creates a DeleteDeveloperRoleCommand to delete a specific developer role by its name.
     *
     * @param role The name of the developer role to be deleted.
     */
    public DeleteDeveloperRoleCommand(String role) {
        requireNonNull(role);
        toAdd = role;
    }

    /**
     * Executes the operation to delete a developer role from the address book.
     *
     * @param model The model that contains the address book data.
     * @return A CommandResult indicating the success or failure of the operation.
     * @throws CommandException If an error occurs during the execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (DeveloperRoles.isRemovableRole(model, toAdd)) {
            if (DeveloperRoles.isNotInList()) {
                throw new CommandException(MESSAGE_CANNOT_DELETE_NONEXISTING + MESSAGE_EXISTING_DEVELOPERS_ROLES
                        + DeveloperRoles.printRoles());
            } else if (!DeveloperRoles.isNotDefault()) {
                throw new CommandException(MESSAGE_CANNOT_DELETE_PREXISTS);
            } else if (!DeveloperRoles.isNoRepeat()) {
                throw new CommandException(MESSAGE_CANNOT_DELETE_REPEAT);
            }
        }

        String successMessage = String.format(MESSAGE_SUCCESS, Messages.format(toAdd));
        TabIndex index = TabIndex.Developer;

        DeveloperRoles newRole = new DeveloperRoles(toAdd.toString());
        DeveloperRoles.deleteDeveloperRole(newRole);
        model.commitAddressBook(model, successMessage, index);
        return new CommandResult(successMessage, index);
    }

    /**
     * Checks if this DeleteDeveloperRoleCommand is equal to another object.
     *
     * @param other The object to compare with this DeleteDeveloperRoleCommand.
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

        DeleteDeveloperRoleCommand otherDeleteDeveloperRoleCommand = (DeleteDeveloperRoleCommand) other;
        return toAdd.equals(otherDeleteDeveloperRoleCommand.toAdd);
    }

    /**
     * Generates a string representation of this DeleteDeveloperRoleCommand.
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
