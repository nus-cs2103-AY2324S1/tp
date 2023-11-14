package seedu.address.logic.commands.assignment;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.assignment.CliSyntax.PREFIX_ENDFILTER;
import static seedu.address.logic.parser.assignment.CliSyntax.PREFIX_STARTFILTER;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ASSIGNMENTS;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentBetweenStartandEndPredicate;

/**
 * Lists all persons in the address book to the user.
 */
public class ListAssignmentCommand extends AssignmentCommand {

    public static final String COMMAND_WORD = "list-a";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists assignments saved between the specified dates, if provided. "
            + "Parameters: "
            + "[" + PREFIX_STARTFILTER + "YYYY-MM-DD HH:mm" + "]\n"
            + "[" + PREFIX_ENDFILTER + "YYYY-MM-DD HH:mm" + "]\n"
            + "Example: " + COMMAND_WORD + " s/2023-06-18 e/2023-07-18";

    private final Predicate<Assignment> predicate;

    public ListAssignmentCommand() {
        this.predicate = PREDICATE_SHOW_ALL_ASSIGNMENTS;
    }

    public ListAssignmentCommand(AssignmentBetweenStartandEndPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setView(correctViewNeeded);

        model.updateFilteredAssignmentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_ASSIGNMENTS_LISTED_OVERVIEW, model.getFilteredAssignmentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ListAssignmentCommand)) {
            return false;
        }

        ListAssignmentCommand otherCommand = (ListAssignmentCommand) other;
        return predicate.equals(otherCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
