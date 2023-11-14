package seedu.address.logic.commands.internship.role;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.internship.InternshipCommand;
import seedu.address.model.Model;
import seedu.address.model.internship.role.InternshipRole;

/**
 * Command to add an InternshipRole
 */
public class AddInternshipRoleCommand extends InternshipCommand {
    public static final String COMMAND_WORD = "add-i-role";

    public static final String MESSAGE_USAGE = "add-i-role n/NAME r/ROLE c/CYCLE "
            + "[d/DESCRIPTION] [p/PAY] [o/awaiting/follow-up/ghosted/rejected/offered/accepted] [l/LOCATION] [t/TAG]";

    public static final String MESSAGE_SUCCESS = "New internship role added: %1$s";

    public static final String MESSAGE_DUPLICATE_ROLE = "This internship role already exists in the address book";

    private final InternshipRole toAdd;

    /**
     * Creates an AddInternshipRole command with the given internship, input cannot be null.
     * @param toAdd the internship role to add
     */
    public AddInternshipRoleCommand(InternshipRole toAdd) {
        requireNonNull(toAdd);
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasInternshipRole(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ROLE);
        }

        model.addInternshipRole(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddInternshipRoleCommand)) {
            return false;
        }

        AddInternshipRoleCommand otherAddCommand = (AddInternshipRoleCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
