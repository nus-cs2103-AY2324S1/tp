package seedu.address.logic.commands;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

import java.time.LocalDateTime;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_START_DATE_TIME;

public class DeleteEventCommand extends Command {
    private final LocalDateTime eventTime;

    public static final String COMMAND_WORD = "deleteEvent";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes an event from the calendar. "
            + "Parameters: "
            + PREFIX_EVENT_DESCRIPTION + "DESCRIPTION "
            + PREFIX_EVENT_START_DATE_TIME + "START DATE AND TIME "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_EVENT_DESCRIPTION + "Contemplate Existence "
            + PREFIX_EVENT_START_DATE_TIME + "2024-01-01 12:00 ";

    public static final String MESSAGE_SUCCESS = "Event deleted: %1$s";

    public static final String MESSAGE_NO_EVENT = "This is not a valid existing event.";

    public DeleteEventCommand(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        return new CommandResult("WIP");
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
