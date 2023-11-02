package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_START_DATE_TIME;

import java.time.LocalDateTime;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;

/**
 * Delete an event from the calendar of an existing person in the address book.
 */
public class DeleteContactEventCommand extends Command {

    public static final String COMMAND_WORD = "deleteContactEvent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes an event from the calendar of the person "
            + "identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_EVENT_START_DATE_TIME + "ANY TIME WITHIN EVENT DURATION \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_EVENT_START_DATE_TIME + "2024-01-01 12:00 ";

    public static final String MESSAGE_DELETE_EVENT_FROM_PERSON_SUCCESS = "Event deleted from %s: %2$s";
    public static final String MESSAGE_NO_EVENT = "There is no valid existing event at this timing.";

    private final Index index;
    private final LocalDateTime eventTime;

    /**
     * @param index of the person in the filtered person list to delete an event from.
     * @param eventTime time of the event to delete.
     */
    public DeleteContactEventCommand(Index index, LocalDateTime eventTime) {
        requireAllNonNull(index, eventTime);

        this.index = index;
        this.eventTime = eventTime;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        //Obtain the calendar of the person and tries to delete an event from the person's calendar
        Person personToEdit;
        try {
            personToEdit = lastShownList.get(index.getZeroBased());
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Event toDelete;

        try {
            toDelete = personToEdit.findEvent(eventTime);
            personToEdit.deleteEvent(eventTime);
        } catch (Exception e) {
            throw new CommandException(MESSAGE_NO_EVENT);
        }
        return new CommandResult(String.format(MESSAGE_DELETE_EVENT_FROM_PERSON_SUCCESS,
                personToEdit.getName(), Messages.format(toDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteContactEventCommand)) {
            return false;
        }

        DeleteContactEventCommand otherDeleteContactEventCommand = (DeleteContactEventCommand) other;
        return index.equals(otherDeleteContactEventCommand.index)
                && eventTime.equals(otherDeleteContactEventCommand.eventTime);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("eventTime", eventTime)
                .toString();
    }
}
