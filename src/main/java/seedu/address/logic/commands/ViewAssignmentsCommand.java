package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Shows a list of assignment details belonging to a person.
 */
public class ViewAssignmentsCommand extends AssignmentCommand {

    public static final String COMMAND_WORD = "viewassignments";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows a list of assignment details belonging to a "
            + "person, identified by the index number used in the displayed person list. "
            + "Parameters: STUDENT_INDEX (must be a positive integer) ";

    public static final String MESSAGE_SUCCESS = "Showing all assignment details of: %1$s";

    public static final CommandType COMMAND_TYPE = CommandType.VIEW_ASSIGNMENTS;

    private final Index index;

    /**
     * Creates a ViewAssignmentsCommand to show a list of assignment details belonging to the specified person.
     */
    public ViewAssignmentsCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person person = lastShownList.get(index.getZeroBased());

        return new CommandResult(String.format(MESSAGE_SUCCESS, person.getName()), COMMAND_TYPE);
    }

    @Override
    public CommandType getCommandType() {
        return COMMAND_TYPE;
    }

    @Override
    public Index getIndex() {
        return this.index;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewAssignmentsCommand)) {
            return false;
        }

        ViewAssignmentsCommand otherViewAssignmentsCommand = (ViewAssignmentsCommand) other;
        return index.equals(otherViewAssignmentsCommand.index);
    }
}
