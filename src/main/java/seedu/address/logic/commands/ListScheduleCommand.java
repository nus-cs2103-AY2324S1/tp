package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SCHEDULES;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.schedule.Status;
import seedu.address.model.schedule.StatusPredicate;
import seedu.address.model.schedule.TutorPredicate;

/**
 * Lists all schedules in the address book to the user.
 */
public class ListScheduleCommand extends Command {

    public static final String COMMAND_WORD = "list-s";

    public static final String MESSAGE_SUCCESS = "Listed all schedules";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": List the schedules in the address book. If index or status is specified, it lists the schedule"
        + "identified by that index number in the displayed tutor list or the status.\n"
        + "Parameters: \nINDEX (must be a positive integer, optional to add) \n"
        + PREFIX_STATUS + "STATUS (0 or 1 value, optional to add)"
        + "Example: \n"
        + COMMAND_WORD + " 1, \n" + COMMAND_WORD + " m/0, \n" + COMMAND_WORD + " 1 m/1";

    private final Index targetIndex;
    private final Status status;

    /**
     * Creates an ListScheduleCommand to list the specified {@code Schedule}
     */
    public ListScheduleCommand(Index targetIndex, Status status) {
        this.targetIndex = targetIndex;
        this.status = status;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person tutor = null;
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex != null) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            tutor = lastShownList.get(targetIndex.getZeroBased());
            TutorPredicate indexPredicate = new TutorPredicate(tutor);
            model.updateFilteredScheduleList(indexPredicate);
        }

        if (status != null) {
            StatusPredicate statusPredicate = new StatusPredicate(status, tutor);
            model.updateFilteredScheduleList(statusPredicate);
        }

        if (targetIndex == null && status == null) {
            model.updateFilteredScheduleList(PREDICATE_SHOW_ALL_SCHEDULES);
            return new CommandResult(MESSAGE_SUCCESS);
        }

        return new CommandResult(String.format(Messages.MESSAGE_SCHEDULES_LISTED_OVERVIEW,
            model.getFilteredScheduleList().size()));
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ListScheduleCommand)) {
            return false;
        }

        if (targetIndex != null) {
            ListScheduleCommand otherFindCommand = (ListScheduleCommand) other;
            return targetIndex.equals(otherFindCommand.targetIndex);
        } else {
            return true;
        }
    }


}
