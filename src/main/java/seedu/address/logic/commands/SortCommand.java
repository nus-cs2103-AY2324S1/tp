package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/** Sorts the current list by the desired field */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the list of employees from the last listing by the specified field in the specified order"
            + "(name / salary / overtime / leaves).\n"
            + "Parameters: "
            + PREFIX_FIELD + "FIELD "
            + PREFIX_ORDER + "ORDER\n"
            + "Example: " + COMMAND_WORD + " f/salary in/ascending";

    public static final String MESSAGE_SUCCESS = "Successfully sorted employees by %1$s. ";
    public static final String MESSAGE_NO_FIELD = "There needs to be an field to sort the list by. ";
    public static final String MESSAGE_WRONG_FIELD = "Field %1$s cannot be used to sort the list. ";
    public static final String MESSAGE_NO_ORDER = "There needs to be an order of sorting the list. ";
    public static final String MESSAGE_WRONG_ORDER = "Only orders ascending and descending is accepted.";


    private final String field;
    private final String order;

    /** Constructs a new SortCommand object */
    public SortCommand(String field, String order) {
        requireAllNonNull(field, order);

        this.field = field;
        this.order = order;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        switch (field.toLowerCase()) {
        case "":
            throw new CommandException(String.format(MESSAGE_NO_FIELD, field));
        case "salary":
            handleSortingOrder(model, "salary", order);
            break;
        case "name":
            handleSortingOrder(model, "name", order);
            break;
        case "overtime":
            handleSortingOrder(model, "overtime", order);
            break;
        case "leaves":
            handleSortingOrder(model, "leaves", order);
            break;
        default:
            throw new CommandException(String.format(MESSAGE_WRONG_FIELD, field));
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, field));
    }

    /**
     * Determines whether the list will be sorted in ascending or descending order
     * and calls the corresponding function
     *
     * @param field List will be sorted by this field
     * @param order Order of sorting
     */
    private void handleSortingOrder(Model model, String field, String order) throws CommandException {
        switch (order.toLowerCase()) {
        case "":
            throw new CommandException(String.format(MESSAGE_NO_ORDER, order));
        case "ascending":
            model.updateSortedEmployeeListAscending(field);
            break;
        case "descending":
            model.updateSortedEmployeeListDescending(field);
            break;
        default:
            throw new CommandException(String.format(MESSAGE_WRONG_ORDER, order));
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        //instanceof handles nulls
        if (!(other instanceof SortCommand)) {
            return false;
        }

        SortCommand e = (SortCommand) other;
        return field.equals(e.field) && order.equals(e.order);
    }
}
