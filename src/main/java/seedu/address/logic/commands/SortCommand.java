package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.SortComparator;

/**
 * Sorts all applicants by the given field name.
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all applicants by the given field name.\n"
            + "Parameters: FIELD_NAME\n"
            + "Example: " + COMMAND_WORD + " gpa";

    public static final String MESSAGE_SUCCESS = "Sorted all applicants";

    public static final String MESSAGE_EMPTY_LIST = "No applicants to sort.";

    public final String fieldName;

    /**
     * Constructor for SortCommand.
     * @param fieldName the field name to sort by.
     */
    public SortCommand(String fieldName) {
        requireNonNull(fieldName);
        this.fieldName = fieldName;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.getFilteredPersonList().isEmpty()) {
            throw new CommandException(MESSAGE_EMPTY_LIST);
        }
        Comparator<Person> comparator = new SortComparator(fieldName);
        model.sortFilteredPersonList(comparator);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof SortCommand)) {
            return false;
        }
        SortCommand otherSortCommand = (SortCommand) other;
        return fieldName.equals(otherSortCommand.fieldName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("fieldName", fieldName).toString();
    }
}
