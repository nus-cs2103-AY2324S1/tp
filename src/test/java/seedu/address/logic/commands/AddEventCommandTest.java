package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.event.Event;
import seedu.address.model.event.Meeting;
import seedu.address.model.group.Group;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.testutil.MeetingBuilder;


public class AddEventCommandTest {
    @Test
    public void constructor_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddEventCommand(null));
    }

    @Test
    public void execute_eventAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingEvent modelStub = new ModelStubAcceptingEvent();

        Meeting validEvent = new MeetingBuilder().withEventName("TP MEETING TEST")
                .withEventDate("2025-11-11")
                .withEventStartTime("0000")
                .withEventEndTime("2359")
                .withPerson("Alice Pauline")
                .build();


        CommandResult commandResult = new AddEventCommand(validEvent).execute(modelStub);

        assertEquals(String.format(AddEventCommand.MESSAGE_SUCCESS, Messages.formatEvent(validEvent)),
                commandResult.getFeedbackToUser());
        assertEquals(List.of(validEvent), modelStub.eventsAdded);
    }

    @Test
    public void execute_eventSuppliedWithInvalidPersonNames() throws ParseException {
        ModelStubAcceptingEvent modelStub = new ModelStubAcceptingEvent();

        Meeting invalidEvent = new MeetingBuilder().withEventName("TP MEETING TEST")
                .withEventDate("2025-11-11")
                .withEventStartTime("0000")
                .withEventEndTime("2359")
                .withPerson("Alice Pauline", "Benson Meier")
                .build();

        assertThrows(CommandException.class, String.format(Messages.MESSAGE_INVALID_PERSON,
                listInvalidNames(modelStub.findInvalidNames(invalidEvent.getNames()))), () ->
                new AddEventCommand(invalidEvent).execute(modelStub));
    }

    @Test
    public void execute_eventSuppliedWithInvalidGroupNames() throws ParseException {
        ModelStubAcceptingEvent modelStub = new ModelStubAcceptingEvent();

        Meeting invalidEvent = new MeetingBuilder().withEventName("TP MEETING TEST")
                .withEventDate("2025-11-11")
                .withEventStartTime("0000")
                .withEventEndTime("2359")
                .withGroups("Team2", "Team3")
                .build();

        assertThrows(CommandException.class, String.format(Messages.MESSAGE_INVALID_GROUP,
                listInvalidGroups(modelStub.findInvalidGroups(invalidEvent.getGroups()))), () ->
                new AddEventCommand(invalidEvent).execute(modelStub));
    }

    @Test
    public void execute_eventSuppliedWithoutOptionalFields() throws Exception {
        ModelStubAcceptingEvent modelStub = new ModelStubAcceptingEvent();

        Meeting validEvent = new MeetingBuilder().withEventName("TP MEETING TEST")
                .withEventDate("2025-11-11")
                .build();

        CommandResult commandResult = new AddEventCommand(validEvent).execute(modelStub);

        assertEquals(String.format(AddEventCommand.MESSAGE_SUCCESS, Messages.formatEvent(validEvent)),
                commandResult.getFeedbackToUser());
        assertEquals(List.of(validEvent), modelStub.eventsAdded);
    }



    private String listInvalidNames(Set<Name> invalidNames) {
        StringBuilder builder = new StringBuilder();
        for (Name name : invalidNames) {
            builder.append(name.toString());
            builder.append(", ");
        }
        builder.delete(builder.length() - 2, builder.length()); //removes the last comma
        return builder.toString();
    }

    private String listInvalidGroups(Set<Group> invalidGroups) {
        StringBuilder builder = new StringBuilder();
        for (Group group : invalidGroups) {
            builder.append(group.toString());
            builder.append(", ");
        }

        builder.delete(builder.length() - 2, builder.length()); //removes the last comma
        return builder.toString();
    }

    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredEventList(Predicate<Event> predicates) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEvent(Event toAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Set<Name> findInvalidNames(Set<Name> names) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateAssignedPersons(Person personToEdit, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateAssignedPersons(Person personToEdit) {
            throw new AssertionError("This method should not be called.");
        }

        public void setEvent(Event target, Event editedEvent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Event> getEventList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEvent(Event target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Set<Group> findInvalidGroups(Set<Group> groups) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Set<Group> getEmptyGroups(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeEmptyGroups(Set<Group> emptyGroups) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateGroups() {
            return;
        }
    }

    /**
     * A Model Stub that contains a single event.
     */
    private class ModelStubWithEvent extends ModelStub {
        private final Event event;

        ModelStubWithEvent(Event event) {
            requireNonNull(event);
            this.event = event;
        }
    }

    private class ModelStubAcceptingEvent extends ModelStub {
        final ArrayList<Event> eventsAdded = new ArrayList<>();

        final Name personName = new Name("Alice Pauline");

        final Group groupName = new Group("Team2");
        @Override
        public void addEvent(Event event) {
            requireNonNull(event);
            eventsAdded.add(event);
        }

        @Override
        public Set<Name> findInvalidNames(Set<Name> names) {
            Set<Name> invalidNames = new HashSet<>();

            for (Name name: names) {
                if (!name.equals(personName)) {
                    invalidNames.add(name);
                }
            }

            return invalidNames;
        }

        @Override
        public Set<Group> findInvalidGroups(Set<Group> groups) {
            Set<Group> invalidGroups = new HashSet<>();

            for (Group group: groups) {
                if (!group.equals(new Group("Team2"))) {
                    invalidGroups.add(group);
                }
            }

            return invalidGroups;
        }

    }
}
