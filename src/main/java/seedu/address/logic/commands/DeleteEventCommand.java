package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_START_DATE_TIME;

import java.time.LocalDateTime;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.event.exceptions.EventNotFoundException;

/**
 * Deletes an event from the calendar.
 */
public class DeleteEventCommand extends Command {
    public static final String COMMAND_WORD = "deleteEvent";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes an event from the calendar. "
            + "Parameters: "
            + "ANY TIME WITHIN EVENT DURATION \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_EVENT_START_DATE_TIME + "2024-01-01 12:00 ";

    public static final String MESSAGE_SUCCESS = "Event deleted: %1$s";
    public static final String MESSAGE_NO_EVENT = "There is no valid existing event at this timing.";
    private final LocalDateTime eventTime;

    /**
     * Creates a DeleteEventCommand to delete an event from the calendar.
     *
     * @param eventTime time of the event to delete.
     */
    public DeleteEventCommand(LocalDateTime eventTime) {
        requireNonNull(eventTime);
        this.eventTime = eventTime;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Event toDelete;
        try {
            toDelete = model.findEventAt(eventTime);
            model.deleteEventAt(eventTime);
        } catch (EventNotFoundException e) {
            throw new CommandException(MESSAGE_NO_EVENT);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteEventCommand)) {
            return false;
        }

        DeleteEventCommand otherDeleteCommand = (DeleteEventCommand) other;
        return eventTime.equals(otherDeleteCommand.eventTime);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toDeleteAt", eventTime)
                .toString();
    }
}
