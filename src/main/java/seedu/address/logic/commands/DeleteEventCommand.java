package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_ID;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

/**
 * Deletes an event identified using it's displayed index from EventWise.
 */
public class DeleteEventCommand extends Command {

    public static final String COMMAND_WORD = "deleteEvent";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the event identified by the index number used in the displayed event list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_EVENT_ID + "1";

    public static final String MESSAGE_DELETE_EVENT_SUCCESS = "Deleted Event %1$d: %2$s";

    private final Index targetIndex;

    public DeleteEventCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> lastShownList = model.getFilteredEventsList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Event eventToDelete = lastShownList.get(targetIndex.getZeroBased());
        // Before you delete an event, you also have to remove RSVPs that exist for the event

        model.deleteEvent(eventToDelete);
        String eventDetails = String.format("%s; Description: %s; Date: %s\n",
                eventToDelete.getName(), eventToDelete.getDescription(), eventToDelete.getFromDate());

        // Clear set event to view
        model.setEventToView(null);
        model.updateFilteredEventList(Model.PREDICATE_SHOW_ALL_EVENTS);

        return new CommandResult(String.format(MESSAGE_DELETE_EVENT_SUCCESS, targetIndex.getOneBased(), eventDetails));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeleteEventCommand)) {
            return false;
        }

        DeleteEventCommand otherDeleteCommand = (DeleteEventCommand) other;
        return targetIndex.equals(otherDeleteCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
