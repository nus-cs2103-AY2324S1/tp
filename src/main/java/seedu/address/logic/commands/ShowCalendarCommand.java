package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.schedule.Date;


/**
 * Adds a person to the address book.
 */
public class ShowCalendarCommand extends Command {

    public static final String COMMAND_WORD = "show";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows calendar view for specified date\n"
        + "Parameters: DATE (must be a valid date)\n"
        + "Example: " + COMMAND_WORD + " 2023-09-15";

    public static final String MESSAGE_SUCCESS = "Viewing calendar.";

    private final Date date;

    /**
     * Creates an ShowCalendarCommand to view the specified {@code Date}
     */
    public ShowCalendarCommand(Date date) {
        requireNonNull(date);

        this.date = date;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updateFilteredScheduleList(schedule -> schedule.isOnDate(date));
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ShowCalendarCommand)) {
            return false;
        }

        ShowCalendarCommand otherShowCalendarCommand = (ShowCalendarCommand) other;
        return date.equals(otherShowCalendarCommand.date);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("date", date)
            .toString();
    }
}
