package seedu.address.logic.commands.assignment;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_ASSIGNMENT_PLANNED_DATE_AFTER_END_DATE;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.assignment.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.assignment.CliSyntax.PREFIX_ENDDATE;
import static seedu.address.logic.parser.assignment.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.assignment.CliSyntax.PREFIX_PLANNEDFINISHDATE;
import static seedu.address.logic.parser.assignment.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.assignment.CliSyntax.PREFIX_TAG;

import java.time.LocalDateTime;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;


/**
 * Adds an assignment to the
 * current list of assignments when executed.
 */
public class AddAssignmentCommand extends AssignmentCommand {

    public static final String COMMAND_WORD = "add-a";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds an assignment to the app. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_ENDDATE + "DEADLINE IN YYYY-MM-DD [HH:mm] FORMAT "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION" + "]"
            + "[" + PREFIX_STATUS + "STATUS " + "]\n"
            + "[" + PREFIX_PLANNEDFINISHDATE + "PLAN TO FINISH BY IN YYYY-MM-DD [HH:mm] FORMAT" + "] "
            + "[" + PREFIX_TAG + "TAG" + "]\n"
            + "Example:"
            + " add-a n/Assignment 1 d/description e/2023-12-18 19:00 p/2023-12-17 18:00 t/group s/incomplete";

    public static final String MESSAGE_SUCCESS = "New assignment added: %1$s";

    public static final String MESSAGE_DUPLICATE_ASSIGNMENT = "This assignment already exists in the address book";

    private final Assignment toAdd;

    /**
     * @param assignment to be added
     */
    public AddAssignmentCommand(Assignment assignment) {
        requireNonNull(assignment);
        this.toAdd = assignment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasAssignment(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ASSIGNMENT);
        }

        if (toAdd.getPlannedFinishDate().getDate().isPresent()) {
            LocalDateTime endDate = toAdd.getEnd().getDate().get();
            LocalDateTime plannedFinishDate = toAdd.getPlannedFinishDate().getDate().get();
            if (endDate.isBefore(plannedFinishDate)) {
                throw new CommandException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_ASSIGNMENT_PLANNED_DATE_AFTER_END_DATE));
            }
        }

        model.addAssignment(toAdd);
        model.sortAssignments();
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddAssignmentCommand)) {
            return false;
        }

        AddAssignmentCommand otherAddCommand = (AddAssignmentCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }

}
