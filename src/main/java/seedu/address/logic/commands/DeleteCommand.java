package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.Id;

/**
 * Deletes an employee identified using its displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the employee identified by the unique ID in the displayed employee list.\n"
            + "Parameters: ID (must be in the exact EID format)\n"
            + "Example: " + COMMAND_WORD + " EID1234-5678";

    public static final String MESSAGE_DELETE_EMPLOYEE_SUCCESS = "Deleted employee: %1$s";

    private final Id targetId;

    public DeleteCommand(Id targetId) {
        this.targetId = targetId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Employee> employeeList = model.getAddressBook().getEmployeeList();

        for (Employee employee : employeeList) {
            if (employee.getId().equals(targetId)) {
                model.updateFilteredEmployeeList(Model.PREDICATE_SHOW_ALL_EMPLOYEES);
                model.deleteEmployee(employee);
                return new CommandResult(String.format(MESSAGE_DELETE_EMPLOYEE_SUCCESS, Messages.format(employee)));
            }
        }
        throw new CommandException(Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_ID);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return targetId.equals(otherDeleteCommand.targetId);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetId", targetId)
                .toString();
    }
}
