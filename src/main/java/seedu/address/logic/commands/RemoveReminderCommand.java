package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.timetable.DatedEvent;

/**
 * Remove reminders of events in user's timetable that will be sent to user.
 * Keyword matching is case insensitive.
 */
public class RemoveReminderCommand extends Command {
    public static final String COMMAND_WORD = "rmReminder";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Remove reminders of events in user's timetable \n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " cs2103t lesson";

    public static final String MESSAGE_NO_EVENT = "No such event exists!";

    public static final String MESSAGE_REMOVE_REMINDER_SUCCESS = "Reminder removed for following event: \n";

    private final String eventName;

    public RemoveReminderCommand(String eventName) {
        this.eventName = eventName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Optional<DatedEvent> event = model.getUser().getDatedEvent(eventName);
        if (event.isEmpty()) {
            throw new CommandException(MESSAGE_NO_EVENT);
        } else {
            model.getUser().removeReminder(event.get());
            StringBuilder sb = new StringBuilder(MESSAGE_REMOVE_REMINDER_SUCCESS);
            sb.append(model.getUser().getDatedEvent(eventName).get().getName());
            return new CommandResult(sb.toString());
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemoveReminderCommand)) {
            return false;
        }

        RemoveReminderCommand otherRemoveCommand = (RemoveReminderCommand) other;
        return eventName.equals(otherRemoveCommand.eventName);
    }

    /**
     * Returns a string representation of the RemoveReminderCommand object.
     * Includes the event name for which the reminder is to be removed.
     *
     * @return String representation of the RemoveReminderCommand object.
     */
    public String toString() {
        return new ToStringBuilder(this)
                .add("Remove Reminder for", eventName)
                .toString();
    }
}
