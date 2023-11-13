package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;

/**
 * Adds a assignment to the address book.
 */
public class AddAssignmentCommand extends Command {
    public static final String COMMAND_WORD = "addAssignment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a assignment to the list\n"
            + "Parameters: "
            + PREFIX_ASSIGNMENT + "ASSIGNMENT";

    public static final String MESSAGE_SUCCESS = "New assignment added: %s";

    public static final String MESSAGE_DUPLICATE_ASSIGNMENT = "This assignment already exists in the address book";

    private Assignment toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Assignment}
     */
    public AddAssignmentCommand(Assignment assignment) {
        requireNonNull(assignment);
        toAdd = assignment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasAssignment(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ASSIGNMENT);
        }

        model.addAssignment(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddAssignmentCommand)) {
            return false;
        }

        AddAssignmentCommand otherAddAssignmentCommand = (AddAssignmentCommand) other;
        return toAdd.equals(otherAddAssignmentCommand.toAdd);
    }
}
