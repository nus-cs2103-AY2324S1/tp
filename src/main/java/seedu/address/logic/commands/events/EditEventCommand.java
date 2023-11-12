package seedu.address.logic.commands.events;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_START;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.event.Duration;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventDescription;
import seedu.address.model.event.EventName;
import seedu.address.model.event.Location;
import seedu.address.model.person.Person;

/**
 * Edits the details of an existing event in the events book.
 */
public class EditEventCommand extends Command {
    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the event identified "
            + "by the index number used in the displayed event list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_EVENT_NAME + "EVENT_NAME] "
            + "[" + PREFIX_TIME_START + "START_TIME] "
            + "[" + PREFIX_TIME_END + "END_TIME] "
            + "[" + PREFIX_CLIENT + "CLIENTS] "
            + "[" + PREFIX_LOCATION + "LOCATION] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_EVENT_NAME + "Zoom meeting with Citadel "
            + PREFIX_TIME_END + "31-12-2024 18:00";

    public static final String MESSAGE_EDIT_EVENT_SUCCESS = "Edited Event: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in the events book";

    private final Index index;
    private final EditEventDescriptor editEventDescriptor;

    /**
     * @param index of the event in the filtered event list to edit
     * @param editEventDescriptor details to edit the event with
     */
    public EditEventCommand(Index index, EditEventDescriptor editEventDescriptor) {
        requireNonNull(index);
        requireNonNull(editEventDescriptor);

        this.index = index;
        this.editEventDescriptor = new EditEventDescriptor(editEventDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> lastShownList = model.getFilteredEventList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Event eventToEdit = lastShownList.get(index.getZeroBased());
        Event editedEvent = createEditedEvent(eventToEdit, editEventDescriptor, model);

        if (!eventToEdit.isSameEvent(editedEvent) && model.hasEvent(editedEvent)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }

        model.setEvent(eventToEdit, editedEvent);
        model.updateFilteredEventList(Model.PREDICATE_SHOW_ALL_EVENTS_AFTER_TODAY);
        return new CommandResult(String.format(MESSAGE_EDIT_EVENT_SUCCESS, Messages.format(editedEvent)));
    }

    /**
     * Creates and returns a {@code Event} with the details of {@code eventToEdit}
     * edited with {@code editEventDescriptor}.
     */
    private static Event createEditedEvent(Event eventToEdit, EditEventDescriptor editEventDescriptor, Model model)
            throws CommandException {
        assert eventToEdit != null;

        EventName updatedEventName = editEventDescriptor.getEventName().orElse(eventToEdit.getEventName());

        Duration updatedDuration;
        try {
            String timeStart = editEventDescriptor.getTimeStart().orElse(null);
            String timeEnd = editEventDescriptor.getTimeEnd().orElse(null);
            updatedDuration = Duration.updateDuration(eventToEdit.getDuration(), timeStart, timeEnd);
        } catch (ParseException e) {
            throw new CommandException(e.getMessage());
        }
        if (editEventDescriptor.getClients().isPresent()) {
            Set<Person> clients = editEventDescriptor.getClients().get();
            editEventDescriptor.setClients(getValidClients(clients, model));
        }
        Set<Person> updateClients = editEventDescriptor.getClients().orElse(eventToEdit.getClients());
        Location updatedLocation = editEventDescriptor.getLocation().orElse(eventToEdit.getLocation());
        EventDescription updatedEventDescription = editEventDescriptor.getEventDescription()
                .orElse(eventToEdit.getDescription());

        return new Event(updatedEventName, updatedDuration, updateClients, updatedLocation,
                updatedEventDescription);
    }

    private static Set<Person> getValidClients(Set<Person> clients, Model model) throws CommandException {
        if (!clients.isEmpty()) {
            boolean hasValidClients = clients.stream().allMatch(model::isValidClient);
            if (!hasValidClients) {
                throw new CommandException(Messages.MESSAGE_CLIENT_DOES_NOT_EXIST);
            }
        }
        return model.getAllMatchedClients(clients);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditEventCommand)) {
            return false;
        }

        EditEventCommand otherEditEventCommand = (EditEventCommand) other;
        return index.equals(otherEditEventCommand.index)
                && editEventDescriptor.equals(otherEditEventCommand.editEventDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editEventDescriptor", editEventDescriptor)
                .toString();
    }




    /**
     * Stores the details to edit the event with. Each non-empty field value will replace the
     * corresponding field value of the event.
     */
    public static class EditEventDescriptor {
        private EventName eventName;
        private String startInput;
        private String endInput;
        private Set<Person> clients;
        private Location location;
        private EventDescription eventDescription;

        public EditEventDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code clients} is used internally.
         */
        public EditEventDescriptor(EditEventDescriptor toCopy) {
            setEventName(toCopy.eventName);
            setStartInput(toCopy.startInput);
            setEndInput(toCopy.endInput);
            setClients(toCopy.clients);
            setLocation(toCopy.location);
            setEventDescription(toCopy.eventDescription);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(eventName, startInput, endInput, clients, location, eventDescription);
        }

        public void setEventName(EventName eventName) {
            this.eventName = eventName;
        }

        public Optional<EventName> getEventName() {
            return Optional.ofNullable(eventName);
        }

        public void setStartInput(String startInput) {
            this.startInput = startInput;
        }

        public Optional<String> getTimeStart() {
            return Optional.ofNullable(startInput);
        }

        public void setEndInput(String endInput) {
            this.endInput = endInput;
        }

        public Optional<String> getTimeEnd() {
            return Optional.ofNullable(endInput);
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public Optional<Location> getLocation() {
            return Optional.ofNullable(location);
        }

        public void setEventDescription(EventDescription eventDescription) {
            this.eventDescription = eventDescription;
        }

        public Optional<EventDescription> getEventDescription() {
            return Optional.ofNullable(eventDescription);
        }

        /**
         * Sets {@code clients} to this object's {@code clients}.
         * A defensive copy of {@code clients} is used internally.
         */
        public void setClients(Set<Person> clients) {
            this.clients = (clients != null) ? new HashSet<>(clients) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code clients} is null.
         */
        public Optional<Set<Person>> getClients() {
            return (clients != null) ? Optional.of(Collections.unmodifiableSet(clients)) : Optional.empty();
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
            return Objects.equals(eventName, otherEditEventDescriptor.eventName)
                    && Objects.equals(startInput, otherEditEventDescriptor.startInput)
                    && Objects.equals(endInput, otherEditEventDescriptor.endInput)
                    && Objects.equals(clients, otherEditEventDescriptor.clients)
                    && Objects.equals(location, otherEditEventDescriptor.location)
                    && Objects.equals(eventDescription, otherEditEventDescriptor.eventDescription);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("eventName", eventName)
                    .add("startInput", startInput)
                    .add("endInput", endInput)
                    .add("clients", clients)
                    .add("location", location)
                    .add("eventDescription", eventDescription)
                    .toString();
        }
    }
}
