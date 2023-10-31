package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_END_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_START_DATE_TIME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.calendar.Calendar;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventDescription;
import seedu.address.model.event.EventPeriod;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;


/**
 * Edits the details of an existing person's calendar in the address book.
 */
public class EditContactEventCommand extends Command {

    public static final String COMMAND_WORD = "editContactEvent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the identified person's calendar "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_EVENT_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_EVENT_START_DATE_TIME + "START_DATE] "
            + "[" + PREFIX_EVENT_END_DATE_TIME + "END_DATE] \n"
            + "Example: " + COMMAND_WORD + " 1 " + " 1 "
            + PREFIX_EVENT_DESCRIPTION + "Nap "
            + PREFIX_EVENT_START_DATE_TIME + "2023-10-10 10:00 "
            + PREFIX_EVENT_END_DATE_TIME + "2023-10-10 15:00 ";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";
    private final Index personIndex;
    private final Index eventIndex;
    private final EditEventDescriptor editEventDescriptor;

    /**
     * @param indexArrayList an ArrayList consisting of two elements, that is the Index of target person and
     *                       the index of the target event.
     * @param editEventDescriptor details to edit the calendar with
     */
    public EditContactEventCommand(ArrayList<Index> indexArrayList, EditEventDescriptor editEventDescriptor) {
        requireNonNull(indexArrayList.get(0));
        requireNonNull(indexArrayList.get(1));
        requireNonNull(editEventDescriptor);
        this.personIndex = indexArrayList.get(0);
        this.eventIndex = indexArrayList.get(1);
        this.editEventDescriptor = editEventDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (personIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(personIndex.getZeroBased());
        Person editedPerson = createPersonsEditedEvent(eventIndex, personToEdit, editEventDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createPersonsEditedEvent(Index eventIndex,
                                                   Person personToEdit, EditEventDescriptor editEventDescriptor) {
        assert personToEdit != null;

        Name updatedName = personToEdit.getName();
        Phone updatedPhone = personToEdit.getPhone();
        Email updatedEmail = personToEdit.getEmail();
        Address updatedAddress = personToEdit.getAddress();
        Set<Tag> updatedTags = personToEdit.getTags();
        Calendar calendar = personToEdit.getCalendar();
        List<Event> eventList = calendar.getEventManager().asEventList();
        Event updateEvent = eventList.get(eventIndex.getZeroBased());
        EventPeriod updatePeriod = updateEvent.getEventPeriod();
        LocalDateTime newStartDateTime = editEventDescriptor.getStart().orElse(updatePeriod.getStart());
        LocalDateTime newEndDateTime = editEventDescriptor.getEnd().orElse(updatePeriod.getEnd());
        EventDescription newEventDescription = editEventDescriptor.getEventDescription()
                .orElse(updateEvent.getDescription());
        EventPeriod newEventPeriod = new EventPeriod(newStartDateTime, newEndDateTime);
        Event updatedEvent = new Event(newEventDescription, newEventPeriod);
        eventList.set(eventIndex.getZeroBased(), updatedEvent);
        Calendar updatedCalendar = new Calendar();
        for (Event e: eventList) {
            updatedCalendar.addEvent(e);
        }

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags, updatedCalendar);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the calendar of the person.
     */
    public static class EditEventDescriptor {
        private Calendar calendar;
        private Event event;
        private EventDescription eventDescription;
        private EventPeriod eventPeriod;
        private LocalDateTime start;
        private LocalDateTime end;

        public EditEventDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditEventDescriptor(EditEventDescriptor toCopy) {
            setEventPeriod(toCopy.eventPeriod);
            setEventDescription(toCopy.eventDescription);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(eventDescription, eventPeriod);
        }

        public void setEventDescription(EventDescription eventDescription) {
            this.eventDescription = eventDescription;
        }

        public Optional<EventDescription> getEventDescription() {
            return Optional.ofNullable(eventDescription);
        }

        public void setEventPeriod(EventPeriod eventPeriod) {
            this.eventPeriod = eventPeriod;
        }

        public Optional<EventPeriod> getEventPeriod() {
            return Optional.ofNullable(eventPeriod);
        }
        public void setStart(LocalDateTime start) {
            this.start = start;
        }

        public Optional<LocalDateTime> getStart() {
            return Optional.ofNullable(start);
        }

        public void setEnd(LocalDateTime end) {
            this.end = end;
        }

        public Optional<LocalDateTime> getEnd() {
            return Optional.ofNullable(end);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditEventDescriptor)) {
                return false;
            }

            EditEventDescriptor otherEditEventDescriptor = (EditEventDescriptor) other;
            return Objects.equals(eventDescription, otherEditEventDescriptor.eventDescription)
                    && Objects.equals(eventPeriod, otherEditEventDescriptor.eventPeriod);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("eventDescription", eventDescription)
                    .add("start", eventPeriod.getStart())
                    .add("end", eventPeriod.getEnd())
                    .toString();
        }
    }
}
