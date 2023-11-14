package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EMPLOYEES;

import seedu.address.model.Model;

/**
 * Lists all employees in the ManageHR app to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all employees";
    public static final String MESSAGE_EXAMPLE = COMMAND_WORD;
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List all employees.\n"
            + "\n"
            + "Example: \n" + MESSAGE_EXAMPLE;


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEmployeeList(PREDICATE_SHOW_ALL_EMPLOYEES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
