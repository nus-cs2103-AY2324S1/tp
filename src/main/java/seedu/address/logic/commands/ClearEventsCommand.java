package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONFIRMATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_END_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_START_DATE_TIME;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventPeriod;

/**
 * Deletes all events within a time range in the calendar.
 * Also uses a confirmation check to ensure that events are not accidentally deleted
 */
public class ClearEventsCommand extends Command {
    public static final String COMMAND_WORD = "clearEvents";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes events within a time range from the calendar. "
            + "Shows all events within the time range instead if confirmation is not present.\n"
            + "Parameters: "
            + PREFIX_EVENT_START_DATE_TIME + "START DATE AND TIME "
            + PREFIX_EVENT_END_DATE_TIME + "END DATE AND TIME ["
            + PREFIX_CONFIRMATION + "CONFIRMATION]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_EVENT_START_DATE_TIME + "2024-01-01 12:00 "
            + PREFIX_EVENT_END_DATE_TIME + "2024-01-01 18:00";

    public static final String MESSAGE_SUCCESS = "The following events within the time period have been deleted:\n";
    public static final String MESSAGE_NO_EVENTS = "There are no events within this time period.";
    public static final String MESSAGE_WITHIN_RANGE = "The following events are within the time period:\n";
    public static final String MESSAGE_ADD_CONFIRMATION = "Reenter the command with [c/CONFIRMED] at the end to "
            + "confirm deletion.\n";
    public static final String COMMAND_RESEND_FORMAT = "clearEvents ts/%s te/%s c/CONFIRMED";

    private final EventPeriod clearPeriod;
    private final boolean isConfirmed;

    /**
     * Creates a ClearEventsCommand to delete all events within a time range from the calendar.
     *
     * @param clearPeriod The time period for which to delete all events.
     * @param isConfirmed A boolean to check if the deletion is confirmed by the user.
     */
    public ClearEventsCommand(EventPeriod clearPeriod, boolean isConfirmed) {
        requireAllNonNull(clearPeriod, isConfirmed);
        this.clearPeriod = clearPeriod;
        this.isConfirmed = isConfirmed;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Event> eventsInRange = model.eventsInRange(clearPeriod);
        if (eventsInRange.isEmpty()) {
            throw new CommandException(MESSAGE_NO_EVENTS);
        }
        StringBuilder sb;

        if (isConfirmed) {
            sb = new StringBuilder(MESSAGE_SUCCESS);
        } else {
            sb = new StringBuilder(MESSAGE_WITHIN_RANGE);
        }

        int i = 1;
        for (Event event:eventsInRange) {
            sb.append(String.format("%d. %s\n", i, Messages.format(event)));
            i++;
        }
        if (isConfirmed) {
            model.deleteEventsInRange(clearPeriod);
        } else {
            sb.append(MESSAGE_ADD_CONFIRMATION);
            sb.append(String.format(COMMAND_RESEND_FORMAT,
                    clearPeriod.getFormattedStart(),
                    clearPeriod.getFormattedEnd()));
        }
        return new CommandResult(sb.toString());
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
                .add("confirmed", isConfirmed)
                .toString();
    }
}
