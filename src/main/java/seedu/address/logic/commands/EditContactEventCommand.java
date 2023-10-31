package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.calendar.Calendar;
import seedu.address.model.event.AllDaysEventListManager;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventDescription;
import seedu.address.model.event.EventPeriod;
import seedu.address.model.event.SingleDayEventList;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_END_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_START_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

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
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_EVENT_DESCRIPTION + "Nap "
            + PREFIX_EVENT_START_DATE_TIME + "2023-10-10 10:00 "
            + PREFIX_EVENT_END_DATE_TIME + "2023-10-10 15:00 ";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";
    private final Index index;
    private final EditEventDescriptor editEventDescriptor;


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
            return CollectionUtil.isAnyNonNull(eventDescription, eventPeriod.);
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
            boolean isSameEventDescription = Object
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
