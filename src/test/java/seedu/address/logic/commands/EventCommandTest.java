package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.EVENT_1;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.EventBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyEventBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;
import seedu.address.model.statistics.SummaryStatistic;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class EventCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_eventAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingEventAdded modelStub = new ModelStubAcceptingEventAdded();
        Person validPerson = new PersonBuilder().build();
        new AddCommand(validPerson).execute(modelStub);

        Event validEventInput = new Event(Index.fromZeroBased(0), "Testing",
                LocalDateTime.of(2023, 11, 8, 12, 0),
                LocalDateTime.of(2023, 11, 8, 13, 0));

        Event validEventOutput = new Event(validPerson, "Testing",
                LocalDateTime.of(2023, 11, 8, 12, 0),
                LocalDateTime.of(2023, 11, 8, 13, 0));

        CommandResult commandResult = new EventCommand(validEventInput).execute(modelStub);

        assertEquals(String.format(EventCommand.MESSAGE_SUCCESS, validEventOutput),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validEventOutput), modelStub.eventsAdded);
    }

    @Test
    public void execute_invalidIndex_failure() throws Exception {
        ModelStubAcceptingEventAdded modelStub = new ModelStubAcceptingEventAdded();
        Person validPerson = new PersonBuilder().build();
        new AddCommand(validPerson).execute(modelStub);

        Event eventInput = new Event(Index.fromZeroBased(1), "Testing",
                LocalDateTime.of(2023, 11, 8, 12, 0),
                LocalDateTime.of(2023, 11, 8, 13, 0));

        EventCommand eventCommand = new EventCommand(eventInput);
        assertThrows(CommandException.class, () -> eventCommand.execute(modelStub));
    }

    @Test
    public void equals() {

        Event event1 = new Event(Index.fromZeroBased(0), "Testing",
                LocalDateTime.of(2023, 11, 8, 12, 0),
                LocalDateTime.of(2023, 11, 8, 13, 0));

        Event event2 = new Event(Index.fromZeroBased(0), "Interview",
                LocalDateTime.of(2023, 11, 8, 12, 0),
                LocalDateTime.of(2023, 11, 8, 13, 0));

        EventCommand event1Command = new EventCommand(event1);
        EventCommand event2Command = new EventCommand(event2);

        // same object -> returns true
        assertTrue(event1Command.equals(event1Command));

        // same values -> returns true
        EventCommand event1CommandCopy = new EventCommand(event1);
        assertTrue(event1Command.equals(event1CommandCopy));

        // different types -> returns false
        assertFalse(event1Command.equals(1));

        // null -> returns false
        assertFalse(event1Command.equals(null));

        // different event -> returns false
        assertFalse(event1Command.equals(event2Command));
    }

    @Test
    public void toStringMethod() {
        EventCommand eventCommand = new EventCommand(EVENT_1);
        String expected = EventCommand.class.getCanonicalName() + "{event=" + EVENT_1 + "}";
        assertEquals(expected, eventCommand.toString());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
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
        public Path getEventBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEventBookFilePath(Path eventBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEventBook(ReadOnlyEventBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyEventBook getEventBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTag(Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEvent(Event target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEvent(Event target, Event editedEvent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(List<Predicate<Person>> predicatesList) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortPersonList(Comparator<Person> comparator) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void sortEventList(Comparator<Event> comparator) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public ObservableList<Event> getFilteredEventList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredEventList(Predicate<Event> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredEventList(List<Predicate<Event>> predicatesList) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Tag> getFilteredTagList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTagList(Predicate<Tag> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Index getLastViewedPersonIndex() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setLastViewedPersonIndex(Index index) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTag(Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void loadSummaryStatistics() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public SummaryStatistic getSummaryStatistic() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingEventAdded extends ModelStub {
        final ArrayList<Event> eventsAdded = new ArrayList<>();
        final ArrayList<Person> personsAdded = new ArrayList<>();

        private Index index;

        @Override
        public boolean hasEvent(Event event) {
            requireNonNull(event);
            return eventsAdded.stream().anyMatch(event::isSameEvent);
        }

        @Override
        public void addEvent(Event event) {
            requireNonNull(event);
            eventsAdded.add(event);
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ObservableList<Event> getFilteredEventList() {
            ObservableList<Event> filteredList = FXCollections.observableArrayList(eventsAdded);
            return filteredList;
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            ObservableList<Person> filteredList = FXCollections.observableArrayList(personsAdded);
            return filteredList;
        }

        @Override
        public void setLastViewedPersonIndex(Index index) {
            this.index = index;
        }

        @Override
        public ReadOnlyEventBook getEventBook() {
            return new EventBook();
        }
    }

}
