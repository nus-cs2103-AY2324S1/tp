package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SCHEDULES;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.Status;

/**
 * Sets the status of an existing schedule to missed or completed in the address book.
 */
public class MarkScheduleCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sets the status of the schedule identified to be missed for 0 and completed for 1.\n"
            + "Parameters: "
            + "INDEX (must be a positive integer)"
            + PREFIX_STATUS + "0 or 1\n"
            + "Example: "
            + COMMAND_WORD + " 1 "
            + PREFIX_STATUS + " 1 ";

    public static final String MESSAGE_MARK_MISSED_SUCCESS = "Marked Schedule as Missed: %1$s";

    public static final String MESSAGE_MARK_COMPLETED_SUCCESS = "Marked Schedule as Completed: %1$s";

    private final Index index;

    private final int status;

    /**
     * @param index of the schedule in the filtered schedule list to edit the status
     * @param status to determine whether to mark the schedule as missed or completed
     */
    public MarkScheduleCommand(Index index, int status) {
        requireNonNull(index);

        this.index = index;
        this.status = status;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Schedule> lastShownList = model.getFilteredScheduleList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SCHEDULE_DISPLAYED_INDEX);
        }

        Schedule scheduleToEdit = lastShownList.get(index.getZeroBased());
        Schedule editedSchedule;

        if (status == 0) {
            editedSchedule = new Schedule(scheduleToEdit.getTutor(), scheduleToEdit.getStartTime(),
                    scheduleToEdit.getEndTime(), Status.MISSED);
            model.setSchedule(scheduleToEdit, editedSchedule);
            model.updateFilteredScheduleList(PREDICATE_SHOW_ALL_SCHEDULES);
            return new CommandResult(String.format(MESSAGE_MARK_MISSED_SUCCESS, Messages.format(scheduleToEdit)));
        } else if (status == 1) {
            editedSchedule = new Schedule(scheduleToEdit.getTutor(), scheduleToEdit.getStartTime(),
                    scheduleToEdit.getEndTime(), Status.COMPLETED);
            model.setSchedule(scheduleToEdit, editedSchedule);
            model.updateFilteredScheduleList(PREDICATE_SHOW_ALL_SCHEDULES);
            return new CommandResult(String.format(MESSAGE_MARK_COMPLETED_SUCCESS, Messages.format(scheduleToEdit)));
        } else {
            throw new CommandException(Messages.MESSAGE_INVALID_SCHEDULE_STATUS_INDEX);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handle nulls
        if (!(other instanceof MarkScheduleCommand)) {
            return false;
        }

        // state check
        MarkScheduleCommand e = (MarkScheduleCommand) other;
        return index.equals(e.index)
                && (status == e.status);
    }
}
