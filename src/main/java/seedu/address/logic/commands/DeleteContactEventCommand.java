package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.calendar.Calendar;
import seedu.address.model.event.Event;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Delete an event from the calendar of an existing person in the address book.
 */
public class DeleteContactEventCommand extends Command {

    public static final String COMMAND_WORD = "deleteContactEvent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes an event from the calendar of the person "
            + "identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "ANY TIME WITHIN EVENT DURATION \n"
            + "Example: " + COMMAND_WORD + " "
            + "2024-01-01 12:00 ";

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
        Calendar calendar = personToEdit.getCalendar();

        Event toDelete;
        try {
            toDelete = calendar.findEventAt(eventTime).orElseThrow();
            calendar.deleteEventAt(eventTime);
        } catch (EventNotFoundException e) {
            throw new CommandException(MESSAGE_NO_EVENT);
        }

        Person editedPerson = createEditedPerson(personToEdit, calendar);
        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_DELETE_EVENT_FROM_PERSON_SUCCESS,
                editedPerson.getName(), Messages.format(toDelete)));
    }

    /**
     * Creates and returns a {@code Person} with the new Calendar {@code calendar}
     * edited with {@code event}.
     */
    private static Person createEditedPerson(Person personToEdit, Calendar calendar) {
        assert personToEdit != null;

        Name updatedName = personToEdit.getName();
        Phone updatedPhone = personToEdit.getPhone();
        Email updatedEmail = personToEdit.getEmail();
        Address updatedAddress = personToEdit.getAddress();
        Set<Tag> updatedTags = personToEdit.getTags();

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags, calendar);
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
