package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_END_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_START_DATE_TIME;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

/**
 * Adds an event to the calendar.
 */
public class AddEventCommand extends Command {

    public static final String COMMAND_WORD = "addEvent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an event to the calendar. "
            + "Parameters: "
            + PREFIX_EVENT_DESCRIPTION + "DESCRIPTION "
            + PREFIX_EVENT_START_DATE_TIME + "START DATE AND TIME "
            + PREFIX_EVENT_END_DATE_TIME + "END DATE AND TIME...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_EVENT_DESCRIPTION + "Nap "
            + PREFIX_EVENT_START_DATE_TIME + "2024-01-01 12:00 "
            + PREFIX_EVENT_END_DATE_TIME + "2024-01-01 18:00";

    public static final String MESSAGE_SUCCESS = "New event added: %1$s";

    public static final String MESSAGE_EVENT_CONFLICT = "This event is conflicting with another event";

    private final Event toAdd;

    /**
     * Creates an AddEventCommand to add the event into the calendar.
     *
     * @param event event to be added.
     */
    public AddEventCommand(Event event) {
        requireNonNull(event);
        toAdd = event;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.canAddEvent(toAdd)) {
            throw new CommandException(MESSAGE_EVENT_CONFLICT);
        }

        model.addEvent(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddEventCommand)) {
            return false;
        }

        AddEventCommand otherAddCommand = (AddEventCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
