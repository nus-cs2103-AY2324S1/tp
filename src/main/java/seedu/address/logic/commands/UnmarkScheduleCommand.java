

package seedu.address.logic.commands;
import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SCHEDULES;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.Status;

/**
 * Sets the status of an existing schedule to pending in the address book.
 */
public class UnmarkScheduleCommand extends Command {

    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets the status of the schedule identified to be "
        + "pending.\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_UNMARK_SUCCESS = "Unmarked Schedule: %1$s";

    private final Index index;

    /**
     * @param index of the schedule in the filtered schedule list to edit the status
     */
    public UnmarkScheduleCommand(Index index) {
        requireNonNull(index);

        this.index = index;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Schedule> lastShownList = model.getFilteredScheduleList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SCHEDULE_DISPLAYED_INDEX);
        }

        Schedule scheduleToEdit = lastShownList.get(index.getZeroBased());
        Schedule editedSchedule = new Schedule(scheduleToEdit.getTutor(), scheduleToEdit.getStartTime(),
            scheduleToEdit.getEndTime(), Status.PENDING);

        model.setSchedule(scheduleToEdit, editedSchedule);
        model.updateFilteredScheduleList(PREDICATE_SHOW_ALL_SCHEDULES);

        return new CommandResult(String.format(MESSAGE_UNMARK_SUCCESS, Messages.format(scheduleToEdit)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UnmarkScheduleCommand)) {
            return false;
        }

        // state check
        UnmarkScheduleCommand e = (UnmarkScheduleCommand) other;
        return index.equals(e.index);
    }
}
