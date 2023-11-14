package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.employee.EmployeeNameContainsKeywordsPredicate;
import seedu.address.model.project.ProjectDoneByFilteredEmployeesPredicate;

/**
 * Finds and lists all employees in TaskHub whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindEmployeeCommand extends Command {

    public static final String COMMAND_WORD = "findE";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all employees whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final EmployeeNameContainsKeywordsPredicate predicate;

    public FindEmployeeCommand(EmployeeNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEmployeeList(predicate);

        // update project list to contain only projects containing the filtered employees
        final ProjectDoneByFilteredEmployeesPredicate projectDoneByFilteredEmployeesPredicate =
                new ProjectDoneByFilteredEmployeesPredicate(model.getFilteredEmployeeList());
        model.updateFilteredProjectList(projectDoneByFilteredEmployeesPredicate);

        return new CommandResult(
                String.format(Messages.MESSAGE_EMPLOYEES_LISTED_OVERVIEW, model.getFilteredEmployeeList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindEmployeeCommand)) {
            return false;
        }

        FindEmployeeCommand otherFindEmployeeCommand = (FindEmployeeCommand) other;
        return predicate.equals(otherFindEmployeeCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
