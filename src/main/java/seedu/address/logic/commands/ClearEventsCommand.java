package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_END_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_START_DATE_TIME;

import java.time.LocalDateTime;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.EventPeriod;

public class ClearEventsCommand extends Command{
    public static final String COMMAND_WORD = "clearEvents";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes events within a time range from the calendar. "
            + "Parameters: "
            + PREFIX_EVENT_START_DATE_TIME + "START DATE AND TIME"
            + PREFIX_EVENT_END_DATE_TIME + "END DATE AND TIME...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_EVENT_START_DATE_TIME + "2024-01-01 12:00 "
            + PREFIX_EVENT_END_DATE_TIME + "2024-01-01 18:00";

    public static final String MESSAGE_SUCCESS = "Events Deleted";

    private final EventPeriod clearPeriod;
    private final boolean isConfirmed;

    public ClearEventsCommand(EventPeriod clearPeriod, boolean isConfirmed) {
        requireAllNonNull(clearPeriod, isConfirmed);
        this.clearPeriod = clearPeriod;
        this.isConfirmed = isConfirmed;
    }

    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        return null;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClearEventsCommand)) {
            return false;
        }

        ClearEventsCommand otherClearEventsCommand = (ClearEventsCommand) other;
        boolean periodIsEqual = clearPeriod.equals(otherClearEventsCommand.clearPeriod);
        boolean confirmationIsEqual = isConfirmed == otherClearEventsCommand.isConfirmed;
        return periodIsEqual && confirmationIsEqual;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toClearWithin", clearPeriod)
                .toString();
    }
}
