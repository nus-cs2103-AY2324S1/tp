package seedu.address.logic.commands.eventcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_DATE_PARAMS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATETIME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EVENTS;

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
import seedu.address.model.event.Budget;
import seedu.address.model.event.DateTime;
import seedu.address.model.event.Description;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.event.Location;
import seedu.address.model.event.Material;
import seedu.address.model.event.Role;
import seedu.address.model.Model;

/**
 * Edits the details of an existing event in the address book.
 */
public class EventEditCommand extends Command {

    public static final String COMMAND_WORD = "eedit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the edit identified "
            + "by the index number used in the displayed event list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "EVENT NAME] "
            + "[" + PREFIX_ROLE + "ROLES] "
            + "[" + PREFIX_START_DATETIME + "START DATE] "
            + "[" + PREFIX_LOCATION + "LOCATION] ...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ROLE + "Director "
            + PREFIX_LOCATION + "Kent Ridge";

    public static final String MESSAGE_EDIT_EVENT_SUCCESS = "Edited Event: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in the address book.";

    private final Index index;
    private final EditEventDescriptor editEventDescriptor;

    /**
     * @param index of the event in the filtered event list to edit
     * @param editEventDescriptor details to edit the event with
     */
    public EventEditCommand(Index index, EditEventDescriptor editEventDescriptor) {
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
        Event editedEvent = createEditedEvent(eventToEdit, editEventDescriptor);

        if (!eventToEdit.isSameEvent(editedEvent) && model.hasEvent(editedEvent)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }

        model.setEvent(eventToEdit, editedEvent);
        model.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_EVENT_SUCCESS, Messages.format(editedEvent)));
    }

    /**
     * Creates and returns a {@code Event} with the details of {@code eventToEdit}
     * edited with {@code editEventDescriptor}.
     */
    private static Event createEditedEvent(Event eventToEdit, EditEventDescriptor editEventDescriptor) throws CommandException {
        assert eventToEdit != null;

        EventName updatedEventName = editEventDescriptor.getEventName().orElse(eventToEdit.getEventName());
        Set<Role> updatedRoles = editEventDescriptor.getRoles().orElse(eventToEdit.getRoles());
        Location updatedLocation = editEventDescriptor.getLocation().orElse(eventToEdit.getLocation());
        Description updatedDescription = editEventDescriptor.getDescription().orElse(eventToEdit.getDescription());
        Set<Material> updatedMaterial = editEventDescriptor.getMaterials().orElse(eventToEdit.getMaterials());
        Budget updatedBudget = editEventDescriptor.getBudget().orElse(eventToEdit.getBudget());
        DateTime updatedStartTime = editEventDescriptor.getStartDate().orElse(eventToEdit.getStartDate());
        DateTime updatedEndTime = editEventDescriptor.getEndDate().orElse(eventToEdit.getEndDate());

        if (updatedEndTime.dateAndTime.isBefore(updatedStartTime.dateAndTime)
                || updatedStartTime.equals(updatedEndTime)) {
            throw new CommandException(MESSAGE_INVALID_DATE_PARAMS);
        }
        return new Event(updatedEventName, updatedRoles, updatedStartTime, updatedEndTime, updatedLocation,
                updatedDescription, updatedMaterial, updatedBudget);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventEditCommand)) {
            return false;
        }

        EventEditCommand otherEventEditCommand = (EventEditCommand) other;
        return index.equals(otherEventEditCommand.index)
                && editEventDescriptor.equals(otherEventEditCommand.editEventDescriptor);
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
        private Set<Role> roles;
        private DateTime startDate;
        private DateTime endDate;

        private Location location;
        private Description description;
        private Set<Material> materials;
        private Budget budget;

        public EditEventDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code skills} is used internally.
         */
        public EditEventDescriptor(EditEventDescriptor toCopy) {

            setEventName(toCopy.eventName);
            setRoles(toCopy.roles);
            setStartDate(toCopy.startDate);
            setEndDate(toCopy.endDate);
            setLocation(toCopy.location);
            setDescription(toCopy.description);
            setMaterials(toCopy.materials);
            setBudget(toCopy.budget);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(eventName, roles, startDate, endDate, location, description, materials, budget);
        }
        public void setEventName(EventName eventName) {
            this.eventName = eventName;
        }

        public Optional<EventName> getEventName() {
            return Optional.ofNullable(eventName);
        }

        /**
         * Sets {@code roles} to this object's {@code roles}.
         * A defensive copy of {@code roles} is used internally.
         */
        public void setRoles(Set<Role> roles) {
            this.roles = (roles != null) ? new HashSet<>(roles) : null;
        }

        /**
         * Returns an unmodifiable role set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code roles} is null.
         */
        public Optional<Set<Role>> getRoles() {
            return (roles != null) ? Optional.of(Collections.unmodifiableSet(roles)) : Optional.empty();
        }

        public void setStartDate(DateTime startDate) {
            this.startDate = startDate;
        }

        public Optional<DateTime> getStartDate() {
            return Optional.ofNullable(startDate);
        }

        public void setEndDate(DateTime endDate) {
            this.endDate = endDate;
        }

        public Optional<DateTime> getEndDate() {
            return Optional.ofNullable(endDate);
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public Optional<Location> getLocation() {
            return Optional.ofNullable(location);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        /**
         * Sets {@code materials} to this object's {@code materials}.
         * A defensive copy of {@code materials} is used internally.
         */
        public void setMaterials(Set<Material> materials) {
            this.materials = (materials != null) ? new HashSet<>(materials) : null;
        }

        /**
         * Returns an unmodifiable material set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code materials} is null.
         */
        public Optional<Set<Material>> getMaterials() {
            return (materials != null) ? Optional.of(Collections.unmodifiableSet(materials)) : Optional.empty();
        }

        public void setBudget(Budget budget) {
            this.budget = budget;
        }

        public Optional<Budget> getBudget() {
            return Optional.ofNullable(budget);
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
                    && Objects.equals(roles, otherEditEventDescriptor.roles)
                    && Objects.equals(startDate, otherEditEventDescriptor.startDate)
                    && Objects.equals(endDate, otherEditEventDescriptor.endDate)
                    && Objects.equals(location, otherEditEventDescriptor.location)
                    && Objects.equals(description, otherEditEventDescriptor.description)
                    && Objects.equals(materials, otherEditEventDescriptor.materials)
                    && Objects.equals(budget, otherEditEventDescriptor.budget);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("eventName", eventName)
                    .add("roles", roles)
                    .add("start date", startDate)
                    .add("end date", endDate)
                    .add("location", location)
                    .add("description", description)
                    .add("materials", materials)
                    .add("budget", budget)
                    .toString();
        }
    }
}
