package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_END_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_START_DATE_TIME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.calendar.Calendar;
import seedu.address.model.event.Event;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

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
            + "Example: " + COMMAND_WORD + " "
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
        requireNonNull(index);
        requireNonNull(event);

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

        //Obtain the calendar of the person and tries to add an event to the person's calendar
        Person personToEdit = lastShownList.get(index.getZeroBased());
        Calendar calendar = personToEdit.getCalendar();
        if (!calendar.canAddEvent(event)) {
            throw new CommandException(MESSAGE_EVENT_CONFLICT);
        }
        calendar.addEvent(event);
        Person editedPerson = createEditedPerson(personToEdit, calendar);
        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_ADD_EVENT_TO_PERSON_SUCCESS,
                editedPerson.getName(), Messages.format(event)));
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
