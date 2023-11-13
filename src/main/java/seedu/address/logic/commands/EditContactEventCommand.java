package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_END_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_START_DATE_TIME;
import static seedu.address.logic.parser.ParserUtil.parseDateTimeNonNull;
import static seedu.address.logic.parser.ParserUtil.parseDateTimeToString;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.calendar.UniMateCalendar;
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
            + "Parameters: PERSON_INDEX (must be a positive integer) "
            + "EVENT_INDEX (must be a positive integer)"
            + "[" + PREFIX_EVENT_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_EVENT_START_DATE_TIME + "START_DATE] "
            + "[" + PREFIX_EVENT_END_DATE_TIME + "END_DATE] \n"
            + "Example: " + COMMAND_WORD + " 1 " + "1 "
            + PREFIX_EVENT_DESCRIPTION + "Nap "
            + PREFIX_EVENT_START_DATE_TIME + "2023-10-10 10:00 "
            + PREFIX_EVENT_END_DATE_TIME + "2023-10-10 15:00 ";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";
    public static final String INVALID_EVENT_INDEX = "Invalid Event Index Provided";
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
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.formatCalendar(editedPerson)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}, editing the event with index
     * {@code eventIndex} with {@code editEventDescriptor}.
     */
    private static Person createPersonsEditedEvent(Index eventIndex,
                                                   Person personToEdit, EditEventDescriptor editEventDescriptor)
            throws CommandException {
        assert personToEdit != null;

        Name updatedName = personToEdit.getName();
        Phone updatedPhone = personToEdit.getPhone();
        Email updatedEmail = personToEdit.getEmail();
        Address updatedAddress = personToEdit.getAddress();
        Set<Tag> updatedTags = personToEdit.getTags();
        UniMateCalendar calendar = personToEdit.getCalendar();
        List<Event> eventList = updateEventList(calendar, eventIndex, editEventDescriptor);
        UniMateCalendar updatedCalendar = new UniMateCalendar();
        for (Event e: eventList) {
            updatedCalendar.addEvent(e);
        }

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags, updatedCalendar);
    }

    /**
     * Updates an event by its {@code eventIndex} in the given {@code calendar} with the new information provided in
     * the {@code editEventDescriptor}.
     *
     * @return The updated event list.
     */
    public static List<Event> updateEventList(UniMateCalendar calendar, Index eventIndex,
                                             EditEventDescriptor editEventDescriptor) throws CommandException {
        ObservableList<Event> eventList = calendar.getEventManager().asUnmodifiableObservableList();
        Event updateEvent;
        try {
            updateEvent = eventList.get(eventIndex.getZeroBased());
        } catch (Exception e) {
            throw new CommandException(INVALID_EVENT_INDEX);
        }
        EventPeriod updatePeriod = updateEvent.getEventPeriod();
        LocalDateTime newStartDateTime = editEventDescriptor.getStart().orElse(updatePeriod.getStart());
        LocalDateTime newEndDateTime = editEventDescriptor.getEnd().orElse(updatePeriod.getEnd());
        EventDescription newEventDescription = editEventDescriptor.getEventDescription()
                .orElse(updateEvent.getDescription());
        String stringStartDateTime = parseDateTimeToString(newStartDateTime);
        String stringEndDateTime = parseDateTimeToString(newEndDateTime);
        EventPeriod newEventPeriod = new EventPeriod(stringStartDateTime, stringEndDateTime);
        Event updatedEvent = new Event(newEventDescription, newEventPeriod);
        eventList.set(eventIndex.getZeroBased(), updatedEvent);
        return eventList;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditContactEventCommand)) {
            return false;
        }

        EditContactEventCommand otherEditCommand = (EditContactEventCommand) other;
        return personIndex.equals(otherEditCommand.personIndex)
                && eventIndex.equals(otherEditCommand.eventIndex)
                && editEventDescriptor.equals(otherEditCommand.editEventDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the calendar of the person.
     */
    public static class EditEventDescriptor {
        private UniMateCalendar calendar;
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
            return CollectionUtil.isAnyNonNull(start, end, eventDescription);
        }

        public void setEventDescription(EventDescription eventDescription) {
            this.eventDescription = eventDescription;
        }

        public Optional<EventDescription> getEventDescription() {
            return Optional.ofNullable(eventDescription);
        }

        public void setEventPeriod(EventPeriod eventPeriod) {
            this.eventPeriod = eventPeriod;
            setStart(eventPeriod.getStart());
            setEnd(eventPeriod.getEnd());
        }

        public Optional<EventPeriod> getEventPeriod() {
            return Optional.ofNullable(eventPeriod);
        }
        public void setStart(String startString) {
            LocalDateTime start = parseDateTimeNonNull(startString);
            this.start = start;
        }

        public void setStart(LocalDateTime start) {
            this.start = start;
        }

        public Optional<LocalDateTime> getStart() {
            return Optional.ofNullable(start);
        }

        public void setEnd(String endString) {
            LocalDateTime end = parseDateTimeNonNull(endString);
            this.end = end;
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
                    && Objects.equals(start, otherEditEventDescriptor.start)
                    && Objects.equals(end, otherEditEventDescriptor.end);
        }

        @Override
        public String toString() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return new ToStringBuilder("")
                    .add("eventDescription", eventDescription)
                    .add("start", parseDateTimeToString(eventPeriod.getStart()))
                    .add("end", parseDateTimeToString(eventPeriod.getEnd()))
                    .toString();
        }
    }
}
