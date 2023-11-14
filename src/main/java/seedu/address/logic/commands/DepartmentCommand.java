package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.department.Department;

/**
 * Adds an employee to the ManageHR.
 */
public class DepartmentCommand extends Command {
    /**
     * Action Types for the DepartmentCommand.
     */
    public enum Type {
        ADD,
        DELETE
    }
    public static final String COMMAND_WORD = "department";

    public static final String MESSAGE_EXAMPLE = COMMAND_WORD + " "
            + PREFIX_TYPE + "add "
            + PREFIX_NAME + "Engineering";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds/Delete a department to/from the ManageHR app. "
            + "\nParameters: "
            + "[" + PREFIX_TYPE + "TYPE(add/delete)] "
            + "[" + PREFIX_NAME + "NAME]\n"
            + "\n"
            + "Example: \n" + MESSAGE_EXAMPLE + " ";

    public static final String MESSAGE_ADDED_SUCCESS = "New Department added: %1$s";
    public static final String MESSAGE_DELETE_SUCCESS = "Department deleted: %1$s";
    public static final String MESSAGE_DUPLICATE_DEPARTMENT = "This department already exists in the ManageHR app";
    public static final String MESSAGE_UNDEFINED_DEPARTMENT = "This department does not exist in ManageHR";
    public static final String MESSAGE_UNDEFINED_ACTION_TYPE = "Only add/delete actions are allowed in this context";
    private final Type actionType;
    private final Department department;

    /**
     * Creates an AddCommand to add the specified {@code Employee}
     */
    public DepartmentCommand(Department department, Type type) {
        requireNonNull(department);
        this.department = department;
        this.actionType = type;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        switch (actionType) {
        case ADD:
            if (model.hasDepartment(department)) {
                throw new CommandException(MESSAGE_DUPLICATE_DEPARTMENT);
            }
            model.addDepartment(department);
            return new CommandResult(String.format(MESSAGE_ADDED_SUCCESS, department.getName()));
        case DELETE:
            if (!model.hasDepartment(department)) {
                throw new CommandException(MESSAGE_UNDEFINED_DEPARTMENT);
            }
            model.deleteDepartment(department);
            return new CommandResult(String.format(MESSAGE_DELETE_SUCCESS, department.getName()));
        default:
            throw new CommandException(MESSAGE_UNDEFINED_ACTION_TYPE);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DepartmentCommand)) {
            return false;
        }

        DepartmentCommand otherAddCommand = (DepartmentCommand) other;
        return department.equals(otherAddCommand.department);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("department", department)
                .toString();
    }
}
