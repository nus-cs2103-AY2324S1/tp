package seedu.staffsnap.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_DESCRIPTOR;
import static seedu.staffsnap.model.Model.PREDICATE_HIDE_ALL_EMPLOYEES;
import static seedu.staffsnap.model.Model.PREDICATE_SHOW_ALL_EMPLOYEES;

import seedu.staffsnap.logic.commands.exceptions.CommandException;
import seedu.staffsnap.model.Model;
import seedu.staffsnap.model.employee.Descriptor;


/**
 * Sorts all employees in the address book to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an employee to the address book. "
            + "Parameters: "
            + PREFIX_DESCRIPTOR + "DESCRIPTOR ";
    private static final String MESSAGE_SUCCESS = "Sorted all Employees";

    private final Descriptor descriptor;

    /**
     * Creates a SortCommand to add the specified {@code descriptor}
     */
    public SortCommand(Descriptor descriptor) {
        requireNonNull(descriptor);
        this.descriptor = descriptor;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateSortedEmployeeList(descriptor);
        model.updateFilteredEmployeeList(PREDICATE_HIDE_ALL_EMPLOYEES);
        model.updateFilteredEmployeeList(PREDICATE_SHOW_ALL_EMPLOYEES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
