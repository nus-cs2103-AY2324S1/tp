package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_EMPLOYEES_ON_LEAVE;

import seedu.address.logic.Messages;
import seedu.address.model.Model;

/**
 * Lists all employees in address book who are on leave on the specified date.
 */
public class ListLeaveCommand extends Command {
    public static final String COMMAND_WORD = "listleave";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all employees that are currently on leave."
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEmployeeList(PREDICATE_SHOW_EMPLOYEES_ON_LEAVE);
        return new CommandResult(
                String.format(Messages.MESSAGE_EMPLOYEES_ON_LEAVE_OVERVIEW, model.getFilteredEmployeeList().size()));
    }
}
