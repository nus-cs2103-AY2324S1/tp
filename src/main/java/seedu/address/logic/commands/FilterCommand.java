package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.employee.ContainsDepartmentPredicate;

/**
 * Filters employees in ManageHR app whose department(s) contains the argument keyword.
 * Keyword matching is case-sensitive.
 */
public class FilterCommand extends Command {
    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all employees whose departments contain any of "
            + "the specified keyword (case-sensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD\n"
            + "Example: " + COMMAND_WORD + " R&D";
    private final ContainsDepartmentPredicate predicate;

    public FilterCommand(ContainsDepartmentPredicate predicate) {
        this.predicate = predicate;
    }
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEmployeeList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_EMPLOYEES_LISTED_OVERVIEW, model.getFilteredEmployeeList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterCommand)) {
            return false;
        }

        FilterCommand otherFilterCommand = (FilterCommand) other;
        return predicate.equals(otherFilterCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
