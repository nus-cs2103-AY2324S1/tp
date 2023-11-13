package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_END_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_START_DATE_TIME;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;

/**
 * Adds an event to the calendar of an existing person in the address book.
 */
public class AddContactEventCommand extends Command {

    public static final String COMMAND_WORD = "addContactEvent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an event to the calendar of the person "
            + "identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_EVENT_DESCRIPTION + "DESCRIPTION "
            + PREFIX_EVENT_START_DATE_TIME + "START DATE AND TIME "
            + PREFIX_EVENT_END_DATE_TIME + "END DATE AND TIME...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_EVENT_DESCRIPTION + "Nap "
            + PREFIX_EVENT_START_DATE_TIME + "2024-01-01 12:00 "
            + PREFIX_EVENT_END_DATE_TIME + "2024-01-01 18:00";

    public static final String MESSAGE_ADD_EVENT_TO_PERSON_SUCCESS = "New event added to %s: %2$s";
    public static final String MESSAGE_EVENT_CONFLICT = "This event is conflicting with another event";

    private final Index index;
    private final Event event;

    /**
     * @param index of the person in the filtered person list to add an event to
     * @param event event to be added to the person
     */
    public AddContactEventCommand(Index index, Event event) {
        requireAllNonNull(index, event);

        this.index = index;
        this.event = event;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        // Gets the person which the event should be added to
        Person personToEdit = lastShownList.get(index.getZeroBased());
        if (!personToEdit.canAddEvent(event)) {
            throw new CommandException(MESSAGE_EVENT_CONFLICT);
        }

        personToEdit.addEvent(event);

        return new CommandResult(String.format(MESSAGE_ADD_EVENT_TO_PERSON_SUCCESS,
                personToEdit.getName(), Messages.format(event)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddContactEventCommand)) {
            return false;
        }

        AddContactEventCommand otherAddContactEventCommand = (AddContactEventCommand) other;
        return index.equals(otherAddContactEventCommand.index)
                && event.equals(otherAddContactEventCommand.event);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("event", event)
                .toString();
    }
}
